package com.mananaajaystudios.tankgame;

public class player {
    private final Tank tank;
    private boolean isCurrentTurn;
    public player(Tank tank) {
        this.tank = tank;
    }
    public Tank getTank(){
        return tank;
    }
    public boolean isCurrentTurn() {
        return isCurrentTurn;
    }
    public void setCurrentTurn(boolean currentTurn) {
        isCurrentTurn = currentTurn;
    }
}
