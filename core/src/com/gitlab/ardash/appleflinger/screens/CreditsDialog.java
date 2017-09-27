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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;
import com.gitlab.ardash.appleflinger.i18n.I18N;

public class CreditsDialog extends Dialog{

	private Image backgrPixel;

	public CreditsDialog() {
		super("", new WindowStyle( //$NON-NLS-1$
				Assets.FontAsset.FLINGER_D64_B2_WB_DIAG_MINIL.font,Color.WHITE,
				new TextureRegionDrawable(new TextureRegion(Assets.getTexture(Assets.TextureAsset.LARGE_DIALOG)))
				));
		setModal(true);
		setMovable(false);
		setResizable(false);
		
//		new Textur
		
        backgrPixel = new Image(Assets.getTexture(TextureAsset.BACKGR)); 
		backgrPixel.setSize(GenericScreen.SCREEN_WIDTH, GenericScreen.SCREEN_HEIGHT);
        backgrPixel.setColor(0, 0, 0, 0.8f);

        final LabelStyle lblstyle = Assets.LabelStyleAsset.MINILABEL.style;
		text(I18N.getString("credits"), lblstyle); //$NON-NLS-1$
		
		getContentTable().row().minHeight(40).top();
		text("", lblstyle); //$NON-NLS-1$
		getContentTable().row();
		text(I18N.getString("programming"), lblstyle); //$NON-NLS-1$
		getContentTable().row();
		text("Andreas", lblstyle); //$NON-NLS-1$
		
		getContentTable().row().minHeight(40).top();
		text("", lblstyle); //$NON-NLS-1$
		getContentTable().row();
		text(I18N.getString("engine"), lblstyle); //$NON-NLS-1$
		getContentTable().row();
		text("libGDX", lblstyle); //$NON-NLS-1$
		
		getContentTable().row().minHeight(40).top();
		text("", lblstyle); //$NON-NLS-1$
		getContentTable().row();
		text(I18N.getString("Music"), lblstyle); //$NON-NLS-1$
		getContentTable().row();
		text("Urbana-Metronica (wooh-yeah mix)", lblstyle); //$NON-NLS-1$
		getContentTable().row();
		text("spinningmerkaba feat. Morusque, Jeris, CSoul, Alex Beroza, Yours Truly", lblstyle); //$NON-NLS-1$
        
        SpriteButton btnYes = new SpriteButton(Assets.SpriteAsset.BTN_SQ_EMPTY.get());
		btnYes.setText(I18N.getString("okay")); //$NON-NLS-1$
        button(btnYes);
        	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		backgrPixel.draw(batch, parentAlpha); // semi black background
		super.draw(batch, parentAlpha);
	}
	
}
