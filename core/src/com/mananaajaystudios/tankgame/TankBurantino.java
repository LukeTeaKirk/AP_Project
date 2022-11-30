package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class TankBurantino extends Tank{
    private Sprite tankSprite;
    public TankBurantino(Integer PlayerNumber) {
        super(PlayerNumber);
        super.tankRegion = Atlas.findRegion("Buratino");
        tankSprite = new Sprite(tankRegion);
        tankSprite.setSize(150, 150);
        if(PlayerNumber == 1){
            tankSprite.setPosition(50, 300);
        }
        else if(PlayerNumber == 2){
            tankSprite.setPosition(850, 300);
            tankSprite.flip(true, false);
        }
    }
    @Override
    public void act(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.A) && PlayerNumber == 1) {
            body.applyLinearImpulse(new Vector2(-100f, 0), body.getWorldCenter(), false);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && PlayerNumber == 1) {
            body.applyLinearImpulse(new Vector2(100f, 0), body.getWorldCenter(), false);

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
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(-500, 0);
        FixtureDef fixturedef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setPosition(new Vector2(0,0));
        shape.setRadius(3f);
        fixturedef.shape = shape;
        fixturedef.density = 1.0f;
        fixturedef.friction = 0.3f;
        this.body = world.createBody(bodyDef);
        this.body.setUserData(tankSprite);
        System.out.println(body.getPosition());
        Fixture fix = body.createFixture(fixturedef);
        System.out.println("2");
    }
    @Override
    public void updateBodyPosition() {
        this.tankSprite.setPosition(this.body.getPosition().x + 570, this.body.getPosition().y + 320);
        //this.body = world.createBody(bodyDef);
    }

}

