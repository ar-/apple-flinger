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

public class MissionM_2_9 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		// sling holder
		final float sx = 1.7f;
		final float sy = 1.9f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.4f,0.1f, BodyType.StaticBody)); 
		
		SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
		group.addActor(slingshot1);

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.1584854f, 0.32872343f, -0.0046715895f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.700157f, 0.3273668f, 1.1712522E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.2407436f, 0.3284836f, -0.0014877147f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.781168f, 0.3274161f, 3.5213543E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.321242f, 0.3290238f, -1.0456266E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.8612924f, 0.3273614f, -1.4801888E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 5.4013476f, 0.3317744f, -3.8573286E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.780191f, 0.66987115f, 3.0569805E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.3182306f, 0.67291826f, 0.0014932674f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.8596907f, 0.66928786f, -0.0013984515f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 5.395157f, 0.67665845f, -0.0068485793f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.2373247f, 0.6732527f, -0.0078076804f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.7026653f, 0.6693797f, 0.0013392062f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.156337f, 0.67465043f, -0.003459064f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 5.7447824f, 0.59156525f, 1.5700194f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.8141255f, 0.5912541f, 1.5548493f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.4618322f, 0.19626738f, -6.67095E-5f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 1.603386f, 0.46959478f, -0.016088437f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.2094408f, 0.46467203f, 0.73191077f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.6745899f, 0.8871624f, -0.033945035f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.8584814f, 0.8797744f, -0.002307844f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 4.855734f, 1.1562152f, -0.0027350902f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.782662f, 1.1575391f, 3.9868357E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.7802787f, 0.88077176f, 6.332874E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.6939063f, 0.87999725f, 0.0024986744f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.6776388f, 1.1566428f, 0.0030861378f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.1576843f, 0.9974843f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 3.261909f, 0.99748695f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 4.3364525f, 0.9935893f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 5.419476f, 1.0011419f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.7285576f, 1.1940604f, -0.0010013103f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.8371952f, 1.1937284f, -0.00815959f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.8273177f, 1.3335575f, -0.004352522f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.483787f, 1.3635349f, 0.023336457f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.8961954f, 1.3648578f, 4.2347913E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.580802f, 1.3648303f, 5.7415967E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.984278f, 1.3647918f, -0.001420927f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.6503587f, 1.3639061f, -0.0012683391f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.0424314f, 1.3624574f, -0.020318937f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.718928f, 1.3341259f, -0.0024470806f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 5.382334f, 1.4928837f, -0.033325147f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.1530757f, 1.4925572f, 0.040662814f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.2505085f, 1.5067697f, -9.383678E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.3155255f, 1.5054022f, -0.0026168346f, BodyType.DynamicBody));
		
		// inner row of 1x1 block close to center
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.891766f, 0.12470443f, 0.0017751129f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.8901896f, 0.25857347f, 0.00504864f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.8887143f, 0.3924927f, 0.006887637f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.8887014f, 0.526515f, 0.008159809f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.8879633f, 0.6606611f, 0.010504471f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.886247f, 0.79494745f, 0.012619548f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.884653f, 0.9294121f, 0.01350613f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.883611f, 1.0641356f, 0.013129158f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.87589f, 1.1992265f, 0.00839782f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.8682976f, 1.3351758f, -0.0069735576f, BodyType.DynamicBody));
		
		
		return group;
	}

}
