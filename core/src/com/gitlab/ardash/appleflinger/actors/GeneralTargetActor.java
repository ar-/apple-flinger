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

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.global.PlayerStatus.PlayerSide;

public abstract class GeneralTargetActor extends CircleActor {

	private PlayerSide playerSide = null;

	public GeneralTargetActor(GameWorld world, MaterialConfig mc, float x,
			float y, float diameter, BodyType bodyType) {
		super(world, mc, x, y, diameter, bodyType);
	}

	public PlayerSide getPlayerSide() {
		return playerSide;
	}

	public void setPlayerSide(PlayerSide playerSide) {
		this.playerSide = playerSide;
	}

	@Override
	protected int getDamageToPointsFactor() {
		GameManager gm = GameManager.getInstance();
		if (gm.currentPlayer.side == this.playerSide)
			return 0;
		return 100;
	}

	@Override
	public void setToBeDestroyed() {
		super.setToBeDestroyed();
		Puff p = new Puff(getPhysicalCenterPosition().x, getPhysicalCenterPosition().y);
		getStage().addActor(p);
	}

	@Override
	public boolean remove() {
		// inform the game master
		GameManager.getInstance().onTargetDestroyed(this);
		return super.remove();
	}

}