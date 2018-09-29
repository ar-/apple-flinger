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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.SoundGroupAsset;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.helpers.SoundPlayer;

public class PengActor extends GeneralTargetActor{
	
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
	private EyeState currentEyeState = EyeState.MIDDLE;
	
	private TextureRegionDrawable eyes_closed;
	
	public PengActor(GameWorld world, MaterialConfig mc, float x, float y,
			float diameter, BodyType bodyType) {
		super(world, mc, x, y, diameter, bodyType);
		
        eyes_closed = new TextureRegionDrawable(Assets.SpriteAsset.EYES_CLOSED_PENG.get());

        // this actually sets the size of the image independently from the physic actor!!!
        scaleBy(0.25f);

        // configure the points-label
        damageLabelColor = Color.GREEN;
        
	}
	
	
	public PengActor(GameWorld world, MaterialConfig mc, float x, float y,
			float diameter) {
		this(world, mc, x, y, diameter, BodyType.DynamicBody);
	}
	
	@Override
	public void playHitSound() {
		SoundPlayer.playSound(Assets.getRandomSound(SoundGroupAsset.DORK_HIT));
	}
	
	private static EyeState pickRandomEyeState()
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

			timeToNextEyeAnim = MathUtils.random(1f, maxTimeToNExtAnim);
			// eye blinking can be shorter
			if (currentEyeState == EyeState.BLINK)
				timeToNextEyeAnim = MathUtils.random(blinkClosedTime, blinkClosedTime*2f);
		}
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		setAnimationStatus();
		// set the normal actor drawable for the background, and draw it
		final Drawable originalDrawable = getDrawable();
		super.draw(batch, parentAlpha);
		
		// choose a foreground (eyes etc.) and draw it
		switch (currentEyeState) {
		case CLOSED:
		case BLINK:
			setDrawable(eyes_closed);
			break;
		case MIDDLE:
		default:
			break;
		}
		super.draw(batch, parentAlpha);
		
		// set back to normal for nest draw
		setDrawable(originalDrawable);
	}

	@Override
	public String toJavaString() {
		if (bodyType == BodyType.DynamicBody)
			return "group.addActor (new PengActor(world, MaterialConfig."+mc+", "+(body.getPosition().x)+"f, "+(body.getPosition().y)+"f, 0.5f));";    
	
		return "group.addActor (new PengActor(world, MaterialConfig."+mc+", "+(body.getPosition().x)+"f, "+(body.getPosition().y)+"f, 0.5f, BodyType."+getBodyType()+"));";     
	}
	
}
