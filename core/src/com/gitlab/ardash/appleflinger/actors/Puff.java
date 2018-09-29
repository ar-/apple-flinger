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

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.gitlab.ardash.appleflinger.global.Assets;

public class Puff extends Image {
	
	private ArrayList<SpriteDrawable> sprites = new ArrayList<>(10);
	private int step =0;
	private long lastStepTime=0;
	
	public Puff(float x, float y) {
		Assets.SpriteGroupAsset ag = Assets.SpriteGroupAsset.PUFF;
		for (int i = 0;i<ag.size();i++)
			sprites.add(new SpriteDrawable(ag.get(i).get()));
		
		final float size = 0.5f;
		setPosition(x-size*0.5f,y-size*0.5f);
		setSize(size, size);
		lastStepTime = TimeUtils.millis();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		long now = TimeUtils.millis();
		if (lastStepTime > now-100)
			return;
		lastStepTime = TimeUtils.millis();
		
		if (step>9)
		{
			remove();
			return;
		}
		//step++;
		//step%=10;
		setDrawable(sprites.get(step++));
		//System.out.println("fly"+ delta);
	}

	@Override
	/**
	 * puff is never part of any collision detection
	 */
	public Actor hit(float x, float y, boolean touchable) {
		return null;
	}
}
