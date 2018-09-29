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
package com.gitlab.ardash.appleflinger;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * If this stage is disposed it will also dispose all containing Actors (if they are disposable).
 * This stage has also a method to get all the actors in an array.
 */
public class AdvancedStage extends Stage {
	
	
	private final Set<Actor> allActors = Collections.synchronizedSet(new LinkedHashSet<Actor>());

	public AdvancedStage() {
		super();
		
		// for debuging print position on click
		addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				System.out.println(event.getStageX()+","+event.getStageY()); 
			}
		});
	}

	public AdvancedStage(Viewport viewport, Batch batch) {
		super(viewport, batch);
	}

	public AdvancedStage(Viewport viewport) {
		super(viewport);
	}

	@Override
	public void dispose() {
		for (Actor a : getActors())
		{
			if (a instanceof Disposable) {
				Disposable da = (Disposable) a;
				da.dispose();
			}
		}
		super.dispose();
	}
	
	public static void addAllActorsToSet(Set<Actor> sa, Group g)
	{
		if (sa == null)
			throw new RuntimeException("sa can not be null"); 
		
		if (g==null)
			return;
		
		for (Actor a: g.getChildren())
		{
			sa.add(a);
			if (a instanceof Group) {
				Group gr = (Group) a;
				addAllActorsToSet(sa,gr);
			}
		}
	}
	
	public Set<Actor> getAllActors()
	{
		synchronized (allActors) {
			allActors.clear();
			addAllActorsToSet(allActors, getRoot());
			
			//always return a copy of the set, the internal one is only used for the recursive alls
			return new LinkedHashSet<>(allActors);
		}
	}
	
//	@Override
//	public Actor hit(float stageX, float stageY, boolean touchable) {
//		synchronized (this) {
//			return super.hit(stageX, stageY, touchable);
//		}
//	}

}
