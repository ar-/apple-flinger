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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.Assets.ParticleAsset;
import com.gitlab.ardash.appleflinger.global.Assets.SoundGroupAsset;
import com.gitlab.ardash.appleflinger.global.Assets.SpriteGroupAsset;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.MaterialConfig;
import com.gitlab.ardash.appleflinger.helpers.LinearInterpolator;
import com.gitlab.ardash.appleflinger.helpers.SoundPlayer;

public abstract class PhysicsActor extends Image {

	private static long _nextid=1;
	private final long _id;
	protected final GameWorld world;
	public Body body;
	protected final BodyType bodyType;
	protected final MaterialConfig mc;
	private boolean isPhysicsRemoved;
	private boolean isAbsorbing;
    private boolean isToBeRemoved;
    private boolean isDamagable;
    private final float initialHealth;
    private float health;
    private DestructionState destructionState;
    /// when health was gone to 0
    private boolean isToBeDestroyed;
    private Sound hit_sound;
    private Sound dmg_sound;
    private Sound dest_sound;
    
    /** Particle effect used as a destruction animation of this actor. */
    protected ParticleEffect particleEffect;
    private int originalParticleAmount = 0;
//	protected int damageToPointsFactor;
	protected Color damageLabelColor;
    
	/** actually damage state */
    private enum DestructionState
    {
    	NONE, D1, D2, D3, GONE;
    }

	public PhysicsActor(GameWorld world, BodyType bodyType, MaterialConfig mc) {
		super();
		this.world = world;
		this.bodyType = bodyType;
		this.mc = mc;
		isPhysicsRemoved =false;
		isAbsorbing = false;
		isToBeRemoved =false;
    	initialHealth = mc.hp;
    	health = mc.hp;
    	destructionState = DestructionState.NONE;
		isDamagable =health!=0; // initial health 0 will make it not damagable
    	isToBeDestroyed = false;
    	_id = _nextid++;
    	
    	// set all static bodys industrucable
    	if (bodyType == BodyType.StaticBody)
    		isDamagable=false;
    	
//    	particleEffect = new ParticleEffect();
//        particleEffect.load(Gdx.files.internal("wood_rect.p"), Gdx.files.internal(""));
//    	particleEffect=mc.particleEffect;
        
        getSoundsByMC();
        
        getParticlesByMC();
        
        damageLabelColor = Color.WHITE;
	}

	/**
	 * 
	 */
	private void getSoundsByMC() {
		String mcs = this.mc.toString();
        if (mcs.contains("_")) 
        {
        	mcs = mcs.substring(0, mcs.indexOf('_'));
        	if (mcs.equals("WOOD")) 
        	{
        		hit_sound=Assets.getRandomSound(SoundGroupAsset.WOOD_HIT);
        		dmg_sound=Assets.getRandomSound(SoundGroupAsset.WOOD_DMG);
        		dest_sound=Assets.getRandomSound(SoundGroupAsset.WOOD_DEST);
        	}
        	if (mcs.equals("TARGET")) 
        	{
        		dest_sound=Assets.getRandomSound(SoundGroupAsset.DORK_HIT);
        	}
        }
    	else if (mc ==MaterialConfig.PROJECTILE)
    	{
    		// hit sound wont be played, because is not damagable, also would be the same for each hit
    		// ContactListener Can play these sounds
    		//hit_sound=Assets.apple_hit.getRandom();
    		//dest_sound=Assets.apple_dest.getRandom();
    		dest_sound=Assets.getRandomSound(SoundGroupAsset.APPLE_DEST);
    	}
	}
	
	/**
	 * 
	 */
	private void getParticlesByMC() {
		final float highMax;
		switch (this.mc) {
		case WOOD_RECT:
			particleEffect=new ParticleEffect( Assets.getParticleEffect(ParticleAsset.WOOD_RECT));
			//particleEffect.loadEmitterImages(Assets.AtlasAsset.SCENE);
			break;
		case WOOD_BL_21:
		case WOOD_BL_22:
			particleEffect=new ParticleEffect( Assets.getParticleEffect(ParticleAsset.WOOD_RECT));
			particleEffect.scaleEffect(0.33f);
			break;
		case WOOD_BL_41:
			particleEffect=new ParticleEffect( Assets.getParticleEffect(ParticleAsset.WOOD_RECT));
			highMax = particleEffect.getEmitters().first().getSpawnHeight().getHighMax();
			particleEffect.getEmitters().first().getSpawnHeight().setHighMax(highMax*0.25f);
			particleEffect.scaleEffect(0.5f);
			break;
		case WOOD_BL_42:
			particleEffect=new ParticleEffect( Assets.getParticleEffect(ParticleAsset.WOOD_RECT));
			highMax = particleEffect.getEmitters().first().getSpawnHeight().getHighMax();
			particleEffect.getEmitters().first().getSpawnHeight().setHighMax(highMax*0.5f);
			particleEffect.scaleEffect(0.5f);
			break;
		case WOOD_BL_81:
			particleEffect=new ParticleEffect( Assets.getParticleEffect(ParticleAsset.WOOD_RECT));
			highMax = particleEffect.getEmitters().first().getSpawnHeight().getHighMax();
			particleEffect.getEmitters().first().getSpawnHeight().setHighMax(highMax*0.25f);
			particleEffect.scaleEffect(0.85f);
			particleEffect.getEmitters().first().setMaxParticleCount(10);
			break;
		case WOOD_BL_11:
			particleEffect=new ParticleEffect( Assets.getParticleEffect(ParticleAsset.WOOD_RECT));
//			particleEffect.getEmitters().first().setMaxParticleCount(4);
			particleEffect.scaleEffect(0.25f);
			// TODO try to scale the particles down
			break;
		case PROJECTILE:
			particleEffect=new ParticleEffect( Assets.getParticleEffect(ParticleAsset.APPLE));
			break;
		default:
			particleEffect=null;
			break;
		}
		
		if (particleEffect != null)
		{
	        particleEffect.scaleEffect(1f/GameWorld.UNIT_TO_SCREEN);
	        originalParticleAmount = particleEffect.getEmitters().first().getMaxParticleCount();
		}
	}

	
//	@Override
//	public void dispose() {
//		// TODO nothing here yet, because implementations need their own disposal implemetations
//	}
	
	protected abstract void resetPhysics(MaterialConfig mc, float x,
			float y, float angle, BodyType aBodyType);

	protected void resetPhysics() {
		//world.box2dWorld.destroyBody(body);
		//resetPhysics(world, mc, body.getTransform().getPosition().x, body.getTransform().getPosition().y, bodyType);
		removePhysics();
		reAddPhysics();
	}

	public void removePhysics() {
		// check if not removed already
		if (!isPhysicsRemoved)
			world.box2dWorld.destroyBody(body);
		isPhysicsRemoved = true;
	}

	protected void reAddPhysics() {
		isPhysicsRemoved =false;
		resetPhysics(mc, body.getTransform().getPosition().x, body.getTransform().getPosition().y, body.getAngle(), bodyType);
	}

	public void setPhysicalPosition(Vector2 pos) {
		body.setTransform(pos, body.getAngle());
		resetPhysics();
	}
	
	public void setPhysicalRotation(float rot) {
		removePhysics();
		body.setTransform(body.getTransform().getPosition(), rot);
		reAddPhysics();
		//resetPhysics();
	}
	
	public float getPhysicalRotation() {
		return body.getTransform().getRotation();
	}

	public Vector2 getPhysicalCenterPosition() {
		return body.getWorldCenter();
		//return body.getTransform().getPosition();
	}

	public boolean isAbsorbing() {
		return isAbsorbing;
	}

	public void setAbsorbing(boolean isAbsorbing) {
		this.isAbsorbing = isAbsorbing;
		if (isAbsorbing)
		{
	        Filter filter = body.getFixtureList().get(0).getFilterData();
	        filter.categoryBits = CollisionFilterMasks.ABSORBER;
	        body.getFixtureList().get(0).setFilterData(filter);
		}
		else
		{
	        body.getFixtureList().get(0).setFilterData(new Filter());
		}
	}
	
    public boolean isToBeRemoved() {
		return isToBeRemoved;
	}

	public void setToBeRemoved(boolean isToBeRemoved) {
		this.isToBeRemoved = isToBeRemoved;
	}

	public boolean isDamagable() {
		return isDamagable;
	}

	public void setDamagable(boolean isDamagable) {
		this.isDamagable = isDamagable;
	}

	public void applyDamage(float dmg) {
		if (!isDamagable)
		{
			// this comes on each hit, we can play at least the hit sound if not damageble
			if (dmg>1f)
			{
				playHitSound();
			}
			return;
		}
		
		if (initialHealth<=0) // this is also non-destructable, but also prevents division by 0
			return;
        final float maxPossibleDmg = health;
        // apply only as much dmg as HP are left
        dmg = Math.min(dmg, maxPossibleDmg);
		health-=dmg;
		
		if (dmg>1f)
		{
			//System.out.println("apply dmg " + dmg);
			playHitSound();

			grantPoints(dmg);
		}
		
		// if the hit was too weak, or less than 1 HP left, the item will still be destroyed, but no points granted (unlikely)
		if (health<=0)
		{
			if (isToBeDestroyed)
				return;
			setToBeDestroyed();
			return;
		}
		
		//int applicableDamageState = Math.round(1f/(health/initialHealth))-1;
		int applicableDamageState = (int) (LinearInterpolator.ilx(0, 4, initialHealth, 0, health));
		applyDestructionState(DestructionState.values()[applicableDamageState]);
	}

	/**
	 * Grants the points for this damage to the current player.
	 * Points is usually damage time 10.
	 * Details of this are overwritten in the TagerActor to get more points and different label color
	 * @param dmg
	 */
	private void grantPoints(float dmg) {
		GameManager gm = GameManager.getInstance();
		if(gm.currentGameScreen!=null)
		{
		    final int points = Math.round(dmg)*getDamageToPointsFactor();
			Label la = new PopPointActor(String.valueOf(points),getPhysicalCenterPosition().x,getPhysicalCenterPosition().y, damageLabelColor);
		    gm.currentGameScreen.getGuiStage().addActor(la);
		    gm.currentPlayer.incPoints(points);
		}
	}
	
	private void applyDestructionState(DestructionState ds)
	{
		if (ds != destructionState)
		{
			final int dso = ds.ordinal();
			//if (mc==MaterialConfig.WOOD_RECT)
				//System.out.println("New State of destruction: "+ds+" ("+dso+") for health "+ health);
			
			// if many sprites available
			final SpriteGroupAsset spa = mc.spriteGroup;
			
			if (spa !=null && spa.size() >(dso) && spa.get(dso).get()!=null )
				this.setDrawable(new TextureRegionDrawable(spa.get(dso).get()));
			
			destructionState = ds;
			final EnumSet<DestructionState> soundmakers = EnumSet.of(DestructionState.D1,DestructionState.D2,DestructionState.D3);
			if (soundmakers.contains(ds))
				playDmgSound();

			// also play the particle emitter now on a low scale
			if (particleEffect!= null)
			{
		        particleEffect.getEmitters().first().setMaxParticleCount(MathUtils.random(1, 3));
		        particleEffect.reset();
		        particleEffect.setEmittersCleanUpBlendFunction(true);
		        particleEffect.setPosition(getX()+ getWidth() / 2f,getY()+ getHeight() / 2f);
		        particleEffect.allowCompletion();
		        particleEffect.start();
			}
		}
	}
	
	/**
	 * isDamagable make the actor destroyable. Otherwise it can't be destroyed.
	 * Maybe only for actor that exploder after a time.
	 * @return
	 */
    public boolean isToBeDestroyed() {
		return isToBeDestroyed;
	}
    
    /**
     * initiates the destruction
     * @param isToBeDestroyed
     */
    public void setToBeDestroyed() {
    	if (isToBeDestroyed)
    		return;
    	
		this.isToBeDestroyed = true;
		//	System.out.println("dead");
		isToBeDestroyed=true;
//		world.box2dWorld.destroyBody(body); // object is still locked here
		applyDestructionState(DestructionState.GONE);
        Filter filter = body.getFixtureList().get(0).getFilterData();
        filter.maskBits = CollisionFilterMasks.NONE; // NOTE: can not be set to absorber, because the particles disappear as soon as absorber is hit
        body.getFixtureList().get(0).setFilterData(filter);
		
        if (particleEffect !=null)
        {
	        particleEffect.reset();
	        particleEffect.setEmittersCleanUpBlendFunction(true);
        	particleEffect.getEmitters().first().setMaxParticleCount(originalParticleAmount);
	        particleEffect.setPosition(getX()+ getWidth() / 2f,getY()+ getHeight() / 2f);
	        // TODO maybe find a way to rotate the particle emitter 
	        particleEffect.allowCompletion();
	        particleEffect.start();
        }
        
        playDestSound();
	}

	@Override
    public void draw(Batch batch, float parentAlpha) {
    	if (!isToBeDestroyed)
    	{
        	super.draw(batch, parentAlpha);
            if (particleEffect !=null)
            {
            	if (!particleEffect.isComplete())
            		particleEffect.draw(batch, Gdx.graphics.getDeltaTime());
            }
    	}
    	else
    	{
    		// in this case don't render it anymore, just the particles
            if (particleEffect !=null)
            {
	    		particleEffect.draw(batch, Gdx.graphics.getDeltaTime());
	            if (particleEffect.isComplete()) {
	                isToBeRemoved=true;
	                particleEffect.dispose();
	            }
            }
            else
            {
            	// remove right away if no particle system
            	isToBeRemoved=true;
            }
    	}
    }
    
    public void playHitSound()
    {
		SoundPlayer.playSound(hit_sound);
    }
    
    public void playDmgSound()
    {
		SoundPlayer.playSound(dmg_sound);
    }
    
    public void playDestSound()
    {
		SoundPlayer.playSound(dest_sound);
    }
    
    public BodyType getBodyType()
    {
    	return body.getType();
    }

    public float getHealth() {
		return health;
	}

	public String toJavaString()
    {// TODO maka abstraact
    	return "PhysicsActor(world, BodyType."+getBodyType()+", MaterialConfig."+mc+")";   
    }

    /** 
     * determines the damage multiplier. this was a class bases protected variable before. Now it is a method
     * because points can be different, for other players causing it.
     * @return
     */
	protected abstract int getDamageToPointsFactor();
    
    public long getID()
    {
    	return _id;
    }

}
