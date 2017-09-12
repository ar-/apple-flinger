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
package com.ardash.appleflinger.missions;

import com.ardash.appleflinger.GameWorld;
import com.ardash.appleflinger.actors.BlockActor;
import com.ardash.appleflinger.actors.SlingShotActor;
import com.ardash.appleflinger.global.MaterialConfig;
import com.ardash.appleflinger.missions.Mission.StageFiller;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Group;

public class MissionEmpty implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		float height;
		
		// sling holder
		group.addActor( new BlockActor(world,MaterialConfig.STONE,5.2f, 5.1f,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(5.2f, 5.1f);
        group.addActor(slingshot1);
        
        //group.addActor(new TextActor("AAA"));
        //group.addActor(new PopPointActor("AAA"));
		return group;
	}

}
