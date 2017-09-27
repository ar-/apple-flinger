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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.StringBuilder;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;
import com.gitlab.ardash.appleflinger.i18n.I18N;
import com.gitlab.ardash.appleflinger.listeners.OnTextChangeListener;

public class TextInputDialog extends Dialog{

	private Image backgrPixel;
	protected OnTextChangeListener changeListener = null;
	
	private final Label lblHeadline;
	private final Label lblContent;

	public TextInputDialog() 
	{
		super("", new WindowStyle( //$NON-NLS-1$
				Assets.FontAsset.FLINGER_D64_B2_WB_DIAG_MINIL.font,Color.WHITE,
				new TextureRegionDrawable(new TextureRegion(Assets.getTexture(Assets.TextureAsset.LARGE_DIALOG)))
				));
		setModal(true);
		setMovable(false);
		setResizable(false);
		
        backgrPixel = new Image(Assets.getTexture(TextureAsset.BACKGR)); 
		backgrPixel.setSize(GenericScreen.SCREEN_WIDTH, GenericScreen.SCREEN_HEIGHT);
        backgrPixel.setColor(0, 0, 0, 0.8f);

        final LabelStyle lblstyle = Assets.LabelStyleAsset.MINILABEL.style;
		text(I18N.getString("inputRequired"), lblstyle); //$NON-NLS-1$
		
		getContentTable().row().minHeight(40).top();
		text("", lblstyle); //$NON-NLS-1$
		getContentTable().row();
		lblHeadline = new Label("", lblstyle);
		lblContent = new Label("", lblstyle);
		text(lblHeadline);
		getContentTable().row();
		text(lblContent);
		
		getContentTable().row().minHeight(40).top();
		text("", lblstyle); //$NON-NLS-1$

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
		
        SpriteButton btnCancel = new SpriteButton(Assets.SpriteAsset.BTN_SQ_EMPTY.get());
        btnCancel.setText(I18N.getString("cancel")); //$NON-NLS-1$
        button(btnCancel);
		
        SpriteButton btnOk = new SpriteButton(Assets.SpriteAsset.BTN_SQ_EMPTY.get());
		btnOk.setText(I18N.getString("okay")); //$NON-NLS-1$
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

	private SpriteButton makeCharButton(final String ch) {
		SpriteButton btnChar = new SpriteButton(Assets.SpriteAsset.BTN_BLANK.get());
        btnChar.setText(ch);
        
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
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		backgrPixel.draw(batch, parentAlpha); // semi black background
		super.draw(batch, parentAlpha);
	}

	public void show(Stage guiStage, String headline, String initText, OnTextChangeListener changeListener) {
		super.show(guiStage);
		lblHeadline.setText(headline);
		lblContent.setText(initText);
		this.changeListener=changeListener;
	}
	
}
