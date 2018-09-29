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
package com.gitlab.ardash.appleflinger.actors;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.helpers.LinearInterpolator;

public class Bird extends Image {
	
	private static long nextSpawnTime =0;
	private static final float minSpeed=0.01f;
	private static final float maxSpeed=0.02f;
	private static final float minY=GameWorld.UNIT_HEIGHT/2;
	private static final float maxY=GameWorld.UNIT_HEIGHT;
	private static final float minX=-2.5f;
	private static final float maxX=GameWorld.UNIT_WIDTH+2.5f;
	private static final float minBirdSize=0.05f;
	private static final float maxBirdSize=0.5f;
	private final float speed;

	/**
	 * should be called every frame - determines if it is time for a new bird and spawns it into the stage if needed
	 * @param stage
	 */
	public static void pingBirdSpawn(Group birdContainer)
	{
		final long minTimeBetweenSpawns = 100;
		final long maxTimeBetweenSpawns = 20000;
		if (nextSpawnTime ==0)
		{
			// first bird spawn time
			nextSpawnTime = TimeUtils.millis();
			return;
		}
		
		final long now = TimeUtils.millis();
		if (now>=nextSpawnTime)
		{
			birdContainer.addActor(new Bird());
			nextSpawnTime = now+MathUtils.random(minTimeBetweenSpawns,maxTimeBetweenSpawns);
		}
	}
	
	private ArrayList<SpriteDrawable> sprites = new ArrayList<>(12);
	private int step =0;
	
	public Bird() {
		Assets.SpriteGroupAsset ag = Assets.SpriteGroupAsset.BIRD;
		for (int i = 0;i<ag.size();i++)
			sprites.add(new SpriteDrawable(ag.get(i).get()));
		
		final float size = MathUtils.randomTriangular(minBirdSize, maxBirdSize,0.15f);
		final float y = MathUtils.randomTriangular(minY, maxY);
		
		// make the speed proportional to the size
		//speed = MathUtils.random(minSpeed, maxSpeed);
		speed = LinearInterpolator.ilx(minBirdSize, minSpeed, maxBirdSize, maxSpeed, size);
		
		setPosition(minX, y);
		setSize(size, size);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if (getX()>maxX)
		{
			remove();
			return;
		}
		step++;
		step%=12;
		this.moveBy(speed, 0);
		setDrawable(sprites.get(step));
		//System.out.println("fly"+ delta);
	}

	@Override
	/**
	 * bird is never part of any collision detection
	 */
	public Actor hit(float x, float y, boolean touchable) {
		return null;
	}
}
