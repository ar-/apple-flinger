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
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;
import com.gitlab.ardash.appleflinger.i18n.I18N;

public class QuitDialog extends Dialog{

	private Image backgrPixel;

	public QuitDialog() {
		super("", new WindowStyle(Assets.FontAsset.FLINGER_03_B2_DIAG_MINIL.font,Color.WHITE,new TextureRegionDrawable(Assets.SpriteAsset.DIALOG.get()))); 
		setModal(true);
		setMovable(false);
		setResizable(false);
		
        backgrPixel = new Image(Assets.getTexture(TextureAsset.BACKGR)); 
		backgrPixel.setSize(GenericScreen.SCREEN_WIDTH, GenericScreen.SCREEN_HEIGHT);
        backgrPixel.setColor(0, 0, 0, 0.8f);

        text(I18N.getString("reallyQuit"), Assets.LabelStyleAsset.MINILABEL.style); 
        
        SpriteButton btnYes = new SpriteButton(Assets.SpriteAsset.BTN_SQ_EMPTY.get());
		btnYes.setText(I18N.getString("yes")); 
        SpriteButton btnNo = new SpriteButton(Assets.SpriteAsset.BTN_SQ_EMPTY.get());
        btnNo.setText(I18N.getString("no")); 
        
        button(btnYes);
        button(btnNo);
        
        btnYes.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		super.clicked(event, x, y);
        		System.exit(0);
        	}
        });
        
        //scaleBy(1.5f);
//        setScale(2);
//        setPosition(0, 0);
//        invalidate();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		backgrPixel.draw(batch, parentAlpha); // semi black background
		super.draw(batch, parentAlpha);
	}
	
}
