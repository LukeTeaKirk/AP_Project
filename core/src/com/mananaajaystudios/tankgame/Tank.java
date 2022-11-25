package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

//health bar
//fuel bar
//switch weapon
public class Tank extends Actor {
    protected Sprite tank, fireButton, fuelBar, weaponSelect;
    protected int health, fuel, weapon;
    protected TextureAtlas Atlas;

    Integer PlayerNumber;
    protected TextureRegion tankRegion, fuelRegion, weaponRegion, fireRegion;

    public Tank(Integer PlayerNumber) {
        this.PlayerNumber = PlayerNumber;
        Atlas = new TextureAtlas("Spritesheets/Spritesheet1.atlas");
//        tankRegion = Atlas.findRegion("Buratino");
        fuelRegion = Atlas.findRegion("FuelBar");
        weaponRegion = Atlas.findRegion("SplitterChain");
        fireRegion = Atlas.findRegion("FireButton");
//        tank = new Sprite(tankRegion);
//        tank.setSize(100, 100);
//        tank.setPosition(0, 0);

        if(PlayerNumber == 1){
            fuelBar = new Sprite(fuelRegion);
            fuelBar.setSize(240, 70);
            fuelBar.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/50)*48, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*95 -40);
            weaponSelect = new Sprite(weaponRegion);
            weaponSelect.setSize(75, 75);
            weaponSelect.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/20)*6, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 25) *24 - 35);
            fireButton = new Sprite(fireRegion);
            fireButton.setSize(100, 64);
            fireButton.setPosition(Gdx.graphics.getWidth()- Gdx.graphics.getWidth()/4 +50, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()+20);
//            tank.setPosition(50, 50);
        }
        else if(PlayerNumber == 2){
//            tank.setPosition(500, 50);
//            tank.flip(true, false);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        tank.draw(batch);
        if(PlayerNumber == 1){
            fuelBar.draw(batch);
            weaponSelect.draw(batch);
            fireButton.draw(batch);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
