package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;


import java.io.Serializable;
import java.lang.reflect.Array;

public class Weapon implements Serializable {
    protected String name;
    protected int damage;
    protected float damageDecayFactor;
    protected int areaOfEffect;
    protected int rateOfFall;
    protected int radiusOfProjectile;
    protected int NoOfProjectiles;
    protected Projectile projectile;
    protected BodyDef bodyDef;
    FixtureDef fixtureDef;
    protected int totalAmmo;

    protected Sprite TankSprite, ProjectileSprite;
    public Weapon(){
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        fixtureDef = new FixtureDef();
    }

    public String getName() {
        return name;
    }

    public void Fire(World world, float x, float y, Sprite tankSprite){
        this.TankSprite = tankSprite;
        bodyDef.position.set(tankSprite.getX() -640 +50, tankSprite.getY() -360 +60);
        Projectile projectile = new Projectile(bodyDef, fixtureDef, world, radiusOfProjectile,ProjectileSprite, damage);
        projectile.Shoot(x, y);
    }
}
class Rocket extends Weapon {
    public Rocket() {
        damage = 50;
        damageDecayFactor = 0.5f;
        areaOfEffect = 10;
        rateOfFall = 10;
        totalAmmo = 10;
        name = "Rocket";
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
        radiusOfProjectile = 10;
    }

}

class MachineGun extends Weapon {
    public MachineGun(){
        damage = 20;
        damageDecayFactor = 0.5f;
        areaOfEffect = 10;
        rateOfFall = 10;
        totalAmmo = 100;
        name = "MachineGun";
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
        radiusOfProjectile = 5;
        Texture texture = new Texture("bullet.png");
        ProjectileSprite = new Sprite(texture);
    }

}

class SatelliteStrike extends Weapon {
    public SatelliteStrike(){
        damage = 60;
        damageDecayFactor = 0.5f;
        areaOfEffect = 10;
        rateOfFall = 10;
        totalAmmo = 1;
        name = "Satellite Strike";
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
        radiusOfProjectile = 20;
    }


}