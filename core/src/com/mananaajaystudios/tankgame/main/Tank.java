package com.mananaajaystudios.tankgame.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.io.Serializable;
import java.util.ArrayList;

//health bar
//fuel bar
//switch weapon
public class Tank extends Actor implements Serializable {
    protected transient Sprite tank, fireButton, fuelBar1,fuelBar2, weaponSelect, healthBar;
    protected int health, fuel, weapon;
    protected int healthBarPosition;
    protected transient TextureAtlas Atlas;
    protected transient Body body;
    Integer PlayerNumber;
    protected ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    protected boolean isDead;
    protected Weapon currentWeapon;
    protected transient Sprite tankSprite;
    protected transient ParticleEffect effect;

    protected float ForceX, ForceY;
    protected int isEnabled, canMove;
    protected int firedThisMove = 0;
    protected transient TextureRegion tankRegion, fuelRegion, weaponRegion, fireRegion;
    protected transient Projectile projectile;
    protected Vector2 lastPositionTank;

    public Tank(Integer PlayerNumber) {
        this.PlayerNumber = PlayerNumber;
        Atlas = new TextureAtlas("Spritesheets/Spritesheet1.atlas");

        fuelRegion = Atlas.findRegion("FuelBar");
        weaponRegion = Atlas.findRegion("SplitterChain");
        Texture healthBarTexture = new Texture("HealthBar.jpeg");
        TextureRegion healthRegion = new TextureRegion(healthBarTexture);
        healthBar = new Sprite(healthRegion);
        fuelBar1 = new Sprite(fuelRegion);
        fuelBar2 = new Sprite(fuelRegion);
        this.health = 100;
        this.fuel = 240;
        this.isDead = false;
        this.firedThisMove = 0;
        if(PlayerNumber == 1){

            healthBar.setSize(400, 50);
            healthBar.setPosition(175, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*12);
            healthBarPosition = (int) healthBar.getWidth();
            isEnabled = 1;
            canMove = 1;
            fuelBar1.setSize(240, 70);
            fuelBar1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/50)*48, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*95 -40);
        }
        else if(PlayerNumber == 2){
            healthBar.setSize(400, 50);
            healthBar.setPosition(710, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*12);
            healthBarPosition = (int) healthBar.getWidth();
            isEnabled = 0;
            canMove = 0;
            fuelBar2.setSize(0, 0);
            fuelBar2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/50)*12, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*95 -40);
        }
    }
    public Body getBody() {
        return body;
    }

    public boolean isDead() {
        return isDead;
    }
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void switchWeapon(String weaponName) {
        for (Weapon weapon : weapons) {
            if (weapon.getName().equals(weaponName)) {
                currentWeapon = weapon;
            }
        }
    }

    public void damageTaken(int damage){
        this.health -= damage;
        healthBar.setSize(healthBar.getWidth() - (damage*4), healthBar.getHeight());
        healthBarPosition = (int) healthBar.getWidth();
        if(health <= 0){
            isDead = true;
        }
    }
    public int getPlayerNumber(){
        return PlayerNumber;
    }
    public void setAngleAndPower(float forceX, float forceY){
        this.ForceX = forceX;
        this.ForceY = forceY;
    }
    public void FireWeapon(World world,Sprite tankSprite, Stage stage){

        if(PlayerNumber == 1 && firedThisMove == 0){
            this.projectile = currentWeapon.Fire(world, ForceX, ForceY,tankSprite, stage, PlayerNumber);
            firedThisMove = 1;
        }
        else if(PlayerNumber == 2 && firedThisMove == 0){
            this.projectile = currentWeapon.Fire(world, ForceX, ForceY,tankSprite, stage, PlayerNumber);
            firedThisMove = 1;
        }


    }

    //return projectile
    public Projectile getProjectile(){
        return projectile;
    }
    public void setTankSprite(Sprite tankSprite) {
        this.tankSprite = tankSprite;
    }
    public Sprite getTankSprite() {
        return tankSprite;
    }
    public void enableTank(){
        this.isEnabled = 1;
        this.canMove = 1;
        this.fuel=240;
        this.firedThisMove = 0;
        if(PlayerNumber == 1){
            fuelBar1.setSize(240, 70);
        }
        else if(PlayerNumber == 2){
            fuelBar2.setSize(240, 70);

        }
    }
    public void disableTank(){
        this.isEnabled = 0;
        this.canMove = 0;
        if(PlayerNumber == 1){
            fuelBar1.setSize(0, 0);
        }
        else if(PlayerNumber == 2){
            fuelBar2.setSize(0, 0);
        }
    }
    public int getCanMove(){
        return canMove;
    }

    public void reduceFuel(int Fuel){
        if(this.fuel > 0){
            this.fuel -= Fuel;
            if(PlayerNumber == 1){
                System.out.println("Fuel: " + fuel);
                System.out.println("FuelBar1: " + fuelBar1.getWidth());
                fuelBar1.setSize(fuelBar1.getWidth() - (Fuel), fuelBar1.getHeight());
            }
            else if(PlayerNumber == 2){
                System.out.println("Fuel: " + fuel);
                System.out.println("FuelBar2: " + fuelBar2.getWidth());
                fuelBar2.setSize(fuelBar2.getWidth() - (Fuel), fuelBar2.getHeight());
            }
        }
        if(this.fuel <= 0){
            canMove = 0;
        }
    }
    public int getIsEnabled(){
        return isEnabled;
    }
    public void setBody(World world) {
        System.out.println("running setBody");
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        this.body = world.createBody(bodyDef);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(PlayerNumber == 1){
            fuelBar1.draw(batch);
//            weaponSelect.draw(batch);
            healthBar.draw(batch);
        }
        else if(PlayerNumber == 2){
            healthBar.draw(batch);
            fuelBar2.draw(batch);
        }
    }
    public void updateBodyPosition() {
    }

    @Override
    public void act(float delta) {

        super.act(delta);
    }
    //initialize all transient variables after deserialization
    //called after deserialization
    public void readObject(){
        Atlas = new TextureAtlas("Spritesheets/Spritesheet1.atlas");
        fuelRegion = Atlas.findRegion("FuelBar");
        weaponRegion = Atlas.findRegion("SplitterChain");
        Texture healthBarTexture = new Texture("HealthBar.jpeg");
        TextureRegion healthRegion = new TextureRegion(healthBarTexture);
        healthBar = new Sprite(healthRegion);
        if(PlayerNumber == 1){
            System.out.println("reinit tank1");
            fuelBar1 = new Sprite(fuelRegion);
            fuelBar1.setSize(240, 70);
            fuelBar1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/50)*48, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*95 -40);
            weaponSelect = new Sprite(weaponRegion);
            weaponSelect.setSize(75, 75);
            weaponSelect.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/20)*6 - 200, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 25) *24 - 35);
            healthBar.setSize(400, 50);
            healthBar.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/50)*48, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*12);
            isEnabled = 1;

        }
        else if(PlayerNumber == 2){
            System.out.println("reinit tank2");
            healthBar.setSize(400, 50);
            healthBar.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/50)*22, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 100)*12);
            isEnabled = 0;
        }
    }
}
