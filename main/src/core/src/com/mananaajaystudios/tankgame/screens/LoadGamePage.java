package com.mananaajaystudios.tankgame.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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

public class LoadGamePage implements Screen{
    private TextureAtlas atlas;
    private TopDog parent;
    private Stage stage;

    public LoadGamePage(TopDog temp){
        parent = temp;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        Table table = new Table();
        table.center().right();
        table.setFillParent(true);
        stage.addActor(table);
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        TextButton newGame = new TextButton("RETURN", skin);
        newGame.setScaleX(0.5f);
        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("assets/buttonClick.mp3"));
                sound.play(1F);
                parent.changeScreen("MAIN");
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
        stage.dispose();
    }

}