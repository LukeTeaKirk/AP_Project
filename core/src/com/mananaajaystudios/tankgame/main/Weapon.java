package com.mananaajaystudios.tankgame.main;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.io.Serializable;

public class Weapon implements Serializable {
    protected String name;
    protected int damage;
    protected float damageDecayFactor;
    protected int areaOfEffect;
    protected int rateOfFall;
    protected int radiusOfProjectile;
    protected int NoOfProjectiles;
    protected Projectile projectile;
    protected transient BodyDef bodyDef;
    transient FixtureDef fixtureDef;
    protected int totalAmmo, width, height;
    protected int degrees = 0;
    protected int degrees2 = 0;
    protected int offsetX = 0;
    protected transient Texture texture;
    protected transient Sprite TankSprite, ProjectileSprite;
    public Weapon(){
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        fixtureDef = new FixtureDef();
    }

    public String getName() {
        return name;
    }

    public Projectile Fire(World world, float x, float y, Sprite tankSprite, Stage stage, int Player, Tank tank){
        this.TankSprite = tankSprite;
        bodyDef.position.set(tankSprite.getX() -640 +50, tankSprite.getY() -360 +60);
        Sprite temp = new Sprite(texture);
        temp.setSize(width,height);
        temp.setOriginCenter();
        temp.rotate(-40 + degrees);
        Projectile projectile = new Projectile(bodyDef, fixtureDef, world, radiusOfProjectile,texture, damage, stage, Player, temp, degrees2,offsetX, tank);
        projectile.Shoot(x, y);
        return projectile;
    }
    //initialize all transient variables after deserialization
    //called after deserialization
    public void readObject(){
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        fixtureDef = new FixtureDef();
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
        width = 40;
        height = 80;
        degrees = -45;
        degrees2 = 80;
        offsetX = 60;
        texture = new Texture("Missile.png");
    }
    //initialize all transient variables after deserialization
    //called after deserialization
    @Override
    public void readObject(){
        super.readObject();
        texture = new Texture("Missile.png");
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
        width = 100;
        height = 100;
        texture = new Texture("bullet.png");
    }
    //initialize all transient variables after deserialization
    //called after deserialization
    @Override
    public void readObject(){
        super.readObject();
        texture = new Texture("bullet.png");
    }

}

class SatelliteStrike extends Weapon {
    public SatelliteStrike() {
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
        width = 100;
        height = 100;
        texture = new Texture("Sattellite.png");
    }
    @Override
    public void readObject(){
        super.readObject();
        texture = new Texture("Sattellite.png");
    }
}
class FireBall extends Weapon {
    public FireBall() {
        damage = 60;
        damageDecayFactor = 0.5f;
        areaOfEffect = 10;
        rateOfFall = 10;
        totalAmmo = 1;
        name = "FireBall";
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
        radiusOfProjectile = 10;
        width = 100;
        height = 100;
        texture = new Texture("FireBall.png");
    }
    @Override
    public void readObject(){
        super.readObject();
        texture = new Texture("FireBall.png");
    }

}

class DragonBall extends Weapon {
    public DragonBall() {
        damage = 60;
        damageDecayFactor = 0.5f;
        areaOfEffect = 10;
        rateOfFall = 10;
        totalAmmo = 1;
        name = "DragonBall";
        fixtureDef.density = 3f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 2f;
        radiusOfProjectile = 20;
        width = 100;
        height = 100;
        texture = new Texture("DragonBall.png");
    }
    @Override
    public void readObject(){
        super.readObject();
        texture = new Texture("DragonBall.png");
    }

}
class Nuke extends Weapon {
    public Nuke() {
        damage = 60;
        damageDecayFactor = 0.5f;
        areaOfEffect = 10;
        rateOfFall = 10;
        totalAmmo = 1;
        name = "Nuke";
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 3f;
        radiusOfProjectile = 10;
        width = 10;
        height = 10;
        texture = new Texture("Nuke.png");
    }
    @Override
    public void readObject(){
        super.readObject();
        texture = new Texture("Nuke.png");
    }

}


