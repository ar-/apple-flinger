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
package com.ardash.appleflinger.ai;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
	 * describes a possible shot
	 * 
	 */
	class Shot implements Comparable<Shot> {
		/**
		 * 
		 */
//		private final PlayerSimulator playerSimulatorShot;
		public final long targetID;
		public final long executorRoundID; // the round that this shot has been added in (to delete old ones)
		public Vector2 pullVector = new Vector2();
		public Vector2 targetPos = new Vector2();
		public float dmgToTarget = 0f; // HP to destroy until target reached
		public float distanceToTargetCenter;
		public boolean isPhantomShot;

		public Shot(long targetID, long executorRoundID, float dmgToTarget) {
			this.targetID = targetID;
			this.executorRoundID = executorRoundID;
			this.dmgToTarget = dmgToTarget;
		}

		// public float getDifficulty()
		// {
		// return dmgToTarget+distanceToTargetCenter-pullVector.len(); // longer
		// pulls (stronger = better)
		// }

		@Override
		public String toString() {
			return "Shot [pullVector=" + pullVector + ",len =" + pullVector.len() + ", targetPos=" + targetPos + ", dmgToTarget=" + dmgToTarget //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					+ ", distanceToTargetCenter=" + distanceToTargetCenter + ", difficulty()=" /* //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$
																								 * +
																								 * getDifficulty
																								 * (
																								 * )
																								 */+ ", isPhantomShot=" + isPhantomShot + "]"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		

//		@Override
//		public boolean equals(Object o) {
//			if (this == o)
//				return true;
//			if (o == null || getClass() != o.getClass())
//				return false;
//
//			Shot shot = (Shot) o;
//
//			if (Float.compare(shot.dmgToTarget, dmgToTarget) != 0)
//				return false;
//			if (Float.compare(shot.distanceToTargetCenter, distanceToTargetCenter) != 0)
//				return false;
//			if (!pullVector.equals(shot.pullVector))
//				return false;
//			return targetPos.equals(shot.targetPos);
//
//		}
//
//		@Override
//		public int hashCode() {
//			int result = pullVector.hashCode();
//			result = 31 * result + targetPos.hashCode();
//			result = 31 * result + (dmgToTarget != +0.0f ? Float.floatToIntBits(dmgToTarget) : 0);
//			result = 31 * result + (distanceToTargetCenter != +0.0f ? Float.floatToIntBits(distanceToTargetCenter) : 0);
//			return result;
//		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
//			result = prime * result + getOuterType().hashCode();
			result = prime * result + Float.floatToIntBits(distanceToTargetCenter);
			result = prime * result + Float.floatToIntBits(dmgToTarget);
			result = prime * result + (isPhantomShot ? 1231 : 1237);
			result = prime * result + ((pullVector == null) ? 0 : pullVector.hashCode());
			result = prime * result + ((targetPos == null) ? 0 : targetPos.hashCode());
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
//			if (!getOuterType().equals(other.getOuterType()))
//				return false;
			if (Float.floatToIntBits(distanceToTargetCenter) != Float.floatToIntBits(other.distanceToTargetCenter))
				return false;
			if (Float.floatToIntBits(dmgToTarget) != Float.floatToIntBits(other.dmgToTarget))
				return false;
			if (isPhantomShot != other.isPhantomShot)
				return false;
			if (pullVector == null) {
				if (other.pullVector != null)
					return false;
			} else if (!pullVector.equals(other.pullVector))
				return false;
			if (targetPos == null) {
				if (other.targetPos != null)
					return false;
			} else if (!targetPos.equals(other.targetPos))
				return false;
			return true;
		}

		@Override
		public int compareTo(Shot o) {
			// TODO IDEA: add a hash code to each targetactor, save the target
			// in the shot, retain old list of shots. in the next shot, see if
			// there is still a target at the old taget position, if yes exctue
			// that shot from the old list and dont process a new list

			if (this == o)
				return 0;
			if (o == null)
				throw new RuntimeException("Cannot compare to the NULL shot"); //$NON-NLS-1$
			
			// use non phantoms first
			if (this.isPhantomShot != o.isPhantomShot)
				return (this.isPhantomShot ? 1 : -1);

			// prefer lower targets
//			final int comparedTargetY = (new Float(this.targetPos.y)).compareTo(o.targetPos.y);
//
//			if (comparedTargetY != 0)
//				return comparedTargetY;

//			// prefer a high strength-to-dmg-ratio
//			float thisstr2dmg=this.pullVector.len()/(this.dmgToTarget+0.0001f);
//			float otherstr2dmg=o.pullVector.len()/(o.dmgToTarget+0.0001f);
//			final int comparedS2D = (new Float(thisstr2dmg)).compareTo(otherstr2dmg);
//			
//			if (!MathUtils.isEqual(this.dmgToTarget, o.dmgToTarget, 0.00008f)) //  added one 0
//				return -comparedS2D;
			
			// rpefer less dmg on the way to the target
			final int comparedDMG = (new Float(this.dmgToTarget)).compareTo(o.dmgToTarget);
			if (!MathUtils.isEqual(this.dmgToTarget, o.dmgToTarget, 10f)) // +/-
																			// 1000
																			// is
																			// the
																			// same
				return comparedDMG;

			// prefer shots with better distance to the targets center
			final int comparedTCD = (new Float(this.distanceToTargetCenter)).compareTo(o.distanceToTargetCenter);
			if (!MathUtils.isEqual(this.distanceToTargetCenter, o.distanceToTargetCenter, 0.4f)) // +/-
																									// 0.2
																									// is
																									// the
																									// same,
																									// because
																									// it
																									// is
																									// projectile
																									// radius
				return comparedTCD;

			// prefer low angle (sharp forward) shots
			final int comparedAngle = (new Float(this.pullVector.angle())).compareTo(o.pullVector.angle());
			if (!MathUtils.isEqual(this.pullVector.angle(), o.pullVector.angle(), 10f)) // +/-
																						// 10
																						// deg
																						// is
																						// regarded
																						// to
																						// be
																						// the
																						// same
																						// angle
				return comparedAngle;

			// prefer strong shots
			final int comparedPower = (new Float(this.pullVector.len2())).compareTo(o.pullVector.len2());

			if (comparedPower != 0)
				return -comparedPower;

			// equal
			return 0;
		}

//		private PlayerSimulator getOuterType() {
//			return playerSimulatorShot;
//		}
	}
