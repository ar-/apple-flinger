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
package com.gitlab.ardash.appleflinger.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class RubberBandActor extends Actor implements Disposable{
	private ShapeRenderer shapeRenderer;
    static private boolean projectionMatrixSet;
    public final Vector2 from;
    public final Vector2 to;
    public final Color color;
    public float thickness = 0.025f;
    
    public RubberBandActor() {
        shapeRenderer = new ShapeRenderer();
        from = new Vector2();
        to = new Vector2();
        color = new Color(85f/255f,34f/255f,0,0);
        projectionMatrixSet = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	super.draw(batch, parentAlpha);
        batch.end();
        if(!projectionMatrixSet){
           shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        }
        //shapeRenderer.rotate(0, 0, 1, -45);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(color);
        
        //shapeRenderer.rect(0, 0, 50, 50);
        //shapeRenderer.line(new Vector2(3.2f, 1.1f), new Vector2(4.2f, 2.1f));
        shapeRenderer.rectLine(from, to, thickness);
        shapeRenderer.end();
        batch.begin();
    }
    
    @Override
    public boolean remove() {
    	dispose();
    	return super.remove();
    }
    
    @Override
    public void dispose() {
    	shapeRenderer.dispose();
	}

}
