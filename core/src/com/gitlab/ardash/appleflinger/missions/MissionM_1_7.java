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

public class MissionM_1_7 implements StageFiller{

	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.29472303f, 0.32741436f, -1.3422674E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 0.70109504f, 0.1900003f, 2.286722E-6f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 0.7012418f, 0.45503116f, -1.698263E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 0.9763822f, 0.32747468f, -1.5710837f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.7514822f, 0.32730356f, -1.5708615f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 2.168932f, 0.3342785f, -0.02616747f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.5050385f, 0.12705661f, -1.5742718f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.5081f, 0.2659711f, -1.5832592f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.6506352f, 0.1937785f, -1.5807015f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.5813763f, 0.4698859f, -1.5866648f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.9950154f, 0.32757342f, -1.5711552f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.8270307f, 0.32637033f, -1.5704666f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.2040653f, 0.12814608f, -1.5704495f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.4870467f, 0.1269638f, -1.5727764f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.345657f, 0.12737024f, -1.5763699f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.4852724f, 0.26591986f, -1.5794045f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.3447742f, 0.2672391f, -1.5776011f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.4860706f, 0.40520164f, -1.5797802f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.3734616f, 0.6700938f, -0.0011882305f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.462369f, 0.6803158f, -0.01571269f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 0.29593137f, 0.6697871f, -0.0014037609f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 0.7028394f, 0.6655934f, 0.037912417f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.151652f, 0.67151564f, -0.009359312f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.0283022f, 1.0157263f, -0.01060481f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.3588815f, 1.0121168f, -0.0024775981f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 3.0150497f, 1.5556583f, -0.010394294f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.7902333f, 1.0125786f, -1.5637383f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.8065403f, 1.4210085f, -3.1298928f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.7939122f, 1.6315312f, -3.1279092f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.3891083f, 1.551769f, 3.1390102f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.7792693f, 1.7754097f, -3.1272225f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.8662312f, 1.9633973f, -1.5974907f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.154835f, 1.989218f, -1.5718559f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.367345f, 1.976806f, -1.5509285f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 0.53880626f, 1.9563438f, -1.5660805f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.5593536f, 2.1843858f, 3.1005301f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.8848425f, 2.1734705f, -3.118492f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 4.812166f, 0.8423038f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.0706658f, 0.9931086f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.4322326f, 1.0014533f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.8487415f, 2.390672f, -1.7981514f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 0.65952873f, 2.3779213f, -1.5296272f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.9754198f, 2.4957042f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.5463724f, 2.5052824f, 0.5f));

		// sling holder
		Vector2 slingBase = new Vector2(4.2f, 1.1f);
		group.addActor( new BlockActor(world,MaterialConfig.STONE,slingBase.x, slingBase.y,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(slingBase.x, slingBase.y);
        group.addActor(slingshot1);
        
		return group;
	}

}
