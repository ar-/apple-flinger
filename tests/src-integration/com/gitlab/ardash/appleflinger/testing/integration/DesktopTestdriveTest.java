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
package com.gitlab.ardash.appleflinger.testing.integration;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gitlab.ardash.appleflinger.ActionResolver;
import com.gitlab.ardash.appleflinger.AppleflingerGame;
import com.gitlab.ardash.appleflinger.ai.PlayerSimulator;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.GameState;
import com.gitlab.ardash.appleflinger.helpers.Pref;
import com.gitlab.ardash.appleflinger.i18n.I18N;
import com.gitlab.ardash.appleflinger.missions.Mission;
import com.gitlab.ardash.appleflinger.screens.MissionSelectScreen;


public class DesktopTestdriveTest implements ActionResolver{
	
//	private static final String [] langs = {"es", "fr"};
	//private static final String [] langs = {"de","en","es","fr"};
	private static final String [] langs = {"ru"}; // separate non-latin load
	private static final Mission [] missions = {Mission.M_1_1, Mission.M_1_3, Mission.M_1_4
		,Mission.M_1_5, Mission.M_1_11, Mission.M_1_14};


	@BeforeClass
	public static void init() {
		final LwjglApplication g1 = startNewGame();
		Mission.validate();
		Assets.validate();
	}
	
	@Test
	public void takeScreenShots() {
		GameManager gm = GameManager.getInstance();
		for (final Mission missionToLoad : missions) {
			for (String lang : langs) {
				Pref.setLingo(lang);
				I18N.loadLanguageBundle(Pref.getLingo());
				// assign player names typical for the language
				Pref.setPlayer1Name(getPlayerName(lang, 1));
				Pref.setPlayer2Name(getPlayerName(lang, 2));
				
				loadMission(missionToLoad);
				
				sleep(5000); // some setups shake for 5 seconds
				PlayerSimulator.INSTANCE.playOneRound();
				sleep(2500); // 3 pull + 1 expire
				for (int i = 0 ; i< 60; i++)
				{
					sleep(1000); // wait for swingout
					if (gm.getGameState()==GameState.WAIT_FOR_DRAG)
						break;
				}
				PlayerSimulator.INSTANCE.playOneRound();
				sleep(4200);
				saveScreenShot("../metadata/"+lang+"/images/phoneScreenshots/"+missionToLoad+".png");
				sleep(1000);
				
			}
		}
	}

	private static String getPlayerName(String lang, int player) 
	{
		String ret = null;
		HashMap<String, String> n1 = new HashMap<String, String>();
		HashMap<String, String> n2 = new HashMap<String, String>();
		
		n1.put("de", "Stefan".toUpperCase());
		n2.put("de", "Bianka".toUpperCase());
		n1.put("en", "Ryan".toUpperCase());
		n2.put("en", "Amber".toUpperCase());
		n1.put("es", "Nicolás".toUpperCase());
		n2.put("es", "Sofía".toUpperCase());
		n1.put("fr", "Pierre".toUpperCase());
		n2.put("fr", "Debonnaire".toUpperCase());
		n1.put("ru", "Михаи́л".toUpperCase());
		n2.put("ru", "Светлана".toUpperCase());
		
		if (player == 1)
			ret = n1.get(lang);
		if (player == 2)
			ret = n2.get(lang);
		
		if (ret==null)
			throw new RuntimeException("no name found for "+lang+player);
		return ret;
	}

	private static void loadMission(final Mission missionToLoad) {
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				final GameManager gm = GameManager.getInstance();
				gm.setPlayer2CPU(false);
				gm.setScreen(new MissionSelectScreen());
				
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						gm.resetAll(missionToLoad);
	    				gm.setScreen(missionToLoad);
					}
				});
			}
		});
		sleep(3000);
	}

	private static LwjglApplication startNewGame() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280; // PHONE SIZE (for screenshots)
		config.height = 720; // PHONE SIZE
//		config.audioDeviceBufferCount=0;
//		config.audioDeviceBufferSize=0;
//		config.audioDeviceSimultaneousSources=0;
//		config.allowSoftwareMode=true;
//		config.useGL30 = true;
		
		//Locale.setDefault(newLocale);
		final AppleflingerGame game = new AppleflingerGame(new DesktopTestdriveTest());
		final LwjglApplication app = new LwjglApplication(game, config);
		config.disableAudio = true;
		sleep(5000);
		return app;
	}

	private static void saveScreenShot(final String filename) {
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				ScreenshotFactory.saveScreenshot(filename);
			}
		});
	}

	private static void sleep(int ms) {
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void restartMySelf() {
		// not used in this test implementation
	}

	@Override
	public boolean twPostRecommendation() {
		// not used in this test implementation
		return false;
	}

	@Override
	public boolean fbPostRecommendation() {
		// not used in this test implementation
		return false;
	}

	@Override
	public boolean gpPostRecommendation() {
		// not used in this test implementation
		return false;
	}

	@Override
	public boolean piPostRecommendation() {
		// not used in this test implementation
		return false;
	}

	@Override
	public void submitScoreGPGS(String LeaderBoardId, int score) {
		// not used in this test implementation
	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		// not used in this test implementation
	}

	@Override
	public void incrementAchievementGPGS(String achievementId, int NumSteps) {
		// not used in this test implementation
	}

	@Override
	public void getLeaderboardGPGS(String LeaderBoardId) {
		// not used in this test implementation
	}

	@Override
	public void getAchievementsGPGS() {
		// not used in this test implementation
	}

}
