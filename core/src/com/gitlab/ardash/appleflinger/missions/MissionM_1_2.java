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

public class MissionM_1_2 implements StageFiller{

	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.41767314f, 0.32732463f, 3.446385E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.9745596f, 0.32819203f, 0.0029185568f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.7145187f, 0.6704381f, 0.00242023f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 0.45358843f, 1.0120705f, 0.0031832154f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.4057491f, 0.32877603f, 1.578497f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.6850768f, 0.3278254f, 1.5732833f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.238128f, 0.32721043f, 1.569719f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.8530482f, 0.6712069f, 3.1368935f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.9983855f, 0.9950306f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.5943154f, 1.0198929f, 3.1367218f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.6701865f, 0.3294694f, 3.1336691f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.350742f, 0.18997864f, 3.141454f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.35071f, 0.456873f, 3.1259577f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.9673777f, 0.6697333f, 3.1235974f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.6634784f, 1.0196042f, 3.1293023f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 3.1861773f, 0.9863603f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.1400447f, 0.98934007f, 0.5f));
		group.addActor (new TntActor(world, 0.46671215f, 1.5517133f, 0.0028804922f));

		// sling holder
		Vector2 slingBase = new Vector2(4.2f, 1.1f);
		group.addActor( new BlockActor(world,MaterialConfig.STONE,slingBase.x, slingBase.y,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(slingBase.x, slingBase.y);
        group.addActor(slingshot1);
        
		return group;
	}

}
