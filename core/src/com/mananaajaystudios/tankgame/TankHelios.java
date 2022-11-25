package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TankHelios extends Tank{
    private Sprite tankSprite;

    public TankHelios(Integer PlayerNumber) {
        super(PlayerNumber);
        super.tankRegion = Atlas.findRegion("Helios");
        tankSprite = new Sprite(tankRegion);
        tankSprite.setSize(150, 150);
        if(PlayerNumber == 1){
<<<<<<< HEAD
            tankSprite.setPosition(50, 40);
        }
        else if(PlayerNumber == 2){
            tankSprite.setPosition(850, 40);
            tankSprite.flip(true, false);
=======
            tank.setPosition(50, 80);
        }
        else if(PlayerNumber == 2){
            tank.setPosition(850, 80);
            tank.flip(true, false);
>>>>>>> 3c8f3fd3bd0029aa772fad594b0e2eba75b5310e
        }
    }
    @Override
    public void act(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.A) && PlayerNumber == 1) {
            System.out.println(this.tankSprite.getX());
            this.tankSprite.setPosition(this.tankSprite.getX() - 10,this.tankSprite.getY());
            System.out.println(this.tankSprite.getX());

        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && PlayerNumber == 1) {
            System.out.println(this.tankSprite.getX());
            this.tankSprite.setPosition(this.tankSprite.getX() + 10, this.tankSprite.getY());
            System.out.println(this.tankSprite.getX());
        }


        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        tankSprite.draw(batch);
    }
}
