package com.mananaajaystudios.tankgame.main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Projectile extends Actor {
    private transient BodyDef bodyDef;
    private int playerNumber;
    private boolean CanCauseDamage;
    private FixtureDef fixtureDef;
    private transient Body body;
    World world;
    private transient Sprite projectileSprite;
    private int projectileDamage;
    private boolean Hit = false;
    private transient player otherPlayer;
    private int offsetX;
    private Tank tank;

    public Projectile(BodyDef bodyDef, FixtureDef fixtureDef, World world, int radius, Texture texture, int projectileDamage, Stage stage, int player, Sprite sprite2, int degrees, int offsetX, Tank tank) {
        this.bodyDef = bodyDef;
        this.fixtureDef = fixtureDef;
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        fixtureDef.shape = shape;
        this.world = world;
        CanCauseDamage = false;
        this.offsetX = offsetX;
        playerNumber = player;
        this.projectileSprite = sprite2;
        if(player == 2){
            this.projectileSprite.setFlip(true,false);
            this.projectileSprite.rotate(90 + degrees);
        }
        this.projectileDamage = projectileDamage;
        this.tank = tank;
        stage.addActor(this);
    }

    public int getProjectileDamage(boolean aoe) {
        Vector2 bodypos = body.getPosition();
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        Vector2 tankpos = new Vector2();
        //filter through bodies and keep only the objects that are of type tank
        Tank tank = null;
        for (Body body : bodies) {
            if (body.getUserData() instanceof Tank) {
                if(((Tank) body.getUserData()).getPlayerNumber() != playerNumber){
                    tankpos = body.getPosition();
                    tank = (Tank) body.getUserData();
                    System.out.println("Tank found");
                }
            }
        }
        //return distance between tankpos and bodypos
        float dis = distance(bodypos.x, bodypos.y, tankpos.x, tankpos.y);
        if(!aoe){
            return projectileDamage;
        }
        if(dis>60 && tank != null){
            int damage = (int) (projectileDamage-(dis/10)*2);
            if(damage>0){
                damage = damage;
            }
            else{
                damage = 0;
            }
            System.out.println(tank);
            tank.damageTaken(damage);
            return (int) (damage);
        }
        else{
            return projectileDamage;
        }
    }
    //function which returns distance between two x,y points
    public float distance(float x1, float y1, float x2, float y2){
        float x = Math.abs(x1 - x2);
        float y = Math.abs(y1 - y2);
        return (float) Math.sqrt(x*x + y*y);
    }
    public void setHit(boolean hit) {
        Hit = hit;
        otherPlayer.setCurrentTurn(true);
        tank.projectile = null;
        otherPlayer.getTank().enableTank();

    }
    public boolean isHit() {
        return Hit;
    }
    public Sprite getProjectileSprite(){
        return projectileSprite;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        projectileSprite.draw(batch);
        System.out.println(body.getPosition());
        if(body.getPosition().x > 600 || body.getPosition().x < -700 || body.getPosition().y > 1000 || body.getPosition().y < -1000){
            body.setActive(false);
            setHit(true);
            this.remove();
        }
    }

    public void syncSprite(){
        if(playerNumber ==1){
            projectileSprite.setPosition(body.getPosition().x + 580, body.getPosition().y+ 310);
        }
        else {
            projectileSprite.setPosition(body.getPosition().x + 580 + offsetX, body.getPosition().y+ 310);
        }
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
    //set player2
    public void setPlayer2(player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public Body getBody() {
        return body;
    }


}
