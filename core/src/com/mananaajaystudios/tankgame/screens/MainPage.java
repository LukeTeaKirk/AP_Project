package com.mananaajaystudios.tankgame.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mananaajaystudios.tankgame.TopDog;

import java.util.ArrayList;

public class MainPage implements Screen{
    private TopDog parent;
    private Stage stage;

    private ArrayList<VerticalGroup> TankGroups;
    private TextureAtlas atlas;
    private TextureRegion ChooseBackground;
    private TextureRegionDrawable ChooseBackgroundDrawable;
    private Skin TextSkin,skin;
    private BitmapFont white, black;

    private Sound BgMusic;
    long id;

    public MainPage(TopDog temp){
        parent = temp;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    public long getId() {
        return id;
    }
    public Sound getBgMusic() {
        return BgMusic;
    }
    @Override
    public void show() {
//        BgMusic = Gdx.audio.newSound(Gdx.files.internal("Assets/TankStarsBGM.mp3"));
//        id = BgMusic.play();
//        BgMusic.setLooping(id,true);

        atlas = new TextureAtlas(Gdx.files.internal("Spritesheets/Spritesheet1.atlas"));
        white = new BitmapFont(Gdx.files.internal("fonts/white.fnt"), false);
        black = new BitmapFont(Gdx.files.internal("fonts/black.fnt"), false);
        TextSkin = new Skin(atlas);

        TextureRegion TankCoalition = atlas.findRegion("Coalition");
        TextureRegion TankBuratino = atlas.findRegion("Buratino");
        TextureRegion TankHelios = atlas.findRegion("Helios");
        Texture BuratinoBadge = new Texture(Gdx.files.internal("BurantinoBadge.png"));
        Texture HeliosBadge = new Texture(Gdx.files.internal("HeliosBadge.png"));
        TextureRegionDrawable TankCoalitionDrawable = new TextureRegionDrawable(TankCoalition);
        TextureRegionDrawable TankBuratinoDrawable = new TextureRegionDrawable(TankBuratino);
        TextureRegionDrawable TankHeliosDrawable = new TextureRegionDrawable(TankHelios);
        TextureRegionDrawable BuratinoBadgeDrawable = new TextureRegionDrawable(BuratinoBadge);
        TextureRegionDrawable HeliosBadgeDrawable = new TextureRegionDrawable(HeliosBadge);

        final VerticalGroup TankGroupCoalition = new VerticalGroup();
        final VerticalGroup TankGroupBuratino = new VerticalGroup();
        final VerticalGroup TankGroupHelios = new VerticalGroup();
        TankGroupCoalition.addActor(new Image(TankCoalitionDrawable));
        TankGroupCoalition.center().padTop(130);

        TankGroupBuratino.addActor(new Image(BuratinoBadgeDrawable));
        TankGroupBuratino.space(10);
        TankGroupBuratino.addActor(new Image(TankBuratinoDrawable));
        TankGroupBuratino.center();

        TankGroupHelios.addActor(new Image(HeliosBadgeDrawable));
        TankGroupHelios.space(10);
        TankGroupHelios.addActor(new Image(TankHeliosDrawable));
        TankGroupHelios.center();

        TankGroups = new ArrayList<VerticalGroup>();
        TankGroups.add(TankGroupCoalition);
        TankGroups.add(TankGroupBuratino);
        TankGroups.add(TankGroupHelios);

        Gdx.input.setInputProcessor(stage);
        stage.clear();
        final Table table1 = new Table();
        table1.setSize(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight());
        table1.setPosition(0,0);
        Table table2 = new Table();
        table2.setSize(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight());
        table2.setPosition(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/3,0);
        stage.addActor(table1);
        stage.addActor(table2);
        skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = TextSkin.getDrawable("button_up");
        textButtonStyle.down = TextSkin.getDrawable("button_down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;




        TextButton newGame = new TextButton("NEW GAME", textButtonStyle);
        newGame.setSize(300, 100);
        TextButton exit = new TextButton("LOAD", textButtonStyle);
        exit.setSize(300, 100);
        TextButton load = new TextButton("EXIT", textButtonStyle);
        load.setSize(300, 100);

        ChooseBackground = atlas.findRegion("PopUp");
        ChooseBackgroundDrawable = new TextureRegionDrawable(ChooseBackground);
        Texture ChoooseTankBackground = new Texture(Gdx.files.internal("ChooseTankBackground.png"));
        TextureRegionDrawable ChoooseTankBackgroundDrawable = new TextureRegionDrawable(ChoooseTankBackground);

        table2.setBackground(ChooseBackgroundDrawable);
        table2.add(newGame).size(300,100).pad(10).padLeft(20).padRight(10).align(Align.center);
        table2.row();
        table2.add(load).size(300,100).pad(10).padLeft(20).padRight(10).align(Align.center);
        table2.row();
        table2.add(exit).size(300,100).pad(10).padLeft(20).padRight(10).align(Align.center);
        table2.row();
        table1.setBackground(ChoooseTankBackgroundDrawable);
        table1.add(TankGroupCoalition).size(200,200).pad(10).padLeft(20).padRight(10).align(Align.center);

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("assets/buttonClick.mp3"));
//                sound.play(1F);
                Gdx.app.exit();
            }
        });
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("assets/buttonClick.mp3"));
//                sound.play(1F);
                parent.changeScreen("GAMEMODE");
            }
        });
        load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("assets/buttonClick.mp3"));
//                sound.play(1F);
                parent.changeScreen("LOAD");
            }
        });

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Texture logo = new Texture("assets/tankstars.png");
        Sprite logoSprite = new Sprite(logo);
        stage.draw();
        stage.getBatch().begin();
        stage.getBatch().enableBlending();
        stage.getBatch().draw(logoSprite, 200, 475, 350, 200);
        stage.getBatch().end();
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
        BgMusic.dispose();
        stage.dispose();
    }

}