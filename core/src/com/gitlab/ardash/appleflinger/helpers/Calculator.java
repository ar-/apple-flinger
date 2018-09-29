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

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.gitlab.ardash.appleflinger.GameWorld;

public class Calculator {

	/**
	 * Converts a number in pixels to a number in scene units.
	 * Eg if the screen is 10 units Wide.
	 * You inout an image of 80 px and the screen width is 800 px
	 * This funtion returns 1, because in the scene the object is 1 unit wide
	 * @param pixels
	 * @return
	 */
	public static float convPixelToSceneUnits (float pixels)
	{
		return pixels/GameWorld.UNIT_TO_SCREEN;
	}
	
	public static String md5 (String s)
	{
		try {
			byte[] bytesOfMessage = s.getBytes("UTF-8"); 

			MessageDigest md = MessageDigest.getInstance("MD5"); 
			byte[] digest = md.digest(bytesOfMessage);
			
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext; 
			}
			return hashtext;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
