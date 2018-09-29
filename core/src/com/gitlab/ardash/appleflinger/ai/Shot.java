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
package com.gitlab.ardash.appleflinger.ai;

import com.badlogic.gdx.math.Vector2;
import com.gitlab.ardash.appleflinger.missions.Mission;

public class Shot{
	public Mission mission;
	public Vector2 pullVector;
	public Vector2 targetpos;
	float impact;
	public Shot(Mission mission, Vector2 pullVector, Vector2 targetpos,
			float impact) {
		super();
		this.mission = mission;
		this.pullVector = pullVector;
		this.targetpos = targetpos;
		this.impact = impact;
	}
	public Shot(Mission mission, float pullX, float pullY, float targetX, float targetY,
			float impact) {
		super();
		this.mission = mission;
		this.pullVector = new Vector2(pullX, pullY);
		this.targetpos = new Vector2(targetX, targetY);
		this.impact = impact;
	}
	@Override
	public String toString() {
		return "Shot [mission=" + mission + ", pullVector=" + pullVector
				+ ", targetpos=" + targetpos + ", impact=" + impact + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(impact);
		result = prime * result + ((mission == null) ? 0 : mission.hashCode());
		result = prime * result
				+ ((pullVector == null) ? 0 : pullVector.hashCode());
		result = prime * result
				+ ((targetpos == null) ? 0 : targetpos.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shot other = (Shot) obj;
		if (Float.floatToIntBits(impact) != Float.floatToIntBits(other.impact))
			return false;
		if (mission != other.mission)
			return false;
		if (pullVector == null) {
			if (other.pullVector != null)
				return false;
		} else if (!pullVector.equals(other.pullVector))
			return false;
		if (targetpos == null) {
			if (other.targetpos != null)
				return false;
		} else if (!targetpos.equals(other.targetpos))
			return false;
		return true;
	}
}
