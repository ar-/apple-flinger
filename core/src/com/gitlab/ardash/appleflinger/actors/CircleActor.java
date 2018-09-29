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
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets.SpriteGroupAsset;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;

public class CircleActor extends PhysicsActor {  
	   
	protected final float RADIUS;
    /**
     * shape of block can only be set initially
     * @param world
     * @param x center
     * @param y center
     * @param heigth
     * @param width
     */
    public CircleActor(GameWorld world, MaterialConfig mc, float x, float y, float diameter, BodyType bodyType) { 
    	super(world, bodyType, mc);
    	RADIUS = diameter / 2f;
          
        Texture tex = mc.texture;  
        SpriteGroupAsset spa = mc.spriteGroup;
        if (tex != null) // invisible is possible
        {
        	this.setDrawable(new TextureRegionDrawable(new TextureRegion(tex)));
        }
        else if (spa !=null && spa.size()>=1)
        {
        	this.setDrawable(new TextureRegionDrawable(spa.get(0).get()));
        }

        // this must be called in every constructor of child classes of PhysicsActor
        resetPhysics(mc, x, y, 0f, bodyType);  
        
        // generate circle actor  
        //this.setPosition(body.getPosition().x-widthRadius, body.getPosition().y-heightRadius); // set the actor position at the box2d body position  
        this.setPosition(body.getPosition().x, body.getPosition().y); // set the actor position at the box2d body position  
        this.setSize(diameter, diameter); // scale actor to body's size  
        this.setScaling(Scaling.stretch); // stretch the texture  
        this.setAlign(Align.center);  
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
			float y, float angle, BodyType abodyType) {
		// generate bob's box2d body  
        CircleShape circle = new CircleShape();  
        circle.setRadius(RADIUS);  
          
        BodyDef bodyDef = new BodyDef();  
        bodyDef.type = abodyType;  
        bodyDef.position.x = x;  
        bodyDef.position.y = y;  
        bodyDef.angle = angle;
        bodyDef.linearDamping = mc.linearDamping;  
        bodyDef.angularDamping = mc.angularDamping;  
        
          
        this.body = world.box2dWorld.createBody(bodyDef);  

        Fixture fix = body.createFixture(circle, 50);  
        fix.setDensity(mc.density);  
        fix.setFriction(mc.friction);  
        fix.setRestitution(mc.restitution);  
        Filter filter = new Filter();
		fix.setFilterData(filter);  // TODO activate filter
        // add actor instance to userdata, to modify it later (eg. damage application)
		fix.setUserData(this);

		circle.dispose();
	}  
	
	@Override  
    public void act(float delta) {  
        // here we override Actor's act() method to make the actor follow the box2d body  
        super.act(delta);  
        setOrigin(RADIUS, RADIUS);  
        setRotation(MathUtils.radiansToDegrees * body.getAngle());  
        setPosition(body.getPosition().x-RADIUS, body.getPosition().y-RADIUS);  
    }

	@Override
	protected int getDamageToPointsFactor() {
		return 10;
	}


}  
