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

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.openal.AL;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.gitlab.ardash.appleflinger.ActionResolver;
import com.gitlab.ardash.appleflinger.AppleflingerGame;
import com.gitlab.ardash.appleflinger.ai.PlayerSimulator;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.helpers.Pref;
import com.gitlab.ardash.appleflinger.i18n.I18N;
import com.gitlab.ardash.appleflinger.missions.Mission;
import com.gitlab.ardash.appleflinger.screens.MissionSelectScreen;


public class DesktopTestdriveTest implements ActionResolver{


	@BeforeClass
	public static void init() {
		final LwjglApplication g1 = startNewGame();
		Mission.validate();
		Assets.validate();
	}
	
	@Test
	public void takeScreenShotsEN() {
		Pref.setLingo("en");
		I18N.loadLanguageBundle(Pref.getLingo());
//		sleep(3000);
		final Mission missionToLoad = Mission.M_1_1;
		
		loadMission(missionToLoad);
		sleep(1000);
		PlayerSimulator.INSTANCE.playOneRound();
		sleep(5000);
		PlayerSimulator.INSTANCE.playOneRound();
		sleep(2500);
		saveScreenShot("s01en.png");
		sleep(10000);
	}

	@Test
	public void takeScreenShotsFR() {
		Pref.setLingo("fr");
		I18N.loadLanguageBundle(Pref.getLingo());
		final Mission missionToLoad = Mission.M_1_1;
		
		loadMission(missionToLoad);
//		saveScreenShot("s01fr.png");
		sleep(1000);
		PlayerSimulator.INSTANCE.playOneRound();
		sleep(5000);
		PlayerSimulator.INSTANCE.playOneRound();
		sleep(2900);
		saveScreenShot("s01fr.png");
		sleep(10000);
		AL.destroy();
		Gdx.app.exit();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean twPostRecommendation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fbPostRecommendation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gpPostRecommendation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean piPostRecommendation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void submitScoreGPGS(String LeaderBoardId, int score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incrementAchievementGPGS(String achievementId, int NumSteps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLeaderboardGPGS(String LeaderBoardId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAchievementsGPGS() {
		// TODO Auto-generated method stub
		
	}

}
