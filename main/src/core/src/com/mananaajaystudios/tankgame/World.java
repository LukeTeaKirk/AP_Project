package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class World extends Actor {
    Sprite ground, background;
    TextureAtlas Atlas;
    TextureRegion region, groundRegion, backgroundRegion;

    public World() {
        Atlas = new TextureAtlas(Gdx.files.internal("Spritesheets/tankstar.atlas"));
//        region = Atlas.findRegion("Tank1");
        groundRegion = Atlas.findRegion("ground");
        backgroundRegion = Atlas.findRegion("background");
        ground = new Sprite(groundRegion);
        ground.setSize(1000, 100);
        ground.setPosition(0, 0);
        background = new Sprite(backgroundRegion);
        background.setSize(1000, 1000);
        background.setPosition(0, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        background.draw(batch);
        ground.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
