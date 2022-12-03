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



        if(PlayerNumber == 1){
            fuelBar = new Sprite(fuelRegion);
            fuelBar.setSize(240, 70);
            fuelBar.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/50)*48, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*95 -40);
            weaponSelect = new Sprite(weaponRegion);
            weaponSelect.setSize(75, 75);
            weaponSelect.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/20)*6 - 200, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 25) *24 - 35);
            healthBar.setSize(400, 50);
            healthBar.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/50)*48, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*12);
            isEnabled = 1;

        }
        else if(PlayerNumber == 2){
            healthBar.setSize(400, 50);
            healthBar.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/50)*22, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*12);
            isEnabled = 0;
        }
    }

    public int getPlayerNumber(){
        return PlayerNumber;
    }
    public void setAngleAndPower(float forceX, float forceY){
        this.ForceX = forceX;
        this.ForceY = forceY;
    }
    public void Fire(World world,Sprite tankSprite){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //set position of projectile above tank
        bodyDef.position.set(tankSprite.getX() -640 +50, tankSprite.getY() -360 +90);
//        bodyDef.position.set(tankSprite.getX()-600, tankSprite.getY()-300);
        CircleShape shape = new CircleShape();
        shape.setRadius(10);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
//        Body body = world.createBody(bodyDef);
        if(PlayerNumber == 1){
            Projectile projectile = new Projectile(bodyDef, shape, fixtureDef, world, ForceX, ForceY, this);
//            body.applyLinearImpulse(ForceX, ForceY, body.getPosition().x, body.getPosition().y, true);
        }
        else if(PlayerNumber == 2){
            Projectile projectile = new Projectile(bodyDef, shape, fixtureDef, world, ForceX, ForceY, this);
//            body.applyLinearImpulse(ForceX, ForceY, body.getPosition().x, body.getPosition().y, true);
        }
//        body.createFixture(fixtureDef);

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
            weaponSelect.draw(batch);
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
