package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.physics.box2d.*;

public class CollisionDetector implements ContactListener {

    World world;
    public CollisionDetector(World world) {
        this.world = world;
    }
    @Override
    public void beginContact(Contact contact) {
        if (contact.getFixtureA().getBody().getUserData() instanceof Projectile && contact.getFixtureB().getBody().getUserData() instanceof Tank) {
            Projectile projectile = (Projectile) contact.getFixtureA().getBody().getUserData();
            Tank tank = (Tank) contact.getFixtureB().getBody().getUserData();
            if(projectile.getCanCauseDamage()){
                System.out.println("Tank " + tank.getPlayerNumber() + " hit by projectile");
                projectile.setHit(true);
                tank.damageTaken(projectile.getProjectileDamage());
            }else{
                projectile.setCanCauseDamage(true);
            }

//            tank.setHealth(tank.getHealth() - projectile.getDamage());
        }
        if (contact.getFixtureA().getBody().getUserData() instanceof Projectile && contact.getFixtureB().getBody().getUserData() instanceof GameWorld) {
            System.out.println("Projectile hit world");
            Projectile projectile = (Projectile) contact.getFixtureA().getBody().getUserData();
            if(projectile.getCanCauseDamage()){
                projectile.setHit(true);
                projectile.getProjectileDamage();
            }else{
                projectile.setCanCauseDamage(true);
            }

//            tank.setHealth(tank.getHealth() - projectile.getDamage());
        }
        if (contact.getFixtureB().getBody().getUserData() instanceof Projectile && contact.getFixtureA().getBody().getUserData() instanceof GameWorld) {
            System.out.println("Projectile hit world");
            Projectile projectile = (Projectile) contact.getFixtureB().getBody().getUserData();
            if (projectile.getCanCauseDamage()) {
                projectile.setHit(true);
                projectile.getProjectileDamage();
            } else {
                projectile.setCanCauseDamage(true);
            }
        }
        if (contact.getFixtureB().getBody().getUserData() instanceof Projectile && contact.getFixtureA().getBody().getUserData() instanceof Tank) {
            Projectile projectile = (Projectile) contact.getFixtureB().getBody().getUserData();
            Tank tank = (Tank) contact.getFixtureA().getBody().getUserData();
            if (projectile.getCanCauseDamage()) {
                System.out.println("Tank " + tank.getPlayerNumber() + " hit by projectile");
                projectile.setHit(true);
                tank.damageTaken(projectile.getProjectileDamage());
            } else {
                projectile.setCanCauseDamage(true);
            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
