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

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;

public class Ground extends BlockActor {  

    @SuppressWarnings("static-access")
	public Ground(GameWorld world) {  
    	//super(world,MaterialConfig.METAL,world.UNIT_WIDTH/2f,0, world.UNIT_WIDTH*2,0.1f,BodyType.StaticBody);
    	super(world,MaterialConfig.INVISIBLE,world.UNIT_WIDTH/2f,0, world.UNIT_WIDTH*2,0.1f,BodyType.StaticBody);
    	setDamagable(false); // very important!!
    	
        Filter filter = body.getFixtureList().get(0).getFilterData();
        filter.categoryBits = CollisionFilterMasks.GROUND;
        body.getFixtureList().get(0).setFilterData(filter);

    	//super(world,MaterialConfig.METAL,1,world.UNIT_HEIGHT/2,world.UNIT_WIDTH/2,world.UNIT_HEIGHT/2,BodyType.StaticBody);
    	//super(world,MaterialConfig.METAL,1.5f,1.5f,1,1,BodyType.StaticBody);
    	//super(world,MaterialConfig.METAL,1,1,1,1,BodyType.StaticBody);
    }  
}  
