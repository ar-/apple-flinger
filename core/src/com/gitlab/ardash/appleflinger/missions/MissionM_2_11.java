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

public class MissionM_2_11 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		
		// sling holder
		final float sx = 1.7f;
		final float sy = 2.5f;
		group.addActor( new BlockActor(world,MaterialConfig.STONE,sx, sy,0.4f,0.1f, BodyType.StaticBody)); 
		
		SlingShotActor slingshot1 = new SlingShotActor(sx, sy);
		group.addActor(slingshot1);

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.1405413f, 0.12753749f, 5.998882E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.4199512f, 0.12756148f, 0.0010338549f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.6714187f, 0.1270489f, 1.244765E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.915365f, 0.12725005f, 0.0035568927f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.147579f, 0.12763499f, -0.0032632023f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.3765204f, 0.12721123f, 7.2485174E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.6204526f, 0.12735204f, 0.0011535864f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.8914044f, 0.12733309f, -0.0016971409f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.1534534f, 0.12677604f, -8.4773067E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.402167f, 0.12711541f, 6.6843414E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.661415f, 0.12661107f, -0.010485533f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.9218407f, 0.1266604f, 9.7290333E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 5.2043f, 0.12749995f, 7.3116325E-8f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 5.111627f, 0.27042896f, 0.009382039f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.840253f, 0.26832855f, 0.0072594127f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.5684447f, 0.27019015f, 0.0011453792f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.2970786f, 0.26958042f, 0.0029864686f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.025283f, 0.2697814f, -0.0037239837f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.7538629f, 0.27067277f, -0.0014396684f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.4824855f, 0.2701611f, 5.238217E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.210953f, 0.27046356f, -0.0037416697f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.938925f, 0.27000156f, 0.0083123725f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.6671011f, 0.2692394f, -4.949181E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.3957207f, 0.27069744f, 0.00173538f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 2.3663979f, 0.5912706f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 3.086001f, 0.5979461f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 3.7936788f, 0.6024929f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 4.5039387f, 0.5906209f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.730649f, 0.41492614f, 4.1948305E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.441046f, 0.4160456f, -7.4065945E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.14578f, 0.4151032f, -0.0028250415f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.875249f, 0.41320902f, 0.016772276f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.1244736f, 0.27074552f, 0.0018024086f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.7377f, 0.5566552f, -0.0020853928f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.4414322f, 0.5586799f, 0.0016979189f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.4401302f, 0.697942f, 0.004821901f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.7363582f, 0.6946835f, -0.004479576f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.1530957f, 0.55715966f, -0.0114555415f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.1483555f, 0.69558877f, -0.026528247f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.859262f, 0.5538294f, 0.043646485f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.8425946f, 0.68999624f, 0.0764004f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.1536803f, 0.8376172f, -0.038554426f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.4504104f, 0.8405282f, 0.006344426f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.7273076f, 0.83637285f, -0.0068961885f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.827694f, 0.830695f, 0.0917711f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.466922f, 0.9741562f, -0.027563782f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.7925577f, 0.9887632f, 0.0018591684f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.0858994f, 0.98529345f, 0.0025018288f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.4402483f, 0.98348176f, 0.0064170146f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.129793f, 0.984838f, -0.011315347f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.8158035f, 0.9730444f, 0.09115738f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.7487411f, 0.9788745f, -0.0051576877f, BodyType.DynamicBody));
		
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.4478097f, 1.1282829f, -0.02065745f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 4.1190104f, 1.1184078f, -0.010304204f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.783435f, 1.1191589f, -0.014686579f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.7723403f, 1.2621092f, -0.061767187f, BodyType.DynamicBody));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 4.0740676f, 1.4392406f, 0.5f));
		group.addActor (new PengActor(world, MaterialConfig.TARGET_PENG, 3.486548f, 1.4481035f, 0.5f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.459502f, 1.1750902f, -1.5439351f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.4370484f, 1.4472016f, -1.5674222f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.109355f, 1.1951092f, -1.6091893f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.125101f, 1.4687525f, -1.5877087f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 3.1394813f, 1.7413421f, -1.6118138f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.4410915f, 1.7184303f, -1.5679944f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.606938f, 1.1005727f, -1.5803921f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.961981f, 1.1264527f, -1.5825016f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 4.6019444f, 1.3074503f, -1.5436985f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.968451f, 1.3320483f, -1.6067059f, BodyType.DynamicBody));
		
		return group;
	}

}
