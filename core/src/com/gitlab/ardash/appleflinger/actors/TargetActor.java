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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.global.Assets.SoundGroupAsset;
import com.gitlab.ardash.appleflinger.global.PlayerStatus.PlayerSide;
import com.gitlab.ardash.appleflinger.helpers.SoundPlayer;

public class TargetActor extends CircleActor{
	
	protected enum EyeState 
	{
		MIDDLE, LEFT, RIGHT, UP,DOWN, INNER,OUTER
		, BLINK // closed but very short
//		, BLINK2 // close
//		, BLINK3 // open
//		, BLINK4 // close
		, CLOSED // must be the last one
	}

	/** private lifetime */
	private float privateTime = 0;
	private float lastAnimTimestamp = 0;
	private final float blinkClosedTime = 0.1f;
	private final float maxTimeToNExtAnim = 3f;
	private float timeToNextEyeAnim = MathUtils.random(1f, maxTimeToNExtAnim);
	private boolean eyeAnimationRunning = false;
	private EyeState currentEyeState = EyeState.MIDDLE;
	
//	private TextureRegionDrawable dork_drawable;
	private TextureRegionDrawable eyes_closed;
	private TextureRegionDrawable eyes_down;
	private TextureRegionDrawable eyes_up;
	private TextureRegionDrawable eyes_inner;
	private TextureRegionDrawable eyes_outer;
	private TextureRegionDrawable eyes_left;
	private TextureRegionDrawable eyes_right;
	
	private PlayerSide playerSide = null;
	
	public TargetActor(GameWorld world, MaterialConfig mc, float x, float y,
			float diameter, BodyType bodyType) {
		super(world, mc, x, y, diameter, bodyType);
		
		int addPixelSize =-30;
		int addPixelMove =20;
        //this.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.ben[0].getTexture(), addPixelMove, addPixelMove, Assets.ben[0].getWidth()+addPixelSize, Assets.ben[0].getHeight()+addPixelSize)));
//        Sprite spr = Assets.dork[0];
        //spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
//        spr.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
//        dork_drawable = new TextureRegionDrawable(spr);
        eyes_closed = new TextureRegionDrawable(Assets.SpriteAsset.EYES_CLOSED.get());
        eyes_down = new TextureRegionDrawable(Assets.SpriteAsset.EYES_DOWN.get());
        eyes_up = new TextureRegionDrawable(Assets.SpriteAsset.EYES_UP.get());
        eyes_inner = new TextureRegionDrawable(Assets.SpriteAsset.EYES_INNER.get());
        eyes_outer = new TextureRegionDrawable(Assets.SpriteAsset.EYES_OUTER.get());
        eyes_left = new TextureRegionDrawable(Assets.SpriteAsset.EYES_LEFT.get());
        eyes_right = new TextureRegionDrawable(Assets.SpriteAsset.EYES_RIGHT.get());
//		this.setDrawable(dork_drawable);

        // this actually sets the size of the image independently from the physic actor!!!
        scaleBy(0.25f);

        // configure the points-label
        damageLabelColor = Color.GREEN;
        
	}
	
	
	public TargetActor(GameWorld world, MaterialConfig mc, float x, float y,
			float diameter) {
		this(world, mc, x, y, diameter, BodyType.DynamicBody);
	}
	
	@Override
	public void playHitSound() {
		SoundPlayer.playSound(Assets.getRandomSound(SoundGroupAsset.DORK_HIT));
	}
	
	private EyeState pickRandomEyeState()
	{
		//1 in 3 chance for a blink
		final int blinkChance = MathUtils.random(0, 2);
		if (blinkChance==0)
			return EyeState.BLINK;
		// same change for all states including blink
		final int max = EyeState.CLOSED.ordinal();
		final int rand = MathUtils.random(0, max);
		return EyeState.values()[rand];
	}
	
	private void setAnimationStatus() {
		privateTime += Gdx.graphics.getDeltaTime();
		
		// is it time for the next animation (or animation step) ?
		if (lastAnimTimestamp+timeToNextEyeAnim <= privateTime)
		{
			lastAnimTimestamp = privateTime;
			
			//choose the next step
			switch (currentEyeState) {
			case MIDDLE:
				currentEyeState = pickRandomEyeState();
				break;
			case CLOSED:
				currentEyeState = EyeState.MIDDLE;
				break;
			default:
				currentEyeState = pickRandomEyeState();
				break;
			}
			//MathUtils.ran
			//System.out.println("new anim "+timeToNextEyeAnim);
			timeToNextEyeAnim = MathUtils.random(1f, maxTimeToNExtAnim);
			// eye blinking can be shorter
			if (currentEyeState == EyeState.BLINK)
				timeToNextEyeAnim = MathUtils.random(blinkClosedTime, blinkClosedTime*2f);
		}
		
	}

//	render
	@Override
	public void draw(Batch batch, float parentAlpha) {
		setAnimationStatus();
		// set the normal actor drawable for the background, and draw it
		final Drawable originalDrawable = getDrawable();
		super.draw(batch, parentAlpha);
		
		// choose a foreground (eyes etc.) and drwa it			switch (currentEyeState) {
		switch (currentEyeState) {
		case CLOSED:
		case BLINK:
			setDrawable(eyes_closed);
			break;
		case INNER:
			setDrawable(eyes_inner);
			break;
		case OUTER:
			setDrawable(eyes_outer);
			break;
		case LEFT:
			setDrawable(eyes_left);
			break;
		case RIGHT:
			setDrawable(eyes_right);
			break;
		case UP:
			setDrawable(eyes_up);
			break;
		case DOWN:
			setDrawable(eyes_down);
			break;
		case MIDDLE:
		default:
			break;
		}
		super.draw(batch, parentAlpha);
		
		// set back to normal for nest draw
		setDrawable(originalDrawable);

		
//		ben_drawable.getRegion().getTexture().getTextureData().prepare();
//		Pixmap pixmap = ben_drawable.getRegion().getTexture().getTextureData().consumePixmap();
//		
////		Pixmap pixmap = new Pixmap(
////        Pixmap pixmap = new Pixmap(512, 512, Pixmap.Format.RGBA8888);
////		Mandelbrot.generate(pixmap, 0.0, 0.0, 1.0, 1.0, 32);
//		ColorFilter.generate(pixmap, Color.BLUE, 100, 100, 100, 127, 127, 127, 255);
//		Texture texture = new Texture(pixmap);
//		this.setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
//		super.draw(batch, parentAlpha);
		// working color change:
		
////		batch.setColor(Color.RED);
//		batch.setColor(1,0.5f,0.5f,1);
//		validate();
//
////		Color color = getColor();
////		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
//
//		float x = getX();
//		float y = getY();
//		float scaleX = getScaleX();
//		float scaleY = getScaleY();
//		Sprite spr = Assets.ben[0];
//		
//		Drawable _drawable = getDrawable();
//		float _imageX, _imageY, _imageWidth, _imageHeight;
//		_imageX = getImageX();
//		_imageY = getImageY();
//		_imageWidth = getImageWidth();
//		_imageHeight = getImageHeight();
//		
//		if (_drawable instanceof TransformDrawable) {
//			float rotation = getRotation();
//			if (scaleX != 1 || scaleY != 1 || rotation != 0) {
//				((TransformDrawable)_drawable).draw(batch, x + _imageX, y + _imageY, getOriginX() - _imageX, getOriginY() - _imageY,
//					_imageWidth, _imageHeight, scaleX, scaleY, rotation);
//				return;
//			}
//		}
//		if (_drawable != null) _drawable.draw(batch, x + _imageX, y + _imageY, _imageWidth * scaleX, _imageHeight * scaleY);
	}

//	@Override  
//    public void act(float delta) {  
//        // here we override Actor's act() method to make the actor follow the box2d body  
//        super.act(delta);  
//        setOrigin(RADIUS, RADIUS);  
//        setRotation(MathUtils.radiansToDegrees * body.getAngle());  
//        setPosition(body.getPosition().x-RADIUS, body.getPosition().y-RADIUS);  
//    }



	public PlayerSide getPlayerSide() {
		return playerSide;
	}


	public void setPlayerSide(PlayerSide playerSide) {
		this.playerSide = playerSide;
	}
	
	@Override
	protected int getDamageToPointsFactor() {
		GameManager gm = GameManager.getInstance();
		if (gm.currentPlayer.side == this.playerSide)
			return 0;
		return 100;
	}
	
	@Override
	public void setToBeDestroyed() {
		super.setToBeDestroyed();
		Puff p = new Puff(getPhysicalCenterPosition().x, getPhysicalCenterPosition().y);
		getStage().addActor(p);
	}
	
	@Override
	public String toJavaString() {
		if (bodyType == BodyType.DynamicBody)
			return "group.addActor (new TargetActor(world, MaterialConfig."+mc+", "+(body.getPosition().x)+"f, "+(body.getPosition().y)+"f, 0.5f));"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	
//    	return "group.addActor (new BlockActor(world, MaterialConfig."+mc+", "+(body.getPosition().x)+"f, "+(body.getPosition().y)+"f, "+rr+"f, BodyType."+getBodyType()+"));";
		return "group.addActor (new TargetActor(world, MaterialConfig."+mc+", "+(body.getPosition().x)+"f, "+(body.getPosition().y)+"f, 0.5f, BodyType."+getBodyType()+"));"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}
	
	@Override
	public boolean remove() {
		// inform the game master
		GameManager.getInstance().onTargetDestroyed(this);
		return super.remove();
	}
	
}
