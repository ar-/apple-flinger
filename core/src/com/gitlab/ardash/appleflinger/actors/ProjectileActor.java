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
package com.gitlab.ardash.appleflinger.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.GameState;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.global.Assets.SoundGroupAsset;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;
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
				System.out.println("touchDown"+x +","+y); //$NON-NLS-1$ //$NON-NLS-2$
				lastTouchDown.set(body.getTransform().getPosition().cpy());
				removePhysics();
				GameManager.getInstance().setGameState(GameState.DRAGGING);
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
				gm.getInputMultiplexer().clear();
				gm.getInputMultiplexer().addProcessor(gm.currentGameScreen.getGuiStage());
				gm.currentGameScreen.setAnnouncementText(I18N.getString("waiting"), true); //$NON-NLS-1$
				gm.onShotFired();
				SoundPlayer.playSound(Assets.getRandomSound(SoundGroupAsset.WHIZZ), 0.25f);
				reAddPhysics();
				body.setGravityScale(1.0f);
				
				if (gm.RECORDSHOTS)
					System.err.println("Applying "+shootDirection + " len: ");
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
			float y, float angle, BodyType bodyType) {
		super.resetPhysics(mc, x, y, angle, bodyType);
		// no mass for procetiles - mass will be added on shot
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
	
}
