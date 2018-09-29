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
package com.gitlab.ardash.appleflinger.listeners;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.gitlab.ardash.appleflinger.actors.AppleActor;
import com.gitlab.ardash.appleflinger.actors.GeneralTargetActor;
import com.gitlab.ardash.appleflinger.actors.Ground;
import com.gitlab.ardash.appleflinger.actors.PhysicsActor;
import com.gitlab.ardash.appleflinger.actors.TntActor;
import com.gitlab.ardash.appleflinger.global.GameManager;

public class MyContactListener implements ContactListener{
	
	
	
    public MyContactListener() {
    	/*intentionally empty block*/
	}

	@Override 
    public void endContact(Contact contact) {
		// not needed, was to buggy to memorize current touches
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
		}
        if (udB instanceof PhysicsActor) {
			final PhysicsActor pa = (PhysicsActor)udB;
			pa.applyDamage(impact);
		}
        
		// record direct apple hits
		if (GameManager.RECORDSHOTS)
		{
			if ((udA instanceof AppleActor) && (udB instanceof GeneralTargetActor))
			{
				GameManager.recordTargetPosition(((PhysicsActor) udA).getX(), ((PhysicsActor) udA).getY(), impact);
			}
			if ((udA instanceof GeneralTargetActor) && (udB instanceof AppleActor))
			{
				GameManager.recordTargetPosition(((PhysicsActor) udB).getX(), ((PhysicsActor) udB).getY(), impact);
			}
			if ((udA instanceof AppleActor) && (udB instanceof TntActor))
			{
				GameManager.recordTargetPosition(((PhysicsActor) udA).getX(), ((PhysicsActor) udA).getY(), impact);
			}
			if ((udA instanceof TntActor) && (udB instanceof AppleActor))
			{
				GameManager.recordTargetPosition(((PhysicsActor) udB).getX(), ((PhysicsActor) udB).getY(), impact);
			}
			
			if ((udA instanceof Ground) && (udB instanceof GeneralTargetActor))
			{
				GameManager.recordTargetPosition(((PhysicsActor) udB).getX(), ((PhysicsActor) udB).getY(), impact);
			}
			if ((udA instanceof GeneralTargetActor) && (udB instanceof Ground))
			{
				GameManager.recordTargetPosition(((PhysicsActor) udA).getX(), ((PhysicsActor) udA).getY(), impact);
			}
			
			if (udA instanceof GeneralTargetActor)
			{
				GameManager.recordTargetPosition(((PhysicsActor) udA).getX(), ((PhysicsActor) udA).getY(), impact);
			}
			if (udB instanceof GeneralTargetActor)
			{
				GameManager.recordTargetPosition(((PhysicsActor) udB).getX(), ((PhysicsActor) udB).getY(), impact);
			}
			
		}
    }
    
    @Override
    public void preSolve (Contact contact, Manifold oldManifold){
    	//This is called after a contact is updated.
    }
    
    @Override
    public void postSolve (Contact contact, ContactImpulse impulse){
    	//This lets you inspect a contact after the solver is finished.
    }

}
