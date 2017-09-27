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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.SpriteAsset;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.i18n.I18N;

public class LanguageDialog extends Dialog{

	private Image backgrPixel;

	public LanguageDialog() {
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
		text(I18N.getString("select_language"), lblstyle); //$NON-NLS-1$
		
		getContentTable().row().minHeight(240).top();

		getContentTable().add(makeLingoButton(Assets.SpriteAsset.BTN_REFRESH, I18N.s("default")));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_DE, "deutsch"));
		getContentTable().row().minHeight(180).top();
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_EN, "english"));
		getContentTable().add(makeLingoButton(Assets.SpriteAsset.FLAG_ES, "español"));

        getContentTable().row();
        
        SpriteButton btnYes = new SpriteButton(Assets.SpriteAsset.BTN_SQ_EMPTY.get());
		btnYes.setText(I18N.getString("cancel")); //$NON-NLS-1$
        button(btnYes);
	}

	private SpriteButton makeLingoButton(final Assets.SpriteAsset sa, String lbl) {
		SpriteButton btn = new SpriteButton(sa.get());
        btn.setText("\n"+lbl);
        btn.setLabelStyle(Assets.LabelStyleAsset.MINILABEL.style);
        
        btn.addListener(new ClickListener(){
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
		return btn;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		backgrPixel.draw(batch, parentAlpha); // semi black background
		super.draw(batch, parentAlpha);
	}
	
}
