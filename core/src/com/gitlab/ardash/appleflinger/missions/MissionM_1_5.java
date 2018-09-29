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

public class MissionM_1_5 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 0.30866924f, 0.12680978f, -7.6552003E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.0309083f, 0.1263965f, -1.3208506E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.6956329f, 0.2682445f, -0.0020335922f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.6584506f, 0.12585907f, -4.6210177E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.2683794f, 0.12575977f, 4.218761E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.9558766f, 0.26636925f, -0.0018044488f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.015178f, 0.12714383f, -0.0022484786f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.487864f, 0.12596764f, -0.0016921754f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.2920265f, 0.2682835f, -0.0055905357f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.358784f, 0.12749992f, 1.6252872E-7f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 4.912937f, 0.12749201f, -1.351596E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 4.611767f, 0.27069375f, -2.9675776E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.7129111f, 0.61105037f, -0.0021657022f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.981288f, 0.6089514f, -0.0018433714f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.265559f, 0.6112898f, -0.0060781552f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 4.568393f, 0.61387336f, -4.6128535E-4f, BodyType.DynamicBody));
		group.addActor (new TntActor(world, 3.925939f, 0.6123696f, 0.011988189f));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.6953845f, 0.95414925f, 0.005738709f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.3498136f, 0.95293796f, -0.001984736f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.0218005f, 1.0120353f, 0.0035664102f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 3.3677256f, 1.0127093f, -0.008788175f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 0.6769983f, 1.0130949f, -0.0038528414f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 0.6882652f, 1.2836401f, -0.0053761574f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.0103807f, 1.3005184f, -8.786748E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.3839123f, 1.2832501f, -0.012169078f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.3758855f, 1.494835f, -0.0126620885f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.6763147f, 1.4951423f, -0.0056424215f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_21, 2.0063868f, 1.5107614f, -0.0010065536f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.017626f, 1.6553015f, -0.0022910128f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.3729694f, 1.6405665f, -0.012739358f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.6933908f, 1.6406945f, -0.0056363777f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.2737656f, 1.984891f, -1.5837873f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 3.5529234f, 1.9813021f, -1.5834311f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 1.8965397f, 1.9985996f, -1.573058f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.1821043f, 1.997862f, -1.5737914f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 0.5571264f, 1.9844983f, -1.5766333f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 0.83780533f, 1.9829199f, -1.5762357f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.0502188f, 2.3413184f, -0.0044898507f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 3.4262545f, 2.3259182f, -0.01263423f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 0.7088659f, 2.3266006f, -0.005310011f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.7097392f, 2.4722133f, -0.005222273f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.0644782f, 2.4875588f, -0.0070037367f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.4259505f, 2.4715161f, -0.012388182f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.8999667f, 2.6082237f, -0.012176466f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.9745486f, 2.6195138f, -0.012121152f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.5740323f, 2.6268604f, -0.007295561f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.6095574f, 2.6337805f, -0.0071715834f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 0.27372542f, 2.6168756f, -0.004699659f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.1662489f, 2.6123738f, -0.004835081f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.4490995f, 2.7560718f, -0.012136412f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.0839968f, 2.773369f, -0.0072936537f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 0.71233433f, 2.7570457f, -0.004690123f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 0.2801429f, 2.9030976f, -0.009242964f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.1572182f, 2.8981142f, -0.006217909f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.6225287f, 2.9199162f, -0.008230162f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.5660706f, 2.9129264f, -0.007345152f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.993534f, 2.906885f, -0.015203428f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 3.907656f, 2.8935804f, -0.012132597f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.217241f, 1.509059f, -8.9068414E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.7988331f, 1.5094274f, -8.5635186E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 1.8152106f, 1.0936363f, -0.0019759657f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_11, 2.2271507f, 1.0929005f, 0.0031128405f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 4.5508933f, 1.1313812f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.532829f, 1.2737975f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.3210675f, 0.56721085f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.3437505f, 1.9288769f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.6020787f, 3.078099f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 3.3090098f, 3.078296f, 0.5f));
		
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
