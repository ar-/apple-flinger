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
package com.gitlab.ardash.appleflinger.testing.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.GameManager.OnCurrentPlayerChangeListener;
import com.gitlab.ardash.appleflinger.global.GameState;
import com.gitlab.ardash.appleflinger.helpers.Pref;
import com.gitlab.ardash.appleflinger.missions.Mission;
import com.gitlab.ardash.appleflinger.testing.GdxTestRunner;

@SuppressWarnings("unused")
@RunWith(GdxTestRunner.class)
public class GameManagerTest {

	GameManager gm = GameManager.getInstance();

	@Before
	public void init() {
		gm.setOnCurrentPlayerChangeListener(new OnCurrentPlayerChangeListener() {
			@Override
			public void onCurrentPlayerChange() { /*intentionally empty block*/	}
		});
		gm.resetAll(null);
		gm.resetRound(Mission.M_1_1);
	}
	
	@Test
	public void checkInitGameState() {
		GameState gameState = gm.getGameState();
		assertEquals(gameState, GameState.START_APP);
		
		gm.turnToNextPlayer();
	}

	@Test
	public void playerTurns() {
		GameState gameState = gm.getGameState();
		assertEquals(gm.NONE, gm.currentPlayer);
		
		gm.turnToNextPlayer();
		assertEquals(gm.PLAYER1, gm.currentPlayer);

		gm.turnToNextPlayer();
		assertEquals(gm.PLAYER2, gm.currentPlayer);

		gm.turnToNextPlayer();
		assertEquals(gm.PLAYER1, gm.currentPlayer);
	}

	@Test
	public void mission1CannotBeDisabled() {
		Pref.setMissionActivated(Mission.M_1_1,false);
		assertTrue(Pref.isMissionActivated(Mission.M_1_1));
	}

	@Test
	public void missionCanBeDisabled() {
		Pref.setMissionActivated(Mission.M_1_2,true);
		assertTrue(Pref.isMissionActivated(Mission.M_1_2));
		Pref.setMissionActivated(Mission.M_1_2,false);
		assertFalse(Pref.isMissionActivated(Mission.M_1_2));

		Pref.setMissionActivated(Mission.M_1_2,true);
		assertTrue(Pref.isMissionActivated(Mission.M_1_2));
		Pref.setMissionActivated(Mission.M_1_2,false);
		assertFalse(Pref.isMissionActivated(Mission.M_1_2));
	}

	@Test
	public void headlessTest() {
		GameState gameState = gm.getGameState();
		assertEquals(gameState, GameState.START_APP);
//		MainMenuScreen mms = (MainMenuScreen)gm.getGame().getScreen();
//		gm.turnToNextPlayer();
		
		// TODO build own headless app and use for tests
	}


}
