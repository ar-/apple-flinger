/*******************************************************************************
 * Copyright (C) 2015-2018 Andreas Redmer <ar-appleflinger@abga.be>
 * Copyright (C) 2017 Сухичев Михаил Иванович <sukhichev@yandex.ru>
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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.AdvancedDialog;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.SpriteAsset;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.helpers.BackButtonAdapter;
import com.gitlab.ardash.appleflinger.i18n.I18N;

public class LanguageDialog extends AdvancedDialog{
	
	static Stage lastStage = null;

	public LanguageDialog() {
        final LabelStyle lblstyle = Assets.LabelStyleAsset.MINILABEL.style;
		text(I18N.getString("select_language"), lblstyle); 
		getContentTable().getCells().get(0).colspan(3);
		
		getContentTable().row().minHeight(240).top();

		getContentTable().add(makeLingoButton(Assets.SpriteAsset.BTN_REFRESH, "\n\n"+I18N.s("default")));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_NB, "\n\nbokmål"));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_DE, "\n\ndeutsch"));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_EN, "\n\nenglish"));
		getContentTable().row().minHeight(180).top();
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_EO, "\n\nesperanto"));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_ES, "\n\nespañol"));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_FR, "\n\nfrançais"));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_GL, "\n\ngalego"));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_IT, "\n\nitaliano"));
		getContentTable().row().minHeight(180).top();
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_NL, "\n\nnederlands"));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_PL, "\n\npolski"));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_PT, "\n\nportuguês"));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_RU, "\n\nрусский")); //russkiy
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_SV, "\n\nsvenska"));

        getContentTable().row();
        
		LabelSpriteButton btnCancel = new LabelSpriteButton(EMPTY_TEX, I18N.getString("cancel"));
        button(btnCancel);
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
	
	private static LabelSpriteButton makeLingoButton(final Assets.SpriteAsset sa, String lbl) {
        LabelSpriteButton btn2 = new LabelSpriteButton(sa.get(), lbl);
        
        btn2.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		super.clicked(event, x, y);
                final String loadingTextInCurrentLanguage = I18N.s("loading");
                if (sa == SpriteAsset.BTN_REFRESH)
                {
                    I18N.loadLanguageBundle("");
                }
                else
                {
                	String languageCode = sa.name().replace("FLAG_", "").toLowerCase();
                	I18N.loadLanguageBundle(languageCode);
                }
                
                // show a brief (1 second) loading dialog as fix for #56 .
                // AndroidPreferences doesn't commit() on flush(), so it needs a bit more time.
                AdvancedDialog d = new AdvancedDialog();
				d.getContentTable().add(new Label(loadingTextInCurrentLanguage+"...", Assets.LabelStyleAsset.MINILABEL.style));
                d.show(lastStage);
                d.addAction(Actions.delay(1, Actions.run(new Runnable() {
					@Override
					public void run() {
		                GameManager.getInstance().getActionResolver().restartMySelf();
					}
				})));
        	}
        });
		return btn2;
	}
	
	@Override
	public Dialog show(Stage stage) {
		lastStage = stage;
		return super.show(stage);
	}
}
