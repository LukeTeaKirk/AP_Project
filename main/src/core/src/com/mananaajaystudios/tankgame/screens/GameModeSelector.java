package com.mananaajaystudios.tankgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mananaajaystudios.tankgame.TopDog;

public class GameModeSelector implements Screen{
    private TextureAtlas atlas;
    private TopDog parent;
    private Stage stage;

    public GameModeSelector(TopDog temp){
        parent = temp;

        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        Table table = new Table();
        table.center().right();
        table.setFillParent(true);
        stage.addActor(table);

        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        //create buttons
        TextButton newGame = new TextButton("1V1", skin);
        TextButton pve = new TextButton("P V COMP", skin);

        //add buttons to table
        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(pve).fillX().uniformX();

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen("TANKP1");
            }
        });

        pve.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen("");
            }
        });

    }
    public static TextureRegion backgroundTexture;
    public static TextureRegion tankTexture;
    public static Sprite tankSprite;
    public static Sprite backgroundSprite;
    public static TextureRegion groundTexture;
    public static Sprite groundSprite;
    private SpriteBatch spriteBatch;
    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        atlas = new TextureAtlas(Gdx.files.internal("Spritesheets/Spritesheet1.atlas"));
        backgroundTexture = atlas.findRegion("background");
        backgroundSprite =new Sprite(backgroundTexture);
        groundTexture = atlas.findRegion("ground");
        groundSprite =new Sprite(groundTexture);

        tankTexture = atlas.findRegion("Abrams");
        tankSprite =new Sprite(tankTexture);

        TextureRegion PauseMenu = atlas.findRegion("PausePopUp");
        Sprite purpleSprite =new Sprite(PauseMenu);
        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.getBatch().begin();
        stage.getBatch().enableBlending();
        stage.getBatch().draw(backgroundSprite, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().draw(groundSprite, 0, 0, Gdx.graphics.getWidth(), 200);
        stage.getBatch().draw(tankSprite, 100, 100, 220*2, 142*2);
        stage.getBatch().draw(purpleSprite, 700, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when teh screen size is changed
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // dispose of assets when not needed anymore
        stage.dispose();
    }

}