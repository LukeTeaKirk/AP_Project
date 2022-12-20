package com.mananaajaystudios.tankgame.main;

import com.badlogic.gdx.Game;
import com.mananaajaystudios.tankgame.main.screens.*;

public class TopDog extends Game {
    private SplashScreen splashScreen;
    private MainPage mainPage;
    private GameModeSelector gameMode;
    private MyTankGame tankGame;
    private LoadGamePage loadGame;

    private EndGame endGame;
    private TankSelectorPlayer2 tankSelectorp2;
    private TankSelectorPlayer1 tankSelectorp1;
    private MyTankGame mytankGame;
    @Override
    public void create() {
        splashScreen = new SplashScreen(this);
        setScreen(splashScreen);
    }
    public MainPage getMainPage(){
        return this.mainPage;
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
            case "TANKP1":
                tankSelectorp1 = new TankSelectorPlayer1(this);
                this.setScreen(tankSelectorp1);
                break;
            case "TANKP2":
                if(tankSelectorp2 == null) tankSelectorp2 = new TankSelectorPlayer2(this);
                this.setScreen(tankSelectorp2);
                break;
            case "GAME":
                //if(mytankGame == null) mytankGame = new MyTankGame(this);
                this.setScreen(mytankGame);
                break;
            case "LOAD":
                if(loadGame == null) loadGame = new LoadGamePage(this);
                System.out.println("com/mananaajaystudios/tankgame/test");
                this.setScreen(loadGame);
                break;

        }
    }
    public void changeScreen(String screen, com.mananaajaystudios.tankgame.main.Game game){
        switch(screen){
            case "INGAME":
                tankGame = new MyTankGame(this, game);
                this.setScreen(tankGame);
                break;
        }
    }
}
