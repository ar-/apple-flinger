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

public class MissionM_1_6 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 0.38740405f, 0.19607168f, -1.3771826E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 0.38725436f, 0.47319657f, -4.7881744E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.437362f, 0.19592765f, -3.2755794E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.437168f, 0.47274047f, -1.340507E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.499618f, 0.19586465f, 2.0438267E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.4989777f, 0.4725421f, 4.1200532E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.5996616f, 0.19598272f, 2.9107826E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.5988977f, 0.4729196f, 9.619327E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 0.38776585f, 0.75035137f, -8.407954E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.4244546f, 0.7495799f, -2.836816E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.4987164f, 0.74924827f, 3.5579753E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.5721285f, 0.749867f, 0.0013844362f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.9612855f, 0.9609745f, -3.3045272E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.0719328f, 0.96101075f, 6.1064033E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.87540525f, 0.9616134f, -7.7649765E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 0.5246333f, 1.1672608f, -6.772652E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.4223752f, 1.1663707f, 9.24785E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.9474266f, 1.1663104f, 9.2892325E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 0.94938064f, 1.1669874f, -6.5939885E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.4745631f, 1.1665902f, -0.0010947079f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.4496171f, 1.1661927f, 3.9066604E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.9498793f, 1.1663522f, -3.6401668E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.0479896f, 1.3717057f, -2.471712E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.9680704f, 1.3716869f, -4.8100704E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.89655113f, 1.3724879f, -5.5059086E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.7717551f, 1.7155825f, -4.8481216E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.2397254f, 1.7145963f, -4.2755136E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.9891626f, 1.7144748f, -5.5453525E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.3877132f, 2.0581574f, -0.0010994743f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.6259189f, 2.05765f, 3.6112676E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 0.6990926f, 2.0554898f, 7.5493575E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 0.698737f, 2.1953285f, 7.1623625E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.2883625f, 2.0543714f, -3.3364733E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.2880363f, 2.1941133f, -2.2045056E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.288031f, 2.3339882f, -1.928615E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 0.6848278f, 2.3352375f, 9.7478833E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.0000584f, 2.0532494f, 0.0033770632f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.9998603f, 2.1910095f, 0.011568327f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.9856524f, 2.3294356f, 0.016970806f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.464265f, 0.12677774f, 8.4391946E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.454029f, 0.26539943f, 0.0016446311f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.6889033f, 0.28446612f, -0.7142664f, BodyType.DynamicBody));
		
		// dorks
		
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 4.1328125f, 0.30499968f, 0.5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 3.03125f, 0.30499348f, 0.5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.9609371f, 0.30499995f, 0.5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.9101097f, 0.3058071f, 0.5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.3944867f, 1.6938298f, 0.5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.5519369f, 1.6933044f, 0.5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.4785056f, 2.379539f, 0.5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.5238785f, 2.3799913f, 0.5f, BodyType.DynamicBody));
		
//		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 5.9609375f, 0.30499968f, 0.5f, BodyType.DynamicBody));
//		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 4.125f, 0.30499944f, 0.5f, BodyType.DynamicBody));
//		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 7.8812675f, 0.30905384f, 0.5f, BodyType.DynamicBody));

//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.0547273f, 0.3289174f, 0.005092918f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.9554245f, 0.32915226f, -0.0052048806f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.87371093f, 0.3931921f, -0.006335526f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.3009984f, 1.7071393f, -0.0024267638f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.5931058f, 1.7123526f, 0.0042842464f, BodyType.DynamicBody));	
		
		// for pos check
		//group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT,  2.5912971f, 2.3855486f, 0.006268544f, BodyType.DynamicBody));
		//group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.2952052f, 2.3854315f, -0.004558256f, BodyType.DynamicBody));
		
		// tmeplate
		//group.addActor (new DorkActor(world, MaterialConfig.STONE, 6, 3, 0.5f, BodyType.DynamicBody));
//		group.addActor (new DorkActor(world, MaterialConfig.STONE, 2.5912971f, 2.3855486f, 0.5f, BodyType.DynamicBody));
//		group.addActor (new DorkActor(world, MaterialConfig.STONE, 1.2952052f, 2.3854315f, 0.5f, BodyType.DynamicBody));
		
		
		// sling holder
		final Vector2 slingBase = new Vector2(2,3);
		group.addActor( new BlockActor(world,MaterialConfig.STONE,slingBase.x, slingBase.y,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(slingBase.x, slingBase.y);
        group.addActor(slingshot1);
        
        //group.addActorActor(new TextActor("AAA"));
        //group.addActorActor(new PopPointActor("AAA"));
		return group;
	}

}
