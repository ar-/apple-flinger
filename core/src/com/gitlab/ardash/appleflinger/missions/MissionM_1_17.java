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

public class MissionM_1_17 implements StageFiller{
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();

		//fixed holders
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 3.1031249f, 0.44062495f, 0.1f,1.2f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 3.1031249f, 2.4f, 0.1f,1.2f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 1.6187499f, 0.55f, 0.1f,1.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 1.6187499f, 1.55f, 0.1f,1.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 1.6187499f, 2.55f, 0.1f,1.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 1.6187499f, 3.55f, 0.1f,1.1f, BodyType.StaticBody));
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 1.6187499f, 5.878125f, 0.1f,1.2f, BodyType.StaticBody));	
		group.addActor (new BlockActor(world, MaterialConfig.STONE, 2.2518752f, 3.0f, 1.2f,0.1f, BodyType.StaticBody));
		
		// bottom contruct
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.9751328f, 0.5921734f, -1.5652031f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.9843554f, 1.6579628f, -1.5767311f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_42, 2.768907f, 0.19460414f, 2.6992173E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.9060276f, 0.46403974f, 4.6828602E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_22, 2.6311896f, 0.46392453f, -0.0011491457f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.778739f, 0.6671291f, -3.0991226E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 2.7786198f, 0.8061102f, 0.001917871f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.772596f, 1.1451205f, 0.003230127f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.7760046f, 1.6845399f, 0.0032759032f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.2781487f, 0.59000427f, -1.570986f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.2781825f, 1.6550438f, -1.5718405f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.2093327f, 2.2618165f, -0.009065898f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.1385694f, 2.5874872f, 0.5f));
		
		//left/top construst
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.2893677f, 3.5644307f, -0.001912038f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.2835764f, 3.0246906f, -0.0016305587f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.2840774f, 2.4850872f, -0.0012907324f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.2857538f, 1.9455423f, -9.814389E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.2847532f, 1.4060652f, -6.687521E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.2863063f, 0.8666319f, -3.5633924E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.2905943f, 0.32722467f, -6.820842E-5f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.7423436f, 0.32733446f, -1.545623E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.7297401f, 0.8669525f, -5.941053E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.73032695f, 1.4066193f, -0.0010022661f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.732958f, 1.9463197f, -1.5721785f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.73182154f, 2.4860742f, -1.5725266f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.72753555f, 3.0259063f, -1.572805f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.74730206f, 3.565731f, -1.5730873f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.4865153f, 4.366475f, -1.5731817f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.0286427f, 4.3674893f, -1.5745932f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.4873986f, 5.165793f, -1.573093f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.0403357f, 5.166769f, -1.5746847f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_41, 1.284369f, 5.506173f, -0.0021945157f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.2383149f, 5.8291016f, 0.5f));
		
		// sling holder
		final Vector2 slingBase = new Vector2(3,3);
		group.addActor( new BlockActor(world,MaterialConfig.STONE,slingBase.x, slingBase.y,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(slingBase.x, slingBase.y);
        group.addActor(slingshot1);
        
        //group.addActorActor(new TextActor("AAA"));
        //group.addActorActor(new PopPointActor("AAA"));
		return group;
	}

}
