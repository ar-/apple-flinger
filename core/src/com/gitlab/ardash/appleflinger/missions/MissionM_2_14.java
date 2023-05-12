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

public class MissionM_2_14 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		// sling holder
		final float sx = 2.0f;
		final float sy = 2.1f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.4f,0.1f, BodyType.StaticBody)); 
		
		SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
		group.addActor(slingshot1);

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.312945f, 0.3279697f, 9.093629E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.853178f, 0.32747793f, 1.7667362E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.3933387f, 0.32758614f, -3.3337658E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.9334702f, 0.32750157f, 1.0558308E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.47347f, 0.327676f, 1.2965662E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.0141377f, 0.32749987f, 2.1844226E-8f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.9186f, 0.86751807f, 8.5058426E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.771354f, 0.3273288f, 4.9076945E-4f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 2.8526807f, 0.86743057f, 5.72485E-5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.7668571f, 0.8669594f, 0.0015934645f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.0256443f, 0.8675034f, 5.189267E-7f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.3063848f, 0.8454641f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 3.3904495f, 0.8452215f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 4.4645743f, 0.8451903f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.460019f, 1.2106565f, -4.149387E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.395004f, 1.2105895f, 1.0384723E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.3291202f, 1.2104979f, 6.748418E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 5.1282f, 1.2106345f, -4.4344415E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.6596f, 1.2079848f, 0.017934028f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.744036f, 1.556662f, 0.049337056f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.8434787f, 1.5536397f, 9.615817E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.8905663f, 1.5538111f, -5.251945E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.984609f, 1.5537655f, -4.453427E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.439676f, 1.5538049f, -7.9371675E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.718342f, 2.0937939f, -3.645583E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.722104f, 2.6337955f, -2.9425384E-5f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 4.1633897f, 2.0938818f, 2.7144898E-4f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 3.368989f, 1.5313905f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.3135035f, 1.53111f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.360346f, 1.8991797f, -0.0027334488f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.287943f, 1.9069985f, -0.019544842f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.8101497f, 2.248025f, -0.0011616311f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.177619f, 2.6338978f, 3.1679226E-4f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 3.362411f, 2.2198346f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.9569793f, 2.6522458f, 0.013347789f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.6827376f, 2.6507359f, -8.6304673E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.973703f, 2.9175646f, 0.013887918f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.6977687f, 2.9157307f, -9.224963E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.9061224f, 3.0459354f, 0.28023803f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.489718f, 3.0427182f, -6.244401E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.2229512f, 3.1700504f, 0.093449436f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.271276f, 3.3090706f, -0.112017795f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.1343638f, 0.53266555f, 0.95984536f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.9094454f, 0.51794124f, 0.90330976f, BodyType.DynamicBody));
		
		return group;
	}

}
