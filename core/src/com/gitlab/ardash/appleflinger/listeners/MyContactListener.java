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
package com.gitlab.ardash.appleflinger.listeners;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.gitlab.ardash.appleflinger.actors.PhysicsActor;

public class MyContactListener implements ContactListener{
	
	
	
    public MyContactListener() {
    	//just
	}

	@Override 
    public void endContact(Contact contact) {
    }
	  
    @Override
    public void beginContact(Contact contact) {
        // this happens twice because preSolve is called for (a,b) and (b,a) 
    	
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Body bodyA = fixtureA.getBody();
        Body bodyB = fixtureB.getBody();
        Vector2 velocityA = bodyA.getLinearVelocity();
        Vector2 velocityB =  bodyB.getLinearVelocity();
        
        //float impact = velocityA.dot(velocityB);
        float impact = velocityA.dst2(velocityB);
        //System.out.println(impact);
        
        // absorb actors
        Object udA = fixtureA.getUserData();
        Object udB = fixtureB.getUserData();
        
        if (udA instanceof PhysicsActor && udB instanceof PhysicsActor )
        {
        	PhysicsActor paA = (PhysicsActor)udA;
        	PhysicsActor paB = (PhysicsActor)udB;
        	if (paA.isAbsorbing())
        	{
        		paB.setToBeRemoved(true);
        	}
        }
        
        // apply damage
        if (udA instanceof PhysicsActor) {
			final PhysicsActor pa = (PhysicsActor)udA;
			pa.applyDamage(impact);
//			// if one of them is an apple, play the apple hit sound
//			if ((pa instanceof TargetActor) || (pa instanceof AppleActor))
//			{
//				if (impact >=1f)
//				SoundPlayer.playSound(Assets.apple_hit.getRandom());
//			}
//			if (impact >=1f && (pa instanceof TargetActor))
//			{
//				SoundPlayer.playSound(Assets.dork_hit.getRandom());
//			}
		}
        if (udB instanceof PhysicsActor) {
			final PhysicsActor pa = (PhysicsActor)udB;
			pa.applyDamage(impact);
			
//			// if one of them is an apple, play the apple hit sound
//			if (impact >1f && (pa instanceof AppleActor))
//			{
//				SoundPlayer.playSound(Assets.apple_hit.getRandom());
//			}
//			if (impact >=1f && (pa instanceof TargetActor))
//			{
//				SoundPlayer.playSound(Assets.dork_hit.getRandom());
//			}
		}
    }
    
    @Override
    public void preSolve (Contact contact, Manifold oldManifold){
//        // this happens twice because preSolve is called for (a,b) and (b,a) 
//    	
//        Fixture fixtureA = contact.getFixtureA();
//        Fixture fixtureB = contact.getFixtureB();
//        Body bodyA = fixtureA.getBody();
//        Body bodyB = fixtureB.getBody();
//        Vector2 velocityA = bodyA.getLinearVelocity();
//        Vector2 velocityB =  bodyB.getLinearVelocity();
//        
//        //float impact = velocityA.dot(velocityB);
//        float impact = velocityA.dst2(velocityB);
//        //System.out.println(impact);
//        
//        // absorb actors
//        Object udA = fixtureA.getUserData();
//        Object udB = fixtureB.getUserData();
//        
//        if (udA instanceof PhysicsActor && udB instanceof PhysicsActor )
//        {
//        	PhysicsActor paA = (PhysicsActor)udA;
//        	PhysicsActor paB = (PhysicsActor)udB;
//        	if (paA.isAbsorbing())
//        	{
//        		paB.setToBeRemoved(true);
//        	}
//        }
//        
//        // apply damage
//        if (udA instanceof PhysicsActor) {
//			final PhysicsActor pa = (PhysicsActor)udA;
//			pa.applyDamage(impact);
//		}
//        if (udB instanceof PhysicsActor) {
//			final PhysicsActor pa = (PhysicsActor)udB;
//			pa.applyDamage(impact);
//		}
    }
    
    @Override
    public void postSolve (Contact contact, ContactImpulse impulse){
    	// TODO implement damage, after hit. impact = new direction force of object
//        if(impulse.getNormalImpulses()[0] > 0.01)
//        {
//            /* If body A was hit */
//            if(contact.getFixtureA() != null)
//            {
//                if (Actor.class.isInstance(contact.getFixtureA().getBody().getUserData())) {
//                    Actor a = (Actor) contact.getFixtureA().getBody().getUserData();
////                    evalDamage(a, impulse);
//
//                }
//            }
//
//            /* If body B was hit */
//            if(contact.getFixtureB() != null)
//            {
//                if (Actor.class.isInstance(contact.getFixtureB().getBody().getUserData())) {
//                    Actor b = (Actor) contact.getFixtureB().getBody().getUserData();
////                    evalDamage(b, impulse);
//                }
//            }
//        }
    }

}
