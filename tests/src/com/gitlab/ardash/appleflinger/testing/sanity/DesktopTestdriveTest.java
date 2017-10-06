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
package com.gitlab.ardash.appleflinger.testing.sanity;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gitlab.ardash.appleflinger.ActionResolver;
import com.gitlab.ardash.appleflinger.AppleflingerGame;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.missions.Mission;
import com.gitlab.ardash.appleflinger.screens.MainMenuScreen;
import com.gitlab.ardash.appleflinger.screens.MissionSelectScreen;


public class DesktopTestdriveTest implements ActionResolver{

	GameManager gm = GameManager.getInstance();

	@Before
	public void init() {
//		gm.setOnCurrentPlayerChangeListener(new OnCurrentPlayerChangeListener() {
//			@Override
//			public void onCurrentPlayerChange() {
//			}
//		});
//		gm.resetAll(null);
//		gm.resetRound(Mission.M_1_1);
	}
	
	@Test
	public void minitTest1() {
//		String[] arg = new String[0];
//		DesktopLauncher.main(arg);
	}

	@Test
	public void minitTest2() {
		Mission.validate();
		Assets.validate();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280; // PHONE SIZE (for screenshots)
		config.height = 720; // PHONE SIZE
		config.allowSoftwareMode=true;
		config.useGL30 = true;
		
		try
		{
			final AppleflingerGame game = new AppleflingerGame(this);
			new LwjglApplication(game, config);
			TimeUnit.SECONDS.sleep(5);
			final MainMenuScreen mmscreen = (MainMenuScreen) game.getScreen();
			
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {
					gm.setPlayer2CPU(true);
					gm.setScreen(new MissionSelectScreen());
					
					Gdx.app.postRunnable(new Runnable() {
						
						@Override
						public void run() {
		    				gm.resetAll(Mission.M_1_1);
		    				gm.setScreen(Mission.M_1_1);
							
						}
					});
					
				}
			});
//			gm.setPlayer2CPU(true);
//			gm.setScreen(new MissionSelectScreen());
			TimeUnit.SECONDS.sleep(11);
			
		}
		catch (ExceptionInInitializerError e)
		{
			System.err.println("ExceptionInInitializerError");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
