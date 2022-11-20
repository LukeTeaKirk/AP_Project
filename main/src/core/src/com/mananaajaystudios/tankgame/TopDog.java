package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.Game;
import com.mananaajaystudios.tankgame.screens.EndGame;
import com.mananaajaystudios.tankgame.screens.GameModeSelector;
import com.mananaajaystudios.tankgame.screens.MainPage;

public class TopDog extends Game {
    private MainPage mainPage;
    private GameModeSelector gameMode;
    private PauseMenu pauseMenu;
    private MyTankGame tankGame;
    private EndGame endGame;

    public MainPage init(){
        mainPage = new MainPage(this);
        return mainPage;
    }
    @Override
    public void create() {
        mainPage = new MainPage(this);
        setScreen(mainPage);
    }

    public void changeScreen(String screen){
        switch(screen){
            case "MAIN":
                if(mainPage == null) mainPage = new MainPage(this);
                this.setScreen(mainPage);
                break;
            case "GAMEMODE":
                System.out.println("switch");
                if(gameMode == null) gameMode = new GameModeSelector(this);
                this.setScreen(gameMode);
                break;
            /*case "GAME":
                if(tankGame == null) tankGame = new MyTankGame(this);
                this.setScreen(tankGame);
                break;
            case "PAUSE":
                if(pauseMenu == null) pauseMenu = new PauseMenu(this);
                this.setScreen(pauseMenu);
                break;
            case "ENDGAME":
                if(endGame == null) endGame = new EndGame(this);
                this.setScreen(endGame);
                break;*/
        }
    }
}
