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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets.SpriteGroupAsset;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.helpers.Calculator;

public class BlockActor extends PhysicsActor {  
	   
    private final float heightRadius;
    private final float widthRadius;

    /**
     * shape of block can only be set initially
     * @param world
     * @param x center
     * @param y center
     * @param heigth
     * @param width
     */
    public BlockActor(GameWorld world, MaterialConfig mc, float x, float y, float awidth, float aheigth, BodyType bodyType) { 
    	super(world, bodyType, mc);
    	widthRadius = awidth / 2f;
    	heightRadius = aheigth / 2f;

        Texture tex = mc.texture;  
        SpriteGroupAsset spa = mc.spriteGroup;
        if (tex != null) // invisible is possible
        {
        	this.setDrawable(new TextureRegionDrawable(new TextureRegion(tex)));
        }
        else if (spa !=null && spa.size() >=1)
        {
        	this.setDrawable(new TextureRegionDrawable(spa.get(0).get()));
        }
        
        resetPhysics(mc, x, y, 0f, bodyType);  
        body.setGravityScale(0); // 0 gravity for testing
        
        // generate actor  
        this.setPosition(body.getPosition().x, body.getPosition().y); // set the actor position at the box2d body position 

        this.setSize(awidth*1.02f, aheigth*1.02f); // scale actor to body's size  
        this.setScaling(Scaling.stretch); // stretch the texture  
        this.setAlign(Align.center);  
    }

    /**
     * this constructor gets width and height from the texture
     * @param world
     * @param mc
     * @param x
     * @param y
     * @param bodyType
     */
    public BlockActor(GameWorld world, MaterialConfig mc, float x, float y, BodyType bodyType) { 
    	this(world,mc, x, y, getWidthFromMCTexture(mc),getHeightFromMCTexture(mc), bodyType);
    }
    
    public BlockActor(GameWorld world, MaterialConfig mc, float x, float y, float rotation, BodyType bodyType) { 
    	this(world,mc, x, y,bodyType);
    	//this.setRotation(rotation);
    	setPhysicalRotation(rotation);
    }


	/**
	 * @param mc
	 * @return
	 */
	private static float getWidthFromMCTexture(MaterialConfig mc) {
		if (mc.texture!=null)
		{
			return Calculator.convPixelToSceneUnits(mc.texture.getWidth());
		}
		if (mc.spriteGroup.get(0).get()!=null)
		{
			return Calculator.convPixelToSceneUnits(mc.spriteGroup.get(0).get().getWidth());
		}
		throw new RuntimeException("Cannot retrive width if no texture is given."); 
	}
	
	/**
	 * @param mc
	 * @return
	 */
	private static float getHeightFromMCTexture(MaterialConfig mc) {
		if (mc.texture!=null)
		{
			return Calculator.convPixelToSceneUnits(mc.texture.getHeight());
		}
		if (mc.spriteGroup.get(0).get()!=null)
		{
			return Calculator.convPixelToSceneUnits(mc.spriteGroup.get(0).get().getHeight());
		}
		throw new RuntimeException("Cannot retrive height if no texture is given."); 
	}

	/**
	 * @param world
	 * @param mc
	 * @param x
	 * @param y
	 * @param bodyType
	 */
    @Override
    protected void resetPhysics(MaterialConfig mc, float x,
			float y, float angle, BodyType aBodyType) {
		// generate bob's box2d body  
        PolygonShape recShape = new PolygonShape();  
        recShape.setAsBox(widthRadius,heightRadius);
        //recShape.setAsBox(widthRadius,heightRadius,new Vector2(x, y),0);
//        ArrayList<Vector2> corners = new ArrayList<Vector2>();
//        corners.add(new Vector2(x, y));
//        corners.add(new Vector2(x, y+aheigth));
//        corners.add(new Vector2(x+awidth, y+aheigth));
//        corners.add(new Vector2(x+awidth, y));
//        recShape.set(corners.toArray(new Vector2[corners.size()]));
        //recShape.setRadius(radius);
          
        BodyDef bodyDef = new BodyDef();  
        bodyDef.type = aBodyType;  
        bodyDef.position.x = x;  
        bodyDef.position.y = y;  
        bodyDef.angle = angle;
        bodyDef.linearDamping = mc.linearDamping;  
        bodyDef.angularDamping = mc.angularDamping;  
        
          
        this.body = world.box2dWorld.createBody(bodyDef);  
          
        Fixture fix = body.createFixture(recShape, 50);  
        fix.setDensity(mc.density);  
        fix.setFriction(mc.friction);  
        fix.setRestitution(mc.restitution);  
        Filter filter = new Filter();
		fix.setFilterData(filter);  // TODO activate filter
        // add actor instance to userdata, to modify it later (eg. damage application)
		fix.setUserData(this);

        recShape.dispose();
	}
    
    @Override  
    public void act(float delta) {  
        // here we override Actor's act() method to make the actor follow the box2d body  
        super.act(delta);  
        setOrigin(widthRadius, heightRadius);  
        setRotation(MathUtils.radiansToDegrees * body.getAngle());  
        setPosition(body.getPosition().x-widthRadius, body.getPosition().y-heightRadius);  
    }

    @Override
    public String toJavaString()
    {
    	// initial pos is always set like this: pos - half of raduis
    	final float rr = getPhysicalRotation();
    	return "group.addActor (new BlockActor(world, MaterialConfig."+mc+", "+(body.getPosition().x)+"f, "+(body.getPosition().y)+"f, "+rr+"f, BodyType."+getBodyType()+"));";
		//return "PhysicsActor(world, "+getBodyType()+", MaterialConfig."+mc+")";
    }

	@Override
	protected int getDamageToPointsFactor() {
		return 10;
	}

}  
