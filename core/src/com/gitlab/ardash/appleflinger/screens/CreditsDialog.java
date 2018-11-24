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

import com.badlogic.gdx.scenes.scene2d.ui.AdvancedDialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.helpers.BackButtonAdapter;
import com.gitlab.ardash.appleflinger.i18n.I18N;

public class CreditsDialog extends AdvancedDialog{
	static String translators="Allan Nordhøy, Andreas Redmer, ButterflyOfFire, Dmitry Mikhirev, Heimen Stoffels, Iván Seoane, Jonatan Nyberg, Markel, Sérgio Marques, Simon Dottor, Sukhichev Mikhail Ivanovich, Verdulo, WaldiS, xin, ";
	
	public CreditsDialog() 
	{
		final int paragraphPadding = 20;
        final LabelStyle lblstyle = Assets.LabelStyleAsset.MINILABEL.style;
		text(I18N.getString("credits"), lblstyle); 
		
		getContentTable().row().minHeight(paragraphPadding).maxHeight(paragraphPadding).top();
		text("", lblstyle); 
		getContentTable().row();
		text(I18N.getString("programming"), lblstyle); 
		getContentTable().row();
		text("Andreas Redmer", lblstyle); 
		
		getContentTable().row().minHeight(paragraphPadding).maxHeight(paragraphPadding).top();
		text("", lblstyle); 
		getContentTable().row();
		text(I18N.getString("translations"), lblstyle); 
		getContentTable().row();
		translators = translators.trim();
		text(translators.substring(0, translators.length() - 1), lblstyle, GenericScreen.SCREEN_WIDTH*0.7f); 
		
		getContentTable().row().minHeight(paragraphPadding).maxHeight(paragraphPadding).top();
		text("", lblstyle); 
		getContentTable().row();
		text(I18N.getString("engine") + ": libGDX", lblstyle); 
		
		getContentTable().row().minHeight(paragraphPadding).maxHeight(paragraphPadding).top();
		text("", lblstyle); 
		getContentTable().row();
		text(I18N.getString("Music"), lblstyle); 
		getContentTable().row();
		text("Urbana-Metronica (wooh-yeah mix)", lblstyle); 
		getContentTable().row();
		text("spinningmerkaba feat. Morusque, Jeris, CSoul, Alex Beroza, Yours Truly", lblstyle); 
        
		LabelSpriteButton btnOkay = new LabelSpriteButton(EMPTY_TEX, I18N.getString("okay"));
        button(btnOkay);
        GenericScreen.linkHardwareBackButtonToAdapter(new BackButtonAdapter() {
			@Override
			public boolean handleBackButton() {
				GameManager.getInstance().setScreen(new MainMenuScreen());
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
	
}
