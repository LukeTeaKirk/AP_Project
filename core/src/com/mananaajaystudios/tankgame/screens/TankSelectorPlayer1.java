package com.mananaajaystudios.tankgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mananaajaystudios.tankgame.*;

import java.util.ArrayList;

public class TankSelectorPlayer1 implements Screen{

    private TopDog parent;
    private Stage stage;
    private Integer Index = 0;
    private Boolean Player1Chosen = false;

    private player player1, player2;
    private ArrayList<VerticalGroup> TankGroups;
    private TextureAtlas atlas;
    private TextureRegion ChooseBackground;
    private TextureRegionDrawable ChooseBackgroundDrawable;
    Skin TextSkin,skin;
    private BitmapFont white, black;
    public TankSelectorPlayer1(TopDog temp){
        parent = temp;

        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        atlas = new TextureAtlas(Gdx.files.internal("Spritesheets/Spritesheet1.atlas"));
        white = new BitmapFont(Gdx.files.internal("fonts/white.fnt"), false);
        black = new BitmapFont(Gdx.files.internal("fonts/black.fnt"), false);
        TextSkin = new Skin(atlas);

        //Load Tank Images
        final TextureRegion TankCoalition = atlas.findRegion("Coalition");
        final TextureRegion TankBuratino = atlas.findRegion("Buratino");
        final TextureRegion TankHelios = atlas.findRegion("Helios");
        Texture CoalitionBadge = new Texture(Gdx.files.internal("CoalitionBadge.png"));
        Texture BuratinoBadge = new Texture(Gdx.files.internal("BurantinoBadge.png"));
        Texture HeliosBadge = new Texture(Gdx.files.internal("HeliosBadge.png"));
        TextureRegionDrawable TankCoalitionDrawable = new TextureRegionDrawable(TankCoalition);
        TextureRegionDrawable TankBuratinoDrawable = new TextureRegionDrawable(TankBuratino);
        TextureRegionDrawable TankHeliosDrawable = new TextureRegionDrawable(TankHelios);
        TextureRegionDrawable CoalitionBadgeDrawable = new TextureRegionDrawable(CoalitionBadge);
        TextureRegionDrawable BuratinoBadgeDrawable = new TextureRegionDrawable(BuratinoBadge);
        TextureRegionDrawable HeliosBadgeDrawable = new TextureRegionDrawable(HeliosBadge);

        final VerticalGroup TankGroupCoalition = new VerticalGroup();
        final VerticalGroup TankGroupBuratino = new VerticalGroup();
        final VerticalGroup TankGroupHelios = new VerticalGroup();
        TankGroupCoalition.addActor(new Image(CoalitionBadgeDrawable));
        TankGroupCoalition.space(10);
        TankGroupCoalition.addActor(new Image(TankCoalitionDrawable));
        TankGroupCoalition.center();

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

        final Table table2 = new Table();

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




        final TextButton Choose = new TextButton("Choose", textButtonStyle);
        Choose.setSize(100, 50);
        ChooseBackground = atlas.findRegion("PopUp");
        ChooseBackgroundDrawable = new TextureRegionDrawable(ChooseBackground);
        Texture ChoooseTankBackground = new Texture(Gdx.files.internal("ChooseTankBackground.png"));
        TextureRegionDrawable ChoooseTankBackgroundDrawable = new TextureRegionDrawable(ChoooseTankBackground);




        Texture rightArrow = new Texture(Gdx.files.internal("rightarrow.png"));
        Texture leftArrow = new Texture(Gdx.files.internal("leftarrow.png"));
        TextureRegionDrawable rightArrowDrawable = new TextureRegionDrawable(new TextureRegion(rightArrow));
        TextureRegionDrawable leftArrowDrawable = new TextureRegionDrawable(new TextureRegion(leftArrow));
        final ImageButton rightArrowButton = new ImageButton(rightArrowDrawable);
        final ImageButton leftArrowButton = new ImageButton(leftArrowDrawable);
//        rightArrowButton.setSize(50,50);
//        leftArrowButton.setSize(50,50);

        final Texture PlayerOneButton = new Texture(Gdx.files.internal("PlayerOneChoose.png"));
        final TextureRegionDrawable PlayerOneButtonDrawable = new TextureRegionDrawable(new TextureRegion(PlayerOneButton));
        final ImageButton PlayerOneChoose = new ImageButton(PlayerOneButtonDrawable);
        PlayerOneChoose.setSize(100,100);

        table2.setBackground(ChooseBackgroundDrawable);
        table2.add(PlayerOneChoose).size(200,50).padTop(10).padLeft(60).padRight(10);
        table2.row();
        table2.add(leftArrowButton).size(50,50).pad(10).align(Align.left);
        table2.add(rightArrowButton).size(50,50).pad(10).align(Align.right);
        table2.row();
        //add Choose button to centre of table2
        table2.add(Choose).size(100,50).pad(10).padLeft(60).padRight(10).align(Align.center);

        table1.setBackground(ChoooseTankBackgroundDrawable);
        table1.add(TankGroupCoalition).size(200,200).pad(10).padLeft(60).padRight(10).align(Align.center);

        rightArrowButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Index<2) {
                    Index++;
                    table1.clear();
                    table1.add(TankGroups.get(Index)).size(200,200).pad(10).padLeft(60).padRight(10).align(Align.center);
                }
            }
        });
        leftArrowButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Index>0) {
                    Index--;
                    table1.clear();
                    table1.add(TankGroups.get(Index)).size(200,200).pad(10).padLeft(60).padRight(10).align(Align.center);
                }
            }
        });
        Choose.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!Player1Chosen) {
                    if (Index == 0) {
                        player1 = new player(new TankCoalition(1));
                    }
                    if (Index == 1) {
                        player1 = new player(new TankBurantino(1));

                    }
                    if (Index == 2) {
                        player1 = new player(new TankHelios(1));
                    }
                    //clear table2 for next player
                    table2.clear();
                    //add new player to table2
                    Texture PlayerTwoButton = new Texture(Gdx.files.internal("PlayerTwoChoose.png"));
                    TextureRegionDrawable PlayerTwoButtonDrawable = new TextureRegionDrawable(new TextureRegion(PlayerTwoButton));
                    ImageButton PlayerTwoChoose = new ImageButton(PlayerTwoButtonDrawable);
                    PlayerTwoChoose.setSize(100, 100);
                    table2.add(PlayerTwoChoose).size(200, 50).padTop(10).padLeft(60).padRight(10);
                    table2.row();
                    table2.add(leftArrowButton).size(50, 50).pad(10).align(Align.left);
                    table2.add(rightArrowButton).size(50, 50).pad(10).align(Align.right);
                    table2.row();
                    //add Choose button to centre of table2
                    table2.add(Choose).size(100, 50).pad(10).padLeft(60).padRight(10).align(Align.center);
                    //add new tank to table1
                    table1.clear();
                    table1.add(TankGroupCoalition).size(200, 200).pad(10).padLeft(60).padRight(10).align(Align.center);
                    //reset index
                    Index = 0;
                    Player1Chosen = true;
                }
                else if(Player1Chosen){
                    if (Index == 0) {
                        player2 = new player(new TankCoalition(2));
                    }
                    if (Index == 1) {
                        player2 = new player(new TankBurantino(2));

                    }
                    if (Index == 2) {
                        player2 = new player(new TankHelios(2));
                    }
                    //set screen to game
                    parent.changeScreen("INGAME",player1,player2);

            }

            }

        });

    }


    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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