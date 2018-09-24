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
package com.gitlab.ardash.appleflinger.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameState;
import com.gitlab.ardash.appleflinger.i18n.I18N;

public class MainMenuScreen extends GenericScreen {

	private Label loadingLabel;

	public MainMenuScreen() {

	}

	@Override
	public void show() {
		super.show();
		gm.setGameState(GameState.MAIN_MENU);
		Assets.enqueueAll();
	}

	@Override
	protected void buildGameGUI() {
		super.buildGameGUI();
		
        SpriteButton btn_close = new SpriteButton(Assets.SpriteAsset.BTN_CLOSE.get());
        btn_close.moveBy(SCREEN_WIDTH-100, SCREEN_HEIGHT-100);
        btn_close.setCheckable(true);
        guiStage.addActor(btn_close);
        btn_close.addListener(new ClickListener(){
        	@Override
	        public void clicked(InputEvent event, float x, float y) {
	        	super.clicked(event, x, y);
	        	new QuitDialog().show(guiStage);
        }});
		
		Assets.SpriteAsset.BTN_1PLAYER.get().setSize(200, 200);
		Assets.SpriteAsset.BTN_2PLAYERS.get().setSize(200, 200);
		final SpriteButton btnStartP1 = new SpriteButton(Assets.SpriteAsset.BTN_1PLAYER.get());
		
		final SpriteButton btnStartP2 = new SpriteButton(Assets.SpriteAsset.BTN_2PLAYERS.get());

		final Label newGameLabel = new Label(I18N.getString("startNewGame"), menustyle); 
		newGameLabel.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				btnStartP1.mark(true);
				btnStartP2.mark(true);
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				btnStartP1.mark(false);
				btnStartP2.mark(false);
				super.touchUp(event, x, y, pointer, button);
			}
		});
		
		Table maintable = new Table();
		maintable.setFillParent(true);
		maintable.add(btnStartP1).width(SCREEN_WIDTH/4);
		maintable.add(newGameLabel).width(newGameLabel.getWidth()).colspan(2).center();
		maintable.add(btnStartP2).width(SCREEN_WIDTH/4);
		maintable.row();
		maintable.add(new Label(I18N.getString("alone"), menustyle)); 
		maintable.add().colspan(2);
		maintable.add(new Label(I18N.getString("twoPlayers"), menustyle)); 
		maintable.row();
		maintable.add().height(60);
		maintable.row();
		guiStage.addActor(maintable);
		
		btnStartP1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				gm.setPlayer2CPU(true);
				gm.setScreen(new MissionSelectScreen());
			}
		});

		btnStartP2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				gm.setPlayer2CPU(false);
				gm.setScreen(new MissionSelectScreen());
			}
		});

		SpriteButton btnSettings = new SpriteButton(Assets.SpriteAsset.BTN_SETTINGS.get());
        btnSettings.setHeight(btnSettings.getHeight()*2);
        btnSettings.setWidth( btnSettings.getWidth()*2);
        guiStage.addActor(btnSettings);

		btnSettings.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				gm.setScreen(new SettingsScreen());
			}
		});
		
//        Label labelMessage = new Label("Click a button to start a new game", labelstyle);
//		labelMessage.setPosition(0, SCREEN_HEIGHT *0.2f);
//		labelMessage.setWidth(SCREEN_WIDTH);
//		labelMessage.setAlignment(Align.center);
//		guiStage.addActor(labelMessage);

        LabelStyle loadinglabelstyle = new LabelStyle(Assets.LabelStyleAsset.BIGMENUSTYLE.style);
        loadinglabelstyle.fontColor=Color.RED;
        loadingLabel = new Label(I18N.getString("loading")+" ...\n0%", loadinglabelstyle);    
       // loadingLabel.setPosition(0, SCREEN_HEIGHT*5f);  
        loadingLabel.setWidth(SCREEN_WIDTH);
        loadingLabel.setHeight(SCREEN_HEIGHT);
        loadingLabel.setAlignment(Align.center);
        guiStage.addActor(loadingLabel);
        
        
        // social buttons
        SpriteButton btnTW = new SpriteButton(Assets.SpriteAsset.BTN_TW.get());
        SpriteButton btnFB = new SpriteButton(Assets.SpriteAsset.BTN_FB.get());
        SpriteButton btnGP = new SpriteButton(Assets.SpriteAsset.BTN_GP.get());
        SpriteButton btnPI = new SpriteButton(Assets.SpriteAsset.BTN_PI.get());

		Table bottomtable = new Table();
		bottomtable.setFillParent(true);
		bottomtable.align(Align.bottomRight);
		bottomtable.add(btnGP).width(btnGP.getWidth()*1.5f).height(btnGP.getWidth()*2);
		bottomtable.add(btnTW).width(btnTW.getWidth()*1.5f).height(btnTW.getWidth()*2);
		bottomtable.add(btnFB).width(btnFB.getWidth()*1.5f).height(btnFB.getWidth()*2);
		bottomtable.add(btnPI).width(btnPI.getWidth()*1.5f).height(btnPI.getWidth()*2);
		bottomtable.add().width(btnTW.getWidth()*0.25f);
		guiStage.addActor(bottomtable);

		btnTW.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				gm.getActionResolver().twPostRecommendation();
			}
		});

		btnFB.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				gm.getActionResolver().fbPostRecommendation();
			}
		});

		btnGP.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				gm.getActionResolver().gpPostRecommendation();
			}
		});

		btnPI.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				gm.getActionResolver().piPostRecommendation();
			}
		});

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		// load more assets in the background
		final boolean done = Assets.loadAsync();
		if (!done)
		{
			int percent = Assets.getPercentLoaded();
			loadingLabel.setText(String.format(I18N.getString("loading")+" ...\n%d%%", percent));  
		}
		else
		{
			loadingLabel.remove();
		}
		
	}
}
