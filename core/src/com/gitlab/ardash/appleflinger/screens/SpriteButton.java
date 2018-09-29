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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.SoundGroupAsset;
import com.gitlab.ardash.appleflinger.helpers.SoundPlayer;

public class SpriteButton extends ImageButton
{   
	private boolean isCheckable = false;
//	private boolean isMarked;
	
	@Deprecated
	/**
	 * does not contain new functions like marker or label
	 * @param texture_up
	 * @param texture_down
	 * @param background
	 */
    public SpriteButton(Texture texture_up, Texture texture_down, Texture background)
    {
        super(new SpriteDrawable(new Sprite(texture_up)),
              new SpriteDrawable(new Sprite(texture_down)));

        this.setBackground(new SpriteDrawable(new Sprite(background)));
    }
    
    public SpriteButton(Sprite texture_up, Sprite texture_down, Sprite background)
    {
        super(new SpriteDrawable(new Sprite(texture_up)),
              new SpriteDrawable(new Sprite(texture_down)));

        this.setBackground(new SpriteDrawable(new Sprite(background)));

        // all these buttons have the wood sound
        addListener(new ClickListener()
        {@Override
        public void clicked(InputEvent event, float x, float y) {
        	if (isDisabled())
        		return;
        	super.clicked(event, x, y);
			SoundPlayer.playSound(Assets.getRandomSound(SoundGroupAsset.WOOD_HIT));
        }});
        
//        marker = new Image(texture_up);
//        marker.setColor(Color.RED);
//        marker.scaleBy(1);
//        add(marker).padTop(-getHeight()/2).padLeft(-getWidth());

    }
    
    public SpriteButton(Sprite texture_up, Sprite texture_down)
    {
    	this(texture_up, texture_down, texture_down);
    }
    
    public SpriteButton(Sprite texture_up)
    {
    	this(texture_up, texture_up, texture_up);
    }
    
    @Override
    public void setDisabled(boolean isDisabled) {
    	super.setDisabled(isDisabled);
    	
    	if (isDisabled())
    	{
        	this.setColor(0, 0, 0, 0.5f);
    	}
    	else
    	{
        	this.setColor(0, 0, 0, 1f);
    	}
    		
    }    

    @Override
    public void draw(Batch batch, float parentAlpha) {

    	if (this.isPressed() && !isDisabled())
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

    public boolean isCheckable() {
		return isCheckable;
	}

	public void setCheckable(boolean isCheckable) {
		this.isCheckable = isCheckable;
		
		if (isCheckable)
		{
			final ImageButtonStyle style = getStyle();
			style.imageChecked = style.imageDown;
			setStyle(style);
		}
		
	}
	
	public void mark(boolean isMarked)
	{
    	if (isMarked)
    		getImage().setColor(Color.GREEN);
    	else
    		getImage().setColor(Color.WHITE);
		
	}

//	public boolean isMarked() {
//		return isMarked;
//	}
//
//	public void setMarked(boolean isMarked) {
//		this.isMarked = isMarked;
//	}
	
	
    
}
