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

public class MissionM_2_18 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		// sling holder
		final float sx = 3.9f;
		final float sy = 2.9f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.4f,0.1f, BodyType.StaticBody)); 
		SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
		group.addActor(slingshot1);

		//structure behind the sling
		float bx = 1.6f;
		float by = 2.0f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by,0.1f,by*2f, BodyType.StaticBody)); 
		
		bx += 0.2f; by = 1.5f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by,0.1f,by*2f, BodyType.StaticBody)); 

		bx += 0.8f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by,0.1f,by*2f, BodyType.StaticBody)); 

		// under the sling
		bx += 1.15f; by = 1.4f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by,0.1f,by*2f, BodyType.StaticBody)); 
		bx += 0.3f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by,0.1f,by*2f, BodyType.StaticBody)); 

		// roof behind sling
		bx = 1.8f; by = 3.7f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by,0.1f,0.75f, BodyType.StaticBody)); 
		bx += 0.8f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by,0.1f,0.75f, BodyType.StaticBody));
		
		bx -= 0.5f; by += 0.35f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by,1.1f,0.1f, BodyType.StaticBody)); 

		// socket in front of sling
		bx = 5.5f; by = 0.55f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by, 0.1f,by*2f, BodyType.StaticBody)); 
		bx -= 0.35f; by += 0.55f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by, 0.75f,0.1f, BodyType.StaticBody)); 
		
		
		// wood
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.190454f, 3.08056f, 2.0306883E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.1905837f, 3.2264006f, 9.023282E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.725364f, 0.12550128f, 0.0017356139f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.873886f, 1.0496806f, 1.7500225f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.9170642f, 0.12730682f, 7.9028227E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.4720063f, 0.8262885f, 1.6302953f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.056752f, 0.1277278f, 0.0021695883f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.0174122f, 0.8536309f, 1.6861846f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.196536f, 0.1278969f, 0.003450232f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.3312516f, 0.95500475f, -1.4813238f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.173411f, 0.9024482f, -2.562875f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.332637f, 0.8163611f, 1.6436235f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.620657f, 1.051206f, -1.5708109f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.6091695f, 0.12746431f, 4.5966727E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.625166f, 0.36666042f, 0.028128471f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.5966027f, 1.1911943f, -1.316397E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.4729636f, 0.9658723f, 0.056271043f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.8173585f, 0.26061264f, -0.8102988f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.7368593f, 1.0246084f, 0.17491272f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.8025553f, 0.89172345f, 0.17748907f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.4020724f, 0.1277528f, 0.004237918f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.619404f, 0.8456f, 1.5707479f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.4255428f, 0.2681542f, 0.020400673f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.1558692f, 0.27370977f, 0.004066468f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.0692682f, 0.49195525f, -1.3817272f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.4069047f, 0.6809081f, 0.05228029f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.0795815f, 0.71067566f, -3.0844178f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.7304292f, 0.47437653f, 1.5710552f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.6193566f, 0.57436854f, 1.5709903f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.2173805f, 0.4968686f, 1.7615764f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.8617375f, 0.69103247f, 1.7489192f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.930444f, 0.42882696f, 1.7421329f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.314584f, 1.15875f, 1.6606205f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.1556118f, 1.1259954f, 1.7905719f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.017662f, 1.0683053f, 1.7833393f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.4048562f, 0.4738118f, 1.6069744f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.9051664f, 1.4359955f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.1625009f, 3.5471733f, 0.5f));
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.2431283f, 0.13525859f, -4.7853635E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.246875f, 0.284971f, -2.6077234E-7f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 5.090625f, 0.30499944f, 0.5f));


		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.399995f, 4.3773036f, 6.4808497E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.1562047f, 4.7197585f, 0.0044502444f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.791017f, 1.2304147f, 0.004043828f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.64024f, 1.3752447f, 0.0040187114f, BodyType.DynamicBody));
		
		// upper peng
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.8374996f, 4.359267f, 0.5f));

		return group;
	}

}
