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
package com.gitlab.ardash.appleflinger.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.MusicAsset;
import com.gitlab.ardash.appleflinger.global.Assets.SoundGroupAsset;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;
import com.gitlab.ardash.appleflinger.helpers.Pref;
import com.gitlab.ardash.appleflinger.helpers.SoundPlayer;
import com.gitlab.ardash.appleflinger.i18n.I18N;
import com.gitlab.ardash.appleflinger.listeners.OnTextChangeListener;

public class SettingsScreen extends GenericScreen {  
	   
    @Override
    protected void buildGameGUI() {
    	super.buildGameGUI();
    	
    	// SPRITE TABLE BACKGROUND
        Table spriteTable = new Table();
        spriteTable.setFillParent(true);
        spriteTable.setTouchable(Touchable.disabled);

        Image woddhangerBack = new Image(Assets.getTexture(TextureAsset.WOODHANGER));
        woddhangerBack.setTouchable(Touchable.disabled);
    	spriteTable.add(woddhangerBack).bottom().expandX().expandY();

    	Image volumeBack = new Image(Assets.getTexture(TextureAsset.VOLUME));
    	volumeBack.setTouchable(Touchable.disabled);
    	spriteTable.add(volumeBack).bottom().expandX().expandY();
        guiStage.addActor(spriteTable);

        // OVERLAY TABLE
        Table olTable = new Table();
        olTable.setFillParent(true);
        olTable.setTouchable(Touchable.disabled);

        Label lbl1 = new Label(I18N.getString("player_names"),menustyle); 
        lbl1.setAlignment(Align.center);
        lbl1.setTouchable(Touchable.disabled);
    	olTable.add(lbl1).center().top().width(SCREEN_WIDTH/2).expandY().padTop(238);

        final Label lblPN1 = new Label(Pref.getPlayer1name(),Assets.LabelStyleAsset.MINILABEL.style); 
        lblPN1.setAlignment(Align.center);
        
        final Label lblPN2 = new Label(Pref.getPlayer2name(),Assets.LabelStyleAsset.MINILABEL.style); 
        lblPN2.setAlignment(Align.center);
        
        // bugfix: if name not visible (empty or wrong font type: reset it)
        if (lblPN1.getWidth()==0)
        {
        	Pref.setPlayer1Name(I18N.s("player1"));
        	lblPN1.setText(Pref.getPlayer1name());
        }
        if (lblPN2.getWidth()==0)
        {
        	Pref.setPlayer2Name(I18N.s("player2"));
        	lblPN2.setText(Pref.getPlayer2name());
        }
        
        Label lbl2 = new Label(I18N.getString("Volume"),menustyle); 
        lbl2.setAlignment(Align.center);
        lbl2.setTouchable(Touchable.disabled);
    	
        Label lblSound = new Label(I18N.getString("SoundFX"),Assets.LabelStyleAsset.MINILABEL.style); 
        lblSound.setAlignment(Align.center);
        
        Label lblMusic = new Label(I18N.getString("Music"),Assets.LabelStyleAsset.MINILABEL.style); 
        lblMusic.setAlignment(Align.center);
        
        // RIGHT OVERLAY
    	Table olRight = new Table();
    	olRight.add(lbl2).center().top().width(SCREEN_WIDTH/2);
       	olRight.row();
    	olRight.add(lblSound).center().top().padTop(70);//.width(SCREEN_WIDTH/2);
       	olRight.row();
    	olRight.add(lblMusic).center().top().padTop(185);//.width(SCREEN_WIDTH/2);
    	
    	// PUT IN STAGE
    	olTable.add(olRight).center().top().width(SCREEN_WIDTH/2).expandY().padTop(238);
    	guiStage.addActor(olTable);

        Table signOutOverlayTable = new Table();
        signOutOverlayTable.setFillParent(true);
    	signOutOverlayTable.add(lblPN1).expandX().expandY().padBottom(-500).padRight(280);
    	signOutOverlayTable.add().expandX().expandY();
    	signOutOverlayTable.row();
    	signOutOverlayTable.add(lblPN2).expandX().expandY().padBottom(300).padRight(280);
    	signOutOverlayTable.add().expandX().expandY();
        guiStage.addActor(signOutOverlayTable);
        
        
        Drawable background = new SpriteDrawable(Assets.SpriteAsset.SLIDERBACK.get());
        Drawable knob = new SpriteDrawable(Assets.SpriteAsset.WOOD_BL_22_0.get());
		SliderStyle style = new SliderStyle(background, knob );
		
		final Slider soundSlider = new Slider(0f, 1f, 0.1f, false, style );
		soundSlider.setWidth(280);
		soundSlider.setValue(Pref.getSoundVol());
		soundSlider.setPosition(1300, 515);
		soundSlider.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Pref.setSoundVol(soundSlider.getValue());
				SoundPlayer.playSound(Assets.getRandomSound(SoundGroupAsset.WOOD_HIT));
			}
		});
		
		final Slider musicSlider = new Slider(0f, 1f, 0.1f, false, style );
		musicSlider.setWidth(280);
		musicSlider.setValue(Pref.getMusicVol());
		musicSlider.setPosition(1300, 515-245);
		musicSlider.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Pref.setMusicVol(musicSlider.getValue());
				final Music bg = Assets.getMusic(MusicAsset.BG);
				bg.setVolume(Pref.getMusicVol());
//				if (bg.isPlaying())
//				{
//					
//				}
			}
		});
		
		guiStage.addActor(soundSlider);
		guiStage.addActor(musicSlider);
        
        //back btn
        SpriteButton btnBack = new SpriteButton(Assets.SpriteAsset.BTN_BACK.get());
        btnBack.moveBy(SCREEN_WIDTH-100, SCREEN_HEIGHT-100);
        guiStage.addActor(btnBack);
        btnBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				gm.setScreen(new MainMenuScreen());
			}
		});
        linkHardwareBackButtonToButton(btnBack);
        
        // player lbl action listeners
        lblPN1.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		super.clicked(event, x, y);
	        	new TextInputDialog().show(guiStage,
	        			I18N.getString("name_of_player_1"),
	        			Pref.getPlayer1name(),
	        			new OnTextChangeListener() {
							@Override
							public void onTextChange(String newTextFromDialog) {
								if (newTextFromDialog.isEmpty())
									newTextFromDialog = I18N.s("player1");
			        			Pref.setPlayer1Name(newTextFromDialog);
			        			lblPN1.setText(newTextFromDialog);
							}
						});
        	}
        });

        // player lbl action listeners
        lblPN2.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		super.clicked(event, x, y);
	        	new TextInputDialog().show(guiStage,
	        			I18N.getString("name_of_player_2"),
	        			Pref.getPlayer2name(),
	        			new OnTextChangeListener() {
							@Override
							public void onTextChange(String newTextFromDialog) {
								if (newTextFromDialog.isEmpty())
									newTextFromDialog = I18N.s("player2");
			        			Pref.setPlayer2Name(newTextFromDialog);
			        			lblPN2.setText(newTextFromDialog);
							}
						});
        	}
        });

    }
    
    
}  
