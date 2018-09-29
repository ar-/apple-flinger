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

public class MissionM_2_3 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();

		// sling holder
		final float sx = 1.7457057f;
		final float sy = 1.9315788f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
        group.addActor(slingshot1);
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.0530264f, 0.32694656f, 0.001330654f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.0537956f, 0.6686902f, 0.0048786094f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.546941f, 0.81621945f, -0.0018347917f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.077709f, 0.327916f, 1.5731894f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.4768679f, 0.8081611f, -3.1227064f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.0373113f, 1.0287861f, 3.1399624f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.02491f, 1.305851f, 3.1400902f, BodyType.DynamicBody));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.822415f, 1.1359446f, 0.5f));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.2244111f, 1.1233804f, 0.5f));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.420438f, 0.19304532f, 1.5702724f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.8496227f, 0.28838038f, 2.8295372f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 5.52275f, 0.19310087f, 1.5709382f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 5.066333f, 0.28367218f, 0.30246237f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.371681f, 0.32390675f, -0.1177723f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.4384847f, 0.46204063f, -0.11770746f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.2486696f, 0.70013547f, 0.30211145f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.6704195f, 0.71149623f, -0.31138346f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.93179f, 0.5694526f, 0.3248432f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.766069f, 0.5660331f, -0.124207705f, BodyType.DynamicBody));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 4.4946475f, 0.78166646f, 0.5f));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.836256f, 0.8955891f, -1.7005258f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.152044f, 0.83799326f, -1.6891274f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.000186f, 1.1985576f, 3.0200503f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.5819974f, 1.2713592f, 3.0120852f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 5.0706987f, 1.355006f, -0.12912214f, BodyType.DynamicBody));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 4.3361154f, 1.6265801f, 0.5f));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.705167f, 1.5459086f, -1.6931099f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.0408473f, 1.5036277f, -1.7006477f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.85626f, 1.6713536f, 3.0259936f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.290565f, 0.32433844f, 3.1399157f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.33737674f, 0.8576874f, 3.13752f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.3284761f, 1.3910903f, 3.1379015f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.3191507f, 1.9251028f, 3.137276f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.34749398f, 2.4602427f, 3.1365588f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.34719953f, 2.9974515f, 3.1368792f, BodyType.DynamicBody));
		return group;
	}

}
