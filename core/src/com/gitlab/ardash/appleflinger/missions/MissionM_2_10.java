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

public class MissionM_2_10 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		// sling holder
		final float sx = 1.7f;
		final float sy = 0.5f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.3f,0.1f, BodyType.StaticBody)); 
		
		SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
		group.addActor(slingshot1);

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.4707003f, 0.19471568f, 0.003350113f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.9280362f, 0.19711284f, 0.0032511814f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.014246f, 0.19624273f, 3.0317967E-6f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.3876307f, 0.1962868f, 1.4296197E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.559874f, 0.1962494f, 3.2554226E-6f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.5214431f, 0.60520303f, -1.5695273f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.1236453f, 0.60513514f, -1.569656f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.244097f, 0.40855607f, -0.0012447039f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.312378f, 0.551608f, -0.0012828509f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.5512857f, 0.40812275f, 1.41462215E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.399521f, 0.5543673f, 4.8478498E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.3322835f, 0.69754374f, 0.0010937055f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 3.7374225f, 0.58242667f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.9460158f, 0.58348167f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.3507977f, 0.6014294f, 1.5824536f, BodyType.DynamicBody));
		
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.9229434f, 0.94712996f, -0.001411753f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.018337f, 0.9398116f, -0.0136910435f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.100608f, 1.2837362f, 1.5757148f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.4266026f, 1.0916559f, 3.1398864f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 4.0473237f, 1.6870255f, 3.1322799f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.1298437f, 1.2362447f, 3.1357017f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.2432542f, 1.4391035f, 3.127706f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.266274f, 1.7019757f, 3.1244597f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.6481164f, 1.8998693f, 3.1239562f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.680726f, 0.73736393f, 1.5596734f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.513032f, 1.076558f, -0.00441858f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 3.656294f, 1.4116647f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 3.643535f, 2.2202873f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 4.486259f, 1.3935757f, 0.5f));
		
		return group;
	}

}
