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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;

public class BackgroundActor extends Image{
	private ShapeRenderer shapeRenderer;
    
    public BackgroundActor() {
    	super(Assets.getTexture(TextureAsset.BACKGR));
    	setSize(GameWorld.UNIT_WIDTH*1.5f, GameWorld.UNIT_HEIGHT);
    	setPosition(-GameWorld.UNIT_WIDTH/4, -0.7f);
    	
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	final Color skyColor = new Color(88/255f,183/255f,255/255f,1);
    	final Color groundColor = new Color(62/255f,45/255f,29/255f,1);
        batch.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin();
        shapeRenderer.set(ShapeType.Filled);
        shapeRenderer.setColor(skyColor);
        shapeRenderer.rect(-GameWorld.UNIT_WIDTH/2, 0, GameWorld.UNIT_WIDTH*2, GameWorld.UNIT_HEIGHT*2);
        shapeRenderer.setColor(groundColor);
        shapeRenderer.rect(-GameWorld.UNIT_WIDTH/2, -GameWorld.UNIT_HEIGHT*2, GameWorld.UNIT_WIDTH*2, GameWorld.UNIT_HEIGHT*2);
        shapeRenderer.end();
        batch.begin();
    	super.draw(batch, parentAlpha);
    }
    
    @Override
    public boolean remove() {
    	shapeRenderer.dispose();
    	return super.remove();
    }
    
//    @Override
//    public Actor hit(float x, float y, boolean touchable) {
////    	return null;
//    	//return super.hit(x, y, touchable);
//    }
    
}
