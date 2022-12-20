package com.mananaajaystudios.tankgame.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameWorld extends Actor {
    private Sprite ground, background,VsImage;

    private World world;
    private TextureAtlas Atlas;
    private TextureRegion region, groundRegion, backgroundRegion;
    private Table table;

    public GameWorld(World world) {
        this.world = world;

        Atlas = new TextureAtlas(Gdx.files.internal("Spritesheets/Spritesheet1.atlas"));
        backgroundRegion = Atlas.findRegion("background");
        background = new Sprite(backgroundRegion);
        background.setSize(1500, 845);
        background.setPosition(Gdx.graphics.getWidth() / 2 - background.getWidth() / 2, Gdx.graphics.getHeight() / 2 - background.getHeight() / 2 +25);

        Texture groundTexture = new Texture(Gdx.files.internal("ground1.png"));
        TextureRegion groundRegion = new TextureRegion(groundTexture);
        ground = new Sprite(groundRegion);
        ground.setSize(1304, 732);
        ground.setPosition(0, -80);
        //Ground
        //body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[]{
                new Vector2(-660, 360),
                new Vector2(-660, 0),
                new Vector2(-640, -37),
                new Vector2(-600, -50),
                new Vector2(-550, -55),
                new Vector2(-500, -70),
                new Vector2(-450, -85),
                new Vector2(-420, -90),
                new Vector2(-400, -85),
                new Vector2(-350, -75),
                new Vector2(-300, -65),
                new Vector2(-250, -50),
                new Vector2(-220, -45),
                new Vector2(-200, -47),
                new Vector2(-150, -60),
                new Vector2(-125, -68),
                new Vector2(-100, -68),
                new Vector2(-50, -54),
                new Vector2(-35, -52),
                new Vector2(-20, -53),
                new Vector2(0, -57),
                new Vector2(20, -65),
                new Vector2(50, -68),
                new Vector2(70, -75),
                new Vector2(85, -77),
                new Vector2(100, -75),
                new Vector2(120, -72),
                new Vector2(150, -63),
                new Vector2(200, -48),
                new Vector2(220, -46),
                new Vector2(230, -45),
                new Vector2(250, -40),
                new Vector2(275, -37),
                new Vector2(300, -40),
                new Vector2(320, -45),
                new Vector2(350, -55),
                new Vector2(400, -68),
                new Vector2(425, -71),
                new Vector2(450, -67),
                new Vector2(500, -52),
                new Vector2(525, -50),
                new Vector2(550, -52),
                new Vector2(600, -69),
                new Vector2(640, -80),
                new Vector2(660, -80),
                new Vector2(660, 360)
        });

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundShape;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;

        Body bodytemp = world.createBody(bodyDef);
        bodytemp.createFixture(fixtureDef);
        bodytemp.setUserData(this);
        groundShape.dispose();

        Texture vsImageTexture = new Texture("vs.png");
        TextureRegion vsImageRegion = new TextureRegion(vsImageTexture);
        VsImage = new Sprite(vsImageRegion);
        VsImage.setSize(60, 50);
        VsImage.setPosition(Gdx.graphics.getWidth() / 2 - VsImage.getWidth() / 2, Gdx.graphics.getHeight() - 85);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        background.draw(batch);
//        ground.draw(batch);
        ground.draw(batch);
        VsImage.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
