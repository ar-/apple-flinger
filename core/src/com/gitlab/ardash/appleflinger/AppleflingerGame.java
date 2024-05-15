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
package com.gitlab.ardash.appleflinger;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.TimeUtils;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.missions.Mission;
import com.gitlab.ardash.appleflinger.screens.GameScreen;
import com.gitlab.ardash.appleflinger.screens.MainMenuScreen;
import com.gitlab.ardash.appleflinger.screens.MissionSelectScreen;
import com.gitlab.ardash.appleflinger.screens.SettingsScreen;

public class AppleflingerGame extends Game {
//	public class AppleflingerGame extends ApplicationAdapter {
	public GameWorld world;
	
	public final ActionResolver actionResolver;
	
	public AppleflingerGame(ActionResolver actionResolver) {
		super();
		this.actionResolver = actionResolver;
	}

	// FPS stuff
	private long secondStart=0;
	
	@Override
	public void create () {
		GameManager gm = GameManager.getInstance(); // also loggs the start time (track loading time, game runtime)
		Gdx.app.setLogLevel(Application.LOG_ERROR);
		if (GameManager.DEBUG)
			Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		//deactivate HW buttons
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
		
//		// if the game was paused, this method is also called (if is not the app init)
//		if (gm.currentGameScreen!=null)
//		{
//			// until now pause cause too much trouble, so I start a new mission of the same type
//			// that is as close as it can get
//			Assets.load();
//			gm.registerGameObject(this);
//			//setScreen(gm.currentGameScreen); // the will load the save game (BUGGY!!!)
//			setScreen(gm.getCurrentMission()); // start new game in same mission
//			return;
//		}
		
		gm.registerGameObject(this); // cant be in constructor
		//gm.setGameState(GameState.START_APP);

		setScreen(new MainMenuScreen());

//		setScreen(Mission.Empty);
//	gm.setGameState(GameState.LOADING_SCREEN);
		
	}
	
	/**
	 * mission name as parameter means we want to start the actual gameplay with a certain mission
	 * @param s
	 */
	public void setScreen(Mission mission) 
	{
		switch (mission) 
		{
			case NONE:
				MissionSelectScreen.setSelectedEpisode(0);
				this.setScreen(new MissionSelectScreen());
				break;
			case END_OF_EPISODE_1:
				// this comes when 1 episode is finished - set this to episode 2
				MissionSelectScreen.setSelectedEpisode(2);
				this.setScreen(new MissionSelectScreen());
				break;
			case END_OF_EPISODE_2:
				// this comes when 2 episode is finished - set this to episode selection sub-menu
				MissionSelectScreen.setSelectedEpisode(3);
				this.setScreen(new MissionSelectScreen());
				break;
			case END_OF_EPISODE_3:
				// this comes when 3 episode is finished - set this to episode selection sub-menu
				MissionSelectScreen.setSelectedEpisode(0);
				this.setScreen(new MissionSelectScreen());
				break;
			default:
				this.setScreen(new GameScreen(mission));
				break;
		}
	}
	
	@Override
	/**
	 * overridden to add a tracker
	 */
	public void setScreen(Screen screen) {
		super.setScreen(screen);
		String className = screen.getClass().getSimpleName();
		if (screen instanceof GameScreen) {
			className="GS"; 
		}
		if (screen instanceof MainMenuScreen) {
			className="MMS"; 
		}
		if (screen instanceof MissionSelectScreen) {
			className="MSS"; 
		}
		if (screen instanceof SettingsScreen) {
			className="SS"; 
		}
		Gdx.app.debug(this.getClass().getSimpleName(), "setting screen to "+className); 
	}
	
	@Override
	public void render() {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        //Gdx.gl.glClearColor(0.3f, 0.3f, 1.0f, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render(); // important!
		
		// inform gamemanger about runtime
		GameManager.getInstance().addGameRuntime(Gdx.graphics.getDeltaTime());
		
		// print FPS after screen render - only every second
		if (GameManager.DEBUG)
			if (secondStart < TimeUtils.millis()-1000)
			{
				final int fps = Gdx.graphics.getFramesPerSecond();
				System.out.println("FPS: "+fps);
				secondStart = TimeUtils.millis();
			}
	}
	
	
	@Override
		public void resume() {
			super.resume();
		}
	
	@Override
		public void pause() {
			super.pause();
//			Assets.dispose();
		}
	
	@Override
	public void dispose() {
		super.dispose();
		if (world !=null)
			world.dispose();
		if (screen !=null)
			screen.dispose();
		Assets.dispose();
	}

}
