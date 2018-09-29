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
import com.gitlab.ardash.appleflinger.actors.DorkActor;
import com.gitlab.ardash.appleflinger.actors.SlingShotActor;
import com.gitlab.ardash.appleflinger.actors.TntActor;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.missions.Mission.StageFiller;

public class MissionM_1_11 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();

		//upper holder
		final BlockActor upperHolder = new BlockActor(world,MaterialConfig.STONE,1.9f, 3.1f,3.3f,0.1f, BodyType.StaticBody);
		group.addActor( upperHolder); 

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.5098397f, 0.32757342f, 2.8368554E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.0501912f, 0.32738262f, 3.3600954E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.5907143f, 0.32734948f, 2.648698E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.1318955f, 0.3271838f, 2.9775492E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.5094789f, 0.8676011f, 3.9788167E-4f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 1.5947134f, 0.867191f, 1.3819788E-4f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.0533638f, 0.73558563f, -9.092913E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.0510619f, 1.0119067f, -0.0014731038f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.1406598f, 0.73469573f, 0.0016405332f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.1429465f, 1.0103761f, 0.0038855849f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.1723137f, 1.2197027f, 0.008174082f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.6051867f, 1.2098895f, -4.526208E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.0681386f, 1.2215998f, -0.0045366166f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 0.52817714f, 1.2173715f, 0.026099833f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.3356397f, 1.3729872f, -0.043418605f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.0409517f, 1.3603736f, 0.009906873f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.7687621f, 1.3555601f, 1.02753984E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.4866273f, 1.3562381f, -0.002865806f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.213587f, 1.364453f, -0.01354077f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 0.9410608f, 1.3676951f, 0.0015012771f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 0.6652099f, 1.3689421f, 0.012759008f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 0.39127153f, 1.3601279f, 0.025830308f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.9300121f, 1.5673096f, 0.001746623f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.3645619f, 1.5718297f, -0.016871398f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.9409655f, 1.8435209f, -8.78678E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.3962264f, 1.8481668f, -0.015497439f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.071592f, 2.2515962f, -1.5749778f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.7931751f, 2.2534347f, -1.5796126f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.5153593f, 2.2554622f, -1.5818061f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.2363315f, 2.2592676f, -1.5856856f, BodyType.DynamicBody));
//		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.8397567f, 1.6875254f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.063146f, 2.657117f, -1.5753897f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.3756862f, 2.600297f, -0.013674196f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.3879765f, 2.7430954f, -0.013666566f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.330103f, 3.2962794f, 1.1974978E-4f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 2.7897103f, 3.4288294f, 0.001060093f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.2494352f, 3.2958188f, 0.0014472645f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.3834407f, 3.5675204f, 0.0037131947f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.195296f, 3.5676851f, 0.0015006702f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.110644f, 3.565637f, 0.010129515f, BodyType.DynamicBody));
//		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.9124999f, 3.4049938f, 0.5f));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.8426515f, 3.2897449f, 9.055774E-4f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.8359488f, 3.5542033f, 0.002904479f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.0852728f, 3.829236f, 0.016950194f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.8119463f, 3.8188555f, 0.0036674181f, BodyType.DynamicBody));
//		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.9226589f, 4.089627f, 0.043378416f, BodyType.DynamicBody));
//		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.474419f, 3.9495397f, 0.5f));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.2934214f, 3.2900562f, -3.0160823E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.290536f, 3.5561075f, -0.0049441373f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.2624134f, 3.821245f, -0.004940839f, BodyType.DynamicBody));
		
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.7135506f, 3.2882357f, 0.002093648f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.6997782f, 3.5494797f, 0.005851003f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.6723648f, 3.8112767f, 0.0048273983f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.0908716f, 3.5604498f, 0.017000128f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.0711493f, 3.8195658f, 0.02410154f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.9111594f, 4.018157f, 0.016844101f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.3758771f, 4.0240073f, -0.004339735f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.9346718f, 4.336325f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.8000002f, 3.4049993f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.8421941f, 1.6690935f, 0.5f));

		// sling holder
		group.addActor( new BlockActor(world,MaterialConfig.STONE,5.2f, 2.1f,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(5.2f, 2.1f);
        group.addActor(slingshot1);
        
        //group.addActorActor(new TextActor("AAA"));
        //group.addActorActor(new PopPointActor("AAA"));
		return group;
	}

}
