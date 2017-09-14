/*******************************************************************************
 * Copyright (C) 2015-2017 Andreas Redmer <andreasredmer@mailchuck.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.gitlab.ardash.appleflinger.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.gitlab.ardash.appleflinger.AdvancedStage;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.actors.BlockActor;
import com.gitlab.ardash.appleflinger.actors.PhysicsActor;
import com.gitlab.ardash.appleflinger.actors.TargetActor;
import com.gitlab.ardash.appleflinger.actors.ThinkBubble;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.PlayerStatus.PlayerSide;
import com.gitlab.ardash.appleflinger.helpers.PausableThreadPoolExecutor;

public class PlayerSimulator {

	public static final PlayerSimulator INSTANCE = new PlayerSimulator();
	
	private ThinkBubble thinkBubble = new ThinkBubble(0, 0);

//	private float currentTargetOffset = 0f;

	private boolean isDraging = false;
	private int step = 0;
	private int pulledSteps = 0;
	private float timeActive = 0;
	private Vector2 goodPullVector = null;
	private Patch[][] patches;
	private final ArrayList<Curve> allCurves = new ArrayList<Curve>(1136);

	private Shot currentBestShot = null;
	//private TreeSet<Shot> shots = (TreeSet<Shot>) Collections.synchronizedSet(new TreeSet<Shot>());
	private final TreeSet<Shot> shots = new TreeSet<Shot>();

	private boolean initialised = false;

	private FutureTask<Boolean> initialiserTask;

	private PausableThreadPoolExecutor seekerPool = new PausableThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(), new LPThreadFactory());
	
    static class LPThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        LPThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                                  Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + //$NON-NLS-1$
                          poolNumber.getAndIncrement() +
                         "-thread-"; //$NON-NLS-1$
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                                  namePrefix + threadNumber.getAndIncrement(),
                                  0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.MIN_PRIORITY)
                t.setPriority(Thread.MIN_PRIORITY);
            return t;
        }
    }
	
	private PlayerSimulator() {
		// init all patches
		patches = new Patch[12][6];
		for (int x = 0; x < 12; x++) {
			for (int y = 0; y < 6; y++) {
				final Patch patch = new Patch();
				patches[x][y] = patch;
			}
		}
		resetPatches();

		initCurves();
		System.out.println("curves created: " + allCurves.size()); //$NON-NLS-1$

	}
	
	private class NewRoundInitialiser implements Callable<Boolean> {
		 
	    public NewRoundInitialiser(){
	    	
	    }
	    
	    @Override
	    public Boolean call() throws Exception {

			if (!GameManager.getInstance().isPlayer2CPU())
				return true;

			// only if new level was loaded
			GameManager gm = GameManager.getInstance();
			resetCurves(gm.PLAYER2.slingshot.getSlingShotCenter());

			// only if new level was loaded
			assignCurvesToPatches();

			// set best shot to straight forward shot
			currentBestShot = new Shot(-1, 0, 0);
			currentBestShot.targetPos.set(0, 0);
			currentBestShot.pullVector.set(1, 0);
			
			shots.clear();
			initialised = true;
			//tickThinking(); // on some phose init is so slow, that first tick does not happen
	    	
	    	return true;
	    }
	 
	}
	
	public void startBackgroundInitForNextRound()
	{
		initialised = false;
		
		//eset everything, becasue a round could have been canceled while the simulator was avtive
		isDraging = false;
		step = 0;
		pulledSteps = 0;
		timeActive = 0;
		
		//re create the seekerpool to discard old workers (should be shutdown anyway
		seekerPool = new PausableThreadPoolExecutor(1, 1,
	            0L, TimeUnit.MILLISECONDS,
	            new LinkedBlockingQueue<Runnable>(), new LPThreadFactory());
		
		NewRoundInitialiser callable1 = new NewRoundInitialiser();
		initialiserTask = new FutureTask<Boolean>(callable1);
		ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(initialiserTask);
	}

	private void assignCurvesToPatches() {
		final float stepSeconds = 0.01f;
		final float maxSeconds = 3f;

		// offsets for points on the circle
		final ArrayList<Vector2> offsets = new ArrayList<Vector2>(5);
		offsets.add(new Vector2(0, 0));
		offsets.add(new Vector2(-0.2f, -0.2f)); // 0.2 is projectile radius
		offsets.add(new Vector2(0.2f, 0.2f));
		offsets.add(new Vector2(-0.2f, 0.2f));
		offsets.add(new Vector2(0.2f, -0.2f));

		for (Curve c : allCurves) {
			float t = 0f;
			while (true) {
				t += stepSeconds;
				final Vector2 estimatedImpactPosNotOffsetted = estimatedPosAfterTseconds(c.startPos, t, c.angle, c.v0 * 10);

				// try different offsets, to check if there would be a wall hit
				for (int i = 0; i < offsets.size(); i++) {
					final Vector2 estimatedImpactPos = estimatedImpactPosNotOffsetted.cpy().add(offsets.get(i));

					// add this curve to all Patches that contain it
					for (int x = 0; x < 12; x++) {
						for (int y = 0; y < 6; y++) {
							if (patches[x][y].contains(estimatedImpactPos))
								patches[x][y].affectedCurves.add(c);
						}
					}

				}
				if (t >= maxSeconds)
					break;
				if (estimatedImpactPosNotOffsetted.x < 0 || estimatedImpactPosNotOffsetted.y < 0)
					break;
			}

		}

		// print for checking
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 12; x++) {
				System.out.print(patches[x][y].affectedCurves.size() + "|"); //$NON-NLS-1$
			}
			System.out.println();
		}

	}

	private void resetPatches() {
		float patchWidth = GameWorld.UNIT_WIDTH / 12;
		float patchHeight = GameWorld.UNIT_HEIGHT / 6;
		Float area = null;
		for (int x = 0; x < 12; x++) {
			for (int y = 0; y < 6; y++) {
				patches[x][y].height = patchHeight;
				patches[x][y].width = patchWidth;
				patches[x][y].x = x;
				patches[x][y].y = y;
				patches[x][y].affectedCurves.clear();

				if (area == null)
					area = patches[x][y].area();

				if (area != patches[x][y].area())
					throw new RuntimeException("areas have different size"); //$NON-NLS-1$
			}
		}
	}

	
	private void resetCurves(Vector2 slingshotCenter) {
		for (Curve c : allCurves) {
			c.startPos.set(slingshotCenter);
			c.startPos.add(c.pullVector);
		}
	}

	// once time in constuctor

	private void initCurves() {
		final Vector2 initialForceVector = new Vector2(-0.8f, 0.8f); // 45deg,
																		// the
																		// first
																		// thing
																		// to
																		// try

		// start with 45 degree and then decrease
		// try max power for different degrees
		float dx = 0.0f;
		float dy = 0.0f;

		Vector2 forceVector = null;
		Vector2 pullVector = null;

		// moving dy (forward shots)
		for (float forceVectorClamp = 0.8f; forceVectorClamp >= 0.1f; forceVectorClamp -= 0.05f) {// float
																									// forceVectorClamp
																									// =0.8f;
																									// after
																									// trying
																									// all
																									// dy
																									// ,,
																									// decrease
																									// the
																									// force
																									// and
																									// try
																									// all
																									// dy
																									// again
			for (dy = 0.4f; dy <= 1.2f; dy += 0.05f) // 0.01
			{
				forceVector = initialForceVector.cpy().add(dx, -dy);
				forceVector.clamp(0, forceVectorClamp);
				pullVector = forceVector.cpy().scl(-1);

				Curve c = new Curve(dx, dy, forceVectorClamp);
				c.forceVector.set(forceVector);
				c.pullVector.set(pullVector);
				c.angle = 180 - forceVector.angle();
				c.v0 = forceVector.len();
				allCurves.add(c);
			}
		}

		dx = 0.0f;
		dy = 0.0f;
		// now the same but moving dx (upward shots)
		for (float forceVectorClamp = 0.8f; forceVectorClamp >= 0.6f; forceVectorClamp -= 0.05f) {
			// after trying all dy ,, decrease the force and try all dy again
			for (dx = 0.0f; dx <= 0.7f; dx += 0.05f) // 0.01
			{
				forceVector = initialForceVector.cpy().add(dx, -dy);
				forceVector.clamp(0, forceVectorClamp);
				pullVector = forceVector.cpy().scl(-1);

				Curve c = new Curve(dx, dy, forceVectorClamp);
				c.forceVector.set(forceVector);
				c.pullVector.set(pullVector);
				c.angle = 180 - forceVector.angle();
				c.v0 = forceVector.len();
				allCurves.add(c);
			}
		}

		// all curves are not filled and wont be changes again
	}

	public void playOneRound() {
		System.out.println("Simulating one round ..."); //$NON-NLS-1$
		isDraging = true;
		step = 0;
		timeActive = 0;
		pulledSteps = 0;
		goodPullVector = null;
		// shots2.clear();
		
		// show thought bubble
		GameManager gm = GameManager.getInstance();
		final PhysicsActor currentProjectile = gm.currentPlayer.slingshot.getCurrentProjectile();
		final Stage stage = currentProjectile.getStage();
		final Vector2 pp = currentProjectile.getPhysicalCenterPosition();
		thinkBubble.setPosition(pp.x,pp.y);
		stage.addActor(thinkBubble);
		
		
		// now we need to be sure that we are initialised
		try {
			initialiserTask.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public void act(float delta) {
		GameManager gm = GameManager.getInstance();
		
		// don't do anything if not dragging (dragging is only triggered by
		// playOneRound())
		if (!isDraging)
			return;

		timeActive += delta;
		
		// before anything happens we give it 3 seconds thinking time
		if (timeActive < 10)
		{
			tickThinking();
			//unpauseThinking();
			return;
		}
		
		// pause thinking will be called below in getGoodPullVector()

		final float pullingSteps = 25;

		switch (step) {
		case 0:
			touchDown();
			step++;
			break;
		case 1:
			thinkBubble.remove();
			// slowly drag to the dragTarget
			final Vector2 slingShotCenter = gm.currentPlayer.slingshot.getSlingShotCenter();

			if (goodPullVector == null)
//				goodPullVector = findGoodPullVectorForCurrentSlingshot(); // old stuff
				goodPullVector = getGoodPullVector();
			
			Vector2 dragTarget = slingShotCenter.cpy()
					.add(goodPullVector.cpy().scl(1 * (Math.min(pulledSteps, pullingSteps) / (float) pullingSteps)));
			// System.out.println("shooting at: "+dragTarget);
			// in 10 steps
			touchDragTo(dragTarget);
			if (pulledSteps >= pullingSteps * 2 + 120) // wait twice as long
														// after pulling // or
														// longer if still lags
														// on phone
				step++;
			break;
		case 2:
			touchUp();
			isDraging = false;
			goodPullVector = null;
			break;

		default:
			break;
		}
	}
	
	// starts thinking process and initiates more thinkers (runners) to keep the brain full
	public void tickThinking()
	{
		GameManager gm = GameManager.getInstance();
		if (!(gm.isPlayer2CPU() && initialised))
			return;

		// if shots empty start right away
		// check amount of queued executors = N (incl. currently running)
		// check amount of Targets left on stage = T
		// if shots.first.target == shots.last.target (only one target left in queue) start new executor for target.last
		
		// first version is easy: if empty shots start executor for remaining targets
//		if (!shots.isEmpty())
//			return;
		unpauseThinking();
		if (seekerPool.getActiveCount()!=0  || seekerPool.getQueue().size()!=0)
			return;
		
		System.out.println("shots: "+shots.size()); //$NON-NLS-1$
		System.out.println("No more shots left, starting seekers in the background"); //$NON-NLS-1$
		final AdvancedStage stage = gm.currentGameScreen.getRenderer().world.stage;
		
		// stating a new round of executors
		long executorRoundId= TimeUtils.millis();
		
//		ShotCleaner cleanerCall = new ShotCleaner();
//		FutureTask<Boolean> cleanerTask = new FutureTask<Boolean>(cleanerCall);
//        seekerPool.execute(cleanerTask);
		
        // the actual object returned by getAllActors() changes all the time, so work on a copy
        final Set<Actor> allactors = new HashSet<Actor>(stage.getAllActors());
        final ArrayList<Actor> aaList= new ArrayList<Actor>(allactors);
        Collections.shuffle(aaList); // shuffle the set of actors
		for (Actor a : aaList) {
			if (a instanceof TargetActor) {
				TargetActor ta = (TargetActor) a;
				// only enemies, not owns
				if (ta.getPlayerSide() == PlayerSide.LEFT) {
					ShotSeeker seekerCall = new ShotSeeker(ta, executorRoundId);
					FutureTask<Boolean> seekerTask = new FutureTask<Boolean>(seekerCall);
					
					// for better performance on slow phones, never add more than 4 executors
					if (seekerPool.getQueue().size()<5)
						seekerPool.execute(seekerTask);
				}
			}
		}

	}
	
	// stops all executors, call this at the end of the round because the thinkers touch the stage
	public void stopThinking()
	{
		seekerPool.shutdownNow();
		try {
			seekerPool.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void pauseThinking()
	{
		seekerPool.pause();
	}
	
	private void unpauseThinking()
	{
		seekerPool.resume();
	}
	
	// return the current best shot from list
	// return null if there is none , is avoided
	public Vector2 getGoodPullVector ()
	{
		// thinking causes lag, and lag fucks up throws, so stop thinking before throw (TODO stabilise FPS?)
		pauseThinking();
		synchronized (shots) {
			Shot goodShot;
			while (true)
			{
//				if (shots.size()<3)
//				{
					// poll first one for very small sizes
					goodShot = shots.pollFirst();
					if (goodShot==null)
						return currentBestShot.pullVector; // no shot found, do default shot
//				}
//				else
//				{
//					// poll random one if larger number is available
//					final int minUpperIndex = 2;
//					final int maxUpperIndex = Math.max(minUpperIndex, shots.size()/2);
//					final int index = MathUtils.random(0, maxUpperIndex);
//					goodShot = (Shot) shots.toArray()[index];
//					
//					if (goodShot == null)
//						continue; //should not happen
//				}
				// cleaning in a separate runner thread refused to work, so do it here quickly
				if (synIsThereATargetAt3(goodShot.targetPos))
					break;
			}
			System.out.println("Good shot " + goodShot); //$NON-NLS-1$
			
			// TODO add variation (calibrate here)
			final Vector2 pullVector = goodShot.pullVector.cpy();

			final float clamp = MathUtils.random(-0.0625f, 0.0625f); // +/- 0-05 is
																		// a
																		// deviation
																		// of 6.25%
			final float xoffset = MathUtils.random(-0.035f, 0.035f);
			final float yoffset = MathUtils.random(-0.035f, 0.035f);
			pullVector.x += xoffset;
			pullVector.y += yoffset;
			pullVector.scl(1f + clamp);
			
			// save for next time
			currentBestShot = goodShot;
			return pullVector;
		}
	}
	
	private boolean synIsThereATargetAt3 (Vector2 targetPos) {
		GameManager gm = GameManager.getInstance();
		final AdvancedStage stage = gm.currentGameScreen.getRenderer().world.stage;
		
		for (float offset = 0.0f; offset <= 3.0f; offset += 0.25f) {

			final Actor hitActor = stage.getRoot().hit(targetPos.x - offset, targetPos.y, false);
			
			if (hitActor != null)
			{
				if (hitActor instanceof TargetActor) {
					TargetActor ta = (TargetActor) hitActor;
					if (ta.getPlayerSide()==PlayerSide.LEFT)
						return true;
				}
			}
		}		
		return false;
	}

	
	// the new ways is a callable , fills shots
	// seeks shots for the target, fills the shot-set
 	private class ShotSeeker implements Callable<Boolean> {
		
		private final TargetActor target;
		private final long executorRoundId;
		 
	    public ShotSeeker(TargetActor target, long executorRoundId){
	    	this.target = target;
	    	this.executorRoundId = executorRoundId;
	    }
	    
	    @Override
	    public Boolean call() throws Exception {
	    	// call for this target was ordered earlier, but the cleaner can have it removed already
	    	if (target.isToBeRemoved() || target.isToBeDestroyed() || target.getStage()==null)
	    		return true;
	    	
	    	// sometimes it is so slow that not all offsets can be calculated, so shuffle the list of offests here
	    	ArrayList<Float> offsetList = new ArrayList<Float>(13);
			for (float offset = 0.25f; offset <= 2.0f; offset += 0.25f) {
				offsetList.add(offset);
			}

			// try the 0 offset always first
			if (synFillShots(0))
				return true;
			
			Collections.shuffle(offsetList);
	    	for (Float offset: offsetList)
	    	{
				final boolean foundSomething = synFillShots(offset) ;
				if (foundSomething)
					break; // breaks as soon as there was at least one shout found for this offset
	    	}
			
	    	return true;
	    }
	    
		// fills shots for the target but only for 1 specific offset (can be 0) 
	    // offset 0 means it is the actual atarget and no phantom
		private boolean synFillShots(float localTargetOffset) {
			boolean foundSomething =false;
			final LinkedHashSet<Curve> neededCurves = new LinkedHashSet<Curve>();

			// ensure only enemies, not owns
			if (target.getPlayerSide() == PlayerSide.LEFT) {

				for (int x = 0; x < 12; x++) {
					for (int y = 0; y < 6; y++) {
						if (patches[x][y].contains(target.getX()+ localTargetOffset, target.getY()))
							//if (patches[x][y].contains(target.getPhysicalPosition().x+ localTargetOffset, target.getPhysicalPosition().y))
							neededCurves.addAll(patches[x][y].affectedCurves);
					}
				}
			}
			System.out.println("["+target.getID()+"] [off:"+localTargetOffset+"] neededCurves: " + neededCurves.size()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			for (Curve curve : neededCurves) {
				final Shot goodShot = synWillItHitTarget(curve.startPos, curve.angle, curve.v0, localTargetOffset);
				if (goodShot == null)
					continue; // this way hits no target

				// TODO maybe return if a 0dmg path was found
				goodShot.pullVector = curve.pullVector;
				//goodShot.isPhantomShot = localTargetOffset>0; // has been set

				synchronized (shots) {
					shots.add(goodShot);
				}
				foundSomething =true;
			}
//			System.out.println("curves processed");
			return foundSomething;
		}
	 
		// returns the shot (and all its properties) to the target (if there is one)
		private Shot synWillItHitTarget(final Vector2 startPos, final float angleInDeg, final float v0, float localTargetOffset) {
			GameManager gm = GameManager.getInstance();
			final AdvancedStage stage = gm.currentGameScreen.getRenderer().world.stage;

			float dmgOnWayToTaget = 0.0f;
			float t = 0.0f;
			final float stepSeconds = 0.005f;// = 0.005f;works excpt in last level
			final float maxSeconds = 3f;

			// phantoms: +x-offsetted targets
			ArrayList<Circle> phantoms = new ArrayList<Circle>();
			ArrayList<TargetActor> phantomOriginals = new ArrayList<TargetActor>();
			if (localTargetOffset > 0) {
				synchronized (stage) {
					// fill the phantom list
			        // the actual object returned by getAllActors() changes all the time, so work on a copy
			        final Set<Actor> allactors = new HashSet<Actor>(stage.getAllActors());
					for (Actor a : allactors) {
						if (a instanceof TargetActor) {
							TargetActor ta = (TargetActor) a;
							if (ta.getPlayerSide() != PlayerSide.RIGHT) // not the own ones
							{
								//phantoms.add(new Circle(ta.getX() + localTargetOffset, ta.getY(), 0.5f));
								phantoms.add(new Circle(ta.getPhysicalCenterPosition().x + localTargetOffset, ta.getPhysicalCenterPosition().y, 0.5f));
								phantomOriginals.add(ta);
							}
						}
					}
				}
			}

			// offsets for points on the circle
			final ArrayList<Vector2> offsets = new ArrayList<Vector2>();
			offsets.add(new Vector2(0, 0));
			offsets.add(new Vector2(-0.2f, -0.2f)); // 0.2 is projectile radius
			offsets.add(new Vector2(0.2f, 0.2f));
			offsets.add(new Vector2(-0.2f, 0.2f));
			offsets.add(new Vector2(0.2f, -0.2f));

			while (true) {
				t += stepSeconds;
				final Vector2 estimatedImpactPosNotOffsetted = estimatedPosAfterTseconds(startPos, t, angleInDeg, v0 * 10);

				// try diffrent offsets, to check if there would be a wall hit
				for (int i = 0; i < offsets.size(); i++) {
					final Vector2 estimatedImpactPos = estimatedImpactPosNotOffsetted.cpy().add(offsets.get(i));
					// final Vector2 estimatedImpactPos = estimatedPosAfterTseconds
					// (startPos,t,angleInDeg, v0*10).add(offsets.get(i));
					final Actor estimatedHitTarget;
					synchronized (stage) {
						// this hit call fuck up the drag, so after wards we touch to projectile again
//						estimatedHitTarget = stage.getRoot().hit(1, 1, false);
						estimatedHitTarget = stage.getRoot().hit(estimatedImpactPos.x, estimatedImpactPos.y, false);
//						estimatedHitTarget = stage.hit(estimatedImpactPos.x, estimatedImpactPos.y, false);
//						stage.hit(currentProjectile.getPhysicalPosition().x, currentProjectile.getPhysicalPosition().y, true);
//						estimatedHitTarget = null;
					}

					if (estimatedHitTarget != null) {
						if (estimatedHitTarget instanceof PhysicsActor) {
							PhysicsActor pa = (PhysicsActor) estimatedHitTarget;

							if (pa instanceof BlockActor) {
								// TODO add circle and triangle shapes here
								BlockActor ba = (BlockActor) pa;
								final BodyType bodyType = ba.getBodyType();

								if (bodyType != BodyType.DynamicBody)
									return null; // there is a wall in the way

								if (!ba.isDamagable())
									return null; // there is a wall in the way

								dmgOnWayToTaget += ba.getHealth();
							}

							if (pa instanceof TargetActor) {
								TargetActor ta = (TargetActor) pa;
								// don't aim on this if it is my own
								if (ta.getPlayerSide() == PlayerSide.RIGHT)
									return null;

								Shot sh = new Shot(ta.getID(),executorRoundId, dmgOnWayToTaget);
								// the following lines used estimatedImpactPosNotOffsetted before, not done anymore
								// because IsTargetStillThere checking relies on beeing accurate
								sh.targetPos.set(estimatedImpactPos.x, estimatedImpactPos.y);
								sh.distanceToTargetCenter = estimatedImpactPos.dst(
										ta.getPhysicalCenterPosition().x , ta.getPhysicalCenterPosition().y);
								sh.isPhantomShot = false;
								return sh;
							}

						}
					}

					// if no real target hit, maybe a phantom?
					for (int j = 0; j < phantoms.size(); j++) {
						Circle c = phantoms.get(j);
						if (c.contains(estimatedImpactPos)) {
							Shot sh = new Shot(phantomOriginals.get(j).getID(),executorRoundId, dmgOnWayToTaget);
							// the following lines used estimatedImpactPosNotOffsetted before, not done anymore
							// because IsTargetStillThere checking relies on beeing accurate
							sh.targetPos.set(estimatedImpactPos.x, estimatedImpactPos.y);
							sh.distanceToTargetCenter = estimatedImpactPos.dst(c.x, c.y);
							sh.isPhantomShot = true;
							return sh;
						}
					}
				}
				// if (target.contains(estimatedImpactPos))
				// {
				// return true;
				// }
				if (t >= maxSeconds)
					break;
				if (estimatedImpactPosNotOffsetted.x < 0 || estimatedImpactPosNotOffsetted.y < 0)
					break;
			}
			
			return null;
		} // EOF
	}


 	// removes all shots that wouldnt hit a target anymore
// 	private class ShotCleaner implements Callable<Boolean> {
//
//		@Override
//		public Boolean call() throws Exception {
//			synchronized (shots) {
//				System.out.println("Cleaning shots with invalid targets, have now: "+shots.size());
//				int sizeBefore = shots.size();
//				int removed = 0;
//				int iterated = 0;
//				
//				for (int iii=0; iii<shots.size() ; iii++)
//				for (Iterator it = shots.iterator(); it.hasNext();) {
//					Shot s = (Shot) it.next();
//					if (!synIsThereATargetAt2(s.targetPos))
//					{
//						shots.remove(s);
//						removed++;
//					}
//					iterated++;
//				}
//				if (iterated != sizeBefore)
//				//	throw new RuntimeException("wrong iteratios");
//				System.err.println("wrong iteratios");
//				if (sizeBefore-removed != shots.size())
//				//	throw new RuntimeException("wrong size");
//				System.err.println("wrong size");
//				//System.out.println("Cleaning shots with invalid targets, have now: "+shots.size());
//			}
//			return null;
//		}
//		private boolean synIsThereATargetAt2 (Vector2 targetPos) {
//			GameManager gm = GameManager.getInstance();
//			final AdvancedStage stage = gm.currentGameScreen.getRenderer().world.stage;
//			
//			final Actor hitActor = stage.getRoot().hit(targetPos.x, targetPos.y, false);
//			
//			if (hitActor != null)
//			{
//				if (hitActor instanceof TargetActor) {
//					TargetActor ta = (TargetActor) hitActor;
//					if (ta.getPlayerSide()==PlayerSide.LEFT)
//						return true;
//				}
//			}
//			
//			return false;
//		}
// 	}
 	
	/**
	 * Returns the estimated postion after t seconds
	 * 
	 * @param startPos
	 *            throw position
	 * @param t
	 *            time in seconds
	 * @param angleInDeg
	 *            throw angle in positive degrees
	 * @param v0
	 *            initial speed in m/s
	 * @return
	 */
	private static Vector2 estimatedPosAfterTseconds(final Vector2 startPos, final float t, final float angleInDeg, final float v0) {
		final float g = GameWorld.GRAVITY.y;
		final float Sx = v0 * MathUtils.cosDeg(angleInDeg) * t;
		final float Sy = v0 * MathUtils.sinDeg(angleInDeg) * t + 0.5f * g * (t * t);

		// we throw in direction -x
		final Vector2 ret = new Vector2(startPos);
		ret.add(-Sx, Sy);
		return ret;

	}

	private void touchDown() {
		GameManager gm = GameManager.getInstance();
		final PhysicsActor currentProjectile = gm.currentPlayer.slingshot.getCurrentProjectile();
		InputEvent touchDown = new InputEvent();
		touchDown.setListenerActor(currentProjectile);
		touchDown.setTarget(currentProjectile);
		touchDown.setStage(currentProjectile.getStage());
		touchDown.setType(Type.touchDown);
		currentProjectile.fire(touchDown);
	}

	private void touchDragTo(Vector2 dt) {
		pulledSteps++;
		GameManager gm = GameManager.getInstance();
		final PhysicsActor currentProjectile = gm.currentPlayer.slingshot.getCurrentProjectile();
		InputEvent touchDragged = new InputEvent();
		touchDragged.setListenerActor(currentProjectile);
		touchDragged.setTarget(currentProjectile);
		touchDragged.setStage(currentProjectile.getStage());
		touchDragged.setType(Type.touchDragged);
		touchDragged.setStageX(dt.x);
		touchDragged.setStageY(dt.y);
		currentProjectile.fire(touchDragged);
	}

	private void touchUp() {
		GameManager gm = GameManager.getInstance();
		final PhysicsActor currentProjectile = gm.currentPlayer.slingshot.getCurrentProjectile();
		InputEvent touchUp = new InputEvent();
		touchUp.setListenerActor(currentProjectile);
		touchUp.setTarget(currentProjectile);
		touchUp.setStage(currentProjectile.getStage());
		touchUp.setType(Type.touchUp);
		// touchUp.setStageX(12);
		// touchUp.setStageY(0);
		currentProjectile.fire(touchUp);
	}
	
	
}
