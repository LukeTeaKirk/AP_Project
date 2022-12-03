package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Projectile extends Actor {

    private BodyDef bodyDef;
    private CircleShape circleShape;
    private FixtureDef fixtureDef;
    private Body body;
    private Tank ownerTank;

    private boolean Hit = false;

    public Projectile(BodyDef bodyDef, CircleShape circleShape, FixtureDef fixtureDef, World world, float x, float y, Tank ownerTank) {
        this.bodyDef = bodyDef;
        this.circleShape = circleShape;
        this.fixtureDef = fixtureDef;
        this.body = world.createBody(bodyDef);
        this.body.setUserData(this);
        body.applyLinearImpulse(x, y, body.getPosition().x, body.getPosition().y, true);
        this.body.createFixture(fixtureDef);
        this.ownerTank = ownerTank;
    }
    public void setHit(boolean hit) {
        Hit = hit;
    }
    public boolean isHit() {
        return Hit;
    }

    public Tank getOwnerTank() {
        return ownerTank;
    }

    public Body getBody() {
        return body;
    }
}
