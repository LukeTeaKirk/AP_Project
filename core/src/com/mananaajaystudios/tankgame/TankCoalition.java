package com.mananaajaystudios.tankgame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import javax.annotation.processing.SupportedSourceVersion;

public class TankCoalition extends Tank {
    private transient Sprite tankSprite;

    public TankCoalition(Integer PlayerNumber) {
        super(PlayerNumber);
        super.tankRegion = Atlas.findRegion("Coalition");
        tankSprite = new Sprite(tankRegion);
        weapons.add(new MachineGun());
        weapons.add(new Rocket());
        weapons.add(new SatelliteStrike());
        currentWeapon = weapons.get(0);

        tankSprite.setSize(100, 100);
        if(PlayerNumber == 1){
            tankSprite.setPosition(120, 250);
        }
        else if(PlayerNumber == 2){
            tankSprite.setPosition(850, 250);
            tankSprite.flip(true, false);
        }
        setTankSprite(tankSprite);
    }
    @Override
    public void act(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.A) && this.canMove == 1){
            body.applyLinearImpulse(new Vector2(-100f, 0), body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && this.canMove == 1) {
            body.applyLinearImpulse(new Vector2(100f, 0), body.getWorldCenter(), true);

        }
//        this.body.setUserData(tankSprite);
        super.act(delta);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        tankSprite.draw(batch);
    }



    @Override
    public void setBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        bodyDef.angularDamping = 1f;
//        bodyDef.linearDamping = 1f;
        if(PlayerNumber ==1){
            if(lastPositionTank != null){
                bodyDef.position.set(lastPositionTank);
            }
            else{
                bodyDef.position.set(-500, 0);
            }
        }
        else{
            if(lastPositionTank != null){
                bodyDef.position.set(lastPositionTank);
            }
            else{
                bodyDef.position.set(500, 0);
            }
        }
        FixtureDef fixturedef = new FixtureDef();
        CircleShape shape = new CircleShape();
        PolygonShape shape2 = new PolygonShape();
        shape2.setAsBox(35f,24f);
        shape.setPosition(new Vector2(0,0));
        shape.setRadius(3f);
        fixturedef.shape = shape2;
        fixturedef.density = 0.1f;
        fixturedef.friction = 0.1f;
        this.body = world.createBody(bodyDef);
        this.body.setUserData(this);
        System.out.println(body.getPosition());
        Fixture fix = body.createFixture(fixturedef);
    }
    @Override
    public void updateBodyPosition() {
        this.tankSprite.setPosition(this.body.getPosition().x + 590, this.body.getPosition().y + 310);
        tankSprite.setOriginCenter();
        tankSprite.setRotation(this.body.getAngle()*70);
        lastPositionTank = this.body.getPosition();
//        System.out.println(this.body.getAngle() + " " + tankSprite.getRotation());
        //this.body = world.createBody(bodyDef);
    }
    //initialize all transient variables after deserialization
    //called after deserialization
    @Override
    public void readObject(){
        Atlas = new TextureAtlas("Spritesheets/Spritesheet1.atlas");
        fuelRegion = Atlas.findRegion("FuelBar");
        weaponRegion = Atlas.findRegion("SplitterChain");
        Texture healthBarTexture = new Texture("HealthBar.jpeg");
        TextureRegion healthRegion = new TextureRegion(healthBarTexture);
        //reinitialize tankregion
        tankRegion = Atlas.findRegion("Coalition");
        healthBar = new Sprite(healthRegion);
        tankSprite = new Sprite(tankRegion);
        tankSprite.setSize(100, 100);
        if (PlayerNumber == 1) {
            tankSprite.setPosition(120, 250);
            fuelBar1 = new Sprite(fuelRegion);
            fuelBar1.setSize(240, 70);
            fuelBar1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 50) * 48, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100) * 95 - 40);
            weaponSelect = new Sprite(weaponRegion);
            weaponSelect.setSize(75, 75);
            weaponSelect.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 20) * 6 - 200, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 25) * 24 - 35);
            healthBar.setSize(400, 50);
            healthBar.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 50) * 48, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100) * 12);
            healthBar.setSize(healthBarPosition, 50);
            isEnabled = 1;

        } else if (PlayerNumber == 2) {
            fuelBar2 = new Sprite(fuelRegion);
            fuelBar2.setSize(0, 0);
            fuelBar2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/50)*12, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*95 -40);
            tankSprite.setPosition(850, 250);
            tankSprite.flip(true, false);
            healthBar.setSize(400, 50);
            healthBar.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 50) * 22, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100) * 12);
            healthBar.setSize(healthBarPosition, 50);
            isEnabled = 0;
        }
        weapons.forEach(Weapon::readObject);
        setTankSprite(tankSprite);
    }
}

