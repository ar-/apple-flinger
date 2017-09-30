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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.i18n.I18N;

public class ThinkBubble extends Image{
	private float currentAlpha=0f;
	Label lbl;
	private final GameManager gm;
	public ThinkBubble(float stageX, float stageY) {
		super();
		Texture tex = Assets.getTexture(TextureAsset.THINKING);
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        this.setDrawable(new TextureRegionDrawable(new TextureRegion(tex)));
        //setPosition(stageX, stageY);
        setSize(1.5f, 1);
        //scaleBy(0.05f);
        lbl = new Label(I18N.s("thinking"), Assets.LabelStyleAsset.MINILABEL.style); //$NON-NLS-1$
        lbl.setSize(1,1);
        lbl.setAlignment(Align.center);
        gm = GameManager.getInstance();
	}
	
	@Override
	/**
	 * happens when added to stage again
	 */
	protected void setStage(Stage stage) {
		super.setStage(stage);
		currentAlpha=0f;
        gm.currentGameScreen.getGuiStage().addActor(lbl);
		final float x = getX()+getWidth()/2;
        final float y = getY()+getHeight()/2+0.1f;
		lbl.setPosition(x*GameWorld.UNIT_TO_SCREEN, y*GameWorld.UNIT_TO_SCREEN);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if (currentAlpha<1f)
		{
			currentAlpha+=0.01f;
			setColor(1,1,1,currentAlpha);
			lbl.setColor(1,1,1,currentAlpha);
		}
	}
	
	@Override
	/**
	 * fade out before actual remove
	 */
	public boolean remove() {
		if (currentAlpha>0)
		{
			currentAlpha-=0.04f; // must be > 0.01 because act() will increase it again
			return false;
		}
		lbl.remove();
		return super.remove();
	}
	
//	@Override
//	public void draw(Batch batch, float parentAlpha) {
//		super.draw(batch, parentAlpha);
//		BitmapFont font = Assets.FontAsset.NJNARUTO_GSTAGE.font;
////		BitmapFont font = new BitmapFont();
////        font.setColor(0.5f,0.4f,0,1);
//        
//        
//		//font.draw(batch, "DDDDDDDDDDDDDDDDDDDDDDDD", 10, 10,0.01f,Align.center,false);
//		font.draw(batch, "DDDDDDDDDDDDDDDDDDDDDDDD", 10, 10,   3,4,     0.01f,Align.center,false);
//	}
}
