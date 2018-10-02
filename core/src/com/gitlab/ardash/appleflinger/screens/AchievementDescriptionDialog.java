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

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.helpers.Achievement;
import com.gitlab.ardash.appleflinger.helpers.BackButtonAdapter;
import com.gitlab.ardash.appleflinger.i18n.I18N;

public class AchievementDescriptionDialog extends AdvancedDialog{
	private final LabelSpriteButton btnOkay;

	public AchievementDescriptionDialog(Achievement a) 
	{
        final LabelStyle lblstyle = Assets.LabelStyleAsset.MINILABEL.style;
		text(I18N.getString(a.getNameId()), lblstyle); 
		
		getContentTable().row().minHeight(40).top();
		text("", lblstyle); 
		getContentTable().row();
		text(I18N.getString(a.getDescriptionId()), lblstyle); 
		getContentTable().row();
        
		btnOkay = new LabelSpriteButton(EMPTY_TEX, I18N.getString("okay"));
        button(btnOkay);
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
