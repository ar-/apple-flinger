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

public class MissionM_2_13 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		// sling holder
		final float sx = 2.1f;
		final float sy = 2.9f;
		final float w = 0.7f;
		final float opening = 0.5f;
		
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy, w,0.1f, BodyType.StaticBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx-w/2f, sy/2f,0.1f,sy, BodyType.StaticBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx+w/2f, sy/2f+opening/2f,0.1f,sy-opening, BodyType.StaticBody)); 
		
		SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
		group.addActor(slingshot1);

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.3421814f, 0.12871939f, 0.0015450601f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.3480034f, 0.27099413f, 0.005164258f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.3477838f, 0.41337377f, 0.008809562f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.109755f, 0.62038344f, 0.011496221f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.115277f, 0.89504635f, 0.011910027f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.1143224f, 1.3020294f, 0.0122652855f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.1010916f, 1.6429188f, 0.012633552f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.1104858f, 1.786165f, 0.013155551f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.9491282f, 1.9887817f, 0.0149975475f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.2424755f, 1.9926769f, 0.011324759f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.0906107f, 2.1972513f, 0.012414436f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.093635f, 2.518517f, 0.5f));
		return group;
	}

}
