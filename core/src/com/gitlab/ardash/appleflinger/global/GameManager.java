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
package com.gitlab.ardash.appleflinger.global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gitlab.ardash.appleflinger.ActionResolver;
import com.gitlab.ardash.appleflinger.AppleflingerGame;
import com.gitlab.ardash.appleflinger.actors.TargetActor;
import com.gitlab.ardash.appleflinger.global.PlayerStatus.PlayerSide;
import com.gitlab.ardash.appleflinger.helpers.GPGS;
import com.gitlab.ardash.appleflinger.helpers.Pref;
import com.gitlab.ardash.appleflinger.listeners.OnGameOverListener;
import com.gitlab.ardash.appleflinger.missions.Mission;
import com.gitlab.ardash.appleflinger.screens.GameScreen;

/** 
 * singleton
 * 
 */
final public class GameManager {
	
	public static final boolean DEBUG = false;
//	public static final boolean DEBUG = true;
	public static final boolean DEBUGZOOM = false;
//	public static final boolean DEBUGZOOM = true;
//	public static final boolean SANDBOX = false;
	public static final boolean SANDBOX = true;
//	public static final boolean ALLLEVELS = false;
	public static final boolean ALLLEVELS = true;
	public static final boolean RECORDSHOTS = false;
//	public static final boolean RECORDSHOTS = true;
	private static Vector2 lastPullVector = new Vector2();
	private GameState gameState;
	public PlayerStatus NONE;
	public PlayerStatus PLAYER1;
	public PlayerStatus PLAYER2;
	private static GameManager instance;
	private OnCurrentPlayerChangeListener onCurrentPlayerChangeListener;
	private OnGameOverListener onGameOverListener;
	private Mission currentMission = null;

	private boolean isPlayer2CPU = true;
//	private boolean isPlayer2CPU = false;
	
	public GameScreen currentGameScreen;
	
	public interface OnCurrentPlayerChangeListener
	{
		public void onCurrentPlayerChange();
	}
	public PlayerStatus currentPlayer;
	public PlayerStatus winner;
	private PlayerStatus previousRoundWinner;
	private AppleflingerGame game;
	private int targetsDestroyedThisRound;
	private int targetsAvailableThisRound;
	private int shotsFiredThisRound;
	private final InputMultiplexer inputMultiplexer;
	
	public boolean isPaused;
	/**
	 * the current winning streak (checking for non-interrupted streak compared to 'getWins()')
	 */
	private int wonRoundsInARow;
	public final long gameStartTime;
	
	public static GameManager getInstance()
	{
		if (instance == null)
			instance = new GameManager();
		return instance;
	}
	
	private GameManager()
	{
		this.gameStartTime = TimeUtils.millis();
		gameState = GameState.START_APP;
		inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		resetAll(null);
	}
	
	@SuppressWarnings("unused")
	public static void recordPullVector(Vector2 v)
	{
		if (RECORDSHOTS && instance.currentPlayer == instance.PLAYER2)
		{
			lastPullVector = v.cpy();
		}
	}

	@SuppressWarnings("unused")
	public static void recordTargetPosition(float x, float y, float impact)
	{
		if (RECORDSHOTS && instance.currentPlayer == instance.PLAYER2)
		{
			if (impact > 1.0)
			{
				//print in Java notation like the following line
				//add(new Shot(Mission.M_1_1, -3, 7, 10, 20, 75));
				// to shorten the string
				Vector2 p = lastPullVector;
				String m = instance.getCurrentMission().name();
				float i = impact;
				System.err.println("add(new Shot(Mission."+m+", "+p.x+"f, "+p.y+"f, "+x+"f, "+y+"f, "+i+"f));");
			}
		}
	}

	public InputMultiplexer getInputMultiplexer() {
		return inputMultiplexer;
	}

	public void turnToNextPlayer() {
		endGameIfItIsOver();
		if (currentPlayer == PLAYER1)
			currentPlayer = PLAYER2;
		else
			currentPlayer = PLAYER1;
		onCurrentPlayerChangeListener.onCurrentPlayerChange();
		
		if (PLAYER1.getPointsThisShot()>=10000)
		{
			getActionResolver().unlockAchievementGPGS(GPGS.ACH_WREAK_HAVOC);
		}
		
		if (PLAYER1.getEnemiesKilledThisShot()==3)
		{
			getActionResolver().unlockAchievementGPGS(GPGS.ACH_TRIPPLE_POP);
		}
		
		if (PLAYER1.getEnemiesKilledThisShot()==4)
		{
			getActionResolver().unlockAchievementGPGS(GPGS.ACH_FOURFOLD_POP);
		}
		
		if (PLAYER1.getEnemiesKilledThisShot()>=5)
		{
			getActionResolver().unlockAchievementGPGS(GPGS.ACH_FIVEFOLD_POP);
		}
		
		// TODO shot based rest function for gamemanager needed ?
		PLAYER1.resetShot();
		PLAYER2.resetShot();
	}
	
	private void endGameIfItIsOver() {
		// player 1 starts
		// if current player = 2 : determine winner
		// if current player = 1 : message for last chance?
		if (currentPlayer == PLAYER2)
		{
			int aliveP1=PLAYER1.getOwnTargetsAlive();
			int aliveP2=PLAYER2.getOwnTargetsAlive();
			if (aliveP1==0 || aliveP2==0)
			{
				// GAME OVER !!!
				if (aliveP1 == aliveP2)
				{
					// all dead , have to check points
					// TODO implement draw!
					if (PLAYER1.getPoints() > PLAYER2.getPoints())
						winner = PLAYER1;
					else
						winner = PLAYER2;
				}
				else
				{
					if (aliveP1 > aliveP2)
						winner = PLAYER1;
					else
						winner = PLAYER2;
				}
			}
			//System.out.println(aliveP1 + " " + aliveP2);
		}
		
		// if there is a winner
		if (winner != NONE)
		{
			PLAYER1.incAllPoints(PLAYER1.getPoints());
			PLAYER2.incAllPoints(PLAYER2.getPoints());
			winner.incWins();
			onGameOverListener.onGameOver();
			
			if (previousRoundWinner == winner)
				wonRoundsInARow++;
			else
				wonRoundsInARow = 1;
			
			if (winner == PLAYER1)
			{
				if (wonRoundsInARow==3)
				{
					getActionResolver().unlockAchievementGPGS(GPGS.ACH_BEGINNER_STREAK);
				}
				
				if (shotsFiredThisRound<=2 && currentMission == Mission.M_1_9)
				{
					getActionResolver().unlockAchievementGPGS(GPGS.ACH_BEGINNERS_LUCK);
				}
			}
			
			// achievement to google
			getActionResolver().incrementAchievementGPGS(GPGS.ACH_POINTS_FARMER, PLAYER1.getPoints()/100);
			
			// submit highscore to google
			getActionResolver().submitScoreGPGS(GPGS.LEAD_MOST_POINTS, PLAYER1.getAllPoints());
			
			// save winner for next round 
			previousRoundWinner = winner;
		}
		
	}
	
	public void onShotFired ()
	{
		shotsFiredThisRound++;
	}
	
	/**
	 * every target actor calls this as soon as it gets destroyed
	 * good for statistics/achievements/ads
	 * @param ta
	 */
	public void onTargetDestroyed(TargetActor ta)
	{
		targetsDestroyedThisRound++;
		
		if (ta.getPlayerSide() == PlayerSide.LEFT)
		{
			PLAYER1.targetsLostThisRound++;
		}
		else
		{
			PLAYER2.targetsLostThisRound++;
		}

		if (ta.getPlayerSide()!=currentPlayer.side)
			currentPlayer.incEnemiesKilledThisShot();

	}

	/**
	 * resets the playable part of the game
	 * this is the reset for one round
	 * @param mission 
	 */
	public void resetRound(Mission mission)
	{
		currentPlayer =  NONE;
		winner = NONE;
		setCurrentMission(mission);
    	setPaused(false);
		PLAYER1.resetRound();
		PLAYER2.resetRound();
		
		targetsDestroyedThisRound=0;
		targetsAvailableThisRound=0;
		shotsFiredThisRound=0;
	}
	
	/**
	 * resets also the All* parts of the game (AllPoints and AllWins)
	 * basically by creating new player objects
	 * @param mission
	 */
	public void resetAll(Mission mission)
	{
		isPaused = false;
		NONE = new PlayerStatus(PlayerSide.NONE);
		PLAYER1 = new PlayerStatus(PlayerSide.LEFT);
		PLAYER2 = new PlayerStatus(PlayerSide.RIGHT);
		previousRoundWinner = NONE;
		wonRoundsInARow = 1;
		PLAYER1.setName(Pref.getPlayer1name());
		PLAYER2.setName(Pref.getPlayer2name());
		
		if (game!=null)
			resetRound(mission);
	}

	public void setOnCurrentPlayerChangeListener(
			OnCurrentPlayerChangeListener onCurrentPlayerChangeListener) {
		this.onCurrentPlayerChangeListener = onCurrentPlayerChangeListener;
	}

	public void setOnGameOverListener(OnGameOverListener onGameOverListener) {
		this.onGameOverListener = onGameOverListener;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState newState) {
		if (gameState ==newState)
			return;
		
		// if we come from the status INIT_WORLD, do something before it changes
		if (gameState ==GameState.INIT_WORLD)
		{
			targetsAvailableThisRound = PLAYER1.getOwnTargetsAlive()+PLAYER2.getOwnTargetsAlive();
		}
		
		// TODO simple don't do it instead of exceptioning
		if (!gameState.nexts().contains(newState))
			throw new RuntimeException("Invalid State change from "+ gameState + " to "+ newState);  
		
		this.gameState = newState;
		if (DEBUG)
			System.out.println("changing game state: " + newState); 
	}

	public void registerGameObject(AppleflingerGame appleflingerGame) {
		this.game = appleflingerGame;
	}
	
	public void setScreen (Screen screen)
	{
		game.setScreen(screen);
	}
	public void setScreen (Mission mission)
	{
		game.setScreen(mission);
	}
	
	public AppleflingerGame getGame ()
	{
		return game;
	}

	public ActionResolver getActionResolver ()
	{
		return game.actionResolver;
	}

	public Mission getCurrentMission() {
		return currentMission;
	}

	protected void setCurrentMission(Mission currentMission) {
		this.currentMission = currentMission;
	}

	public boolean isPlayer2CPU() {
		return isPlayer2CPU;
	}

	public void setPlayer2CPU(boolean isPlayer2CPU) {
		this.isPlayer2CPU = isPlayer2CPU;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	
	
}
