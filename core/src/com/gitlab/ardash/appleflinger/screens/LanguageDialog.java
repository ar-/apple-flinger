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

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.SpriteAsset;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.i18n.I18N;

public class LanguageDialog extends AdvancedDialog{

	public LanguageDialog() {
        final LabelStyle lblstyle = Assets.LabelStyleAsset.MINILABEL.style;
		text(I18N.getString("select_language"), lblstyle); 
		
		getContentTable().row().minHeight(240).top();

		getContentTable().add(makeLingoButton(Assets.SpriteAsset.BTN_REFRESH, "\n\n"+I18N.s("default")));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_DE, "\n\ndeutsch"));
		getContentTable().row().minHeight(180).top();
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_EN, "\n\nenglish"));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_ES, "\n\nespañol"));
		getContentTable().row().minHeight(180).top();
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_FR, "\n\nfrançais"));

        getContentTable().row();
        
		LabelSpriteButton btnYes = new LabelSpriteButton(EMPTY_TEX, I18N.getString("cancel"));
        button(btnYes);
	}

	private static LabelSpriteButton makeLingoButton(final Assets.SpriteAsset sa, String lbl) {
//		SpriteButton btn = new SpriteButton(sa.get());
//        btn.setText("\n"+lbl);
//        btn.setLabelStyle(Assets.LabelStyleAsset.MINILABEL.style);
        LabelSpriteButton btn2 = new LabelSpriteButton(sa.get(), lbl);
        
        btn2.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		super.clicked(event, x, y);
                if (sa == SpriteAsset.BTN_REFRESH)
                {
                    I18N.loadLanguageBundle("");
                }
                else
                {
                	String languageCode = sa.name().replace("FLAG_", "").toLowerCase();
                	I18N.loadLanguageBundle(languageCode);
                }
                GameManager.getInstance().getActionResolver().restartMySelf();
        	}
        });
		return btn2;
	}
	
}
