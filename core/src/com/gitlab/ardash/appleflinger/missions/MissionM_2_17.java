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
package com.gitlab.ardash.appleflinger.missions;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.actors.BlockActor;
import com.gitlab.ardash.appleflinger.actors.PengActor;
import com.gitlab.ardash.appleflinger.actors.SlingShotActor;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.missions.Mission.StageFiller;

public class MissionM_2_17 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		// sling holder
		final float sx = 4.7f;
		final float sy = 0.5f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.4f,0.1f, BodyType.StaticBody)); 

		
		final float bx = 2.3f;
		float by = 0.3f;
		float d = 0.9f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx+d/2f, by,0.1f,0.1f, BodyType.StaticBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx-d/2f, by,0.1f,0.1f, BodyType.StaticBody)); 
		by += 0.99f;
		d = 0.5f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx+d/2f, by,0.1f,0.1f, BodyType.StaticBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx-d/2f, by,0.1f,0.1f, BodyType.StaticBody)); 
		by += 0.99f;
		d = 0.9f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx+d/2f, by,0.1f,0.1f, BodyType.StaticBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx-d/2f, by,0.1f,0.1f, BodyType.StaticBody)); 
		by += 0.99f;
		d = 0.5f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx+d/2f, by,0.1f,0.1f, BodyType.StaticBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx-d/2f, by,0.1f,0.1f, BodyType.StaticBody)); 

		
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.3062499f, 0.421875f, 0.0f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.296659f, 0.7485696f, 0.5f));
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.2781544f, 1.4204098f, 6.554527E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.2966866f, 2.409818f, -1.7139022E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.2874827f, 3.4004657f, 4.51332E-6f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.2968209f, 1.7399046f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.3064275f, 2.7286654f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.315625f, 3.7202556f, 0.5f));
//		
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.2970285f, 1.2303884f, 8.379534E-5f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.2966135f, 2.0298278f, -1.6995217E-4f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.3157794f, 2.8304124f, 1.2981922E-4f, BodyType.DynamicBody));
//		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.306093f, 1.5497706f, 0.5f));
//		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.306476f, 2.348697f, 0.5f));
//		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.306078f, 3.1499074f, 0.5f));
		
		SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
		group.addActor(slingshot1);


		
		return group;
	}

}
