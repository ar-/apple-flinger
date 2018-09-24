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
package com.gitlab.ardash.appleflinger.helpers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.MusicAsset;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.GameState;
import com.gitlab.ardash.appleflinger.i18n.I18N;
import com.gitlab.ardash.appleflinger.missions.Mission;

/**
 * Persistant Prefenrences
 *
 */
public class Pref {
	private static final String PREFS_NAME = "AppleFlingerPrefs"; 
	private static final String PEPPER = "tp6UgD9JKAoWSww1Ogduts2UFlFn9CTqQJX8Zdw7VBZv0o51jKD9QTnuoxzA2Lcj"; 
	private static final Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);
//	private static final HashMap h;
//	public enum P{
//		soundOn,
//		musicVol,
//		soundVol,
//		player1name,
//		player2name;
//	}
	
	private static Boolean soundOn = null;
	private static Float musicVol = null;
	private static Float soundVol = null;
	private static String player1name = null;
	private static String player2name = null;
	
	/**
	 * The selected language. Overrides the system language if selected.
	 * System language is "".
	 */
	private static String lingo = null;
	private static HashMap<Mission,Boolean> activatedLevels = new HashMap<>();
	private static HashMap<Achievement,Integer> unlockedAchievements = new HashMap<>();

	
	public static boolean getSoundOn() {
		if (soundOn == null)
		{
			soundOn = prefs.getBoolean("soundOn", true); 
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
		prefs.putBoolean("soundOn", soundOn); 
		prefs.flush();
	}
	
	
	
	public static float getMusicVol() {
		if (musicVol == null)
		{
			musicVol = prefs.getFloat("musicVol", 1.0f); 
		}
		return musicVol;
	}

	public static void setMusicVol(float amusicVol) {
		musicVol = amusicVol;
		prefs.putFloat("musicVol", musicVol); 
		prefs.flush();
	}

	public static float getSoundVol() {
		if (soundVol == null)
		{
			soundVol = prefs.getFloat("soundVol", 1.0f); 
		}
		return soundVol;
	}

	public static void setSoundVol(float asoundVol) {
		soundVol = asoundVol;
		prefs.putFloat("soundVol", soundVol); 
		prefs.flush();
	}

	public static String getPlayer1name() {
		if (player1name == null)
		{
			player1name = prefs.getString("player1name", I18N.s("player1")); 
		}
		return player1name;
	}
	
	public static void setPlayer1Name(String aplayer1name) {
		player1name = aplayer1name;
		prefs.putString("player1name", player1name); 
		prefs.flush();
	}

	public static String getPlayer2name() {
		if (player2name == null)
		{
			player2name = prefs.getString("player2name", I18N.s("player2")); 
		}
		return player2name;
	}
	
	public static void setPlayer2Name(String aplayer2name) {
		player2name = aplayer2name;
		prefs.putString("player2name", player2name); 
		prefs.flush();
	}

	public static String getLingo() {
		if (lingo == null)
		{
			lingo = prefs.getString("lingo", "");
		}
		return lingo;
	}
	
	public static void setLingo(String alingo) {
		lingo = alingo;
		prefs.putString("lingo", lingo);
		prefs.flush();
	}

	public static boolean isMissionActivated(Mission mission) {
		
		if (mission.getMinor() ==1)
			return true; // minor=1 can't be deactivated
		
		Boolean act = activatedLevels.get(mission);
		if (act == null)
		{
			final String key = Calculator.md5(PEPPER+"Activated"+mission.name()); 
			act = prefs.getBoolean(key, false);
			activatedLevels.put(mission,act);
		}
		return act;
	}

	public static void setMissionActivated(Mission mission, boolean activated) {
		activatedLevels.put(mission,activated);
		final String key = Calculator.md5(PEPPER+"Activated"+mission.name()); 
		prefs.putBoolean(key, activated);
		prefs.flush();
	}

	public static boolean isAchievementUnlocked(Achievement achievement) {
		Integer unl = getAchievementProgress(achievement);
		return unl.equals(Integer.MAX_VALUE);
	}

	private static Integer getAchievementProgress(Achievement achievement) {
		Integer progress = unlockedAchievements.get(achievement);
		if (progress == null)
		{
			final String key = Calculator.md5(PEPPER+"Achieved"+achievement.name()); 
			progress = prefs.getInteger(key, 0);
			unlockedAchievements.put(achievement,progress);
		}
		return progress;
	}

	public static void unlockAchievement(Achievement achievement){
		incrementAchievement(achievement, 1, 1);
	}
	
	public static void incrementAchievement(Achievement achievement, int numSteps, int goal){
		if (isAchievementUnlocked(achievement))
			return; // is already unlocked

		Integer progress = getAchievementProgress(achievement);
		progress+=numSteps;
		if (progress>=goal)
			progress = Integer.MAX_VALUE;
		unlockedAchievements.put(achievement,progress);
		final String key = Calculator.md5(PEPPER+"Achieved"+achievement.name()); 
		prefs.putInteger(key, progress);
		prefs.flush();
		
		Gdx.app.log("Pref", "Achievement " + achievement + " set to " + progress);
	}

}
