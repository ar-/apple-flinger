/*******************************************************************************
 * Copyright (C) 2015-2023 Andreas Redmer <ar-appleflinger@abga.be>
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

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
//import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.gitlab.ardash.appleflinger.ActionResolver;
import com.gitlab.ardash.appleflinger.AppleflingerGame;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.missions.Mission;

public class DesktopLauncher implements ActionResolver{
	public static void main (String[] arg) {
		(new DesktopLauncher()).launch();
	}

	@SuppressWarnings("unused")
	public void launch()
	{
		Mission.validate();
		Assets.validate();
		//HCStrategy.validate(); //done in sanity check (test stage)

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
//		config.width = (int)(GameScreen.SCREEN_WIDTH);
//		config.height = (int)(GameScreen.SCREEN_HEIGHT);
//		config.width = (int)(GameScreen.SCREEN_WIDTH*0.5);
//		config.height = (int)(GameScreen.SCREEN_HEIGHT*0.5);

//		config.width = 1280; // PHONE SIZE (Ifor screenshots)
//		config.height = 720; // PHONE SIZE

		//		config.width = (int)(GameScreen.SCREEN_WIDTH*0.8);
//		config.height = (int)(GameScreen.SCREEN_HEIGHT*0.8);
//		config.width = (int)(GameScreen.SCREEN_WIDTH*0.25);
//		config.height = (int)(GameScreen.SCREEN_HEIGHT*0.25);
		
//		config.disableAudio = true;
		
		
        Settings settings = new Settings();
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;
//		TexturePacker.process(settings, "./art/sprites/ui", "./android/assets", "ui");
//		TexturePacker.process(settings, "../art/sprites/scene", "../android/assets", "scene");
		TexturePacker t; // keep this here, otherwise import gets always removed
		try
		{
			new Lwjgl3Application(new AppleflingerGame(this), config);
		}
		catch (ExceptionInInitializerError e)
		{
			System.err.println("ExceptionInInitializerError");
		}
	}
	
	@Override
	public void restartMySelf() {
		System.out.println("Cannot restart desktop app. Please start it again.");
		System.exit(0);
	}
	
	@Override
	public void keepScreenOn(boolean on) {
		Gdx.app.log("ActionResolver", "keepScreenOn "+ new Boolean(on).toString().toUpperCase());
	}

	@Override
	public boolean twPostRecommendation() {
		
		@SuppressWarnings("unused")
		String tweetUrl = 
			    String.format("https://twitter.com/intent/tweet?text=%s&url=%s",
			    		ARH.urlEncode(recommendationText), ARH.urlEncode(marketUrl));

		//Gdx.net.openURI(tweetUrl);
		if (GameManager.DEBUG)
			System.out.println("twPostRecommendation");
		return true;
	}

	@Override
	public boolean fbPostRecommendation() {
		if (GameManager.DEBUG)
			System.out.println("fbPostRecommendation");
		return true;
	}

	@Override
	public boolean gpPostRecommendation() {
		if (GameManager.DEBUG)
			System.out.println("gpPostRecommendation");
		return true;
	}

	@Override
	public boolean piPostRecommendation() {
		if (GameManager.DEBUG)
			System.out.println("piPostRecommendation");
		return true;
	}

}
