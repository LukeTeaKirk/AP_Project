package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TankBurantino extends Tank{
    private Sprite tankSprite;
    public TankBurantino(Integer PlayerNumber) {
        super(PlayerNumber);
        super.tankRegion = Atlas.findRegion("Buratino");
        tankSprite = new Sprite(tankRegion);
        tankSprite.setSize(150, 150);
        if(PlayerNumber == 1){
            tankSprite.setPosition(50, 40);
        }
        else if(PlayerNumber == 2){
            tankSprite.setPosition(850, 40);
            tankSprite.flip(true, false);
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

