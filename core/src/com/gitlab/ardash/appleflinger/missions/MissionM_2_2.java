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

public class MissionM_2_2 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();

		// sling holder
		final float sx = 1.7457057f;
		final float sy = 1.9315788f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
        group.addActor(slingshot1);
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.29585204f, 0.3274962f, 8.440216E-6f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.33055392f, 0.8674893f, 2.9481664E-5f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.29659936f, 1.4074836f, 3.7067057E-5f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, -0.4603081f, 0.3310106f, 3.138776f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.84052676f, 0.32749924f, 2.1816873E-6f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.3817146f, 0.32856703f, 0.001671194f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.921715f, 0.32820764f, 0.0016711587f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.4620903f, 0.32756448f, 2.4616253E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.002088f, 0.32757536f, 2.469684E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.4163929f, 0.8704832f, 1.1058669E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.4710948f, 0.8675664f, 2.4604137E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.5420861f, 0.3275703f, 2.4711122E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.5062015f, 0.8676475f, -1.2457105E-5f, BodyType.DynamicBody));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.9887016f, 0.8450719f, 0.5f));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.9472197f, 0.84576565f, 0.5f));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 0.8553181f, 0.844999f, 0.5f));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.5844548f, 1.2700938f, 2.447186E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.647332f, 1.2713505f, 0.009433967f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.5626123f, 1.2753172f, -0.01814559f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.9324338f, 1.3962735f, -0.37019646f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.923602f, 1.3953582f, -0.3684548f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.94557315f, 1.5049974f, -0.6065689f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 5.0251017f, 0.12976007f, 2.6927108E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.7586055f, 0.34074038f, 6.0379197E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.300306f, 0.34123695f, 3.2297248E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.714798f, 0.5517616f, 2.2238854E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 5.250795f, 0.55251896f, 3.8191577E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.9784694f, 0.7635148f, 0.0016484594f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 4.968567f, 1.0341959f, 0.0020415278f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.959248f, 1.238952f, 0.002609662f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.959347f, 1.3815993f, 0.0028216813f, BodyType.DynamicBody));

		return group;
	}

}
