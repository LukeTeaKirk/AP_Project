package com.mananaajaystudios.tankgame;

public class Game {

    private final player player1, player2;

    public Game(player player1, player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public player getPlayer1(){
        return player1;
    }
    public player getPlayer2(){
        return player2;
    }
}
