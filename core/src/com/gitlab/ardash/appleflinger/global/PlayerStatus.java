/*******************************************************************************
 * Copyright (C) 2015-2017 Andreas Redmer <andreasredmer@mailchuck.com>
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
package com.gitlab.ardash.appleflinger.global;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gitlab.ardash.appleflinger.AdvancedStage;
import com.gitlab.ardash.appleflinger.actors.SlingShotActor;
import com.gitlab.ardash.appleflinger.actors.TargetActor;
import com.gitlab.ardash.appleflinger.listeners.OnPointsChangeListener;

public class PlayerStatus {
	
	public enum PlayerSide {
		LEFT,RIGHT, NONE
	}

	public String name;
	private int points;
	private int pointsThisShot;
	private int allPoints;
	private int wins;
	public final PlayerSide side;
	public SlingShotActor slingshot;
	private OnPointsChangeListener onPointsChangeListener;
	
	int targetsLostThisRound;
	private int enemiesKilledThisShot;
	
//	/**
//	 * reference to the point label. If this is tset then the points can be updated on the screen
//	 */
//	public Label pointLabel;

	public PlayerStatus(PlayerSide side) {
		this.side=side;
		allPoints=0;
		wins=0;
	}
	
	public void resetRound()
	{
		points =0;
		setOnPointsChangeListener(null);
		slingshot = null;
		setPoints(0);
		targetsLostThisRound=0;

		resetShot();
	}

	public void resetShot()
	{
		pointsThisShot=0;
		enemiesKilledThisShot=0;
	}

	public int getAllPoints() {
		return allPoints;
	}

	protected void incAllPoints(int allPoints) {
		this.allPoints += allPoints;
	}

	public int getWins() {
		return wins;
	}

	protected void incWins() {
		this.wins++;
	}



	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
		if (onPointsChangeListener !=null)
			onPointsChangeListener.onPointChange();
	}
	
	public void incPoints(int points) {
		setPoints(points+getPoints());
		this.pointsThisShot+=points;
	}

	public void setOnPointsChangeListener(
			OnPointsChangeListener onPointsChangeListener) {
		this.onPointsChangeListener = onPointsChangeListener;
	}

	protected int getPointsThisShot() {
		return pointsThisShot;
	}
	
	protected int getEnemiesKilledThisShot() {
		return enemiesKilledThisShot;
	}

	protected void incEnemiesKilledThisShot() {
		this.enemiesKilledThisShot++;
	}

	public int getOwnTargetsAlive() {
		int ret=0;
		// over the slingshot we can go to the stage - on the stage we can count the targets
		if (slingshot==null)
			return 99;
		final AdvancedStage s = (AdvancedStage)slingshot.getStage();
		for (Actor a : s.getAllActors())
		{
			if (a instanceof TargetActor) {
				TargetActor ta = (TargetActor) a;
				if (ta.getPlayerSide()==side)
					ret++;
			}
		}
		return ret;
	}
	
	
}
