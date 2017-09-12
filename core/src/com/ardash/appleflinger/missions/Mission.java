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
package com.ardash.appleflinger.missions;

import java.lang.reflect.Constructor;

import com.ardash.appleflinger.GameWorld;
import com.badlogic.gdx.scenes.scene2d.Group;

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

	    NONE; // don't use this

//	    private final int major;
//	    private final int minor;
//		public StageFiller stageFiller;
//	    Mission(int major, int minor, StageFiller mission) {
//	        this.major = major;
//	        this.minor = minor;
//	        this.stageFiller = mission;
//	    }
	    
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
		    	Class<?> clazz = Class.forName("com.ardash.appleflinger.missions.Mission"+toString()); //$NON-NLS-1$
		    	Constructor<?> ctor = clazz.getConstructor();
		    	//Object object = ctor.newInstance(new Object[] { ctorArgument });
				Object object = ctor.newInstance();
				if (object instanceof StageFiller)
					return (StageFiller)object;
				throw new RuntimeException("object was a class but not a stagefiller"); //$NON-NLS-1$
			} catch (Exception e) {
				throw new RuntimeException("Error getting the StageFillerClass", e); //$NON-NLS-1$
			}
	    }

	    public int getMajor()
	    {
	    	if (this == Empty || this == NONE || this == END_OF_CHAPTER)
	    		return 0;
	    	String s = toString().split("_")[1]; //$NON-NLS-1$
	    	return Integer.valueOf(s);
	    }
	    
	    public int getMinor()
	    {
	    	if (this == Empty || this == NONE || this == END_OF_CHAPTER)
	    		return 0;
			String s = toString().split("_")[2]; //$NON-NLS-1$
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
	    
	    public interface StageFiller {
	    	/**
	    	 * fill the non mirror-part (in the middle) of the stage
	    	 * @param world
	    	 * @param stage
	    	 */
			public Group fillMirrorStage(GameWorld world);
	    	
	}
	
}
