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

public class MissionM_2_16 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		// sling holder
		final float sx = 2.0f;
		final float sy = 2.1f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.4f,0.1f, BodyType.StaticBody)); 
		
		SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
		group.addActor(slingshot1);

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 0.7672526f, 0.1870518f, -0.0020797804f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 0.76954806f, 0.45401734f, -0.006684741f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.75154465f, 0.6628097f, -0.007933304f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.7719812f, 0.80456495f, -0.008008568f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.165292f, 0.94328153f, -0.006428498f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 0.390466f, 0.9498829f, -0.014505064f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.1522083f, 1.5475085f, 1.5650406f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.47204417f, 1.5520155f, 1.545969f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.8006435f, 2.1552677f, -0.007530769f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.1669085f, 2.2953749f, -0.007408698f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 0.4704796f, 2.3005176f, -0.007383903f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.5764976f, 0.12638041f, 0.0027345817f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.246112f, 0.12737253f, 0.0036634607f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.8987854f, 0.26764286f, 0.005042474f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.9013011f, 0.40851894f, 0.008878151f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.896311f, 0.54897326f, 0.0117716f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.889708f, 0.6897323f, 0.014897744f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.8923573f, 0.8902805f, 0.025161186f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.873229f, 1.0902607f, 0.035478037f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.8700838f, 1.230496f, 0.03652517f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 0.80524844f, 1.1254318f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 0.8129092f, 2.4752245f, 0.5f));
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.2260125f, 1.5860118f, -1.5345877f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.446782f, 1.5574096f, -1.5337142f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.8321526f, 1.914426f, 0.036588304f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.5326684f, 2.046393f, 0.036611196f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.1289542f, 2.0699203f, 0.02113878f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.8384748f, 1.5496501f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.8209336f, 2.234386f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.29117f, 2.0740294f, 0.036351793f, BodyType.DynamicBody));
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.593055f, 0.32633224f, 4.938917E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.565906f, 0.6662431f, 8.658336E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.5837135f, 0.804449f, 0.0016920492f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.9186115f, 1.1428124f, -1.5721937f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.2453046f, 1.1414812f, -1.5721327f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.5564117f, 1.4798629f, 0.0020358404f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.5635076f, 1.6188433f, 0.0024859747f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.897424f, 2.2239714f, -1.5689608f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.242405f, 2.2224581f, -1.5692126f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.5554805f, 2.8280883f, 0.0022305807f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.205502f, 2.9734704f, 0.0057095843f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.9260745f, 2.9743247f, 0.0019711815f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 4.5805187f, 1.1279253f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 4.569479f, 1.9389039f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 4.5668716f, 3.1478312f, 0.5f));
		
		return group;
	}

}
