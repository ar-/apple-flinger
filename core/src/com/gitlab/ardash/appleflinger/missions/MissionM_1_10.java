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

public class MissionM_1_10 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.48658523f, 0.32753342f, -3.0985256E-4f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.0261658f, 0.3049915f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.6115495f, 0.32730952f, -4.5203393E-5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.13132f, 0.3047929f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.7302017f, 0.3268344f, -3.7101156E-5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 3.2649205f, 0.30464748f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.794782f, 0.32712555f, -1.9324452E-4f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 4.338698f, 0.30498564f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.8874846f, 0.34555265f, -0.071378075f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.967434f, 0.8816117f, -0.07221198f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.392979f, 0.82091135f, -0.17563777f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.2951856f, 0.82510537f, -0.19042042f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.2088513f, 0.816498f, -0.19232807f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.0919025f, 0.83567053f, 0.13148354f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.51958007f, 0.8675251f, -7.187842E-4f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.7410443f, 0.84295917f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.6441077f, 0.84436584f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 3.825703f, 0.8437609f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.842572f, 1.3491131f, -0.1756077f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.9359055f, 1.3491119f, -0.17296906f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.7665685f, 1.3468844f, -0.21805617f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.51829904f, 1.4078807f, -0.0024300157f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.0333676f, 1.4183302f, -0.07249615f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.3125129f, 1.3230667f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 3.407265f, 1.3297361f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 4.5061398f, 1.3263968f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.0688351f, 1.3545837f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.468695f, 1.8441982f, -0.0076316493f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.0410686f, 1.9598161f, -0.07487539f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.3633144f, 1.8466296f, -0.0015390931f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.2530491f, 1.841f, -0.0131278485f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 3.9450545f, 1.8722552f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.828975f, 1.8759879f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.3673273f, 1.1963665f, -0.29607314f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.4102448f, 1.3300576f, -0.28924507f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.4411759f, 1.467494f, -0.28058037f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.4712741f, 1.6045667f, -0.28068438f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.7270491f, 1.8853937f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.1728053f, 1.8624539f, -0.262338f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.57161367f, 1.9477404f, -0.0026488816f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.785653f, 2.2022693f, -0.06700586f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.5988538f, 2.1868792f, 0.024156813f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.6798599f, 2.189274f, 0.0054796166f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.2215304f, 2.1891134f, -0.008229023f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.141625f, 2.188942f, -0.0032797772f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.191854f, 2.1847024f, -0.013603039f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.3694301f, 2.1646595f, -0.26226953f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.62932f, 2.1860898f, -0.007545824f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.0885768f, 2.2401936f, -0.26278067f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.2213609f, 2.3556492f, -0.26299822f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.5872439f, 2.4092486f, -0.2582787f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.2034035f, 2.330298f, -0.013552542f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.9215686f, 2.3341563f, -0.009674031f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.3421671f, 2.3316157f, 0.01020905f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.0118394f, 2.335252f, -0.0044793678f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.6431575f, 2.3322184f, -0.007450727f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.4157677f, 2.4084735f, -2.2702525f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.733654f, 2.332845f, 0.0022667758f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.6438804f, 2.3310673f, 0.01065704f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.9076135f, 2.5067234f, -0.13420825f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.4510458f, 2.4758887f, -0.0012503562f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.9855316f, 2.4750981f, -0.0070012547f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.5201986f, 2.4769251f, 0.005254475f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.0544004f, 2.478959f, 0.0016909864f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.7402296f, 2.50416f, -0.4273405f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.684649f, 2.622153f, 0.004852614f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.5949192f, 2.6217651f, -0.009522991f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.5126561f, 2.660448f, 0.19100824f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.6723368f, 2.4874573f, -0.0027083396f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.056506f, 2.963446f, 1.575066f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.2619114f, 2.9639862f, 1.5602955f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.9477463f, 2.9574382f, 1.5604824f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.3361945f, 2.9600754f, 1.5751947f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.7034633f, 3.3016248f, -3.1369276f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.6371925f, 3.300128f, 3.1321003f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 3.6618845f, 2.9423022f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.5874858f, 2.941448f, 0.5f));
		
		// sling holder
		final Vector2 slingBase = new Vector2(2f,3.5f);
		group.addActor( new BlockActor(world,MaterialConfig.STONE,slingBase.x, slingBase.y,0.9f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(slingBase.x, slingBase.y);
        group.addActor(slingshot1);
        
        //group.addActorActor(new TextActor("AAA"));
        //group.addActorActor(new PopPointActor("AAA"));
		return group;
	}

}
