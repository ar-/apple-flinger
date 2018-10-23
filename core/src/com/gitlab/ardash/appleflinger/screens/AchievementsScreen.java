/*******************************************************************************
 * Copyright (C) 2018 Andreas Redmer <ar-appleflinger@abga.be>
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

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.helpers.Achievement;
import com.gitlab.ardash.appleflinger.helpers.BackButtonAdapter;
import com.gitlab.ardash.appleflinger.helpers.Pref;
import com.gitlab.ardash.appleflinger.i18n.I18N;

public class AchievementsScreen extends GenericScreen {
	public static final float ACH_BTN_WIDTH = 390f;
	public static final float ACH_TXT_WIDTH = ACH_BTN_WIDTH * 0.8f;
	   
	public AchievementsScreen() {

	}
    
    @Override
    public void show() {
    	super.show();
    }
    
    @Override
    protected void buildGameGUI() {
    	super.buildGameGUI();
    	
        Table table = new Table();
        table.setFillParent(true);
        
        int i=0;
        for (final Achievement a : Achievement.values())
        {
    		final Sprite sprite = Pref.isAchievementUnlocked(a)?Assets.SpriteAsset.BTN_UNLOCKED.get():Assets.SpriteAsset.BTN_LOCKED.get();
			sprite.setSize(ACH_BTN_WIDTH, ACH_BTN_WIDTH);
            final String luText = Pref.isAchievementUnlocked(a)?I18N.getString("unlocked"):I18N.getString("locked");
			final String labelText = I18N.getString(a.getNameId())+"\n"+luText;
			final LabelSpriteButton achBtn = new LabelSpriteButton(sprite, labelText, ACH_TXT_WIDTH);
    		table.add(achBtn);
    		achBtn.addListener(new ClickListener() {
    			@Override
    			public void clicked(InputEvent event, float x, float y) {
    				super.clicked(event, x, y);
    				GenericScreen.linkHardwareBackButtonToAdapter(new BackButtonAdapter() {
    					@Override
    					public boolean handleBackButton() {
    	    				GameManager.getInstance().setScreen(new AchievementsScreen());
    						return true;
    					}
    				});
    				new AchievementDescriptionDialog(a).show(guiStage);
    			}
    		});
    		
    		if (i++==3)
    			table.row();
        }

        guiStage.addActor(table);
        
        // message label below
        Label labelMessage = new Label(I18N.getString("clickAnAchievement"), menustyle);   
		labelMessage.setPosition(0, SCREEN_HEIGHT/7);  
		labelMessage.setWidth(SCREEN_WIDTH);
		labelMessage.setAlignment(Align.center);
        guiStage.addActor(labelMessage);
        
        //back btn
        final SpriteButton btnBack = new SpriteButton(Assets.SpriteAsset.BTN_BACK.get());
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
    }
    
}  
