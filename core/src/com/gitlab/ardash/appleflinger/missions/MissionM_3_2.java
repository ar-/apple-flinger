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
import com.gitlab.ardash.appleflinger.actors.TntActor;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.missions.Mission.StageFiller;

public class MissionM_3_2 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.35f, 0.3275122f, 0.0f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 3.8f, 0.32749552f, 5.5269406E-6f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.25f, 0.32749864f, 0.0f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 2.7f, 0.3274941f, 4.936328E-6f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.15f, 0.32749668f, 0.0f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 1.6f, 0.32749638f, -2.753632E-6f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.05f, 0.32749635f, 0.0f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 0.5f, 0.3274946f, -2.4503E-6f));
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.775f, 0.6706072f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.875f, 0.6706126f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.975f, 0.6706122f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.075f, 0.67061895f, 0.0f, BodyType.DynamicBody));
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 0.35f, 0.87618774f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 0.35f, 1.1411417f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 0.35f, 1.4061034f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.35f, 0.876213f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.35f, 1.1411573f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.35f, 1.4061015f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.45f, 0.8762083f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.45f, 1.1411363f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.45f, 1.4060713f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.55f, 0.87621695f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.55f, 1.1411841f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.55f, 1.4061117f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 4.5f, 0.8762631f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 4.5f, 1.1411841f, 0.0f, BodyType.DynamicBody));
		
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.775f, 1.6117051f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.875f, 1.6117016f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.975f, 1.6117016f, 0.0f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.05f, 1.5f, -0.255f, BodyType.DynamicBody));

		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.775f, 0.99123913f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.875f, 0.99123496f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.975f, 0.99123496f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 4.075f, 0.99123496f, 0.5f));
		
		// sling holder
		final Vector2 slingBase = new Vector2(1.25f, 2.5f);
		group.addActor( new BlockActor(world,MaterialConfig.STONE,slingBase.x, slingBase.y, 0.4f, 0.15f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(slingBase.x, slingBase.y);
        group.addActor(slingshot1);
        
		return group;
	}

}
