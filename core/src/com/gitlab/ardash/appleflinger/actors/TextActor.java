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
package com.gitlab.ardash.appleflinger.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.i18n.I18N;

public class TextActor extends Actor{

	private String text ;
	public TextActor(String text) {
		super();
		this.text = text;
        font = Assets.FontAsset.NJNARUTO_72.font;
        font.setColor(0.5f,0.4f,0,1);   //Brown is an underated Colour
		this.scaleBy(0.01f);
	}

    BitmapFont font;

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
        font.draw(batch, I18N.getString("score")+": 0 "+text , getX(), getY()); //$NON-NLS-1$ //$NON-NLS-2$
        //Also remember that an actor uses local coordinates for drawing within
        //itself!
	}
}
