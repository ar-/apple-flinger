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

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.GameState;
import com.gitlab.ardash.appleflinger.helpers.Pref;
import com.gitlab.ardash.appleflinger.i18n.I18N;
import com.gitlab.ardash.appleflinger.missions.Mission;

public class MissionSelectScreen extends GenericScreen implements Screen {  
	   
    public MissionSelectScreen() {

	}
    
    @Override
    public void show() {
    	super.show();
		gm.setGameState(GameState.MISSION_SELECT);
    }
    
    @Override
    protected void buildGameGUI() {
    	super.buildGameGUI();
    	
        Table table = new Table();
        table.setFillParent(true);
        
        int i=0;
    	for (final Mission mission : Mission.values())
    	{
    		int thisMajor = 1;
    		if (mission.getMajor() == thisMajor)
    		{
		        final LevelButton btnMission = new LevelButton(mission.getMinor());
		        btnMission.moveBy(SCREEN_WIDTH/2-100, SCREEN_HEIGHT/2-100);
//	            table.add(btnStart);
	            table.add(btnMission).width(150).height(150);
	            
	            if (!GameManager.ALLLEVELS) // if not debugging, disable missions properly
	            	btnMission.setDisabled(!Pref.isMissionActivated(mission));
	            
	            btnMission.addListener(new ClickListener() {
	    			@Override
	    			public void clicked(InputEvent event, float x, float y) {
		            	if (btnMission.isDisabled())
		            		return;
	    				super.clicked(event, x, y);
	    				gm.resetAll(mission);
	    				gm.setScreen(mission);
	    			}
	    		});

	            if (++i%9==0)
	            	table.row();
    		}
    	}
    	
        guiStage.addActor(table);
        
        // message label below
        Label labelMessage = new Label(I18N.getString("chooseALevel"), menustyle);   //$NON-NLS-1$
		labelMessage.setPosition(0, SCREEN_HEIGHT/4);  
		labelMessage.setWidth(SCREEN_WIDTH);
		labelMessage.setAlignment(Align.center);
        guiStage.addActor(labelMessage);
        
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

    }
    
    
}  
