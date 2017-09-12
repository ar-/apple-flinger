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
package com.ardash.appleflinger;

import com.ardash.appleflinger.ai.PlayerSimulator;
import com.ardash.appleflinger.global.Assets;
import com.ardash.appleflinger.global.GameManager;
import com.ardash.appleflinger.missions.Mission;
import com.ardash.appleflinger.screens.GameScreen;
import com.ardash.appleflinger.screens.MainMenuScreen;
import com.ardash.appleflinger.screens.MissionSelectScreen;
import com.ardash.appleflinger.screens.SettingsScreen;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.TimeUtils;

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
	public void setScreen(Mission mission) {
		PlayerSimulator.INSTANCE.stopThinking();
		if (mission != Mission.NONE && mission != Mission.END_OF_CHAPTER)
		{
			
			this.setScreen(new GameScreen(mission));
		}
		else
			// TODO this come when 1 chapter is finished - set this to chapter selection sub-menu as soon as it exists
			this.setScreen(new MissionSelectScreen());
			
	}
	
	@Override
	/**
	 * overridden to add a tracker
	 */
	public void setScreen(Screen screen) {
		super.setScreen(screen);
		String className = screen.getClass().getSimpleName();
		if (screen instanceof GameScreen) {
			className="GS"; //$NON-NLS-1$
		}
		if (screen instanceof MainMenuScreen) {
			className="MMS"; //$NON-NLS-1$
		}
		if (screen instanceof MissionSelectScreen) {
			className="MSS"; //$NON-NLS-1$
		}
		if (screen instanceof SettingsScreen) {
			className="SS"; //$NON-NLS-1$
		}
		Gdx.app.debug(this.getClass().getSimpleName(), "setting screen to "+className); //$NON-NLS-1$
	}
	
	public void render() {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        //Gdx.gl.glClearColor(0.3f, 0.3f, 1.0f, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render(); // important!
		
		// print FPS after screen render - only every second
		if (secondStart < TimeUtils.millis()-1000)
		{
//			System.out.println("FPS: "+Gdx.graphics.getFramesPerSecond());
			secondStart = TimeUtils.millis();
		}
	}
	
	
	@Override
		public void resume() {
			super.resume();
		//	PlayerSimulator.INSTANCE.pauseThinking();
		}
	
	@Override
		public void pause() {
			super.pause();
//			Assets.dispose();
		//	PlayerSimulator.INSTANCE.unpauseThinking();
		}
	
	@Override
	public void dispose() {
		super.dispose();
		if (world !=null)
			world.dispose();
		if (screen !=null)
			screen.dispose();
		PlayerSimulator.INSTANCE.stopThinking();
		Assets.dispose();
	}

}
