/*******************************************************************************
 * Copyright (C) 2017-2018 Andreas Redmer <ar-appleflinger@abga.be>
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
import com.badlogic.gdx.scenes.scene2d.ui.AdvancedDialog;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.helpers.BackButtonAdapter;
import com.gitlab.ardash.appleflinger.helpers.Pref;
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
		
		final Table tblContent = getContentTable();
		tblContent.row().minHeight(40).top();
		text("", lblstyle); 
		tblContent.row();
		lblHeadline = new Label("", lblstyle);
		lblContent = new Label("", lblstyle);
		text(lblHeadline);
		tblContent.row();
		text(lblContent);
		
		tblContent.row().minHeight(40).top();
		text("", lblstyle); 

        addLocalizedKeyboard(tblContent);
		
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
        
        GenericScreen.linkHardwareBackButtonToAdapter(new BackButtonAdapter() {
			@Override
			public boolean handleBackButton() {
				GameManager.getInstance().setScreen(new SettingsScreen());
				return true;
			}
		});
	}
	
	@Override
	public void hide() {
		GenericScreen.linkHardwareBackButtonToAdapter(new BackButtonAdapter() {
			@Override
			public boolean handleBackButton() {
				GameManager.getInstance().setScreen(new MainMenuScreen());
				return true;
			}
		});
		super.hide();
	}
	
	private void addLocalizedKeyboard(final Table tblContent) {
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
        
		// internationalize keyboard
        String lingo = Pref.getLingo();
        if (lingo.length()>=2)
        {
        	lingo=lingo.substring(0, 2);
    		addLocalizedKeyboard(tblContent, btnBackSpace, lingo);
        }
        else
        {
    		addLocalizedKeyboard(tblContent, btnBackSpace, "en");
        }
	}

	private void addLocalizedKeyboard(final Table tblContent,
			SpriteButton btnBackSpace, String langCode)
	{
		langCode = langCode.toLowerCase();
		if (langCode.equals("nb")) {
			tblContent.row();
			tblContent.add(makeKeyboardRow("QWERTYUIOPÅ"));
	        tblContent.add(btnBackSpace);
			tblContent.row();
			tblContent.add(makeKeyboardRow("ASDFGHJKLØÆ"));
			tblContent.row();
			tblContent.add(makeKeyboardRow("ZXCVBNM"));
			tblContent.row();
		} else if (langCode.equals("de")) {
			tblContent.row();
			tblContent.add(makeKeyboardRow("QWERTZUIOPÜ"));
	        tblContent.add(btnBackSpace);
			tblContent.row();
			tblContent.add(makeKeyboardRow("ASDFGHJKLÖÄ"));
			tblContent.row();
			tblContent.add(makeKeyboardRow("YXCVBNM"));
			tblContent.row();
	    } else if (langCode.equals("eo")) {
			tblContent.row();
			tblContent.add(makeKeyboardRow("ŜĜERTŬUIOP"));
	        tblContent.add(btnBackSpace);
			tblContent.row();
			tblContent.add(makeKeyboardRow("ASDFGHJKLĴ"));
			tblContent.row();
			tblContent.add(makeKeyboardRow("ZĈCVBNMĤ"));
			tblContent.row();
		} else if (langCode.equals("es") || langCode.equals("gl") || langCode.equals("pt")) {
			tblContent.row();
			tblContent.add(makeKeyboardRow("QWERTYUIOPÜÚ"));
	        tblContent.add(btnBackSpace);
			tblContent.row();
			tblContent.add(makeKeyboardRow("ASDFGHJKLÑÇ"));
			tblContent.row();
			tblContent.add(makeKeyboardRow("ZXCVBNMÁÉÍÓ"));
		} else if (langCode.equals("fr")) {
			tblContent.row();
			tblContent.add(makeKeyboardRow("ÉÊËÈÔÇÀÂ")); // Ÿ is not in font
			tblContent.row();
			tblContent.add(makeKeyboardRow("AZERTYUIOPÛÜ"));
	        tblContent.add(btnBackSpace);
			tblContent.row();
			tblContent.add(makeKeyboardRow("QSDFGHJKLMÙ"));
			tblContent.row();
			tblContent.add(makeKeyboardRow("WXCVBNÏÎ"));
			tblContent.row();
		} else if (langCode.equals("it")) {
			tblContent.row();
			tblContent.add(makeKeyboardRow("QWERTYUIOPÈÉ"));
	        tblContent.add(btnBackSpace);
			tblContent.row();
			tblContent.add(makeKeyboardRow("ASDFGHJKLÒÀÙ"));
			tblContent.row();
			tblContent.add(makeKeyboardRow("ZXCVBNMÍÎÚÓÌ"));
			tblContent.row();
		} else if (langCode.equals("ru")) {
			tblContent.row();
			tblContent.add(makeKeyboardRow("ЙЦУКЕНГШЩЗХЪ"));
	        tblContent.add(btnBackSpace);
			tblContent.row();
			tblContent.add(makeKeyboardRow("ФЫВАПРОЛДЖЭ"));
			tblContent.row();
			tblContent.add(makeKeyboardRow("ЯЧСМИТЬБЮЁ"));
			tblContent.row();
		} else if (langCode.equals("sv")) {
			tblContent.row();
			tblContent.add(makeKeyboardRow("QWERTYUIOPÅ"));
	        tblContent.add(btnBackSpace);
			tblContent.row();
			tblContent.add(makeKeyboardRow("ASDFGHJKLÖÄ"));
			tblContent.row();
			tblContent.add(makeKeyboardRow("ZXCVBNM"));
			tblContent.row();
		} else {
			tblContent.row();
			tblContent.add(makeKeyboardRow("QWERTYUIOP"));
	        tblContent.add(btnBackSpace);
			tblContent.row();
			tblContent.add(makeKeyboardRow("ASDFGHJKL"));
			tblContent.row();
			tblContent.add(makeKeyboardRow("ZXCVBNM"));
			tblContent.row();
		}
		
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
