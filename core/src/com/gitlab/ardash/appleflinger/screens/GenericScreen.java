/*******************************************************************************
 * Copyright (C) 2015-2017 Andreas Redmer <ar-appleflinger@abga.be>
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
package com.gitlab.ardash.appleflinger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.MusicAsset;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.GameState;
import com.gitlab.ardash.appleflinger.helpers.Pref;
import com.gitlab.ardash.appleflinger.helpers.SoundPlayer;

public abstract class GenericScreen implements Screen{

	public static float SCREEN_WIDTH = 1920;
	public static float SCREEN_HEIGHT = 1080;
	protected Stage guiStage;
	protected OrthographicCamera guiCam;
	protected GameManager gm;
	protected LabelStyle menustyle;

	public GenericScreen() {
		super();
		gm = GameManager.getInstance();
	}

	@Override
	public void show() {  
	    gm.setGameState(GameState.LOADING_SCREEN);
		SoundPlayer.playMusic(Assets.getMusic(MusicAsset.BG));
		
	    //this.stage = new Stage(); // create the GUI stage
	    //this.stage.setViewport(SCREEN_WIDTH, SCREEN_HEIGHT, false); // set the GUI stage viewport to the pixel size
	    //this.stage = new Stage(new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT));
	    //this.stage = new Stage(new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT));
	    this.guiStage = new Stage(new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT));
	    
	    gm.getInputMultiplexer().clear();
	    gm.getInputMultiplexer().addProcessor(guiStage);
	
	    // register with game manger
		gm.currentGameScreen=null;
	    
	    // add GUI actors to stage, labels, meters, buttons etc.  
	    buildGameGUI();
	
	}

	/**
	 * 
	 */
	protected void buildGameGUI() {
	        menustyle = Assets.LabelStyleAsset.BIGMENUSTYLE.style;
        
	        Image background = new Image(Assets.getTexture(TextureAsset.BACKGR));
	        background.setAlign(Align.center);
	        background.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
	        background.setScaling(Scaling.fill);
	        guiStage.addActor(background);
	        
	        Label gameTitle;
	        LabelStyle largelabelstyle = Assets.LabelStyleAsset.HEADLINE.style;
	        gameTitle = new Label("ApPlE FlInGeR", largelabelstyle);   
	        gameTitle.setPosition(0, SCREEN_HEIGHT*0.73f);  
	        gameTitle.setWidth(SCREEN_WIDTH);  
	        gameTitle.setAlignment(Align.center);  
	        guiStage.addActor(gameTitle);  
	        
	        final SpriteButton btnSound = new SpriteButton(Assets.SpriteAsset.BTN_SOUND_ON.get(),Assets.SpriteAsset.BTN_SOUND_OFF.get());
	        btnSound.moveBy(0, SCREEN_HEIGHT-100);
	        btnSound.setCheckable(true);
	        guiStage.addActor(btnSound);
	        btnSound.addListener(new ClickListener(){
	        	@Override
	        public void clicked(InputEvent event, float x, float y) {
	        	super.clicked(event, x, y);
	        		Pref.setSoundOn(!btnSound.isChecked());
	        }});
	        btnSound.setChecked(!Pref.getSoundOn());
	        
	        SpriteButton btn_world = new SpriteButton(Assets.SpriteAsset.BTN_WORLD.get());
	        btn_world.moveBy(SCREEN_WIDTH-400, SCREEN_HEIGHT-100);
	        guiStage.addActor(btn_world);
	        btn_world.addListener(new ClickListener(){@Override
	        public void clicked(InputEvent event, float x, float y) {
	        	super.clicked(event, x, y);
	        	new LanguageDialog().show(guiStage);
	        }});
	
	        SpriteButton btn_info = new SpriteButton(Assets.SpriteAsset.BTN_INFO.get());
	        btn_info.moveBy(SCREEN_WIDTH-250, SCREEN_HEIGHT-100);
	        guiStage.addActor(btn_info);
	        btn_info.addListener(new ClickListener(){@Override
	        public void clicked(InputEvent event, float x, float y) {
	        	super.clicked(event, x, y);
	        	new CreditsDialog().show(guiStage);
	        }});
	
	        // TODO add more other GUI elements here  
		}

	@Override
	public void render(float delta) {
	    guiCam = (OrthographicCamera) guiStage.getCamera();  
	    guiCam.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);  
	
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
	    Gdx.gl.glEnable(GL20.GL_TEXTURE_2D);  
	    guiCam.update();
	
	    guiStage.act(delta); // update GUI  
	    
	    if (GameManager.DEBUG)
	    {
	    	guiStage.setDebugAll(true);
	    }
	    guiStage.draw(); // draw the GUI  
	}

	@Override
	public void resize(int width, int height) {
		// pass true because camera is unchanged on this UI stage
		guiStage.getViewport().update(width, height, true);
	}

	public Stage getGuiStage() {
		return guiStage;
	}

	public OrthographicCamera getGuiCam() {
		return guiCam;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		// TODO with the current implementation a screen should be disposed after hiding
		//dispose();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
