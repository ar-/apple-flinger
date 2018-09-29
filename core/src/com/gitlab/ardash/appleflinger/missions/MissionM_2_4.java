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

public class MissionM_2_4 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();

		// sling holder
		final float sx = 1.7457057f;
		final float sy = 1.9315788f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
        group.addActor(slingshot1);
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.2207198f, 0.1271439f, 6.430873E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.556728f, 0.12725729f, 6.0257793E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.8562828f, 0.12729193f, 8.2554633E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.1483061f, 0.1274491f, -4.143569E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.4281845f, 0.1273528f, -3.4972283E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.6983218f, 0.12726104f, -2.6442012E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.965049f, 0.12648448f, -0.0029681663f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.2605257f, 0.12579538f, -0.0021442617f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.5947948f, 0.124934465f, -0.0014338647f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.8931878f, 0.12480367f, -6.947322E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.4874372f, 0.2655218f, -0.005806009f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.4064713f, 0.27024838f, -9.493594E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 0.915097f, 0.12706842f, 5.745689E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.3392164f, 0.26970094f, 9.5971156E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.8821084f, 0.60554963f, 1.5648156f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.3470128f, 0.60870945f, 1.564602f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.8056974f, 0.61300486f, 1.570923f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.4449608f, 0.6111164f, 1.5789796f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.7662599f, 0.6118851f, 1.5687867f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.2811948f, 0.61256367f, 1.5716096f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.0033822f, 0.61246717f, 1.5701371f, BodyType.DynamicBody));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.1072779f, 0.59113896f, 0.5f));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.550928f, 0.950056f, -0.005610021f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.4090865f, 0.9514926f, 0.017053096f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.3411803f, 0.95546263f, 0.001283137f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.868291f, 1.0920632f, 1.4254256E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.036666f, 1.0971407f, 0.0033659616f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.3622868f, 1.0978658f, 0.0030493417f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.5967352f, 1.0985374f, 0.00263354f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.8405317f, 1.0991622f, 0.0020880383f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.1028461f, 1.0893946f, 0.017079799f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.3570707f, 1.0939127f, 0.01983401f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.6082773f, 1.0981423f, 0.017377343f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.8713374f, 1.1023692f, 0.0111365f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.1290882f, 1.0928774f, -1.5500383E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.3857708f, 1.0926574f, -3.152211E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.6009507f, 1.0925107f, -4.792531E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.5994685f, 1.235338f, 0.0032057443f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.5160413f, 1.2422682f, 0.008405177f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.4493219f, 1.2405717f, 0.004071681f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.390659f, 0.5628245f, -1.1009393f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.5560837f, 0.56326663f, -1.103415f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.4457852f, 0.49020827f, 0.80330795f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.23601215f, 0.4868451f, 0.79854345f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.8860695f, 1.3827411f, 0.0043768566f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.3239665f, 1.3798188f, 0.0054983776f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.6765976f, 1.5752615f, -1.5658745f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.5309293f, 1.5747006f, -1.5650047f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.6008606f, 1.9146605f, -3.1374865f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.6009269f, 2.059324f, -3.1373644f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.6099892f, 2.264527f, -3.1375322f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.6098154f, 2.466769f, -3.1375628f, BodyType.DynamicBody));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.9721925f, 1.5667893f, 0.5f));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.1897417f, 1.5586923f, 0.5f));

		return group;
	}

}
