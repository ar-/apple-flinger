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

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.gitlab.ardash.appleflinger.global.Assets;

public class LevelButton extends SpriteButton{

	private Label labelMessage;

	public LevelButton(int label) {
		super(Assets.SpriteAsset.BTN_BLANK.get());
		//Assets.btn_blank.scale(0.5f);
		
        LabelStyle labelstyle = Assets.LabelStyleAsset.MISSIONNUMBER.style;
        labelMessage = new Label(String.valueOf(label), labelstyle);
		labelMessage.setSize(this.getWidth(), this.getHeight());
		labelMessage.setAlignment(Align.center);
        this.addActor(labelMessage);  
	}


	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (labelMessage.getWidth() != this.getWidth())
			labelMessage.setWidth(this.getWidth());
		if (labelMessage.getHeight() != this.getHeight())
			labelMessage.setHeight(this.getHeight());
	}


}
