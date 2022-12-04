package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import java.io.Serializable;
import java.util.ArrayList;

//health bar
//fuel bar
//switch weapon
public class Tank extends Actor implements Serializable {
    protected Sprite tank, fireButton, fuelBar, weaponSelect, healthBar;
    protected int health, fuel, weapon;
    protected TextureAtlas Atlas;
    MoveToAction action = new MoveToAction();
    protected Body body;
    Integer PlayerNumber;
    protected Sprite tankSprite;
    protected ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    protected boolean isDead;
    protected Weapon currentWeapon;
    protected float ForceX, ForceY;
    protected int isEnabled;
    protected TextureRegion tankRegion, fuelRegion, weaponRegion, fireRegion;

    public Tank(Integer PlayerNumber) {
        this.PlayerNumber = PlayerNumber;
        Atlas = new TextureAtlas("Spritesheets/Spritesheet1.atlas");

        fuelRegion = Atlas.findRegion("FuelBar");
        weaponRegion = Atlas.findRegion("SplitterChain");
        Texture healthBarTexture = new Texture("HealthBar.jpeg");
        TextureRegion healthRegion = new TextureRegion(healthBarTexture);
        healthBar = new Sprite(healthRegion);

        this.health = 100;
        this.isDead = false;
        if(PlayerNumber == 1){
            fuelBar = new Sprite(fuelRegion);
            fuelBar.setSize(240, 70);
            fuelBar.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/50)*48, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*95 -40);
//            weaponSelect = new Sprite(weaponRegion);
//            weaponSelect.setSize(75, 75);
//            weaponSelect.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/20)*6 - 200, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 25) *24 - 35);
            healthBar.setSize(400, 50);
            healthBar.setPosition(175, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*12);
            isEnabled = 1;

        }
        else if(PlayerNumber == 2){
            healthBar.setSize(400, 50);
            healthBar.setPosition(710, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*12);
            isEnabled = 0;
        }
    }

    public boolean isDead() {
        return isDead;
    }
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void switchWeapon(String weaponName) {
        for (Weapon weapon : weapons) {
            if (weapon.getName().equals(weaponName)) {
                currentWeapon = weapon;
            }
        }
    }

    public void damageTaken(int damage){
        this.health -= damage;
        healthBar.setSize(healthBar.getWidth() - (damage*4), healthBar.getHeight());
        if(health <= 0){
            isDead = true;
        }
    }
    public int getPlayerNumber(){
        return PlayerNumber;
    }
    public void setAngleAndPower(float forceX, float forceY){
        this.ForceX = forceX;
        this.ForceY = forceY;
    }
    public void FireWeapon(World world,Sprite tankSprite){

        if(PlayerNumber == 1){
            currentWeapon.Fire(world, ForceX, ForceY,tankSprite);

        }
        else if(PlayerNumber == 2){
            currentWeapon.Fire(world, ForceX, ForceY,tankSprite);

        }


    }

    public void setTankSprite(Sprite tankSprite) {
        this.tankSprite = tankSprite;
    }
    public Sprite getTankSprite() {
        return tankSprite;
    }
    public void enableTank(){
        isEnabled = 1;
    }
    public void disableTank(){
        isEnabled = 0;
    }
    public void setBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        this.body = world.createBody(bodyDef);
    }

        @Override
    public void draw(Batch batch, float parentAlpha) {
        if(PlayerNumber == 1){
            fuelBar.draw(batch);
//            weaponSelect.draw(batch);
            healthBar.draw(batch);
        }
        else if(PlayerNumber == 2){
            healthBar.draw(batch);
        }
    }
    public void updateBodyPosition() {
    }

    @Override
    public void act(float delta) {

        super.act(delta);
    }
}
