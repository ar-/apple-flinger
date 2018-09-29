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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.SnapshotArray;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;
import com.gitlab.ardash.appleflinger.helpers.LinearInterpolator;

public class SlingShotActor extends Group {
	
	private final RubberBandActor rubber1;
	private final RubberBandActor rubber2;
	
	/** smallest rb1len found until now*/
	private float minRB1lenFound;
	/** largest rb1len found until now*/
	private float maxRB1lenFound;
	private boolean isMirrored;

	// this group had a repaired hit()
	// note: must not be a disposing group, because projectile keeps existing after shot, so it can fly
	public class ProjectileContainer extends Group
	{
		@Override
		public Actor hit(float x, float y, boolean touchable) {
			return super.hit(x, y, touchable);
		}
	}

	private final ProjectileContainer projectileContainer;
	
    public SlingShotActor(float x, float y) { 
    	
    	// correct position to slingshot foot
    	x-=0.8f;
        
    	projectileContainer = new ProjectileContainer();
    	rubber1 = new RubberBandActor();
    	rubber2 = new RubberBandActor();
    	minRB1lenFound = 0.2280351f;
    	maxRB1lenFound = 0.7940331f;
    	isMirrored = false;

    	projectileContainer.setPosition(-x, -y); // shift back to 0,0
        Texture backtex = Assets.getTexture(TextureAsset.SLINGSHOTB);  
        backtex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//        backtex.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
		Image back = new Image();
		back.setDrawable(new TextureRegionDrawable(new TextureRegion(backtex)));
		back.setSize(0.9f, 0.9f);
        back.setScaling(Scaling.stretch);
        back.setAlign(Align.center);  
        back.setTouchable(Touchable.disabled);
		this.addActor(back);
          
		this.addActor(rubber1);
		this.addActor(projectileContainer);
		this.addActor(rubber2);
		
        Texture fronttex = Assets.getTexture(TextureAsset.SLINGSHOTF);  

        fronttex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image front = new Image();
		front.setDrawable(new TextureRegionDrawable(new TextureRegion(fronttex)));
		front.setSize(0.9f, 0.9f);
		front.setScaling(Scaling.stretch);
		front.setAlign(Align.center);
		front.setTouchable(Touchable.disabled);
		this.addActor(front);

        this.setPosition(x, y); // set the actor position at the box2d body position 
		rubber1.from.set(getSlingShotBackJoint());
		rubber1.to.set(getSlingShotCenter());
		rubber2.from.set(getSlingShotFrontJoint());
		rubber2.to.set(getSlingShotCenter());
		adjustRubberBandThickness();
    }

	private void adjustRubberBandThickness() {
		//final float minThickness = 0.025f;
		final float minThickness = 0.055f;
		final float maxThickness = 0.08890508f;
		
		// adjust by current length of back-side rubberband (rubber1)
		final float len = Math.abs(rubber1.from.dst(rubber1.to));
		
		if (len < minRB1lenFound)
			minRB1lenFound = len;
		if (len > maxRB1lenFound)
			maxRB1lenFound = len;
		
		final float minRB1len = minRB1lenFound;
		final float maxRB1len = maxRB1lenFound;
		
		// interpolate: x=lengths y=thicknesses
		final float newThickness = LinearInterpolator.ilx(minRB1len, maxThickness, maxRB1len, minThickness, len);
		rubber1.thickness= newThickness;
		rubber2.thickness= newThickness;

		//System.out.println(len + " : " + newThickness);
}

	/**
	 * @return the front joint where the front rubberband is connected to the slingshot
	 */
	private Vector2 getSlingShotFrontJoint() {
		//System.out.println(getSlingShotCenter().cpy().add(-0.10f,-0.1f));
		final Vector2 ret = getSlingShotCenter().cpy();
		final Vector2 offset = new Vector2(-0.10f,-0.1f);
		if (isMirrored)
		{
			offset.scl(-1,1);
		}
		return ret.add(offset);
		//return ret.add(-0.10f,-0.1f);
	}

	/**
	 * @return the back joint where the back rubberband is connected to the slingshot
	 */
	private Vector2 getSlingShotBackJoint() {
		final Vector2 ret = getSlingShotCenter().cpy(); // TODO not cpy becasue is new
		final Vector2 offset = new Vector2(0.22f,-0.06f);
		if (isMirrored)
		{
			offset.scl(-1,1);
		}
		return ret.add(offset);
		//return getSlingShotCenter().cpy().add(0.22f,-0.06f);
	}
    
    /**
     * returns the position where a projectile should be placed initially
     * @return
     */
    public Vector2 getSlingShotCenter ()
    {
    	Vector2 slingshotCenterOffset = new Vector2(0.5f,0.8f);
    	if (isMirrored)
    		slingshotCenterOffset.scl(-1f, 1);
       	return slingshotCenterOffset.add(getX(),getY());
       	//return new Vector2(getX()+0.5f, getY()+0.8f);
    }
    
    public void addProjectile(ProjectileActor pa)
    {
    	projectileContainer.addActor(pa);
    	pa.setPhysicalPosition(getSlingShotCenter());
    	
    	// notify the projectile, that it is currently holding it (so it can signal it to release later)
    	pa.setSlingShotActor(this);
    	//this.addActorAt(1, pa);
    }
    
    public PhysicsActor getCurrentProjectile ()
    {
    	SnapshotArray<Actor> projectiles = projectileContainer.getChildren();
		if (projectiles.size >0)
    	{
    		return (PhysicsActor)projectiles.first();
    	}
    	return null;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
    	// adjust rubberband to projectile before drawing
    	PhysicsActor pa = getCurrentProjectile();
    	if (pa != null)
    	{
    		//small offset, to put the between
    		// TODO the end of the slingshot rubber that connects to the projectile can be adjusted here
    		final float rubberx = pa.getX()+pa.getOriginX();
			final float rubbery = pa.getY()+pa.getOriginY();
			rubber1.to.set(rubberx,rubbery);
    		rubber2.to.set(rubberx,rubbery);
    	}
    	else // set them back to the initial position
    	{
			rubber1.to.set(getSlingShotFrontJoint());
    		rubber2.to.set(getSlingShotBackJoint());
    	}
    	adjustRubberBandThickness();

    	super.draw(batch, parentAlpha);
    }

    /**
     * this is called on shot. it relases th projectile from slingshot and adds it to the stage
     */
	public void releaseProjectile() {
		PhysicsActor pa = getCurrentProjectile();
		if (pa == null)
			return;
		projectileContainer.clear();
		// add the projectile to the parent of the slingshot (the stage) to have it still be visible
		// the parent is now a container group, we use getStage here
		this.getStage().addActor(pa);
    }
	
	@Override
	/**
	 * makes sure that the projectile container is always set back to 0,0
	 */
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
    	projectileContainer.setPosition(-x, -y); // shift back to 0,0
    	
    	if (isMirrored)
    	{
        	projectileContainer.setPosition(x, -y); // shift back to 0,0
    		//projectileContainer.setX(projectileContainer.getX()+1);
    	}
	}
	
	public boolean isMirrored() {
		return isMirrored;
	}

	public void setMirrored(boolean isMirrored) {
		this.isMirrored = isMirrored;
		
		// correct the rubberband connectors
		rubber1.from.set(getSlingShotBackJoint());
		rubber1.to.set(getSlingShotCenter());
		rubber2.from.set(getSlingShotFrontJoint());
		rubber2.to.set(getSlingShotCenter());
		
		// correct visible projectile position
		setPosition(getX(), getY());
		
		projectileContainer.setScale(-1, 1);
		
		this.setScale(-1, 1);
	}

//	@Override
//	public Actor hit(float x, float y, boolean touchable) {
//		return null; // not touchable
//	}

}
