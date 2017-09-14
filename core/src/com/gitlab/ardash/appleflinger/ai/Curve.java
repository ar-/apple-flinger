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
package com.gitlab.ardash.appleflinger.ai;

import com.badlogic.gdx.math.Vector2;

public class Curve 
{
	public static final Vector2 initialForceVector = new Vector2();
	public final float dx; // constant identifier of the curve
	public final float dy; // constant identifier of the curve
	public final float forceVecClamp; // constant identifier of the curve
	public final Vector2 forceVector= new Vector2();
	public final Vector2 pullVector= new Vector2();
	public final Vector2 startPos= new Vector2();
	public float angle;
	public float v0;
	public Curve(float dx, float dy, float forceVecClamp) {
		super();
		this.dx = dx;
		this.dy = dy;
		this.forceVecClamp = forceVecClamp;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(dx);
		result = prime * result + Float.floatToIntBits(dy);
		result = prime * result + Float.floatToIntBits(forceVecClamp);
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
		Curve other = (Curve) obj;
		if (Float.floatToIntBits(dx) != Float.floatToIntBits(other.dx))
			return false;
		if (Float.floatToIntBits(dy) != Float.floatToIntBits(other.dy))
			return false;
		if (Float.floatToIntBits(forceVecClamp) != Float.floatToIntBits(other.forceVecClamp))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Curve [dx=" + dx + ", dy=" + dy + ", forceVecClamp=" + forceVecClamp + ", forceVector=" + forceVector + ", pullVector=" + pullVector //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ ", startPos=" + startPos + ", angle=" + angle + ", v0=" + v0 + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	
	
	
}
