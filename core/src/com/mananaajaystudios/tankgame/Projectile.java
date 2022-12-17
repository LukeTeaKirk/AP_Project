package com.mananaajaystudios.tankgame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Projectile extends Actor {
    private BodyDef bodyDef;
    private int playerNumber;
    private boolean CanCauseDamage;
    private FixtureDef fixtureDef;
    private Body body;
    World world;
    private Sprite projectileSprite;
    private int projectileDamage;
    private boolean Hit = false;

    public Projectile(BodyDef bodyDef, FixtureDef fixtureDef, World world, int radius, Texture texture, int projectileDamage, Stage stage, int player) {
        this.bodyDef = bodyDef;
        this.fixtureDef = fixtureDef;
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        fixtureDef.shape = shape;
        this.world = world;
        CanCauseDamage = false;
        this.projectileSprite = new Sprite(texture);
        this.projectileSprite.setSize(100,30);
        playerNumber = player;
        if(player == 2){
            this.projectileSprite.setFlip(true,false);
        }
        this.projectileDamage = projectileDamage;
        stage.addActor(this);
    }

    public int getProjectileDamage() {
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
                }
            }
        }
        //return distance between tankpos and bodypos
        float dis = distance(bodypos.x, bodypos.y, tankpos.x, tankpos.y);
        System.out.println(dis);
        if(dis>60){
            assert tank != null;
            int damage = (int) (projectileDamage-(dis/10)*2);
            if(damage>0){
                damage = damage;
            }
            else{
                damage = 0;
            }
            System.out.println(projectileDamage + " " + damage);
            tank.damageTaken(damage);
            return (int) (damage);
        }
        else{
            System.out.println(projectileDamage);
            tank.damageTaken(projectileDamage);
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
    }

    public void syncSprite(){
        projectileSprite.setPosition(body.getPosition().x + 585, body.getPosition().y+ 340);
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
