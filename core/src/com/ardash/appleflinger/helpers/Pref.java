/*******************************************************************************
 * Copyright (C) 2017 Andreas Redmer <andreas.redmer@posteo.de>
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
package com.ardash.appleflinger.helpers;

import java.util.HashMap;

import com.ardash.appleflinger.global.Assets;
import com.ardash.appleflinger.global.Assets.MusicAsset;
import com.ardash.appleflinger.global.GameManager;
import com.ardash.appleflinger.global.GameState;
import com.ardash.appleflinger.missions.Mission;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Persistant Prefenrences
 *
 */
public class Pref {
	private static final String PREFS_NAME = "AppleFlingerPrefs"; //$NON-NLS-1$
	private static final String PEPPER = "tp6UgD9JKAoWSww1Ogduts2UFlFn9CTqQJX8Zdw7VBZv0o51jKD9QTnuoxzA2Lcj"; //$NON-NLS-1$
	private static final Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);
	
	private static Boolean googleSignInWanted = null;
	private static Boolean soundOn = null;
	private static Float musicVol = null;
	private static Float soundVol = null;
	private static HashMap<Mission,Boolean> activatedLevels = new HashMap<Mission, Boolean>();

	
	public static boolean getGoogleSignInWanted() {
		if (googleSignInWanted == null)
		{
			//Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);
			googleSignInWanted = prefs.getBoolean("googleSignInWanted", false); //$NON-NLS-1$
		}
		return googleSignInWanted;
	}
	
	public static void setGoogleSignInWanted(boolean agoogleSignInWanted) {
		googleSignInWanted = agoogleSignInWanted;
		//Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);
		prefs.putBoolean("googleSignInWanted", googleSignInWanted); //$NON-NLS-1$
		prefs.flush();
	}
	
	public static boolean getSoundOn() {
		if (soundOn == null)
		{
			//Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);
			soundOn = prefs.getBoolean("soundOn", true); //$NON-NLS-1$
		}
		return soundOn;
	}
	
	public static void setSoundOn(boolean asoundOn) {
		soundOn = asoundOn;
		GameManager gm = GameManager.getInstance();
		
		// stop musics that maybe playing in the background
		if (!soundOn)
		{
			SoundPlayer.stopMusic(Assets.getMusic(MusicAsset.BG));
		}
		else
		{
			// don't start the music in game (but only in menues)
			if (gm.getGameState() != GameState.WAIT_FOR_DRAG && gm.getGameState() != GameState.WAIT_FOR_PHYSICS && gm.getGameState() != GameState.DRAGGING)
				SoundPlayer.playMusic(Assets.getMusic(MusicAsset.BG));
		}
			
		
		//Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);
		prefs.putBoolean("soundOn", soundOn); //$NON-NLS-1$
		prefs.flush();
	}
	
	
	
	public static float getMusicVol() {
		if (musicVol == null)
		{
			musicVol = prefs.getFloat("musicVol", 1.0f); //$NON-NLS-1$
		}
		return musicVol;
	}

	public static void setMusicVol(float amusicVol) {
		musicVol = amusicVol;
		prefs.putFloat("musicVol", musicVol); //$NON-NLS-1$
		prefs.flush();
	}

	public static float getSoundVol() {
		if (soundVol == null)
		{
			soundVol = prefs.getFloat("soundVol", 1.0f); //$NON-NLS-1$
		}
		return soundVol;
	}

	public static void setSoundVol(float asoundVol) {
		soundVol = asoundVol;
		prefs.putFloat("soundVol", soundVol); //$NON-NLS-1$
		prefs.flush();
	}

	public static boolean isMissionActivated(Mission mission) {
		
		if (mission.getMinor() ==1)
			return true; // minor=1 can't be deactivated
		
		Boolean act = activatedLevels.get(mission);
		if (act == null)
		{
			Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);
			final String key = Calculator.md5(PEPPER+"Activated"+mission.name()); //$NON-NLS-1$
			act = prefs.getBoolean(key, false);
			activatedLevels.put(mission,act);
		}
		return act;
	}

	public static void setMissionActivated(Mission mission, boolean activated) {
		activatedLevels.put(mission,activated);
		Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);
		final String key = Calculator.md5(PEPPER+"Activated"+mission.name()); //$NON-NLS-1$
		prefs.putBoolean(key, activated);
		prefs.flush();
	}


}
