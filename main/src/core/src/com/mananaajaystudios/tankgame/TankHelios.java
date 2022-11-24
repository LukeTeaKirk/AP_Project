package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TankHelios extends Tank{

    public TankHelios(Integer PlayerNumber) {
        super(PlayerNumber);
        super.tankRegion = Atlas.findRegion("Helios");
        tank = new Sprite(tankRegion);
        tank.setSize(150, 150);
        if(PlayerNumber == 1){
            tank.setPosition(50, 40);
        }
        else if(PlayerNumber == 2){
            tank.setPosition(850, 40);
            tank.flip(true, false);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        tank.draw(batch);
    }
}
