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
package com.gitlab.ardash.appleflinger.missions;

import java.lang.reflect.Constructor;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.gitlab.ardash.appleflinger.GameWorld;

/**
 * All StageFiller implementations are lightweight and hold only the function to fill the stage.
 * So they can all be loaded.
 *
 */
	public enum Mission {
	    Empty,
	    M_1_1,
	    M_1_2,
	    M_1_3,
	    M_1_4,
	    M_1_5,
	    M_1_6,
	    M_1_7,
	    M_1_8,
	    M_1_9,
	    M_1_10,
	    M_1_11,
	    M_1_12,
	    M_1_13,
	    M_1_14,
	    M_1_15,
	    M_1_16,
	    M_1_17,
	    M_1_18,
	    END_OF_EPISODE_1,
	    M_2_1,
	    M_2_2,
	    M_2_3,
	    M_2_4,
	    M_2_5,
	    M_2_6,
	    M_2_7,
	    M_2_8,
	    M_2_9,
	    M_2_10,
	    M_2_11,
	    M_2_12,
	    M_2_13,
	    M_2_14,
	    M_2_15,
	    M_2_16,
	    M_2_17,
	    M_2_18,
	    END_OF_EPISODE_2,

	    NONE; // don't use this

	    /**
	     * validate all the statuses and check if all the classes are there
	     */
	    public static void validate ()
	    {
	    	for (Mission m : Mission.values())
	    	{
	    		m.getMajor();
	    		m.getMinor();
	    		m.getStageFiller();
	    	}
	    }
	    
	    public StageFiller getStageFiller()
	    {
	    	if (getDummies().contains(this))
	    		return null;
	    	try {
		    	Class<?> clazz = Class.forName("com.gitlab.ardash.appleflinger.missions.Mission"+toString()); 
		    	Constructor<?> ctor = clazz.getConstructor();
		    	//Object object = ctor.newInstance(new Object[] { ctorArgument });
				Object object = ctor.newInstance();
				if (object instanceof StageFiller)
					return (StageFiller)object;
				throw new RuntimeException("object was a class but not a stagefiller"); 
			} catch (Exception e) {
				throw new RuntimeException("Error getting the StageFillerClass "+toString(), e); 
			}
	    }

	    public int getMajor()
	    {
	    	if (getDummies().contains(this))
	    		return 0;
	    	String s = toString().split("_")[1]; 
	    	return Integer.valueOf(s);
	    }
	    
	    public int getMinor()
	    {
	    	if (getDummies().contains(this))
	    		return 0;
			String s = toString().split("_")[2]; 
	    	return Integer.valueOf(s);
	    }
	    
	    public Mission getNext()
	    {
	    	if (getDummies().contains(this))
	    		return END_OF_EPISODE_1;
	    	
	    	int thisOrd = this.ordinal();
	    	Mission next = Mission.values()[thisOrd+1];
	    	
	    	if (next.getMajor()!=this.getMajor())
	    		return END_OF_EPISODE_1;
	    	
	    	if (next.getMinor()!=this.getMinor()+1)
	    		return END_OF_EPISODE_1;
	    	
	    	return next;
	    }
	    
		public static EnumSet<Mission> getDummies()
	    {
	    	return EnumSet.of(Empty, NONE, END_OF_EPISODE_1, END_OF_EPISODE_2);
	    }
	    
	    public static Set<Integer> getAvailableEpisodes()
	    {
	    	Set<Integer> ret = new HashSet<>(2);
	    	for (Mission m : Mission.values())
	    	{
	    		if (m.getMajor()>0)
	    		{
	    			ret.add(m.getMajor());
	    		}
	    	}

	    	return ret;
	    }
	    
	    public interface StageFiller {
	    	/**
	    	 * fill the non mirror-part (in the middle) of the stage
	    	 * @param world
	    	 * @param stage
	    	 */
			public Group fillMirrorStage(GameWorld world);
	    	
	}
	
}
