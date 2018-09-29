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

import java.util.EnumSet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.SoundAsset;
import com.gitlab.ardash.appleflinger.global.Assets.SoundGroupAsset;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.helpers.SoundPlayer;

public class AppleActor extends ProjectileActor{

	public AppleActor(GameWorld world, MaterialConfig mc, float x, float y,
			float diameter, BodyType bodyType) {
		super(world, mc, x, y, diameter, bodyType);
		
		Texture tex = Assets.getTexture(TextureAsset.APPLE);
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        //this.setDrawable(new TextureRegionDrawable(new TextureRegion(tex)));
		
		//stretch the texture correctly onto the actor, so no postion,scaling,origin of all the actor/physics have to be messed up :(
		
		int addPixelSize =-70;
		int addPixelMove =28;
        this.setDrawable(new TextureRegionDrawable(new TextureRegion(tex, addPixelMove, addPixelMove, tex.getWidth()+addPixelSize, tex.getHeight()+addPixelSize)));
	}
	
	@Override
	public void playHitSound() {
		SoundPlayer.playSound(Assets.getRandomSound(SoundGroupAsset.APPLE_HIT));
		final EnumSet<SoundAsset> whizzSounds = Assets.SoundGroupAsset.WHIZZ.members;
		for (SoundAsset whizz: whizzSounds)
			SoundPlayer.stopSound(Assets.getSound(whizz));
	}

}
