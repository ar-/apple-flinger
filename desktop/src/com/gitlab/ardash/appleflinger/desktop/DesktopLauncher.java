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
package com.gitlab.ardash.appleflinger.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gitlab.ardash.appleflinger.ActionResolver;
import com.gitlab.ardash.appleflinger.AppleflingerGame;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.missions.Mission;

public class DesktopLauncher implements ActionResolver{
	public static void main (String[] arg) {
		(new DesktopLauncher()).launch();
	}
	
	
	public void launch()
	{
		Mission.validate();
		Assets.validate();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.width = (int)(GameScreen.SCREEN_WIDTH);
//		config.height = (int)(GameScreen.SCREEN_HEIGHT);
//		config.width = (int)(GameScreen.SCREEN_WIDTH*0.5);
//		config.height = (int)(GameScreen.SCREEN_HEIGHT*0.5);
		config.width = (int)(1280); // PHONE SIZE (Ifor screenshots)
		config.height = (int)(720); // PHONE SIZE
//		config.width = (int)(GameScreen.SCREEN_WIDTH*0.8);
//		config.height = (int)(GameScreen.SCREEN_HEIGHT*0.8);
//		config.width = (int)(GameScreen.SCREEN_WIDTH*0.25);
//		config.height = (int)(GameScreen.SCREEN_HEIGHT*0.25);
		
		
//        Settings settings = new Settings();
//        settings.maxWidth = 512;
//        settings.maxHeight = 512;
//        settings.maxWidth = 1024;
//        settings.maxHeight = 1024;
//		TexturePacker.process(settings, "../art/sprites/wood", "../android/assets", "wood");
//		TexturePacker.process(settings, "../art/sprites/ui", "../android/assets", "ui");
//        settings.maxWidth = 1024;
//        settings.maxHeight = 1024;
//        settings.scale = new float[1];
//        settings.scale[0]=0.5f; // Misc is scaled down to 50%
//		TexturePacker.process(settings, "../art/sprites/misc", "../android/assets", "misc");
		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//		}
		
		try
		{
			new LwjglApplication(new AppleflingerGame(this), config);
		}
		catch (ExceptionInInitializerError e)
		{
			System.err.println("ExceptionInInitializerError");
		}
	}

	@Override
	public boolean twPostRecommendation() {
		
		@SuppressWarnings("unused")
		String tweetUrl = 
			    String.format("https://twitter.com/intent/tweet?text=%s&url=%s",
			    		ARH.urlEncode(recommendationText), ARH.urlEncode(marketUrl));

		//Gdx.net.openURI(tweetUrl);
		System.out.println("twPostRecommendation");
		return true;
	}

	@Override
	public boolean fbPostRecommendation() {
		System.out.println("fbPostRecommendation");
		return true;
	}

	@Override
	public boolean gpPostRecommendation() {
		System.out.println("gpPostRecommendation");
		return true;
	}

	@Override
	public boolean piPostRecommendation() {
		System.out.println("piPostRecommendation");
		return true;
	}

	@Override
	public void submitScoreGPGS(String LeaderBoardId, int score) {
		System.out.println("submitScoreGPGS "+ LeaderBoardId + " : " + score);
	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		System.out.println("unlockAchievement " + achievementId);
	}

	@Override
	public void incrementAchievementGPGS(String achievementId, int NumSteps) {
		System.out.println("incrementAchievement " + achievementId);
	}
	
	@Override
	public void getLeaderboardGPGS(String LeaderBoardId) {
		System.out.println("getLeaderboardGPGS " + LeaderBoardId);
	}

	@Override
	public void getAchievementsGPGS() {
		System.out.println("getAchievementsGPGS");
	}

}
