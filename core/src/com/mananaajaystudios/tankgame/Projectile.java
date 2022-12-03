package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Projectile extends Actor {

    private BodyDef bodyDef;

    private boolean CanCauseDamage;

    private FixtureDef fixtureDef;
    private Body body;
    World world;


    private boolean Hit = false;

    public Projectile(BodyDef bodyDef, FixtureDef fixtureDef, World world, int radius) {
        this.bodyDef = bodyDef;
        this.fixtureDef = fixtureDef;
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        fixtureDef.shape = shape;
        this.world = world;
        CanCauseDamage = false;

    }
    public void setHit(boolean hit) {
        Hit = hit;
    }
    public boolean isHit() {
        return Hit;
    }

    public void setCanCauseDamage(boolean canCauseDamage) {
        CanCauseDamage = canCauseDamage;
    }
    public boolean getCanCauseDamage(){
        return CanCauseDamage;
    }
    public void Shoot(float x, float y){
        this.body = world.createBody(bodyDef);
        this.body.setUserData(this);
        body.applyLinearImpulse(x, y, body.getPosition().x, body.getPosition().y, true);
        this.body.createFixture(fixtureDef);
    }

    public Body getBody() {
        return body;
    }
}
