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
package com.gitlab.ardash.appleflinger.global;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.gitlab.ardash.appleflinger.listeners.OnPhysicStartedListener;
import com.gitlab.ardash.appleflinger.listeners.OnPhysicStateChangeListener;
import com.gitlab.ardash.appleflinger.listeners.OnPhysicStoppedListener;

public class PhysicWorldObserver {
	
	private enum State {
		NONE,INACTIVE,ACTIVE
	}
	
	private final World physicWorld;
	private State state;
	private final LinkedHashSet<String> transforms;
	private final ArrayList<OnPhysicStateChangeListener> listeners;

	public PhysicWorldObserver(World physicWorld) {
		super();
		this.physicWorld = physicWorld;
		this.state = State.NONE;
		this.transforms = new LinkedHashSet<>(200);
		this.listeners = new ArrayList<>(1);
	}
	
	/**
	 * checks the physical world for changes
	 * calls changeState only if the was actually a change
	 */
	public void step()
	{
		// TODO do only something here if delta was higher than previous delta!!
		// observer only if the GameManager indicates, that we are waiting for a simulation to be settled
		// TODO change this ot makes the onPhysicStarted listener obsolete until now
		if (! (GameManager.getInstance().getGameState()==GameState.WAIT_FOR_PHYSICS) )
			return;
		final Array<Body> ba =new Array<>();
		physicWorld.getBodies(ba);
		// first check change in size
		if (ba.size != transforms.size())
		{
			// clear change, some movement happened
			changeState (State.ACTIVE);
			capture();
			return;
		}
		// else: amount of elements are the same, so we compare all of them
		for (final Body b : ba)
		{
			// if any element is not in there, then something moved
			if (!transforms.contains(transformToString (b.getTransform())))
			{
				changeState (State.ACTIVE);
				capture();
				return;
			}
		}
		
		// coming here means nothing moved
		changeState (State.INACTIVE);
		capture();
		return;
	}
	
	/**
	 * can be called any time. changes the state only if the was an actual change
	 */
	private void changeState (State newState)
	{
		if (newState != state)
		{
			state = newState;
			//System.out.println("New State in physic world: "+state);
			for (final OnPhysicStateChangeListener l : listeners)
			{
				// run all listeners for the new state
				if (newState == State.ACTIVE && (l instanceof OnPhysicStartedListener)) {
					OnPhysicStartedListener startl = (OnPhysicStartedListener) l;
					startl.onPhysicStarted();
				}
				if (newState == State.INACTIVE && (l instanceof OnPhysicStoppedListener)) {
					OnPhysicStoppedListener startl = (OnPhysicStoppedListener) l;
					startl.onPhysicStopped();
				}
			}
		}
	}
	
	private static String transformToString(Transform t)
	{
		return t.getPosition().x+"|"+t.getPosition().y+"|"+t.getRotation();  
	}
	
	/**
	 * captures the current situation (all positions and rotations)
	 */
	private void capture()
	{
		transforms.clear();
		final Array<Body> ba =new Array<>();
		physicWorld.getBodies(ba);
		for (final Body b : ba)
		{
			transforms.add(transformToString (b.getTransform()));
		}
		
	}
	
	public void addListener (OnPhysicStateChangeListener l)
	{
		listeners.add(l);
	}
	
	public void removeListener (OnPhysicStateChangeListener l)
	{
		listeners.remove(l);
	}
	
	public void removeAllListeners ()
	{
		listeners.clear();
	}
	

}
