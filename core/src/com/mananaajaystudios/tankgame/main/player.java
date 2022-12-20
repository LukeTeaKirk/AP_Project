package com.mananaajaystudios.tankgame.main;

import java.io.Serializable;

public class player implements Serializable {
    private Tank tank;
    private boolean isCurrentTurn;
    public player(Tank tank) {
        this.tank = tank;
    }
    public Tank getTank(){
        return tank;
    }
    public void setTank(Tank tank) {
        this.tank = tank;
    }
    public boolean isCurrentTurn() {
        return isCurrentTurn;
    }
    public void setCurrentTurn(boolean currentTurn) {
        isCurrentTurn = currentTurn;
    }
}