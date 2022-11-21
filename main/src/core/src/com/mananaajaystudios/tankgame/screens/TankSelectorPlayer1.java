package com.mananaajaystudios.tankgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mananaajaystudios.tankgame.TopDog;

public class TankSelectorPlayer1 implements Screen{

    private TopDog parent;
    private Stage stage;
    public static Texture backgroundTexture;
    public static Texture tankTexture;
    public static Sprite tankSprite;
    public static Sprite backgroundSprite;
    private Label label;
    private String texty;
    int tank = 0;
    public TankSelectorPlayer1(TopDog temp){
        parent = temp;

        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        label = new Label(texty, new Label.LabelStyle(new BitmapFont(), Color.BROWN));
        label.setPosition(140,200);
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        Table table = new Table();
        table.center().right();
        table.setFillParent(true);
        stage.addActor(table);
        stage.addActor(label);
        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        //create buttons
        TextButton newGame = new TextButton("<--", skin);
        TextButton pve = new TextButton("-->", skin);

        //add buttons to table
        table.add(newGame);
        table.row().pad(10, 0, 10, 0);
        table.add(pve);
        table.row().pad(10, 0, 10, 0);

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tank += 1;
                tankTexture = getTankTexture(tank);
                tankSprite =new Sprite(tankTexture);
            }
        });

        pve.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tank -= 1;
                tankTexture = getTankTexture(tank);
                tankSprite =new Sprite(tankTexture);
            }
        });

    }
    private Texture getTankTexture(int tankid){
        if(tankid > 3){
            tankid = 1;
        }
        if(tankid < 1){
            tankid = 3;
        }
        if(tankid == 1){
            return new Texture("assets/t90.jpg");
        }
        if(tankid == 2){
            return new Texture("assets/m5a1.png");
        }
        if(tankid == 3){
            return new Texture("assets/arjun.jpg");
        }
        return null;
    }
    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        backgroundTexture = new Texture("assets/mainBG.png");
        backgroundSprite =new Sprite(backgroundTexture);
        if(tankTexture == null){
            tankTexture = new Texture("assets/m5a1.png");
            tankSprite =new Sprite(tankTexture);
            label.setText("M5A1");
        }

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.getBatch().begin();
        stage.getBatch().enableBlending();
        stage.getBatch().draw(backgroundSprite, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().draw(tankSprite, 100, 100, 220*2, 142*2);
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