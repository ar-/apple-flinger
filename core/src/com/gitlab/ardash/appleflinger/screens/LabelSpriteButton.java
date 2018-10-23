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

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.gitlab.ardash.appleflinger.global.Assets;

public class LabelSpriteButton extends Stack {

	private Label label;
	private SpriteButton btn;
	private Table table;

	public LabelSpriteButton(Sprite texture_up, String text) {
		
		btn = new SpriteButton(texture_up);
		add(btn);
		label = new Label(text, Assets.LabelStyleAsset.MINILABEL.style);
		
		// this is an exception: even if the language is set to latin based
		// the name of some languages, is written in another char set
		if (text.contains("и"))
		{
			LabelStyle originalStyle = Assets.LabelStyleAsset.MINILABEL.style;
			LabelStyle cyrillicStyle = new LabelStyle(originalStyle);
			cyrillicStyle.font = Assets.FontAsset.ZANTROKE_03_B2_DIAG_MINIL_CYRILLIC.font;
			label.setStyle(cyrillicStyle);
		}
		label.setAlignment(Align.center);
		table = new Table();
		table.add(label);
		add(table);
	}

    public LabelSpriteButton(Sprite sprite, String labelText, float wrapWidth) {
		this(sprite, labelText);
		label.setWrap(true);
		table.getCell(label).width(wrapWidth);
	}

	@Override
    public void draw(Batch batch, float parentAlpha) {

    	if (btn.isPressed() && !btn.isDisabled())
    	{
    		this.moveBy(10, -10);
        	super.draw(batch, parentAlpha);
    		this.moveBy(-10, 10);
    	}
    	else
    	{
        	super.draw(batch, parentAlpha);
    	}
    }
    
    @Override
    public Actor hit(float x, float y, boolean touchable) {
    	return btn.hit(x, y, touchable);
    }
    
    public Label getLabel() {
		return label;
	}

}
