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
package com.gitlab.ardash.appleflinger.ai;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.gitlab.ardash.appleflinger.actors.PhysicsActor;
import com.gitlab.ardash.appleflinger.global.GameManager;

public class PlayerSimulator {

	public static final PlayerSimulator INSTANCE = new PlayerSimulator();
	
	private Strategy strategy = new HCStrategy();

	private boolean isDraging = false;
	private int step = 0;
	private int pulledSteps = 0;
//	private float timeActive = 0;
	private Vector2 goodPullVector = null;

	private PlayerSimulator() {
		// private to ensure singleton 
	}
	
	public void startBackgroundInitForNextRound()
	{
		//reset everything, because a round could have been canceled while the simulator was active
		isDraging = false;
		step = 0;
		pulledSteps = 0;
	}

	public void playOneRound() {
		if (GameManager.DEBUG)
			System.out.println("Simulating one round ..."); 
		isDraging = true;
		step = 0;
		pulledSteps = 0;
		goodPullVector = null;
	}

	public void act() {
		GameManager gm = GameManager.getInstance();
		
		// don't do anything if not dragging (dragging is only triggered by playOneRound())
		if (!isDraging)
			return;

		// TODO deactivate input on GUI and stage while dragging
		
		final float pullingSteps = 25; // should be int

		switch (step) {
		case 0:
			touchDown();
			step++;
			break;
		case 1:
			// slowly drag to the dragTarget
			final Vector2 slingShotCenter = gm.currentPlayer.slingshot.getSlingShotCenter();

			if (goodPullVector == null)
				goodPullVector = getGoodPullVector();
			
			Vector2 dragTarget = slingShotCenter.cpy()
					.add(goodPullVector.cpy().scl(1 * (Math.min(pulledSteps, pullingSteps) / pullingSteps)));
			// System.out.println("shooting at: "+dragTarget);
			// in 10 steps
			touchDragTo(dragTarget);
			if (pulledSteps >= pullingSteps * 2 + 120) // wait twice as long
														// after pulling // or
														// longer if still lags
														// on phone
				step++;
			break;
		case 2:
			touchUp();
			isDraging = false;
			goodPullVector = null;
			break;

		default:
			break;
		}
	}

	// return the current best shot from list
	// return null if there is none , is avoided
	public Vector2 getGoodPullVector ()
	{
			// strategies can be adjusted here
			final Vector2 pullVector = strategy.getPullVector();
			
			// degree around 0,360,180 : is horizontal shot -> strength can be adjusted
			// degree around 90,270 (sin > 0.75 ): is vertical shot -> strength should not be reduced (will hit own targets)
			boolean verticalShot = Math.abs(MathUtils.sin(pullVector.angleRad())) > 0.9f;

			// deviation in strength of 6.25%
			final float devStrength = 0.0625f;
			final float devPosition = 0.035f;
			final float clamp = MathUtils.random(verticalShot?-devStrength:0, devStrength); 
			final float xoffset = MathUtils.random(-devPosition, devPosition);
			final float yoffset = MathUtils.random(-devPosition, devPosition);
			pullVector.x += xoffset;
			pullVector.y += yoffset;
			pullVector.scl(1f + clamp);
			
			return pullVector;

	}

	@SuppressWarnings("static-method")
	private void touchDown() {
		GameManager gm = GameManager.getInstance();
		final PhysicsActor currentProjectile = gm.currentPlayer.slingshot.getCurrentProjectile();
		InputEvent touchDown = new InputEvent();
		touchDown.setListenerActor(currentProjectile);
		touchDown.setTarget(currentProjectile);
		touchDown.setStage(currentProjectile.getStage());
		touchDown.setType(Type.touchDown);
		currentProjectile.fire(touchDown);
	}

	private void touchDragTo(Vector2 dt) {
		pulledSteps++;
		GameManager gm = GameManager.getInstance();
		final PhysicsActor currentProjectile = gm.currentPlayer.slingshot.getCurrentProjectile();
		InputEvent touchDragged = new InputEvent();
		touchDragged.setListenerActor(currentProjectile);
		touchDragged.setTarget(currentProjectile);
		touchDragged.setStage(currentProjectile.getStage());
		touchDragged.setType(Type.touchDragged);
		touchDragged.setStageX(dt.x);
		touchDragged.setStageY(dt.y);
		currentProjectile.fire(touchDragged);
	}

	@SuppressWarnings("static-method")
	private void touchUp() {
		GameManager gm = GameManager.getInstance();
		final PhysicsActor currentProjectile = gm.currentPlayer.slingshot.getCurrentProjectile();
		InputEvent touchUp = new InputEvent();
		touchUp.setListenerActor(currentProjectile);
		touchUp.setTarget(currentProjectile);
		touchUp.setStage(currentProjectile.getStage());
		touchUp.setType(Type.touchUp);
		currentProjectile.fire(touchUp);
	}
	
	
}
