package com.mananaajaystudios.tankgame.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import tank class
import com.mananaajaystudios.tankgame.*;

//pause menu button to populate screen and close it
public class MyTankGame extends ApplicationAdapter implements Screen {
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin,textSkin,buttonSkin;
	private Table table, PauseTable;
	private TextButton buttonPause, buttonResume;
	private SpriteBatch batch;
	private Sprite sprite, ground, background;
	private TextureRegion pauseButton, PauseMenu;
	private TextureRegionDrawable pauseButtonDrawable, PauseMenuDrawable;
	private ImageButton pauseButtonImage;
	private player player1, player2;
	private Tank tank1;
	private Tank tank2;
	private TopDog parent;


	public MyTankGame(TopDog temp, player player1, player player2) {
		parent = temp;
		this.player1 = player1;
		this.player2 = player2;

		stage = new Stage(new ScreenViewport());
	}


	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas(Gdx.files.internal("Spritesheets/Spritesheet1.atlas"));
		skin = new Skin(atlas);
		table = new Table();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		pauseButton = atlas.findRegion("Menu");
		final ImageButton pauseIcon = new ImageButton(new TextureRegionDrawable(pauseButton));
		pauseIcon.setPosition(-10, Gdx.graphics.getHeight() - pauseIcon.getHeight());
		pauseIcon.setSize(75, 75);
		final World world = new World();
		stage.addActor(world);
		stage.addActor(pauseIcon);
		tank1 = this.player1.getTank();
		tank2 = this.player2.getTank();
		stage.addActor(tank1);
		stage.addActor(tank2);
		buttonSkin= new Skin(atlas);
		TextButton.TextButtonStyle textButtonStyle1 = new TextButton.TextButtonStyle();
		textButtonStyle1.up = buttonSkin.getDrawable("button_up");
		textButtonStyle1.down = buttonSkin.getDrawable("button_down");
		textButtonStyle1.pressedOffsetX = 1;
		textButtonStyle1.pressedOffsetY = -1;
		BitmapFont black = new BitmapFont(Gdx.files.internal("fonts/black.fnt"), false);
		textButtonStyle1.font = black;

		//create change weapon button
		TextButton buttonChangeWeapon = new TextButton("Change Weapon", textButtonStyle1);
		buttonChangeWeapon.setPosition(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/2 -30, 25);
		buttonChangeWeapon.setSize(240, 50);
		stage.addActor(buttonChangeWeapon);

		//add health bars as actors


		pauseIcon.addListener(new ClickListener(){
			@Override
			public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
				super.clicked(event, x, y);
//				System.out.println("clicked");

				PauseMenu = atlas.findRegion("PausePopUp");
				PauseMenuDrawable = new TextureRegionDrawable(PauseMenu);
				final ImageButton PauseMenuImage = new ImageButton(PauseMenuDrawable);
				PauseMenuImage.setSize(200, 200);
				//set position of pause menu to center of screen
				PauseMenuImage.setPosition(Gdx.graphics.getWidth()/2 - PauseMenuImage.getWidth()/2, Gdx.graphics.getHeight()/2 - PauseMenuImage.getHeight()/2);
				stage.addActor(PauseMenuImage);

				textSkin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
				final TextButton resumeButton = new TextButton("Resume", textSkin);
				resumeButton.setSize(100, 40);
				resumeButton.setPosition(Gdx.graphics.getWidth()/2 - resumeButton.getWidth()/2, Gdx.graphics.getHeight()/2 - resumeButton.getHeight()/2 +50);
				resumeButton.getLabel().setFontScale(0.5f);
				stage.addActor(resumeButton);



				final TextButton SaveButton = new TextButton("Save Game", textSkin);
				SaveButton.setPosition(Gdx.graphics.getWidth()/2 - SaveButton.getWidth()/2 +20, Gdx.graphics.getHeight()/2 - SaveButton.getHeight()/2);
				SaveButton.setSize(100, 40);
				SaveButton.getLabel().setFontScale(0.5f);
				stage.addActor(SaveButton);

				SaveButton.addListener(new ClickListener(){
					@Override
					public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
						super.clicked(event, x, y);
						//save game menu
					}
				});

				final TextButton MainMenuButton = new TextButton("Main Menu", textSkin);
				//set position under resume button
				MainMenuButton.setPosition(Gdx.graphics.getWidth()/2 - MainMenuButton.getWidth()/2 + 20, Gdx.graphics.getHeight()/2 - MainMenuButton.getHeight()/2 - 50);
				MainMenuButton.setSize(100, 40);
				MainMenuButton.getLabel().setFontScale(0.5f);
				stage.addActor(MainMenuButton);

				MainMenuButton.addListener(new ClickListener(){
					@Override
					public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
						super.clicked(event, x, y);
						parent.changeScreen("MAIN");
						//go back to main menu
					}
				});

				resumeButton.addListener(new ClickListener(){
					@Override
					public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
						super.clicked(event, x, y);
						PauseMenuImage.remove();
						resumeButton.remove();
						SaveButton.remove();
						MainMenuButton.remove();
					}
				});

				PauseMenuImage.addListener(new ClickListener(){
					@Override
					public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
						super.clicked(event, x, y);
						PauseMenuImage.remove();
						resumeButton.remove();
						SaveButton.remove();
						MainMenuButton.remove();
					}
				});
			}
		});


	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			// your actions
			System.out.println("a");
			System.out.println(tank1.getX());
			tank1.set
			tank1.setX(tank1.getX()-10);
			//tank1.addAction(Actions.moveTo(tank1.getX()-100, tank1.getY(), 1f));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			// your actions
			tank1.setX(tank1.getX()+10);

			tank2.addAction(Actions.moveTo(tank2.getX()+100, tank2.getY(), 1f));
		}
		stage.act(delta);
		stage.draw();

	}

	@Override
	public void hide() {

	}
}