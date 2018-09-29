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

public class MissionM_2_5 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();

		// sling holder
		final float sx = 1.7457057f;
		final float sy = 1.9315788f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
        group.addActor(slingshot1);
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.5916774f, 0.5901383f, -1.5694107f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.9076982f, 0.5901027f, -1.5692471f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.737854f, 0.5901222f, -1.5692571f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.7614539f, 0.5900992f, -1.5692424f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.8840532f, 0.5901075f, -1.569168f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.615213f, 0.5900989f, -1.5692314f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.0302632f, 0.5901079f, -1.5691279f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.468943f, 0.5901075f, -1.5691522f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.1764839f, 0.5901082f, -1.5691227f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.322712f, 0.59010863f, -1.5691289f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.4105084f, 1.1958433f, -3.141571f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.1535811f, 0.590135f, -1.5702754f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.299504f, 0.59012985f, -1.569913f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.4455562f, 0.5901259f, -1.5696155f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.8621513f, 0.58960825f, -1.5710803f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.007844f, 0.58978856f, -1.5705539f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.3361933f, 1.2016563f, 0.018676948f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.9740058f, 1.8049095f, 1.5475186f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.4072416f, 1.8108329f, 1.548541f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.5518124f, 1.8137336f, 1.5489224f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.8590353f, 1.8027016f, 1.550227f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.712991f, 1.8027039f, 1.5502423f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.6964903f, 1.8166275f, 1.549281f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.5671704f, 1.8027033f, 1.5502652f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.4215615f, 1.8027016f, 1.5502576f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.8412602f, 1.8180056f, 1.5496014f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.2761607f, 1.8027043f, 1.5502118f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.9858296f, 1.8027179f, 1.549899f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.1308846f, 1.8027046f, 1.5502118f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.1183374f, 1.8049988f, 1.5480374f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.262766f, 1.8079103f, 1.5485257f, BodyType.DynamicBody));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.540187f, 0.3050014f, 0.5f));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.0030189f, 0.19568886f, 3.1412406f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.0223781f, 0.4720166f, 3.1405387f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.0238736f, 0.7484879f, 3.14047f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.0249965f, 1.0250841f, 3.1410804f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.3534282f, 1.2855022f, -3.0453947f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 0.8881704f, 1.3891522f, -3.0570753f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.0637885f, 1.601405f, 2.7823462f, BodyType.DynamicBody));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.6105376f, 1.6276091f, 0.5f));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.542138f, 0.9021744f, 0.5f));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.460056f, 0.19731718f, -3.1400754f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.457849f, 0.47706902f, 3.1412635f, BodyType.DynamicBody));
        group.addActor (new TntActor(world, 5.000129f, 0.32749528f, 2.0667198E-4f));
		return group;
	}

}
