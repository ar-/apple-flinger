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

public class MissionM_2_8 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		// sling holder
		final float sx = 4.7f;
		final float sy = 1.9f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.4f,0.1f, BodyType.StaticBody)); 
		
		SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
		group.addActor(slingshot1);

		// stones
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 1.5830871f, 2.123489f, 0.65f, 0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 1.9217669f, 2.3190053f, 0.1f, 0.5f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 1.2385637f, 2.319807f, 0.1f, 0.5f, BodyType.StaticBody));

		group.addActor (new BlockActor(world, MaterialConfig.STONE, 2.964104f, 2.1304505f, 0.65f, 0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 3.2957478f, 2.3330486f, 0.1f, 0.5f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 2.6027503f, 2.3219562f, 0.1f, 0.5f, BodyType.StaticBody));
		
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 2.264104f, 1.1304505f, 0.65f, 0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 2.5957478f, 1.3330486f, 0.1f, 0.5f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 1.9027503f, 1.3219562f, 0.1f, 0.5f, BodyType.StaticBody));
		
		
		// wood on ground and between stones
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 0.81564313f, 0.19518821f, 2.254103E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.753155f, 0.1957379f, 9.3705556E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.6627908f, 0.19622803f, -2.3321716E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.5533392f, 0.1962147f, 5.109843E-6f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.1789913f, 0.408631f, 5.2097713E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.2440255f, 0.4080642f, -3.425065E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.1139091f, 0.406314f, 0.00125635f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.309681f, 2.8590314f, -1.6098553f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.578125f, 2.8466935f, -1.5628649f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.9218748f, 2.8440473f, -1.5813004f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.2562504f, 2.8445733f, -1.5782677f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.5968752f, 1.7261789f, -1.5708815f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.8937501f, 1.7152348f, -1.5682789f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.5968752f, 1.9349709f, -1.5707966f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.89375f, 1.925596f, -1.5707957f, BodyType.DynamicBody));
		
		// enemies
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.5843754f, 2.4284883f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.9624999f, 2.435446f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.25f, 1.43545f, 0.5f));
		
		return group;
	}

}
