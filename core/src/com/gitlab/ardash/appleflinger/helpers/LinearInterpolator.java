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
package com.gitlab.ardash.appleflinger.helpers;

import com.badlogic.gdx.math.Vector2;

public class LinearInterpolator {
	@Deprecated
	public static Vector2 il(Vector2 p1, Vector2 p2, float x) {
		// Pre conditions
		// assert p1.x<x;
		// assert x<p2.x;
		if (x < p1.x)
			x = p1.x;
		if (x > p2.x)
			x = p2.x;
		// Calculate slope from p1 to p2
		final float divisor = p2.y - p1.y;
		float y;
		if (divisor != 0f) {
			float m = (p2.x - p1.x) / divisor;
			// Calculate y position of x
			y = (x - p1.x) * m + p1.y;
		} else
			y = p1.y; // if it is constant
		// create new point
		return new Vector2(x, y);

	}

	public static float ilx(Vector2 p1, Vector2 p2, float x) {
		return ilx(p1.x, p1.y, p2.x, p2.y, x);
	}

	public static float ilx(double p1x, double p1y, double p2x, double p2y, double x) {
		//swap points if needed
		if (p1x>p2x)
		{
			double tmpx = p1x;
			p1x=p2x;
			p2x=tmpx;
			double tmpy = p1y;
			p1y=p2y;
			p2y=tmpy;
		}
		
		if (x < p1x)
			x = p1x;
		if (x > p2x)
			x = p2x;
		double y;
		
		// normalise to (0,0) - remove offset
		final double dx = p2x-p1x;
		final double dy = p2y-p1y;
		
		if (dx==0.)
			return (float)p2y; // return maximum to avoid division by 0
		
		final double xPercent = x/dx;
		
		// now apply same percentage to y (assume xPercent==yPercent)
		y=dy*xPercent;
		// readd offset
		y+=p1y;
		
		// this impl. is wrong
//		// Calculate slope from p1 to p2
//		final float divisor = p2y - p1y;
//		if (divisor != 0f) {
//			float m = (p2x - p1x) / divisor;
//			// Calculate y position of x
//			y = (x - p1x) * m + p1y;
//		} else
//			y = p1y; // if it is constant
		
		

		return (float)y;
	}
}
