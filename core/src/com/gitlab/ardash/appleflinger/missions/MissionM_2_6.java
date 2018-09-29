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

public class MissionM_2_6 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();

		// sling holder
		final float sx = 1.7457057f;
		final float sy = 1.9315788f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
        group.addActor(slingshot1);
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.6635237f, 0.32750922f, 3.5238212E-5f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.6492314f, 0.8678013f, 1.2204062E-5f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.6739397f, 1.4091196f, 0.0016433f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.67083f, 1.9491154f, 0.0016418087f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.6718764f, 2.4891205f, 0.0016310308f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.693303f, 3.0291553f, 0.001631118f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.239707f, 0.32816386f, 1.5758852f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.2371902f, 0.86824906f, 1.5765909f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.2557607f, 1.4085156f, 1.5777467f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.2484035f, 1.9484764f, 1.5777487f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.2562838f, 2.4885433f, 1.5777534f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.816725f, 0.32749215f, 2.8101646E-5f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.8264923f, 0.8674773f, 1.00580895E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.8412004f, 1.4076674f, 8.55891E-4f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.82874f, 1.9483835f, 0.0036520641f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.6739583f, 3.5695724f, -7.8709876E-5f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.8447847f, 2.4884386f, -3.1379163f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.2833796f, 3.0295017f, 1.5715029f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 5.2638826f, 3.5698388f, 1.5722964f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.873414f, 3.0285816f, 1.5746539f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.8544054f, 3.5685308f, 1.5747836f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.7095213f, 4.110085f, 1.5726931f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.8769097f, 4.1089716f, 1.5734179f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.95965f, 4.649634f, 1.5751269f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.665426f, 4.6501813f, 1.5719912f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.611573f, 5.191168f, 1.5719149f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.0703278f, 5.1904025f, 1.5762255f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.340965f, 5.7338734f, 1.5798495f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 5.366327f, 4.111477f, 1.5730212f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 5.2200856f, 4.1096983f, 1.5731356f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 5.3057914f, 4.6498685f, 1.5708392f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 4.9396024f, 5.5956774f, 1.5790865f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.6830444f, 5.5315046f, 1.5760729f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.407667f, 0.32748905f, 1.5708163f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.3872657f, 0.8674648f, 1.5708086f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.426893f, 1.4074448f, 1.5707629f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.421884f, 0.32747892f, 1.5707095f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.4593842f, 0.8674286f, 1.570595f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.4499104f, 1.4073899f, 1.5705569f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.4294715f, 0.32771853f, 1.5690081f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.4566083f, 0.86765414f, 1.568886f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.43932f, 1.4076762f, 1.5687945f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.4376755f, 1.7507912f, 3.1395545f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.4405031f, 1.750467f, 3.1413016f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.4235997f, 1.750501f, -3.1415784f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.01063f, 1.8966749f, -3.1415632f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.9455714f, 1.8968647f, 3.141233f, BodyType.DynamicBody));
        group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.3264592f, 2.0578277f, 3.1216254f, BodyType.DynamicBody));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 3.890625f, 0.30499968f, 0.5f));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.953125f, 0.30499944f, 0.5f));
        group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 1.9781246f, 0.30964243f, 0.5f));
		return group;
	}

}
