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

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.actors.BlockActor;
import com.gitlab.ardash.appleflinger.actors.DorkActor;
import com.gitlab.ardash.appleflinger.actors.SlingShotActor;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.missions.Mission.StageFiller;

public class MissionM_1_16 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		// some static mid-air holders
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 1.4075004f, 1.471875f,0.1f,0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 2.0437498f, 1.471875f,0.1f,0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 2.2124999f, 2.00625f,0.1f,0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 1.3500004f, 2.025f,0.1f,0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 4.0218754f, 1.4437499f,0.1f,0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 4.5f, 1.4437499f,0.1f,0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 3.8343751f, 2.00625f,0.1f,0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 4.753125f, 2.034375f,0.1f,0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 3.5249999f, 4.303125f,0.1f,0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 4.0687504f, 4.2937503f,0.1f,0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 3.3562498f, 4.790625f,0.1f,0.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 4.21875f, 4.8f,0.1f,0.1f, BodyType.StaticBody));
		
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.265623f, 1.5743568f, -6.10935E-6f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.7999984f, 1.6023868f, 2.1990007E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.7905269f, 4.430865f, -0.019724596f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.1501064f, 5.0122986f, -1.9995561f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 3.6745064f, 4.7538457f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.3656325f, 4.9874015f, -1.5707841f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.3750172f, 5.2593746f, -1.5708184f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.2218773f, 1.8111327f, -1.5718827f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.3500025f, 1.814979f, -1.5707841f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.3500429f, 2.219948f, -1.5707841f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.2030036f, 2.2000237f, -1.5824761f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.8351252f, 1.7843635f, -1.5756173f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.63705f, 2.1836927f, -1.7304139f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 4.209337f, 1.894898f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.7556332f, 2.4205635f, -0.024521986f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.8249998f, 2.1337092f, -5.070363E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.2037914f, 2.55267f, -0.024624983f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.3501467f, 2.5738986f, -0.025487104f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.7812865f, 1.9224378f, 0.5f));
		
		// sling holder
		final Vector2 slingBase = new Vector2(1.75f,3);
		group.addActor( new BlockActor(world,MaterialConfig.STONE,slingBase.x, slingBase.y,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(slingBase.x, slingBase.y);
        group.addActor(slingshot1);
        
        //group.addActorActor(new TextActor("AAA"));
        //group.addActorActor(new PopPointActor("AAA"));
		return group;
	}

}
