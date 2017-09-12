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
package com.ardash.appleflinger;

import com.ardash.appleflinger.actors.AppleActor;
import com.ardash.appleflinger.actors.BackgroundActor;
import com.ardash.appleflinger.actors.Bird;
import com.ardash.appleflinger.actors.BlockActor;
import com.ardash.appleflinger.actors.Ground;
import com.ardash.appleflinger.actors.PhysicsActor;
import com.ardash.appleflinger.actors.ProjectileActor;
import com.ardash.appleflinger.actors.SlingShotActor;
import com.ardash.appleflinger.actors.TargetActor;
import com.ardash.appleflinger.ai.PlayerSimulator;
import com.ardash.appleflinger.global.GameManager;
import com.ardash.appleflinger.global.GameState;
import com.ardash.appleflinger.global.MaterialConfig;
import com.ardash.appleflinger.global.PhysicWorldObserver;
import com.ardash.appleflinger.global.PlayerStatus.PlayerSide;
import com.ardash.appleflinger.listeners.MyContactListener;
import com.ardash.appleflinger.listeners.OnPhysicStoppedListener;
import com.ardash.appleflinger.missions.Mission;
import com.ardash.appleflinger.screens.GameScreen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameWorld implements Disposable{  
    
    // here we set up the actual viewport size of the game in meters.  
    public static final float UNIT_TO_SCREEN = 160f; 
    public static float UNIT_WIDTH = GameScreen.SCREEN_WIDTH/UNIT_TO_SCREEN; // 6.4 meters width  (12)
    public static float UNIT_HEIGHT = GameScreen.SCREEN_HEIGHT/UNIT_TO_SCREEN; // 3.75 meters height  (6.75)
//    public static float UNIT_WIDTH = GameScreen.SCREEN_WIDTH/341.333333333f; // 3 meters width  
//    public static float UNIT_HEIGHT = GameScreen.SCREEN_HEIGHT/200f; // 3 meters height  
      
//    public static final Vector2 GRAVITY = new Vector2(0, -9.8f);  
    public static final Vector2 GRAVITY = new Vector2(0, -6.8f);  // REAL GAME
//    public static final Vector2 GRAVITY = new Vector2(0, 0f);  
      
    public final AdvancedStage stage; // stage containing game actors (not GUI, but actual game elements)  
    public World box2dWorld; // box2d world
    public PhysicWorldObserver physicWorldObserver;
	private ProjectileActor firstProjectile;

    private boolean isPhysicPaused;
	
	public final Mission mission;
	private Group birdGroup;
      
    public GameWorld(Mission mission) {      
    	this.mission = mission;
    	isPhysicPaused = false;
    	firstProjectile = null;
    	
        World.setVelocityThreshold(0.2f);
        box2dWorld = new World(GRAVITY, true);
        physicWorldObserver = new PhysicWorldObserver(box2dWorld);
        box2dWorld.setContactListener(new MyContactListener());
        //stage = new Stage(); // create the game stage  
        //stage.setViewport(UNIT_WIDTH, UNIT_HEIGHT, false); // set the game stage viewport to the meters size   
        stage = new AdvancedStage(new FitViewport(UNIT_WIDTH, UNIT_HEIGHT));
        //stage = new Stage(new ExtendViewport(UNIT_WIDTH, UNIT_HEIGHT, UNIT_WIDTH, UNIT_HEIGHT));

        createWorld();  
        
//        PlayerSimulator.INSTANCE.markAsUninitialised();
        PlayerSimulator.INSTANCE.startBackgroundInitForNextRound();
    }  
      
    private void createWorld() {
    	final GameManager gm = GameManager.getInstance();
    	gm.setGameState(GameState.INIT_WORLD);
    	stage.addActor(new BackgroundActor());
    	
    	birdGroup = new Group();
    	stage.addActor(birdGroup);
    	
    	// absorber box
    	float absorberDistance = 4f;
    	BlockActor absorber = new BlockActor(this,MaterialConfig.INVISIBLE,-absorberDistance,UNIT_HEIGHT/2, 1f,UNIT_HEIGHT*2.8f, BodyType.StaticBody); 
    	absorber.setAbsorbing(true);
    	stage.addActor(absorber);
    	absorber = new BlockActor(this,MaterialConfig.INVISIBLE,UNIT_WIDTH+absorberDistance,UNIT_HEIGHT/2, 1f,UNIT_HEIGHT*2.8f, BodyType.StaticBody); 
    	absorber.setAbsorbing(true);
    	stage.addActor(absorber);
    	absorber = new BlockActor(this,MaterialConfig.INVISIBLE,UNIT_WIDTH/2,-absorberDistance/2, UNIT_WIDTH*2,1f, BodyType.StaticBody); 
    	absorber.setAbsorbing(true);
    	stage.addActor(absorber);
    	absorber = new BlockActor(this,MaterialConfig.INVISIBLE,UNIT_WIDTH/2,UNIT_HEIGHT+absorberDistance*1.5f, UNIT_WIDTH*2,1f, BodyType.StaticBody); 
    	absorber.setAbsorbing(true);
    	stage.addActor(absorber);
    	
    	// mirrored part
    	Group leftSideMirror = mission.getStageFiller().fillMirrorStage(this);
    	Group rightSideMirror = mission.getStageFiller().fillMirrorStage(this);
    	
    	// apply a translation for all right side elements
    	// can't use a transformation matrix here, because the physics wont be transformed
    	for (Actor a : rightSideMirror.getChildren())
    	{
    		final float center = UNIT_WIDTH/2f;
    		// d is distance to center
    		final float d = center - a.getX();
    		final float newx = center + d;
    		//final float newx = 9;
    		if (a instanceof PhysicsActor) {
				PhysicsActor pa = (PhysicsActor) a;
				pa.setPhysicalPosition(new Vector2(newx,a.getY()));
				pa.setPhysicalRotation(-pa.getPhysicalRotation());
			}
    		else
    		{
        		if (a instanceof SlingShotActor) {
        			SlingShotActor pa = (SlingShotActor) a;
    				pa.setPosition(newx,a.getY());
    				pa.setMirrored(true);
    				//pa.setX(newx);
    			}
    		}
    	}
    	
    	// apply player sides to the TargetActors
    	for (Actor a : leftSideMirror.getChildren())
    	{
    		if (a instanceof TargetActor) {
				TargetActor ta = (TargetActor) a;
				ta.setPlayerSide(PlayerSide.LEFT);
			}
    	}
    	for (Actor a : rightSideMirror.getChildren())
    	{
    		if (a instanceof TargetActor) {
				TargetActor ta = (TargetActor) a;
				ta.setPlayerSide(PlayerSide.RIGHT);
			}
    	}
    	
    	stage.addActor(leftSideMirror);
    	stage.addActor(rightSideMirror);
    	
    	// store references to the slingshots in the gamestate
    	for (Actor a : leftSideMirror.getChildren())
    		if (a instanceof SlingShotActor) 
    			gm.PLAYER1.slingshot = (SlingShotActor) a;
    	for (Actor a : rightSideMirror.getChildren())
    		if (a instanceof SlingShotActor) 
    			gm.PLAYER2.slingshot = (SlingShotActor) a;
    	
    	//
          
        // create box2d bodies and the respective actors here.  

      //BlockActor ba = new BlockActor(this,MaterialConfig.GLASS,2f,3f,0.1f,0.4f, BodyType.DynamicBody);  
//       stage.addActor( new DamagableBlockActor(this,MaterialConfig.GLASS,2f,3f,0.1f,0.4f, BodyType.DynamicBody));  
//       stage.addActor( new DamagableBlockActor(this,MaterialConfig.GLASS,0.8f,3f,0.1f,0.4f, BodyType.DynamicBody));  
//
//       
//       stage.addActor( new DamagableBlockActor(this,MaterialConfig.GLASS,6.0f,0.4f,0.1f,0.8f, BodyType.DynamicBody));  
//       stage.addActor( new DamagableBlockActor(this,MaterialConfig.GLASS,6.0f,1.4f,0.1f,0.8f, BodyType.DynamicBody));  
//       stage.addActor( new DamagableBlockActor(this,MaterialConfig.GLASS,6.0f,2.4f,0.1f,0.8f, BodyType.DynamicBody));  

//    	stage.addActor(new BlockActor(this,MaterialConfig.STONE,0f,0f,1f,1f, BodyType.StaticBody));
//    	stage.addActor(new BlockActor(this,MaterialConfig.GLASS,1f,1f,1f,1f, BodyType.StaticBody));
//    	stage.addActor(new BlockActor(this,MaterialConfig.WOOD,1f,1f,0.5f,0.5f, BodyType.StaticBody));

       //rampCenter = new CircleActor(this,MaterialConfig.GLASS,5.1f,0.7f,0.1f, BodyType.StaticBody);
       //rampCenterPoint = new Vector2(5.1f,0.7f);
       //stage.addActor(rampCenter);  
//       ProjectileActor firstProjectile = new ProjectileActor(this,MaterialConfig.STONE,5.0f,2.0f,0.4f, BodyType.DynamicBody);
    	
    	final GameWorld thisForListener = this;
    	// prjectile will now be added as soon as the initial physic get settled
    	physicWorldObserver.removeAllListeners();
    	physicWorldObserver.addListener(new OnPhysicStoppedListener() {
			
			@Override
			public void onPhysicStopped() {
				
				// if there is still a projectile, expire it and restart the physics
				if (firstProjectile!=null && !firstProjectile.isToBeDestroyed() && firstProjectile.getStage()!=null)
				{
					firstProjectile.endLifetimeNow();
					continuePhysics();
					return;
				}
				// if there no no projectile anymore, we can go on and create a new one
				gm.turnToNextPlayer();
				if (gm.getGameState()==GameState.GAME_OVER_SCREEN)
					return;
			      firstProjectile = new AppleActor(thisForListener,MaterialConfig.PROJECTILE,0,0,0.4f, BodyType.DynamicBody);
			      gm.currentPlayer.slingshot.addProjectile(firstProjectile);
			      gm.setGameState(GameState.WAIT_FOR_DRAG);
			      // pause physical bahaviour once it was stopping already
			      thisForListener.pausePhysics();
			      
			      if (gm.isPlayer2CPU() && gm.currentPlayer == gm.PLAYER2)
			      {
			    	  PlayerSimulator.INSTANCE.playOneRound();
			    	  //PlayerSimulator.INSTANCE.tickThinking();
			      }
			      else
			      {
			    	  gm.getInputMultiplexer().removeProcessor(stage);
			    	  gm.getInputMultiplexer().addProcessor(stage);
			    	  // we just turned to player 1, so the CPU has now time to think about new shots
			    	  //makes shot too slow and dragging impossible :(
			    	  //PlayerSimulator.INSTANCE.tickThinking();
			      }
			}
		});
    	
        Ground ground = new Ground(this);
        stage.addActor(ground);  
        
        
//        TargetActor ta = new TargetActor(this, MaterialConfig.STONE, 6, 3, 0.5f, BodyType.DynamicBody);
//        stage.addActor(ta);
        
//        SlingShotActor slingshot1 = new SlingShotActor(this, 3.2f, 0.1f);
//        SlingShotActor slingshot2 = new SlingShotActor(this, 8.8f, 0.1f);
////        Vector2 ssc = slingshot.getSlingShotCenter();
////      ProjectileActor firstProjectile = new ProjectileActor(this,MaterialConfig.STONE,ssc.x,ssc.y,0.4f, BodyType.DynamicBody);
//      ProjectileActor firstProjectile = new ProjectileActor(this,MaterialConfig.STONE,0,0,0.4f, BodyType.DynamicBody);
//        slingshot2.addProjectile(firstProjectile);
//		stage.addActor(slingshot1);
//		stage.addActor(slingshot2);
		
        
        // add more game elements here  
        //box2dWorld.setGravity(new Vector2(0.5f, 0.7f));
        //box2dWorld.
        
    	gm.setGameState(GameState.WAIT_FOR_PHYSICS);
    }  
      
    public void update(float delta) {  
      Array<Fixture> fixtures = new Array<Fixture>(0);
		// remove destroyed actors, before simulation step starts
    	box2dWorld.getFixtures(fixtures);
    	for (Fixture f : fixtures)
    	{
    		Object ud = f.getUserData();
    		if (ud instanceof PhysicsActor)
    		{
    			// check if it was absorbed / or exipration has ended the exlposion animation
    			boolean remove = ((PhysicsActor)ud).isToBeRemoved();
    			if (remove)
    			{
    				box2dWorld.destroyBody(f.getBody());
    				Actor a = (Actor)ud;
    				a.remove();
    				
    			}
    		}
    	}
        // perform game logic here  
    	if (!isPhysicPaused)
    	{
//	    	if (delta >0.02f)
//	    		delta = 0.02f;
	    	 if ( delta > 0.25f ) delta = 0.25f; // note: max frame time to avoid spiral of death
	    	 accumulator += delta;
	    	 
	        double newTime = TimeUtils.millis() / 1000.0;
	        double frameTime = Math.min(newTime - currentTime, 0.25);
	        float deltaTime = (float)frameTime;

	        currentTime = newTime;

	        while (accumulator >= step) {
		        box2dWorld.step(step, 25, 25); // update box2d world (high values stablibise stacks)
		        physicWorldObserver.step();
	            accumulator -= step;
	        }
	        
//	        box2dWorld.step(delta, 25, 25); // update box2d world (high values stablibise stacks)
//	        physicWorldObserver.step();
	        //System.out.println(delta);
    	}
        stage.act(delta); // update game stage  
        //System.out.println(delta);
        
        // if player 2 is a CPU it will act now
//        PlayerSimulator.INSTANCE.loadAIforNewLevelIfNotDoneYet();
        PlayerSimulator.INSTANCE.act(delta);
        
        Bird.pingBirdSpawn(birdGroup);
    }
    
    private double accumulator;
    private double currentTime;
    private float step = 1.0f / 60.0f;

    
    public void pausePhysics()
    {
    	isPhysicPaused = true;
    }
    
    public void continuePhysics()
    {
    	//System.out.println("Continueing Physics. lastDelta: "+Gdx.graphics.getDeltaTime());
		//System.err.println("Applying "+shootDirection + " len: "+shootDirection.len()+ " ang: "+shootDirection.angle());
		
    	isPhysicPaused = false;
    }

    @Override
	public void dispose() {
		if (stage !=null)
			stage.dispose();
		if (box2dWorld !=null)
			box2dWorld.dispose();
	}  
    
} 
