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

public class MissionM_1_14 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		//upper holder
		final BlockActor upperHolder = new BlockActor(world,MaterialConfig.STONE,1.9f, 3.0f,3.3f,0.1f, BodyType.StaticBody);
		group.addActor( upperHolder); 
		
		//lower holder
		final BlockActor lowerHolder = new BlockActor(world,MaterialConfig.STONE,1.9f, 1.5f,3.3f,0.1f, BodyType.StaticBody);
		group.addActor( lowerHolder); 
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.3985267f, 0.32748812f, 4.1254018E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.5877075f, 0.7350542f, -1.5660475f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.2221515f, 0.733025f, -1.5697492f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.4260623f, 1.1410545f, -1.5607461f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.5310295f, 1.8244374f, -1.5723923f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.1557539f, 1.8243973f, -1.5711416f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.342852f, 2.3632987f, -1.5721673f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.4911618f, 2.76872f, -1.5721692f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.209731f, 2.7712414f, -1.5703701f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.203633f, 4.6907415f, -3.1388147f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.4786112f, 4.691894f, -3.1276596f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.204964f, 4.9572334f, 3.1411016f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.421995f, 5.0917573f, 2.12242f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.379567f, 3.1946268f, -3.1375558f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.8311906f, 3.194277f, 3.139013f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.085145f, 3.468581f, 3.1411445f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.495441f, 3.4670665f, -3.1403158f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.6753538f, 3.4668174f, 3.139383f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.3407626f, 3.7416906f, 3.1347244f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.7938035f, 3.7429507f, -3.1409519f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.0621517f, 3.9518383f, 3.1383245f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.6562397f, 3.9517972f, 3.1407287f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.468009f, 3.9492965f, 3.1362665f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.3382614f, 4.0917826f, 3.1357725f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.8021183f, 4.0935273f, 3.1405752f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.0671768f, 4.2339983f, 3.1374223f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.4716752f, 4.2322702f, 3.1365736f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.6628997f, 4.2348704f, -3.1391866f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.331951f, 4.3746643f, 3.135124f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.7911906f, 4.375565f, -3.1379755f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.6624923f, 3.323803f, 1.5705789f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.0322163f, 3.3236487f, 1.5677809f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.0770864f, 1.6903934f, 1.5703186f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.5779301f, 1.6902937f, 1.5680584f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.8275454f, 1.8949584f, -3.1409135f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.358654f, 3.662305f, -3.1406808f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.5180048f, 0.12213193f, -1.5707484f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.0551003f, 0.1232342f, -1.5721027f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.2846205f, 0.25994006f, -0.005297168f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.5851447f, 0.59749776f, 1.5638201f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.0326365f, 0.60056466f, 1.5644f, BodyType.DynamicBody));
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.0620108f, 2.2336023f, 1.5686724f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.5834551f, 2.2332265f, 1.5693667f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.6640317f, 4.0015807f, 1.5697863f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.0134592f, 4.0006304f, 1.571007f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.3200734f, 4.340352f, -3.1397676f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.8364581f, 2.5723612f, -3.1407976f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.3280925f, 0.93841f, 3.1360018f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.3235461f, 1.1435392f, 3.1360247f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.8124102f, 2.7767634f, -3.1405687f, BodyType.DynamicBody));
		
		//sqeezed in roofs
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.6537014f, 1.2244235f, -0.6477902f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.998305f, 1.2298084f, 0.6025335f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.0287724f, 2.7217617f, 0.0049704034f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.6198778f, 2.7197065f, 0.010046626f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.1888704f, 2.7976527f, -0.8031116f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 1.4622508f, 2.804768f, 0.6783269f, BodyType.DynamicBody));
		
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 3.3066067f, 0.58170706f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.8216954f, 2.2195547f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.1953123f, 3.3049939f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.3355985f, 3.3090641f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.336675f, 3.979968f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.828305f, 4.8050013f, 0.5f));


		// sling holder
		final Vector2 slingBase = new Vector2(1.75f,4.5f);
		group.addActor( new BlockActor(world,MaterialConfig.STONE,1.9f, slingBase.y,3.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(slingBase.x, slingBase.y);
        group.addActor(slingshot1);

		return group;
	}

}
