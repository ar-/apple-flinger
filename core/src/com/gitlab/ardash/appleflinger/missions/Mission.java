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
package com.gitlab.ardash.appleflinger.missions;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.gitlab.ardash.appleflinger.GameWorld;

/**
 * All StageFiller impleentation are lightweigt and hold only the function to fill the stage.
 * So they can all be loaded.
 *
 */
	public enum Mission {
//	    Empty (0, 0, new MissionEmpty()),
//	    M_1_1 (1, 1, new MissionM_1_1()),
//	    M_1_2 (1, 2, new MissionM_1_2()),
//	    M_1_3 (1, 3, new MissionM_1_3()),
//
//	    NONE (0, 0, null); // don't use this
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
	    END_OF_CHAPTER,
	    M_2_1,
	    M_2_2,
	    M_2_3,
	    M_2_4,
	    M_2_5,
	    M_2_6,

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
	    	if (this == Empty || this == NONE || this == END_OF_CHAPTER)
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
	    	if (this == Empty || this == NONE || this == END_OF_CHAPTER)
	    		return 0;
	    	String s = toString().split("_")[1]; 
	    	return Integer.valueOf(s);
	    }
	    
	    public int getMinor()
	    {
	    	if (this == Empty || this == NONE || this == END_OF_CHAPTER)
	    		return 0;
			String s = toString().split("_")[2]; 
	    	return Integer.valueOf(s);
	    }
	    
	    public Mission getNext()
	    {
	    	if (this == Empty || this == NONE || this == END_OF_CHAPTER)
	    		return END_OF_CHAPTER;
	    	
	    	int thisOrd = this.ordinal();
	    	Mission next = Mission.values()[thisOrd+1];
	    	
	    	if (next.getMajor()!=this.getMajor())
	    		return END_OF_CHAPTER;
	    	
	    	if (next.getMinor()!=this.getMinor()+1)
	    		return END_OF_CHAPTER;
	    	
	    	return next;
	    }
	    
	    public static Set<Integer> getAvailableEpisodes()
	    {
	    	Set<Integer> ret = new HashSet<Integer>(2);
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
