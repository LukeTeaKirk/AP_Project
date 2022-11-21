package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.Game;
import com.mananaajaystudios.tankgame.screens.*;

public class TopDog extends Game {
    private MainPage mainPage;
    private GameModeSelector gameMode;
    private MyTankGame tankGame;
    private EndGame endGame;
    private TankSelectorPlayer2 tankSelectorp2;
    private TankSelectorPlayer1 tankSelectorp1;
    private MyTankGame mytankGame;
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
            case "TANKP1":
                if(tankSelectorp1 == null) tankSelectorp1 = new TankSelectorPlayer1(this);
                this.setScreen(tankSelectorp1);
                break;
            case "TANKP2":
                if(tankSelectorp2 == null) tankSelectorp2 = new TankSelectorPlayer2(this);
                this.setScreen(tankSelectorp2);
                break;
            case "GAME":
                if(mytankGame == null) mytankGame = new MyTankGame(this);
                this.setScreen(mytankGame);
                break;
        }
    }
}
