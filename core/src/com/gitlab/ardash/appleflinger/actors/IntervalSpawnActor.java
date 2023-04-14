/*******************************************************************************
 * Copyright (C) 2023 Dmytro Vasyliovych Klymenko <DmytroVasyliovychKlymenko@super-sm.art>
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

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class IntervalSpawnActor extends Actor {

	private final Group spawnGroup;
	private final Class<? extends Actor> spawnType;
	private float spawnInterval;
	private float minInterval;
	private float maxInterval;

	public IntervalSpawnActor(Group spawnGroup, Class<? extends Actor> spawnType, float minInterval,
			float maxInterval) {
		this.spawnGroup = spawnGroup;
		this.spawnType = spawnType;
		this.minInterval = minInterval;
		this.maxInterval = maxInterval;
		this.spawnInterval = MathUtils.random(minInterval, maxInterval);
	}

	@Override
	public void act(float delta) {
//		System.out.println("IV " + spawnInterval);
		spawnInterval -= delta;
		if (spawnInterval <= 0) {
			Actor newActor = createSpawnActor();
			spawnGroup.addActor(newActor);
			spawnInterval = MathUtils.random(minInterval, maxInterval);
			//spawnInterval *= MathUtils.random(0.9f, 1.1f);
		}
	}

	@SuppressWarnings("deprecation")
	private Actor createSpawnActor() {
		try {
			return spawnType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Failed to create spawn actor", e);
		}
	}

}
