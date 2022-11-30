package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class TankBurantino extends Tank{
    private Sprite tankSprite;
    public TankBurantino(Integer PlayerNumber) {
        super(PlayerNumber);
        super.tankRegion = Atlas.findRegion("Buratino");
        tankSprite = new Sprite(tankRegion);
        tankSprite.setSize(150, 150);
        if(PlayerNumber == 1){
            tankSprite.setPosition(50, 80);
        }
        else if(PlayerNumber == 2){
            tankSprite.setPosition(850, 80);
            tankSprite.flip(true, false);
        }
        this.body.setUserData(tankSprite);
    }
    @Override
    public void act(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.A) && PlayerNumber == 1) {
            body.setLinearVelocity(1f, 0f);
            this.tankSprite.setPosition(this.tankSprite.getX() - 10,this.tankSprite.getY());

        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && PlayerNumber == 1) {
            body.setLinearVelocity(-1f, 0f);
            this.tankSprite.setPosition(this.tankSprite.getX() + 10, this.tankSprite.getY());
        }
        this.body.setUserData(tankSprite);
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
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(this.tankSprite.getX(), this.tankSprite.getY());
        FixtureDef fixturedef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.4f);
        fixturedef.shape = shape;
        fixturedef.density = 1.0f;
        fixturedef.friction = 0.3f;
        this.body = world.createBody(bodyDef);
        body.createFixture(fixturedef);

    }
    public void updateBodyPosition(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(this.tankSprite.getX(), this.tankSprite.getY());
        this.body = world.createBody(bodyDef);
    }

}

