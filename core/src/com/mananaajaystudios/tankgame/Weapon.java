package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.physics.box2d.Body;

import java.lang.reflect.Array;

public class Weapon {
    public int damage;
    public float damageDecayFactor;
    public int areaOfEffect;
    public int rateOfFall;
    public Body projectiles;
    public int totalAmmo;
    public Weapon(){
    }
}
class Rocket extends Weapon {

}

class MachineGun extends Weapon {

}

class SatelliteStrike extends Weapon {

}