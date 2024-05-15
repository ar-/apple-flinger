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

public class MissionM_3_3 implements StageFiller{

	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 0.6163562f, 0.32779393f, -0.0011204906f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.6973883f, 0.3272446f, -5.7630846E-4f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.779652f, 0.32651624f, 0.0010367569f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.861939f, 0.3275022f, 8.4113435E-6f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.1834848f, 0.67092156f, -0.0018654234f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.2613556f, 0.6682338f, -0.0027187741f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 3.3528488f, 0.67225957f, 0.007821882f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.1899201f, 1.0149059f, -0.0066818013f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.2586832f, 1.0090336f, -0.004441875f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 3.3280032f, 1.0146601f, 0.00991232f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 1.6752713f, 1.354685f, -0.0059058024f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.8719425f, 1.3529136f, 0.0100373365f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 1.7444907f, 1.6966652f, -0.00712918f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.8292634f, 1.6949238f, 0.011020487f, BodyType.DynamicBody));

		group.addActor (new BlockActor(world, MaterialConfig.WOOD_BL_81, 2.2881117f, 2.0379553f, -0.001760819f, BodyType.DynamicBody));
		group.addActor (new BlockActor(world, MaterialConfig.WOOD_RECT, 2.269673f, 2.380728f, -0.0014101228f, BodyType.DynamicBody));

		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.156771f, 0.32750863f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.238663f, 0.32688957f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 3.3206463f, 0.32745644f, 0.5f));

		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 1.7391231f, 0.99021524f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.2882056f, 1.6593597f, 0.5f));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.788222f, 0.9844363f, 0.5f));

		// sling holder
		final Vector2 slingBase = new Vector2(1.25f, 2.5f);
		group.addActor( new BlockActor(world,MaterialConfig.STONE,slingBase.x, slingBase.y, 0.4f, 0.15f, BodyType.StaticBody));

        SlingShotActor slingshot1 = new SlingShotActor(slingBase.x, slingBase.y);
        group.addActor(slingshot1);

		return group;
	}

}
