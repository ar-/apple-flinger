/*******************************************************************************
 * Copyright (C) 2015-2018 Andreas Redmer <ar-appleflinger@abga.be>
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
package com.gitlab.ardash.appleflinger.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.SoundGroupAsset;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.GameState;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.helpers.SoundPlayer;
import com.gitlab.ardash.appleflinger.i18n.I18N;

/**
 * contains the functionality to expire (disapprear after time, because this is currently the only one who can expire
 *
 */
public class ProjectileActor extends CircleActor {
	
	/**
	 * keep a reference to the sling, so in can be told to release after the shot.
	 */
	private SlingShotActor slingShotActor;
	
	private final float shotForceMultiplyer = 4000f; // good for adjusted gravity
	private final float maxPullDistance = 0.8f; // good for adjusted gravity
	
	private static final float maxLifetime = 10f;
	private float lifetime = 0;
	private boolean lifetimeStarted = false;
	protected boolean obstacleFound = false; // for slow roll detection
	private Float accLastTimestamp = null; // for acceleration calc
	private Float accLastSpeed = null; // for acceleration calc

	public ProjectileActor(final GameWorld world, MaterialConfig mc, float x,
			float y, float diameter, BodyType bodyType) {
		super(world, mc, x, y, diameter, bodyType);
		
		// projectiles can be set to damageble here
		setDamagable(false);
		
		Texture tex = Assets.getTexture(TextureAsset.APPLE);  
		this.setDrawable(new TextureRegionDrawable(new TextureRegion(tex)));
        this.setScaling(Scaling.stretch); // stretch the texture  
        this.setAlign(Align.center);  

		body.setGravityScale(0);

		//reference for the listener
		final PhysicsActor caller = this;
		
		this.addListener(new InputListener(){
			private final Vector2 lastTouchDown = new Vector2(0,0);
			private final Vector2 shootDirection = new Vector2(0,0);
			
			boolean pullSoundPlayed = false;

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameManager gm = GameManager.getInstance();
				if (GameManager.DEBUG)
					System.out.println("touchDown"+x +","+y);  
				lastTouchDown.set(body.getTransform().getPosition().cpy());
				removePhysics();
				gm.setGameState(GameState.DRAGGING);
				 pullSoundPlayed = false;
				return true;
			}
			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				super.touchDragged(event, x, y, pointer);
				
				// to drag the projectile is must be connected to a slingshot
				if (slingShotActor == null)
					return;
				
				if (!pullSoundPlayed)
				{
					SoundPlayer.playSound(Assets.getRandomSound(SoundGroupAsset.RUBBER),0.25f);
					pullSoundPlayed = true;
				}
				
				final Vector2 rampCenterPoint = slingShotActor.getSlingShotCenter();
				
        		event.setBubbles(false);

				final Vector2 moveTarget= new Vector2(event.getStageX(), event.getStageY());
				final Vector2 moveVector = moveTarget.cpy().sub(rampCenterPoint);
				final float rampDist = rampCenterPoint.dst(moveTarget);
				if (Math.abs(rampDist)>maxPullDistance)
				{
					// too far
					moveVector.clamp(-maxPullDistance, maxPullDistance);
					final Vector2 moveDest = rampCenterPoint.cpy().add(moveVector);
					body.setTransform(moveDest,0);
				}
				else
				{
					// normal
					body.setTransform(moveTarget,0);
				}
				shootDirection.set(rampCenterPoint.cpy().sub(body.getTransform().getPosition()));

				if (GameManager.DEBUG)
				{
					System.out.print(" angle "+moveVector.angle());
					System.out.print(" sin "+MathUtils.sin(moveVector.angleRad()));
					System.out.println(" cos "+MathUtils.cos(moveVector.angleRad()));
				}
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				final GameManager gm = GameManager.getInstance();
				// this event comes in twice in a row -- dont do anything if status is always waiting for physics
				if (gm.getGameState()==GameState.WAIT_FOR_PHYSICS)
					return;
				// SHOOT !!!
				caller.setTouchable(Touchable.disabled);
				// disable touchablity of the game scene
				gm.getInputMultiplexer().removeProcessor(gm.currentGameScreen.getRenderer().world.stage);
				gm.getInputMultiplexer().addProcessor(gm.currentGameScreen.getGuiStage());
				gm.currentGameScreen.setAnnouncementText(I18N.getString("pleaseWait")+" ...", true); 
				gm.onShotFired();
				SoundPlayer.playSound(Assets.getRandomSound(SoundGroupAsset.WHIZZ), 0.25f);
				reAddPhysics();
				body.setGravityScale(1.0f);
				
				GameManager.recordPullVector(shootDirection.cpy().scl(-1f));
				if (GameManager.DEBUG)
					System.out.println(shootDirection);
					
				body.applyForceToCenter(shootDirection.scl(shotForceMultiplyer), true);

				gm.setGameState(GameState.WAIT_FOR_PHYSICS);
				world.continuePhysics();
				final OrthographicCamera camera = gm.currentGameScreen.getRenderer().getCamera();
				camera.position.set(GameWorld.UNIT_WIDTH/2f, GameWorld.UNIT_HEIGHT/2f,0);
				startLifetime();
        		if (slingShotActor != null)
        			slingShotActor.releaseProjectile();
			}
		});

	}
	
	@Override  
    public void act(float delta) {  
        // here we override Actor's act() method to make the actor follow the box2d body  
        super.act(delta);
        // projectiles can be made not-rotating here
        // setRotation(0);  
        if (lifetimeStarted)
        {
        	lifetime+=delta;
        	killIfLifetimeExpired();
        	killIfSlowlyRolling();
        }

        if (GameManager.DEBUG)
        {
        	// print velocity in the first frame of lifetime
	        if (lifetime>0.f && lifetime<=0.04f)
	        	System.err.println("Velocity "+body.getLinearVelocity().len());
	        
        }
    }
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
	}
	
	@Override
	protected void resetPhysics(MaterialConfig mc, float x,
			float y, float angle, BodyType aBodyType) {
		super.resetPhysics(mc, x, y, angle, aBodyType);
		// no mass for projectiles - mass will be added on shot
		body.setGravityScale(0);
	}

	public void setSlingShotActor(SlingShotActor slingShotActor) {
		this.slingShotActor = slingShotActor;
	}
	
	private void startLifetime()
	{
		lifetimeStarted=true;
	}
	
	private void killIfLifetimeExpired()
	{
		if (lifetimeStarted && (lifetime>= maxLifetime))
		{
			setToBeDestroyed();
		}
	}
	
	public void endLifetimeNow()
	{
		lifetime= maxLifetime;
	}
	
	private void killIfSlowlyRolling() {
		
		if (obstacleFound)
			return;
		
		// check if not dead
		if (isToBeDestroyed())
			return;
		
		// check if lifetime is more than 1 sec
		if (lifetime<=1)
			return;
		
		// check that apple is on ground
		if ( getY() > 0.1f)
			return;
		
		// check that movement is only horizontally
		final Vector2 linearVelocity = body.getLinearVelocity();
		if (Math.abs(linearVelocity.y) > 0f)
			return;
		
		// check if movement is slow 
		// (also return if speed is almost 0, since there is nothing to do then)
		final float unitsPerSec = linearVelocity.x;
		final float absUnitsPerSec = Math.abs(unitsPerSec);
		if (absUnitsPerSec > 0.2f || absUnitsPerSec < 0.01f)
			return;
		
		// measure acceleration, return if not enough data yet (needs 2 frames)
		if (accLastSpeed == null || accLastTimestamp == null)
		{
			accLastSpeed = absUnitsPerSec;
			accLastTimestamp = lifetime;
			return;
		}
		
		final float acceleration = (absUnitsPerSec - accLastSpeed) / (lifetime - accLastTimestamp);
		final float absDistToStop = (0-absUnitsPerSec*absUnitsPerSec)/(2*acceleration);

		// prepare ray cast
		final Vector2 worldOrigin = body.getWorldPoint(new Vector2());
		final Vector2 posAtStop = worldOrigin.cpy();
		posAtStop.x += (unitsPerSec>0)? absDistToStop : -absDistToStop;
		
		// make sure speed is not 0, otherwise ray cast can fail with:
		// java: ./Box2D/Collision/b2DynamicTree.h:209: void b2DynamicTree::RayCast(T*, const b2RayCastInput&) const [with T = b2WorldRayCastWrapper]: Assertion `r.LengthSquared() > 0.0f' failed.
		if (absDistToStop < 0.01f)
			return;
		
		// ray cast to find obstacles
		obstacleFound = false;
		body.getWorld().rayCast(new RayCastCallback() {
			
			// causes an error for distance =0 , must be checked for last touch, or current touch
			@Override
			public float reportRayFixture(Fixture fixture, Vector2 point,
					Vector2 normal, float fraction) {
				if (point.dst(worldOrigin)+getOriginX() < absDistToStop)
					obstacleFound = true;
				return 0f;
			}
		}, worldOrigin , posAtStop);
		if (obstacleFound)
			return;
		
		// arrived here, we can safely assume, that the apple won't have any impact any more
		endLifetimeNow();
		if (GameManager.DEBUG)
			System.err.println("killing slow apple: "+unitsPerSec);
	}
	 

}
