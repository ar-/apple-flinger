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
package com.gitlab.ardash.appleflinger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gitlab.ardash.appleflinger.GameRenderer;
import com.gitlab.ardash.appleflinger.GameWorld;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.GameState;
import com.gitlab.ardash.appleflinger.global.Assets.MusicAsset;
import com.gitlab.ardash.appleflinger.global.Assets.SoundAsset;
import com.gitlab.ardash.appleflinger.global.GameManager.OnCurrentPlayerChangeListener;
import com.gitlab.ardash.appleflinger.helpers.Pref;
import com.gitlab.ardash.appleflinger.helpers.SoundPlayer;
import com.gitlab.ardash.appleflinger.i18n.I18N;
import com.gitlab.ardash.appleflinger.listeners.OnGameOverListener;
import com.gitlab.ardash.appleflinger.listeners.OnPointsChangeListener;
import com.gitlab.ardash.appleflinger.missions.Mission;

public class GameScreen implements Screen {  
	   
    // this is actually my tablet resolution in landscape mode. I'm using it for making the GUI pixel-exact.  
//    public static float SCREEN_WIDTH = 1024;  
//    public static float SCREEN_HEIGHT = 600;  
    
    public static float SCREEN_WIDTH = 1920;  
    public static float SCREEN_HEIGHT = 1080;
    
    private GameWorld world; // contains the game world's bodies and actors.  
    private GameRenderer renderer; // our custom game renderer.  
    private Stage guiStage; // stage that holds the GUI. Pixel-exact size.  
    private OrthographicCamera guiCam; // camera for the GUI. It's the stage default camera.  
    
    public final Mission mission;
	private Label labelMessage;
	private PauseScreenActor pauseScreen = new PauseScreenActor();
	private boolean isAnnouncementFrozen= false;
	private Label labelAllPointsP1;
	private Label labelAllPointsP2;
    
    public GameScreen(Mission mission) {
		this.mission = mission;
	}

	@Override  
    public final void show() {
		// preload assets (otherwise sounds play too fast after loading, so they are silenced the first time)
		// if there is  no assync loading done before, this will cause a delay and button stays down a while
		//MAssets.load(); // just to be sure, in case something was skipped by assync loading
        final GameManager gm = GameManager.getInstance();
        // gm.getActionResolver().t_SetAndSendScreenName(mission.name()); //  General GS is enough
		gm.setGameState(GameState.LOADING_SCREEN);
		SoundPlayer.pauseMusic(Assets.getMusic(MusicAsset.BG));

        //this.stage = new Stage(); // create the GUI stage
        //this.stage.setViewport(SCREEN_WIDTH, SCREEN_HEIGHT, false); // set the GUI stage viewport to the pixel size
        //this.stage = new Stage(new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT));
        //this.stage = new Stage(new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.guiStage = new Stage(new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT));

        world = new GameWorld(mission);
        renderer = new GameRenderer(world);
        
        // until further notice the game actor will get all the events (game-over reverts this)
        gm.getInputMultiplexer().clear();
        gm.getInputMultiplexer().addProcessor(guiStage);
        gm.getInputMultiplexer().addProcessor(world.stage);
        
        // register with game manger
        gm.currentGameScreen=this;
        
        // add GUI actors to stage, labels, meters, buttons etc.  
        buildGameGUI();
        setAnnouncementText(I18N.getString("pleaseWait")); //$NON-NLS-1$
    }

	private Label createMiniLabel(CharSequence text)
	{
		final LabelStyle ministyle = new LabelStyle();
		ministyle.font = Assets.FontAsset.CRASHLANDING_64.font;
		ministyle.fontColor = new Color(1, 1, 1, 0.8f);
		final Label labelPointsTop = new Label(text, ministyle);
		labelPointsTop.setTouchable(Touchable.disabled);
		return labelPointsTop;
	}
	
	/**
	 * 
	 */
	private void buildGameGUI() {
        LabelStyle labelstyle = Assets.LabelStyleAsset.MENUSTYLE.style;
//        float lineheight = labelstyle.font.getAscent()+labelstyle.font.getDescent()+labelstyle.font.getCapHeight()+labelstyle.font.getLineHeight();
        float lineheight = labelstyle.font.getLineHeight()+26;
        
        labelMessage = new Label("A", labelstyle);   //$NON-NLS-1$
//        labelMessage.setWrap(true);
        labelMessage.setPosition(0, SCREEN_HEIGHT-lineheight*4);  
        labelMessage.setWidth(SCREEN_WIDTH);  
        labelMessage.setAlignment(Align.top);  
        labelMessage.setTouchable(Touchable.disabled);
        guiStage.addActor(labelMessage);  
        
        Label labelNameP1 = new Label(".", labelstyle);   //$NON-NLS-1$
//        labelNameP1.setWrap(true);
		labelNameP1.setPosition(0, SCREEN_HEIGHT-lineheight*2);  
        labelNameP1.setAlignment(Align.left);  
		labelNameP1.setTouchable(Touchable.disabled);
        guiStage.addActor(labelNameP1);  

        Label labelNameP2 = new Label(".", labelstyle);   //$NON-NLS-1$
//        labelNameP1.setWrap(true);
		labelNameP2.setPosition(0, SCREEN_HEIGHT-lineheight*2);  
		labelNameP2.setWidth(SCREEN_WIDTH);  
		labelNameP2.setAlignment(Align.right);  
		labelNameP2.setTouchable(Touchable.disabled);
        guiStage.addActor(labelNameP2);  
        
        final Label labelPointsP1 = new Label(" "+I18N.getString("POINTS")+":  0 ", labelstyle);   //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		labelPointsP1.setPosition(0, SCREEN_HEIGHT-lineheight*3f);  
		labelPointsP1.setTouchable(Touchable.disabled);
        guiStage.addActor(labelPointsP1);  
        
        final Label labelPointsP2 = new Label(" "+I18N.getString("POINTS")+":  0  ", labelstyle);   //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		labelPointsP2.setPosition(0, SCREEN_HEIGHT-lineheight*3f);  
        labelPointsP2.setWidth(SCREEN_WIDTH);  
        labelPointsP2.setAlignment(Align.right);  
        labelPointsP2.setTouchable(Touchable.disabled);
        guiStage.addActor(labelPointsP2); 
        
//        Table bigMessageTable = new Table();
//        bigMessageTable.pad(0);
//        bigMessageTable.setTouchable(Touchable.disabled);
//        bigMessageTable.setFillParent(true);
//        bigMessageTable.align(Align.top);
////        bigMessageTable.add();
////        bigMessageTable.add();
////        bigMessageTable.add();
////        bigMessageTable.add();
////        bigMessageTable.row();
//        bigMessageTable.add(labelNameP1).right().width(SCREEN_WIDTH*0.2f);
//        bigMessageTable.add(labelNameP2).left().width(SCREEN_WIDTH*0.2f);
//        bigMessageTable.row();
//        bigMessageTable.add(labelPointsP1).width(SCREEN_WIDTH*0.25f).left();
////        bigMessageTable.add(labelMessage).colspan(2).width(SCREEN_WIDTH/3).center();
////        bigMessageTable.add(labelPointsP2).width(SCREEN_WIDTH*0.25f).right();
//        guiStage.addActor(bigMessageTable); 
        
        final GameManager gm = GameManager.getInstance();
        // set listeners for points change
        gm.PLAYER1.setOnPointsChangeListener(new OnPointsChangeListener() {
			@Override
			public void onPointChange() {
				labelPointsP1.setText(String.format(" "+I18N.getString("POINTS")+":  %d ", gm.PLAYER1.getPoints())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				labelAllPointsP1.setText((gm.PLAYER1.getPoints()+gm.PLAYER1.getAllPoints())+""); //$NON-NLS-1$
			}
		});
        gm.PLAYER2.setOnPointsChangeListener(new OnPointsChangeListener() {
			@Override
			public void onPointChange() {
				labelPointsP2.setText(String.format(" "+I18N.getString("POINTS")+":  %d  ", gm.PLAYER2.getPoints())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				labelAllPointsP2.setText((gm.PLAYER2.getPoints()+gm.PLAYER2.getAllPoints())+""); //$NON-NLS-1$
			}
		});
        
        // set game over listener
        final Screen screenToBeDisposed = this;
        gm.setOnGameOverListener(new OnGameOverListener() {
			@Override
			public void onGameOver() {
				// GUI catches all events from now, game not playable anymore
				gm.getInputMultiplexer().removeProcessor(world.stage);
				setAnnouncementText(String.format(I18N.getString("gameOver")+"\n%s "+I18N.getString("won")+"\n"+I18N.getString("withDPoints")+"\n"+I18N.getString("touchScreenToContinue")+".", gm.winner.getName(), gm.winner.getPoints())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
				freezeAnnouncementText();
				gm.setGameState(GameState.GAME_OVER_SCREEN);
				SoundPlayer.playMusic(Assets.getMusic(MusicAsset.BG));
				
        		final Mission nextmission;
        		
				if (gm.isPlayer2CPU())
				{
					if (gm.winner == gm.PLAYER1)
					{
						nextmission = mission.getNext();
					}
					else
					{
						nextmission = mission;
					}
					
	        		// if it was played against computer it unlocks next missions
					Pref.setMissionActivated(nextmission,true);
				}
				else
				{
					// playing against so else, doesn't unlock anything, but it goes over to the next level in any case
					nextmission = mission.getNext();
				}
        		


		        // the only way the gui stage can now receive an event is: GAME OVER
		        guiStage.addListener(new InputListener() {
		        	
		        	@Override
		        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		        		return true;
		        	}
		        	@Override
		        	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		        		super.touchUp(event, x, y, pointer, button);
		        		gm.resetRound(nextmission);
						gm.setScreen(nextmission);
		        		screenToBeDisposed.dispose();
		        	}
		        });

			}
		});
        
        
    	// set now because names don't change during game
		gm.PLAYER1.setName(Pref.getPlayer1name());
   		labelNameP1.setText(String.format(" %s ", gm.PLAYER1.getName())); //$NON-NLS-1$
   		
   		if (gm.isPlayer2CPU())
   		{
   			gm.PLAYER2.setName(I18N.getString("computer")); //$NON-NLS-1$
   		}
   		else
   		{
   			gm.PLAYER2.setName(Pref.getPlayer2name());
   		}
   		labelNameP2.setText(String.format(" %s  ", gm.PLAYER2.getName())); //$NON-NLS-1$
   		
   		// register listener for gamestateChanges
   		gm.setOnCurrentPlayerChangeListener(new OnCurrentPlayerChangeListener() {
			@Override
			public void onCurrentPlayerChange() {
		        setAnnouncementText(String.format("%s\n"+I18N.getString("itIsYourTurn"), gm.currentPlayer.getName()));  //$NON-NLS-1$ //$NON-NLS-2$
			}
		});
   		
   		// pause button
        final SpriteButton btnPause = new SpriteButton(Assets.SpriteAsset.BTN_PAUSE.get());
        btnPause.moveBy(0+100, SCREEN_HEIGHT-100);
        //guiStage.addActor(btnPause);
        btnPause.addListener(new ClickListener(){@Override
        public void clicked(InputEvent event, float x, float y) {
        	guiStage.addActor(pauseScreen);
        	gm.setPaused(true);
        	super.clicked(event, x, y);
        }});
        
        // sound button
        final SpriteButton btnSound = new SpriteButton(Assets.SpriteAsset.BTN_SOUND_ON.get(),Assets.SpriteAsset.BTN_SOUND_OFF.get());
        btnSound.moveBy(0, SCREEN_HEIGHT-100);
        btnSound.setCheckable(true);
        //guiStage.addActor(btnSound);
        btnSound.addListener(new ClickListener(){
        	@Override
        public void clicked(InputEvent event, float x, float y) {
        	super.clicked(event, x, y);
        		Pref.setSoundOn(!btnSound.isChecked());
        }});
        btnSound.setChecked(!Pref.getSoundOn());

        LabelStyle ministyle = new LabelStyle();
        ministyle.font = Assets.FontAsset.CRASHLANDING_64.font;
        ministyle.fontColor = new Color(1, 1, 1, 0.8f);

        labelAllPointsP1 = createMiniLabel(gm.PLAYER1.getAllPoints()+""); //$NON-NLS-1$
        labelAllPointsP2 = createMiniLabel(gm.PLAYER2.getAllPoints()+""); //$NON-NLS-1$
        final Label labelWinsP1 = createMiniLabel(gm.PLAYER1.getWins()+""); //$NON-NLS-1$
        final Label labelWinsP2 = createMiniLabel(gm.PLAYER2.getWins()+""); //$NON-NLS-1$
        
        final Table miniStatsTable = new Table();
//        miniStatsTable.a
//        miniStatsTable.align(Align.topLeft);
        miniStatsTable.setTouchable(Touchable.disabled);
        miniStatsTable.add(labelAllPointsP1).right().top();
        miniStatsTable.add(createMiniLabel(I18N.getString("points"))).padLeft(20).padRight(20); //$NON-NLS-1$
        miniStatsTable.add(labelAllPointsP2).left();
		miniStatsTable.add().width(GameWorld.UNIT_WIDTH/2);
		miniStatsTable.row();
		miniStatsTable.add(labelWinsP1).right();
        miniStatsTable.add(createMiniLabel(I18N.getString("wins"))); //$NON-NLS-1$
		miniStatsTable.add(labelWinsP2).left();
		miniStatsTable.add().width(GameWorld.UNIT_WIDTH/2);
        miniStatsTable.row();
        miniStatsTable.add();
        miniStatsTable.add(createMiniLabel(I18N.getString("level"))); //$NON-NLS-1$
        miniStatsTable.add(createMiniLabel(mission.getMinor()+"")).left(); //$NON-NLS-1$
		miniStatsTable.add().width(GameWorld.UNIT_WIDTH/2);
		final float miniStatsTableWidth = 20*2+60+100*2;
        final Table topLeftTable = new Table();
        topLeftTable.setFillParent(true);
        topLeftTable.align(Align.topLeft);
        topLeftTable.add(btnSound).padLeft(10);
        topLeftTable.add(btnPause).padLeft(10);
        // banner size is unpredicatble atm
        //topLeftTable.add(miniStatsTable).width(SCREEN_WIDTH - 2*(10+btnPause.getWidth()) - BANNER_WIDTH).center();
        //final Cell<Table> miniStatsTableCell = topLeftTable.add(miniStatsTable).padLeft(40).left();
        final Cell<Table> miniStatsTableCell = topLeftTable.add(miniStatsTable).width(SCREEN_WIDTH - 4*(10+btnPause.getWidth())).padLeft(0).center();
        guiStage.addActor(topLeftTable);
        
//		final Table rootTable = new Table();
//		rootTable.setFillParent(true);
//		rootTable.align(Align.top);
//		rootTable.add(topTable).width(Value.percentWidth(50f));
//		guiStage.addActor(rootTable);

        
        // pause screen must be last element to be added pn gui, becasue it overdrwas everything
//   		guiStage.addActor(pauseScreen);
        // TODO add more other GUI elements here  
	}  
      
    @Override  
    public void render(float delta) {  
    	
        guiCam = (OrthographicCamera) guiStage.getCamera();  
        guiCam.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);  
  
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
        Gdx.gl.glEnable(GL20.GL_TEXTURE_2D);  
        guiCam.update();  
  
        // dont update the world, if game paused
        if (!GameManager.getInstance().isPaused())
        	world.update(delta); // update the box2d world          
        guiStage.act(delta); // update GUI  
          
        renderer.render(); // draw the box2d world  
        guiStage.draw(); // draw the GUI  
    }

	@Override
	public void resize(int width, int height) {
		// pass true because camera is unchanged on this UI stage
		guiStage.getViewport().update(width, height, true);
		
		// make the actors and stage still react to touch after resize, but coordinates are still fucked after resize
		// taken out becasue every click refreshes the handler now
//		Gdx.input.setInputProcessor(world.stage);
	}
	
	public void setAnnouncementText(CharSequence text)
	{
		setAnnouncementText(text, false);
	}
	
	public void setAnnouncementText(CharSequence text, boolean silent)
	{
		if (isAnnouncementFrozen)
			return;
		
		labelMessage.setText(text+"\n"); //$NON-NLS-1$
		
		if (!silent)
			SoundPlayer.playSound(Assets.getSound(SoundAsset.NOTIFICATION));
		
		// if it is the final message freeze it so it cant be overwritten anymnore
		// TODO it alos should not disappear anymore
//		GameManager gm = GameManager.getInstance();
//		if (gm.winner != gm.NONE)
//		{
//			freezeAnnouncementText();
//		}
	}

	private void freezeAnnouncementText() {
		//labelMessage.setPosition(0, SCREEN_HEIGHT/2);
		isAnnouncementFrozen = true;
		
	}

	public Stage getGuiStage() {
		return guiStage;
	}

	public OrthographicCamera getGuiCam() {
		return guiCam;
	}

	public GameRenderer getRenderer() {
		return renderer;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		// TODO with the current implementation a screen should be disposed after hiding (maybe not on pausing?)
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}  
}  
