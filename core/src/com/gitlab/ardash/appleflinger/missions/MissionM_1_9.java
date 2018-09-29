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

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.actors.BlockActor;
import com.gitlab.ardash.appleflinger.actors.DorkActor;
import com.gitlab.ardash.appleflinger.actors.SlingShotActor;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.helpers.Calculator;
import com.gitlab.ardash.appleflinger.missions.Mission.StageFiller;

public class MissionM_1_9 implements StageFiller{

	//for BasicStageFiller
	/**
	 * build an inverse U and return the height of the building
	 * @param world
	 * @param group
	 * @param x
	 * @param y
	 * @return
	 */
	@SuppressWarnings("static-method")
	protected float createInverseU(GameWorld world, Group group, float x, float y)
	{
		final float r= 90f * MathUtils.degreesToRadians;
//		group.addActor( new BlockActor(world,MaterialConfig.STONE,x     ,y    ,0.2f,0.8f, BodyType.DynamicBody));  
//		group.addActor( new BlockActor(world,MaterialConfig.STONE,x+1.0f,y    ,0.2f,0.8f, BodyType.DynamicBody));  
//		group.addActor( new BlockActor(world,MaterialConfig.STONE,x+0.5f,y+0.5f,1.2f,0.2f, BodyType.DynamicBody));  
		BlockActor actor = new BlockActor(world,MaterialConfig.WOOD_BL_81,x, y, r, BodyType.DynamicBody);
		group.addActor( actor); 
		float w = actor.getWidth();
		float h = actor.getHeight();
		actor = new BlockActor(world,MaterialConfig.WOOD_BL_81,x+w-h, y, r, BodyType.DynamicBody);
		group.addActor( actor); 
		actor = new BlockActor(world,MaterialConfig.WOOD_BL_81,x+w/2-h/2, y+w/2+h, 0, BodyType.DynamicBody);
		group.addActor( actor); 
		
		return w+h;
	}
	
	@Override
	public Group fillMirrorStage(GameWorld world) {
		Group group = new Group();
		float height;
		height= createInverseU(world, group, 0.2f, 0.5f);
		createInverseU(world, group, 0.2f, height*1.5f);
		createInverseU(world, group, 0.2f, height*2.5f);
		
		final float d = Calculator.convPixelToSceneUnits(88);
		final float r= 90f * MathUtils.degreesToRadians;
		group.addActor( new BlockActor(world,MaterialConfig.WOOD_RECT,1.9f+0*d,d/2f+0*d, 3f*r, BodyType.DynamicBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.WOOD_RECT,1.9f+1*d,d/2f+0*d, 1f*r, BodyType.DynamicBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.WOOD_RECT,1.9f+0*d,d/2f+1*d, 2f*r, BodyType.DynamicBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.WOOD_RECT,1.9f+1*d,d/2f+1*d, 0f*r, BodyType.DynamicBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.WOOD_RECT,1.9f+0*d,d/2f+2*d, 1f*r, BodyType.DynamicBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.WOOD_RECT,1.9f+1*d,d/2f+2*d, 3f*r, BodyType.DynamicBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.WOOD_RECT,1.9f+0*d,d/2f+3*d, 0f*r, BodyType.DynamicBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.WOOD_RECT,1.9f+1*d,d/2f+3*d, 1f*r, BodyType.DynamicBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.WOOD_RECT,1.9f+0*d,d/2f+4*d, 4f*r, BodyType.DynamicBody)); 
		group.addActor( new BlockActor(world,MaterialConfig.WOOD_RECT,1.9f+1*d,d/2f+4*d, 2f*r, BodyType.DynamicBody)); 
		
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.65625f, 0.3095085f, 0.5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.6406392f, 1.5153271f, 0.5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.6574342f, 2.7254267f, 0.5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 0.64214784f, 3.9362338f, 0.5f, BodyType.DynamicBody));
		group.addActor (new DorkActor(world, MaterialConfig.TARGET_DORK, 2.1451294f, 2.9948468f, 0.5f, BodyType.DynamicBody));


		// 45 degree faling boxes
//		final BlockActor actor = new BlockActor(world,MaterialConfig.WOOD_RECT,3.9f+1*d,5f, 30f* MathUtils.degreesToRadians ,BodyType.DynamicBody);
//		//actor.setPhysicalRotation(0.3f);
//		group.addActor( actor); 
		
		// sling holder
		group.addActor( new BlockActor(world,MaterialConfig.STONE,4.2f, 1.1f,0.3f,0.1f, BodyType.StaticBody)); 

        SlingShotActor slingshot1 = new SlingShotActor(4.2f, 1.1f);
        group.addActor(slingshot1);
        
        //group.addActor(new TextActor("AAA"));
        //group.addActor(new PopPointActor("AAA"));
		return group;
	}

}
