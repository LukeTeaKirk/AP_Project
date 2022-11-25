package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class World extends Actor {
    private Sprite ground, background,healthBar;
    private TextureAtlas Atlas;
    private TextureRegion region, groundRegion, backgroundRegion;
    private Table table;

    public World() {
        Atlas = new TextureAtlas(Gdx.files.internal("Spritesheets/Spritesheet1.atlas"));

        groundRegion = Atlas.findRegion("ground");
        backgroundRegion = Atlas.findRegion("background");
        ground = new Sprite(groundRegion);
        ground.setSize(1200, 140);
        ground.setPosition(0, -5);
        background = new Sprite(backgroundRegion);
        background.setSize(1200, 675);
        background.setPosition(Gdx.graphics.getWidth() / 2 - background.getWidth() / 2, Gdx.graphics.getHeight() / 2 - background.getHeight() / 2 +25);

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
