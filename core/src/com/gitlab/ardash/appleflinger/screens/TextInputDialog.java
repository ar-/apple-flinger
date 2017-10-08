/*******************************************************************************
 * Copyright (C) 2017 Andreas Redmer <andreasredmer@mailchuck.com>
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

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.i18n.I18N;
import com.gitlab.ardash.appleflinger.listeners.OnTextChangeListener;

public class TextInputDialog extends AdvancedDialog{
	protected OnTextChangeListener changeListener = null;
	
	private final Label lblHeadline;
	private final Label lblContent;

	public TextInputDialog() 
	{
        final LabelStyle lblstyle = Assets.LabelStyleAsset.MINILABEL.style;
		text(I18N.getString("inputRequired"), lblstyle); 
		
		getContentTable().row().minHeight(40).top();
		text("", lblstyle); 
		getContentTable().row();
		lblHeadline = new Label("", lblstyle);
		lblContent = new Label("", lblstyle);
		text(lblHeadline);
		getContentTable().row();
		text(lblContent);
		
		getContentTable().row().minHeight(40).top();
		text("", lblstyle); 

        SpriteButton btnBackSpace = new SpriteButton(Assets.SpriteAsset.BTN_BACK.get());
        btnBackSpace.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		super.clicked(event, x, y);
        		String s = lblContent.getText().toString();
        		if (s.length()<=0)
        			return;
        		s= s.substring(0, s.length()-1);
        		lblContent.setText(s);
        	}
        });
        
		// TODO internationalize keyboard
		getContentTable().row();
		getContentTable().add(makeKeyboardRow("QWERTYUIOP"));
        getContentTable().add(btnBackSpace);
		getContentTable().row();
		getContentTable().add(makeKeyboardRow("ASDFGHJKL"));
		getContentTable().row();
		getContentTable().add(makeKeyboardRow("ZXCVBNM"));
		getContentTable().row();
		
		LabelSpriteButton btnCancel = new LabelSpriteButton(EMPTY_TEX, I18N.getString("cancel")); 
		button(btnCancel);
		
        LabelSpriteButton btnOk = new LabelSpriteButton(EMPTY_TEX, I18N.getString("okay")); 
		button(btnOk);
        
        btnOk.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		super.clicked(event, x, y);
        		changeListener.onTextChange(lblContent.getText().toString());
        	}
        });
    }

	private HorizontalGroup makeKeyboardRow(String string) {
		HorizontalGroup hg = new HorizontalGroup();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			hg.addActor(makeCharButton(c+""));
		}
		return hg;
	}

	private LabelSpriteButton makeCharButton(final String ch) {
		LabelSpriteButton btnChar = new LabelSpriteButton(Assets.SpriteAsset.BTN_BLANK.get(), ch);
        
        btnChar.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		super.clicked(event, x, y);
        		String s = lblContent.getText().toString();
        		if (s.length()>20)
        			return;
        		lblContent.setText(s+ch);

        	}
        });
        
		return btnChar;
	}
	
	public void show(Stage guiStage, String headline, String initText, OnTextChangeListener textChangeListener) {
		super.show(guiStage);
		lblHeadline.setText(headline);
		lblContent.setText(initText);
		this.changeListener=textChangeListener;
	}
	
}
