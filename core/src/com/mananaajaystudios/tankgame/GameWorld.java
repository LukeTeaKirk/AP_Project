package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameWorld extends Actor {
    private Sprite ground, background,VsImage;

    private TextureAtlas Atlas;
    private TextureRegion region, groundRegion, backgroundRegion;
    private Table table;

    public GameWorld() {
        Atlas = new TextureAtlas(Gdx.files.internal("Spritesheets/Spritesheet1.atlas"));

//        groundRegion = Atlas.findRegion("ground");
        backgroundRegion = Atlas.findRegion("background");
//        ground = new Sprite(groundRegion);
//        ground.setSize(1500, 140);
//        ground.setPosition(0, -5);
        Texture groundTexture = new Texture(Gdx.files.internal("Background1.png"));
        TextureRegion groundRegion = new TextureRegion(groundTexture);
        ground = new Sprite(groundRegion);
        ground.setSize(1304, 732);
        ground.setPosition(0, -80);
        background = new Sprite(backgroundRegion);
        background.setSize(1500, 845);
        background.setPosition(Gdx.graphics.getWidth() / 2 - background.getWidth() / 2, Gdx.graphics.getHeight() / 2 - background.getHeight() / 2 +25);

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