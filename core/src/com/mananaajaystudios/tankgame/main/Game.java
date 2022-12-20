package com.mananaajaystudios.tankgame.main;

import java.io.Serializable;

public class Game implements Serializable {
    public boolean loadedGame;
    public String gameID;
    private player player1, player2;

    public Game(player player1, player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    public void setPlayers(player p1, player p2){
        this.player1 = p1;
        this.player2 = p2;
    }
    public player getPlayer1(){
        return player1;
    }
    public player getPlayer2(){
        return player2;
    }
}