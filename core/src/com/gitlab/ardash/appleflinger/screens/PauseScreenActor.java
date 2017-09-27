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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;
import com.gitlab.ardash.appleflinger.i18n.I18N;
import com.gitlab.ardash.appleflinger.missions.Mission;

public class PauseScreenActor extends Group{
	
	public PauseScreenActor() {
        final float SCREEN_WIDTH = GameScreen.SCREEN_WIDTH;
		final float SCREEN_HEIGHT = GameScreen.SCREEN_HEIGHT;
		final GameManager gm = GameManager.getInstance();
		
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		
		// alpha background that catches all input events
        // ANY image, we draw is black anyway
        // choones backgr image, becasue maybe it gets drawn on stupid devices, just to be sure
        Image backgrPixel = new Image(Assets.getTexture(TextureAsset.BACKGR)); 
		backgrPixel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        backgrPixel.setColor(0, 0, 0, 0.8f);
        addActor(backgrPixel);
        // image must not let touches trough to stage
        backgrPixel.addListener(new InputListener(){@Override
        public boolean touchDown(InputEvent event, float x, float y,
        		int pointer, int button) {
        	return true;
//        	return super.touchDown(event, x, y, pointer, button);
        }});
		
        LabelStyle labelstyle = new LabelStyle();
        labelstyle.font = Assets.FontAsset.BURNSTOWNDAM_216.font;
        labelstyle.fontColor = Color.WHITE;
        
        
		Label labelMessage = new Label(I18N.getString("PAUSE"), labelstyle);   //$NON-NLS-1$
        labelMessage.setPosition(0, SCREEN_HEIGHT-labelstyle.font.getLineHeight());  
        labelMessage.setWidth(SCREEN_WIDTH);  
        labelMessage.setAlignment(Align.center);  
        addActor(labelMessage);  
        
        // close button
        SpriteButton btn_close = new SpriteButton(Assets.SpriteAsset.BTN_CLOSE.get());
        btn_close.moveBy(SCREEN_WIDTH-100, SCREEN_HEIGHT-100);
        btn_close.setCheckable(true);
        addActor(btn_close);
        btn_close.addListener(new ClickListener(){@Override
        public void clicked(InputEvent event, float x, float y) {
//        	setVisible(false);
        	remove();
        	gm.setPaused(false);

//        	super.clicked(event, x, y);
        }});

        // continue btn
        labelstyle.font = Assets.FontAsset.FLINGER_B73_B4_WB_BIGMENU.font;
        Sprite smallPlay = new Sprite(Assets.SpriteAsset.BTN_PLAY.get());
        smallPlay.setSize(100,100);
        SpriteButton btn1 = new SpriteButton(smallPlay);
        btn1.addListener(new ClickListener(){@Override
        public void clicked(InputEvent event, float x, float y) {
//        	setVisible(false);
        	remove();
        	gm.setPaused(false);

//        	super.clicked(event, x, y);
        }});
        
        //reload level btn
        SpriteButton btn2 = new SpriteButton(Assets.SpriteAsset.BTN_REFRESH.get());
        btn2.addListener(new ClickListener(){@Override
        public void clicked(InputEvent event, float x, float y) {
        	final Mission currentMission = gm.getCurrentMission();
			gm.resetRound(currentMission);
        	gm.setScreen(currentMission);
        	super.clicked(event, x, y);
        }});
        
        // abort btn
        SpriteButton btn3 = new SpriteButton(Assets.SpriteAsset.BTN_ABORT.get());
        btn3.addListener(new ClickListener(){@Override
        public void clicked(InputEvent event, float x, float y) {
        	gm.setScreen(new MissionSelectScreen());
        	super.clicked(event, x, y);
        }});

        //labels
        Label lbl1 = new Label(I18N.getString("continue"), labelstyle); //$NON-NLS-1$
		Label lbl2 = new Label(I18N.getString("restart"), labelstyle); //$NON-NLS-1$
		Label lbl3 = new Label(I18N.getString("quitToMenu"), labelstyle); //$NON-NLS-1$
        // centre table
        Table tbl = new Table();
        tbl.setFillParent(true);
        //btnStart.moveBy(SCREEN_WIDTH/2-100, SCREEN_HEIGHT/2-100);
        tbl.add(btn1).width(150).height(150);
        tbl.add(lbl1).width(150).height(150);
        tbl.row();
        tbl.add(btn2).width(150).height(150);
        tbl.add(lbl2).width(150).height(150);
        tbl.row();
        tbl.add(btn3).width(150).height(150);
        tbl.add(lbl3).width(150).height(150);
//        btn_1.align(Align.center);
        addActor(tbl);
		
        
//		setVisible(false);
	}
	
	@Override
	public boolean remove() {
		GameManager gm = GameManager.getInstance();
    	gm.setPaused(false);
		return super.remove();
	}
	

}
