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
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gitlab.ardash.appleflinger.AdvancedStage;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.ParticleAsset;
import com.gitlab.ardash.appleflinger.global.Assets.SoundGroupAsset;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.helpers.SoundPlayer;

public class TntActor extends BlockActor{

	
	public TntActor(GameWorld world, float x, float y, float rotation) {
		super(world, MaterialConfig.WOOD_TNT, x, y, rotation, BodyType.DynamicBody);
		
        // configure the points-label
        damageLabelColor = Color.YELLOW;
        
		particleEffect=new ParticleEffect( Assets.getParticleEffect(ParticleAsset.WOOD_RECT));
        particleEffect.scaleEffect(1f/GameWorld.UNIT_TO_SCREEN);

	}

	// this constructor provides the Static body type option, for the sandbox mode, it should not appear in the actual game
	public TntActor(GameWorld world, float x, float y, float rotation, BodyType bodyType) {
		super(world, MaterialConfig.WOOD_TNT, x, y, rotation, bodyType);
	}
	
	@Override
	protected int getDamageToPointsFactor() {
		return 20;
	}
	
	@Override
	public void playDestSound() {
		super.playDestSound();
		SoundPlayer.playSound(Assets.getRandomSound(SoundGroupAsset.EXPLOSION));
	}
	
	@Override
	public void setToBeDestroyed() {
		super.setToBeDestroyed();
		Puff p = new Puff(getPhysicalCenterPosition().x, getPhysicalCenterPosition().y);
		p.scaleBy(2f);
		p.moveBy(-p.getWidth(), -p.getHeight());
		getStage().addActor(p);
		
		// apply force and damage to surrounding actors
		float explosionRadius = 1.1f;
		final Vector2 expCenter = this.getPhysicalCenterPosition();
		for (Actor a : ((AdvancedStage)getStage()).getAllActors())
		{
			if (a instanceof PhysicsActor) {
				PhysicsActor pa = (PhysicsActor) a;
				if (pa == this)
					continue;

				final Vector2 expVictimCenter = pa.getPhysicalCenterPosition();
				
				if (expCenter.dst(expVictimCenter)>explosionRadius)
					continue ; // too far away
				
				Vector2 force = expCenter.cpy().sub(expVictimCenter);
				final float len = force.len();
				if (len==0f)
					continue;
				force.setLength(1f/len);
				force.scl(-40f);
				
				if (pa.getBodyType() == BodyType.DynamicBody)
				{
					// reduce force on projectile
					if (pa instanceof ProjectileActor) {
						force.scl(0.3f);
					}
					pa.body.applyLinearImpulse(force, expVictimCenter, true);
				}
				
				if (pa.isDamagable())
				{
					final float dmg = force.len();
					pa.applyDamage(dmg/8f);
				}
				
			}
		}
	}
	
	@Override
	public String toJavaString() {
    	final float rr = getPhysicalRotation();
    	return "group.addActor (new TntActor(world, "+(body.getPosition().x)+"f, "+(body.getPosition().y)+"f, "+rr+"f));";    
	}
}
