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

public class MissionM_1_13 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.9990969f, 0.32750106f, 3.9958936E-6f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.0470207f, 0.32805887f, -0.0021313305f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.06335f, 0.30500355f, -0.0021313305f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.5682793f, 0.3075059f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.5234606f, 0.30884668f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.5825465f, 0.3271342f, 0.0013376621f, BodyType.DynamicBody));
//		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 3.06335f, 0.30500355f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.0470207f, 0.32805887f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.777286f, 0.6431142f, -0.059124254f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.7072189f, 0.67540467f, -0.0012279561f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.5880191f, 0.66761065f, 0.011685722f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 0.90477425f, 0.6708435f, -8.235845E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.3152454f, 0.8043725f, 0.027770795f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.2098455f, 0.82324415f, -0.027070455f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.1422421f, 0.8222483f, 0.0047497367f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.5583272f, 1.1537417f, 0.026758691f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.0120888f, 1.138581f, 0.028713454f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.5400207f, 1.6931722f, 0.026864883f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.998816f, 1.6781694f, 0.028806008f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.9894638f, 2.217993f, 0.028863663f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.5305924f, 2.232978f, 0.026912041f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.6692395f, 0.19598323f, 2.5251255E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.209312f, 0.1963105f, 5.6840645E-7f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.9380574f, 0.4741949f, 0.0053623565f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 4.5290966f, 0.4679121f, 1.7656568E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 5.3484344f, 0.4689101f, 0.007193803f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.669834f, 0.74944097f, 0.010166071f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.2105126f, 0.7558429f, 0.015606185f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.9475994f, 1.0299406f, 0.013029333f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 4.533489f, 1.0192865f, 0.010228237f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 5.359709f, 1.031639f, 0.024166282f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.6544614f, 1.3028084f, 0.020753747f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.195371f, 1.3129432f, 0.021787055f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.908822f, 1.5854981f, 0.021309733f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 4.501461f, 1.570865f, 0.020841025f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 5.3150883f, 1.5872836f, 0.02507368f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.6143384f, 1.8561399f, 0.033749443f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.1559134f, 1.8683242f, 0.021427346f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.86154f, 2.1420515f, 0.03349932f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 5.267764f, 2.1433666f, 0.03193977f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 4.4554f, 2.1221056f, 0.0338891f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 4.1171875f, 0.3049993f, 0.5f));
		
		// sling holder
		final Vector2 slingBase = new Vector2(1.75f,1);
		group.addActor( new BlockActor(world,MaterialConfig.STONE,slingBase.x, slingBase.y,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(slingBase.x, slingBase.y);
        group.addActor(slingshot1);
        
        //group.addActorActor(new TextActor("AAA"));
        //group.addActorActor(new PopPointActor("AAA"));
		return group;
	}

}
