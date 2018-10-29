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
import com.gitlab.ardash.appleflinger.actors.TntActor;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.missions.Mission.StageFiller;

public class MissionM_2_12 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		// sling holder
		final float sx = 1.7f;
		final float sy = 1.9f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.25f,0.1f, BodyType.StaticBody)); 
		
		SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
		group.addActor(slingshot1);

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.3125029f, 0.19403557f, 4.0205556E-4f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 2.317794f, 0.5995937f, 8.645947E-4f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.3125806f, 1.0065798f, 0.0014291275f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.6580787f, 0.19321753f, 0.0028366325f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.6575673f, 0.4644591f, 0.009238694f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.88176f, 0.19554669f, 0.0012016384f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.872713f, 0.47143638f, 0.0043633278f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 3.6281545f, 0.8689583f, 0.012539808f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.8710694f, 0.747545f, 0.0070631444f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 4.860766f, 1.1554478f, 0.008739638f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.847112f, 1.5640821f, 0.011396013f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.6087506f, 1.2752f, 0.013881329f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.295263f, 1.3918574f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 3.574052f, 1.6598623f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 4.8265886f, 1.9499617f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.291464f, 0.32633853f, -1.5655689f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.2846227f, 0.863746f, -1.5543804f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.2840276f, 1.4017804f, -1.5431671f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.9033802f, 0.32751f, -1.5708733f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.90407f, 0.8699188f, -1.5600986f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 0.81442773f, 0.32577527f, -1.5715141f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 0.8196249f, 0.7301386f, -3.457864E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.8086239f, 1.1337273f, 8.577506E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.2425804f, 1.9404912f, -1.5261254f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 0.8062359f, 1.6499552f, 0.5f));
		
		return group;
	}

}
