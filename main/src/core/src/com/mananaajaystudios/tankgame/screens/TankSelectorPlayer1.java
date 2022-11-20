package com.mananaajaystudios.tankgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mananaajaystudios.tankgame.TopDog;

public class TankSelectorPlayer1 implements Screen{

    private TopDog parent;
    private Stage stage;

    public TankSelectorPlayer1(TopDog temp){
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
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        // temporary until we have asset manager in
        Label abramsLabel = new Label("Abrams",skin);
        Label arjunLabel = new Label("Arjun", skin);
        Label TLabel = new Label("T90", skin);

        Texture abramsTexture = new Texture("assets/abrams.png");
        Drawable abramsDrawable = new TextureRegionDrawable(new TextureRegion(abramsTexture));
        Texture arjunTexture = new Texture("assets/arjun.jpg");
        Drawable arjunDrawable = new TextureRegionDrawable(new TextureRegion(abramsTexture));
        Texture tTexture = new Texture("assets/t90.jpg");
        Drawable tDrawable = new TextureRegionDrawable(new TextureRegion(abramsTexture));

        ImageButton button1 = new ImageButton(abramsDrawable);
        button1.setTransform(true);
        button1.setSize(300,200);
        button1.getImage().setSize(300,200);
        ImageButton button2 = new ImageButton(arjunDrawable);
        button2.setTransform(true);
        button2.setSize(300,200);
        button2.getImage().setSize(300,200);
        ImageButton button3 = new ImageButton(tDrawable);
        button3.setSize(300,200);
        button3.getImage().setSize(300,200);
        //create buttons

        //add buttons to table
        table.add(button1).size(300,200);
        table.add(button2).size(300,200);
        table.add(button3).size(300,200);
        table.row();
        table.add(abramsLabel);
        table.add(arjunLabel);
        table.add(TLabel);
        table.row();

        // create button listeners
        button1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen("");
            }
        });

        button3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen("");
            }
        });

    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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