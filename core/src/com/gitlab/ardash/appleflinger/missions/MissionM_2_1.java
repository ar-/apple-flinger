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

public class MissionM_2_1 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();

		// sling holder
		final float sx = 1.7457057f;
		final float sy = 1.9315788f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
        group.addActor(slingshot1);
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.30006492f, 0.32749724f, 8.787287E-6f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.33401018f, 0.8675003f, 6.316201E-5f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.30988657f, 1.407494f, 7.739666E-5f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.3245281f, 1.947493f, 8.4047315E-5f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.8406751f, 0.32721505f, -1.2842279E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.3807863f, 0.327618f, -4.5475995E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.9208944f, 0.32799786f, -8.796408E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.460948f, 0.32861206f, -0.0010900071f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.001236f, 0.32748848f, -1.7924096E-5f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.4163266f, 0.8716955f, 0.0015145341f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.4711857f, 0.8702018f, -0.0072105248f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.5413246f, 0.3275751f, 3.0898117E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.5059903f, 0.8677448f, 0.0010963839f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.4877098f, 1.4077109f, 0.0011441333f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.4873836f, 1.9477038f, 0.0011666961f, BodyType.DynamicBody));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.988516f, 0.8449812f, 0.5f));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.9499947f, 0.84547234f, 0.5f));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 0.8906738f, 0.8443046f, 0.5f));        
		return group;
	}

}
