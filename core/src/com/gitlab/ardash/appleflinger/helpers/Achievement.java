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

public enum Achievement {
	ACH_BEGINNER_STREAK,
	ACH_BEGINNERS_LUCK,
	ACH_FIVEFOLD_POP,
	ACH_FOURFOLD_POP,
	ACH_POINTS_FARMER,
	ACH_TRIPLE_POP,
	ACH_WREAK_HAVOC;

	public String getNameId()
	{
		return "name_"+name().toLowerCase();
	}
	
	public String getDescriptionId()
	{
		return "desc_"+name().toLowerCase();
	}
	
	public int getGoal() {
		if (this.equals(ACH_POINTS_FARMER))
		{
			return 1000000;
		}
		
		return 1;
	}
	
	public boolean isIncremental() {
		return getGoal()!=1;
	}
}
