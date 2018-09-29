/*******************************************************************************
 * Copyright (C) 2015-2017 Andreas Redmer <ar-appleflinger@abga.be>
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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;

public class BackgroundActor extends Image
{
	
	public enum BackgroundConfiguration
	{
		ORIGINAL, WINTER;
		
		public TextureAsset textureAsset;
		public Vector2 size;
		public Vector2 position;
		public Color skyColour;
		public Color groundColour;
		static
		{
			ORIGINAL.textureAsset = TextureAsset.BACKGR;
			ORIGINAL.size = new Vector2(GameWorld.UNIT_WIDTH*1.5f, GameWorld.UNIT_HEIGHT);
			ORIGINAL.position = new Vector2(-GameWorld.UNIT_WIDTH/4, -0.7f);
			ORIGINAL.skyColour = new Color(88/255f,183/255f,255/255f,1);
			ORIGINAL.groundColour = new Color(62/255f,45/255f,29/255f,1);
			WINTER.textureAsset = TextureAsset.BACKGR_WINTER;
			WINTER.size = new Vector2(GameWorld.UNIT_WIDTH*1.5f, GameWorld.UNIT_HEIGHT*1.1f);
			WINTER.position = new Vector2(-GameWorld.UNIT_WIDTH/4, -0.7f);
			WINTER.skyColour = new Color(93/255f,209/255f,232/255f,1);
			WINTER.groundColour = new Color(176/255f,227/255f,255/255f,1); //ice
		}
	}
	
	private ShapeRenderer shapeRenderer;
	private BackgroundConfiguration config;
    
    public BackgroundActor(BackgroundConfiguration config) {
    	super(Assets.getTexture(config.textureAsset));
    	this.config = config;
    	setSize(config.size.x, config.size.y);
    	setPosition(config.position.x, config.position.y);
    	
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) 
    {
        batch.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin();
        shapeRenderer.set(ShapeType.Filled);
        shapeRenderer.setColor(config.skyColour);
        shapeRenderer.rect(-GameWorld.UNIT_WIDTH/2, 0, GameWorld.UNIT_WIDTH*2, GameWorld.UNIT_HEIGHT*2);
        shapeRenderer.setColor(config.groundColour);
        shapeRenderer.rect(-GameWorld.UNIT_WIDTH/2, -GameWorld.UNIT_HEIGHT*2, GameWorld.UNIT_WIDTH*2, GameWorld.UNIT_HEIGHT*2);
        shapeRenderer.end();
        batch.begin();
    	super.draw(batch, parentAlpha);
    }
    
    @Override
    public boolean remove() 
    {
    	shapeRenderer.dispose();
    	return super.remove();
    }
    
}
