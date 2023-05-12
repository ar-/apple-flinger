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
package com.gitlab.ardash.appleflinger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.gitlab.ardash.appleflinger.actors.BlockActor;
import com.gitlab.ardash.appleflinger.actors.CollisionFilterMasks;
import com.gitlab.ardash.appleflinger.actors.DorkActor;
import com.gitlab.ardash.appleflinger.actors.PengActor;
import com.gitlab.ardash.appleflinger.actors.PhysicsActor;
import com.gitlab.ardash.appleflinger.actors.TntActor;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.GameState;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.helpers.Achievement;

public class GameRenderer  implements Disposable
{      
    public final GameWorld world;  
    final OrthographicCamera camera;  
    Box2DDebugRenderer renderer;  
    private float wantedZoom;
    
    private final Vector2 lastTouchDown;
    private MaterialConfig currentSandboxConfig = MaterialConfig.WOOD_RECT;
    private float currentSandboxRotation = 0;
	private PhysicsActor sandboxPreviewActor = null;
  
    public GameRenderer(final GameWorld world)  
    {  
        this.world = world;  
        this.renderer = new Box2DDebugRenderer();  
        wantedZoom=1.0f;
        lastTouchDown= new Vector2(0,0);

        // we obtain a reference to the game stage camera. The camera is scaled to box2d meter units  
        this.camera = (OrthographicCamera) world.stage.getCamera();  
      
        // cam movement listener
        world.stage.addListener(new InputListener(){
        	private final Vector2 lastDrag = new Vector2(0,0);
        	private final Vector3 initialCamPos = camera.position.cpy();
        	@Override
        	public boolean touchDown(InputEvent event, float x, float y,
        			int pointer, int button) {
        		lastDrag.set(0,0);
        		lastTouchDown.set(x,y);
        		if (GameManager.DEBUG)
        			System.out.println("s touc "+x+","+y+ " ev: "+event.getRelatedActor());
        		return true;
        	}
        	@Override
        	public void touchDragged(InputEvent event, float x, float y,
        			int pointer) {
        		// don't do anything if the user is dragging the projectile
        		if (GameManager.getInstance().getGameState()==GameState.DRAGGING)
        			return;
        		if (GameManager.DEBUG)
        			System.out.println("s drag "+x+","+y+ "ev:"+event.getRelatedActor());
        		
        		// fix for iss #55, drop out, if the drag is too close to the projectile
        		try
        		{
        			final float disabledDist = 2f;
	        		final Vector2 slingShotCenter = GameManager.getInstance().currentPlayer.slingshot.getSlingShotCenter();
	        		if (slingShotCenter.dst2(x, y) < disabledDist*disabledDist)
	        			return;
        		}
        		catch (NullPointerException e)
        		{
        			// no worries! current player or slingshot not set yet.
        			// player dragged (touched) too early, while stage is still in initial swinging 
        		}
        		
        		super.touchDragged(event, x, y, pointer);
        		final float stageX = event.getStageX();
				final float stageY = event.getStageY();
				if (lastDrag.x ==0 && lastDrag.y==0)
        		{
        			lastDrag.set(stageX,stageY);
        			return;
        		}
				if (lastDrag.epsilonEquals(stageX, stageY, 0.1f))
					return;
        			
        		if (GameManager.DEBUG)
        			System.out.println("s dra2 "+stageX+","+stageY);
        		float factor = 0.1f;
        		if (stageX>lastDrag.x)
        			factor *=-1;
        		applyCamMovment(factor);
        		lastDrag.set(0,0);
        		//lastDrag.set(stageX,stageY);
       	}
			/**
			 * @param factor - or +
			 */
			protected void applyCamMovment(float xfactor) {
				if (xfactor==0f)
					return;
				final float maxx =9f;
				final float minx =3f;
				float x,y=0; // destination
				x = camera.position.x + xfactor;
				if (x>maxx)
					x=maxx;
				if (x<minx)
					x=minx;
				
				y = getTranformedY(x);
				camera.position.set(x, y, 0f);
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				animateZoomTo(getTransZ(camera.position.x)); //animates smoothly
			}
			
			protected float getTranformedY(float x)
			{
				// bring x to interval [0,1]
				x-=6f;
				x = Math.abs(x);
				x/=3f;
				
				return initialCamPos.y-x;
			}			
			
			protected float getTransZ(float x)
			{
				// bring x to interval [0,1]
				x-=6f;
				x = Math.abs(x);
				x/=3f;
				
				return 1f- (x*0.3f);
			}

        });
        
    }  
  
    public void render()  
    {  
    	// SANBOX stuff
    	if (GameManager.SANDBOX)
    	{
    		final int mX = Gdx.input.getX();
    		final int mY = Gdx.input.getY();
			final float x = lastTouchDown.x;
			final float y = lastTouchDown.y;
    		if (sandboxPreviewActor !=null)
    			sandboxPreviewActor.remove();
    		//sandboxPreviewActor =null;
    		
//    		BlockActor addActor=null;
    		//System.out.println((1/0.8f)*mX +", "+ (1/0.8f)*mY);
    		AdvancedStage s = world.stage;
    		if(Gdx.input.isKeyPressed(Keys.NUM_1)) 
    			currentSandboxConfig = MaterialConfig.WOOD_RECT;
    		if(Gdx.input.isKeyPressed(Keys.NUM_2)) 
    			currentSandboxConfig = MaterialConfig.TARGET_DORK;
    		if(Gdx.input.isKeyPressed(Keys.W)) 
    			currentSandboxConfig = MaterialConfig.TARGET_PENG;
    		if(Gdx.input.isKeyPressed(Keys.NUM_3)) 
    			currentSandboxConfig = MaterialConfig.WOOD_BL_11;
    		if(Gdx.input.isKeyPressed(Keys.NUM_4)) 
    			currentSandboxConfig = MaterialConfig.WOOD_BL_21;
    		if(Gdx.input.isKeyPressed(Keys.NUM_5)) 
    			currentSandboxConfig = MaterialConfig.WOOD_BL_22;
    		if(Gdx.input.isKeyPressed(Keys.NUM_6)) 
    			currentSandboxConfig = MaterialConfig.WOOD_BL_41;
    		if(Gdx.input.isKeyPressed(Keys.NUM_7)) 
    			currentSandboxConfig = MaterialConfig.WOOD_BL_42;
    		if(Gdx.input.isKeyPressed(Keys.NUM_8)) 
    			currentSandboxConfig = MaterialConfig.WOOD_BL_81;
    		if(Gdx.input.isKeyPressed(Keys.NUM_9)) 
    			currentSandboxConfig = MaterialConfig.WOOD_TNT;
    		
    		if (currentSandboxConfig!=null)
    		{
    			switch (currentSandboxConfig) {
				case TARGET_DORK:
	    			sandboxPreviewActor = new DorkActor(world,currentSandboxConfig, 10, 6, 0.5f, BodyType.StaticBody);
					break;
				case TARGET_PENG:
	    			sandboxPreviewActor = new PengActor(world,currentSandboxConfig, 10, 6, 0.5f, BodyType.StaticBody);
					break;
				case WOOD_TNT:
	    			sandboxPreviewActor = new TntActor(world, 10, 6, 0.5f,BodyType.StaticBody);
					break;
				default:
	    			sandboxPreviewActor = new BlockActor(world,currentSandboxConfig, 10, 6, currentSandboxRotation, BodyType.StaticBody);
	    			sandboxPreviewActor.body.setGravityScale(0);
					break;
				}
				s.addActor(sandboxPreviewActor);
    		}
    		
    		// O start physics
    		if(Gdx.input.isKeyPressed(Keys.O) ) 
    		{
    			world.continuePhysics();
    		}
    		// R rotates
    		if(Gdx.input.isKeyPressed(Keys.R) ) 
    		{
    			currentSandboxRotation += MathUtils.degreesToRadians * 90;
    		}
    		// C clears stage defunct
    		if(Gdx.input.isKeyPressed(Keys.C) ) 
    		{
    			for (Actor a: s.getActors())
    			{
//    				if (a instanceof Group) {
//    					Group ga = (Group) a;
//						ga.clear();
//					}
    				if (a instanceof PhysicsActor) {
    					PhysicsActor ga = (PhysicsActor) a;
						ga.remove();
					}
    			}
    			
    			Array<Body> bodies = new Array<>();
				world.box2dWorld.getBodies(bodies);
				Array<Fixture> fixtures = new Array<>();
				world.box2dWorld.getFixtures(fixtures);
				// clears the ground too :(
				for (Body body : bodies)
				{
					Filter filter = body.getFixtureList().get(0).getFilterData();
					if (filter.categoryBits != CollisionFilterMasks.GROUND)
						world.box2dWorld.destroyBody(body);
				}
    		}
    		// P prints them all
    		if(Gdx.input.isKeyPressed(Keys.P)) 
    		{
				System.out.println("-------------------------------------------"); 
    			for (Actor a: s.getActors())
    			{
    				if (a instanceof PhysicsActor) {
						PhysicsActor pa = (PhysicsActor) a;
						if (pa.getBodyType()==BodyType.DynamicBody)
							System.out.println(pa.toJavaString());
					}
    			}
				System.out.println("-------------------------------------------"); 
    		}
    		
    		// A shows an achievement popup
    		if(Gdx.input.isKeyPressed(Keys.A)) 
    		{
				GameManager.getInstance().currentGameScreen.spawnAchievementPopup(Achievement.ACH_BEGINNER_STREAK);
    		}
    		
    		if(Gdx.input.isTouched() &&  Gdx.input.justTouched()) 
    		{
    			switch (currentSandboxConfig) {
				case TARGET_DORK:
	    			s.addActor(new DorkActor(world,currentSandboxConfig, x, y, 0.5f, BodyType.DynamicBody));
					break;

				case TARGET_PENG:
	    			s.addActor(new PengActor(world,currentSandboxConfig, x, y, 0.5f, BodyType.DynamicBody));
					break;

				case WOOD_TNT:
	    			s.addActor(new TntActor(world, x, y, 0.0f));
					break;

				default:
	    			s.addActor(new BlockActor(world,currentSandboxConfig, x, y, currentSandboxRotation, BodyType.DynamicBody));
					break;
				}
    		}
    		
		
    	}
          
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
  
        // box2d debug renderering (optional)  
        //camera. zoom*=0.999f;
        updateZoomAnimation();
        camera.update();

        if (GameManager.DEBUG)
        	renderer.render(world.box2dWorld, camera.combined);  
         
        // game stage rendering  
        world.stage.draw();  
    }  
    
    private void updateZoomAnimation()
    {
    	if (GameManager.DEBUGZOOM)
    		camera.zoom =4.5f;
    	final float zoomStep = 0.01f;
    	if (camera.zoom==wantedZoom)
    		return;
    	
    	if (camera.zoom>wantedZoom)
    	{
    		camera.zoom-=zoomStep;
    		if (camera.zoom < wantedZoom)
    			camera.zoom=wantedZoom;
    		return;
    	}
    	
    	if (camera.zoom<wantedZoom)
    	{
    		camera.zoom+=zoomStep;
    		if (camera.zoom > wantedZoom)
    			camera.zoom=wantedZoom;
    		return;
    	}
    }
    
    public void animateZoomTo(float newZoom)
    {
    	wantedZoom=newZoom;
    }

	public OrthographicCamera getCamera() {
		return camera;
	}

	@Override
	public void dispose() {
		world.dispose();
		renderer.dispose();
	}
}  
