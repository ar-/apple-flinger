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

public class MissionM_2_15 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		// sling holder
		final float sx = 4.7f;
		final float sy = 0.5f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.4f,0.1f, BodyType.StaticBody)); 

		final float bx = 2.0f;
		final float w = 2.0f;
		final float h = 0.5f;
		float by = 0.5f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by ,w,0.1f, BodyType.StaticBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx+w/2f, by ,0.1f,h, BodyType.StaticBody)); 
		by += 1.2f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by ,w,0.1f, BodyType.StaticBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx+w/2f, by ,0.1f,h, BodyType.StaticBody)); 
		by += 1.2f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx, by ,w,0.1f, BodyType.StaticBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.STONE,bx+w/2f, by ,0.1f,h, BodyType.StaticBody)); 

		SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
		group.addActor(slingshot1);

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.6725006f, 2.0274992f, -8.74888E-7f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.6725135f, 0.827466f, -5.13619E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.6725008f, 3.2274632f, -1.0735192E-6f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.0261749f, 3.2279508f, 0.015884131f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.0218754f, 2.0324783f, 4.6822116E-8f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.0218694f, 0.8275454f, 0.0022438136f, BodyType.DynamicBody));
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.6733842f, 1.3678111f, 0.008828696f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.5033298f, 2.4297702f, -3.5068762E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.774562f, 2.429528f, -4.3765103E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.6907148f, 3.5671787f, 5.6215853E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.6905224f, 3.7097278f, 0.0015680126f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.8562498f, 0.80624986f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.828125f, 2.0061738f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.8374996f, 3.2061737f, 0.5f));
		
		return group;
	}

}
