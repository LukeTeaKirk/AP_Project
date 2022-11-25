package com.mananaajaystudios.tankgame.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mananaajaystudios.tankgame.Game;
import com.mananaajaystudios.tankgame.TopDog;
import com.mananaajaystudios.tankgame.World;
import com.mananaajaystudios.tankgame.player;

//pause menu button to populate screen and close it
public class MyTankGame extends ApplicationAdapter implements Screen {
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin,textSkin,buttonSkin;

	private TextButton.TextButtonStyle textButtonStyle1;
	private Table table, PauseTable, ConfirmTable;
	private TextButton buttonPause, buttonResume,buttonSave,buttonExit;
	private SpriteBatch batch;
	private Sprite sprite, ground, background;
	private TextureRegion pauseButton, PauseMenu;
	private TextureRegionDrawable pauseButtonDrawable, PauseMenuDrawable;
	ImageButton pauseButtonImage;
	private player player1, player2;

	private TopDog parent;


	public MyTankGame(TopDog temp, Game game) {
		parent = temp;
		this.player1 = game.getPlayer1();
		this.player2 = game.getPlayer2();

		/// create stage and set it as input processor
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
		//set position of pause button to top left corner
		pauseIcon.setPosition(-10, Gdx.graphics.getHeight() - pauseIcon.getHeight());
		pauseIcon.setSize(75, 75);
		final World world = new World();
		stage.addActor(world);
		stage.addActor(pauseIcon);

		stage.addActor(this.player1.getTank());
		stage.addActor(this.player2.getTank());

		buttonSkin= new Skin(atlas);
		textButtonStyle1 = new TextButton.TextButtonStyle();
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
				PauseTable = new Table();
				PauseTable.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()/2 - 125, 300, 300);
				PauseTable.debug();

				PauseMenu = atlas.findRegion("PausePopUp");
				PauseMenuDrawable = new TextureRegionDrawable(PauseMenu);
				final ImageButton PauseMenuImage = new ImageButton(PauseMenuDrawable);
				PauseTable.setBackground(PauseMenuDrawable);


				buttonResume = new TextButton("Resume", textButtonStyle1);
				buttonResume.setSize(240, 50);
				PauseTable.add(buttonResume).size(buttonResume.getWidth(), buttonResume.getHeight()).padBottom(20);
				PauseTable.row();


				buttonSave = new TextButton("Save Game", textButtonStyle1);
				buttonSave.setSize(240, 50);
				PauseTable.add(buttonSave).size(buttonSave.getWidth(), buttonSave.getHeight()).padBottom(20);
				PauseTable.row();

				buttonExit = new TextButton("Main Menu", textButtonStyle1);
				buttonExit.setSize(240, 50);
				PauseTable.add(buttonExit).size(buttonExit.getWidth(), buttonExit.getHeight());
				PauseTable.row();
				stage.addActor(PauseTable);

				buttonResume.addListener(new ClickListener(){
					@Override
					public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
						super.clicked(event, x, y);
						PauseTable.remove();
					}
				});

				buttonSave.addListener(new ClickListener(){
					@Override
					public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
						super.clicked(event, x, y);
						//save game actions
					}
				});

				buttonExit.addListener(new ClickListener(){
					@Override
					public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
						super.clicked(event, x, y);
						PauseTable.remove();
						ConfirmTable = new Table();
						ConfirmTable.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()/2 - 50, 350, 200);
						ConfirmTable.debug();

						ConfirmTable.setBackground(PauseMenuDrawable);

						Label label = new Label("Do you Want to Save the Game?", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
						ConfirmTable.add(label).size(200, 50).padBottom(20).expand(200,0);
						ConfirmTable.row();

						TextButton buttonYes = new TextButton("Yes", textButtonStyle1);
						buttonYes.setSize(100, 50);
						ConfirmTable.add(buttonYes).size(buttonYes.getWidth(), buttonYes.getHeight()).padBottom(20).padLeft(40).padRight(40);

						TextButton buttonNo = new TextButton("No", textButtonStyle1);
						buttonNo.setSize(100, 50);
						ConfirmTable.add(buttonNo).size(buttonNo.getWidth(), buttonNo.getHeight()).padBottom(20).padRight(40);

						stage.addActor(ConfirmTable);

						buttonYes.addListener(new ClickListener(){
							@Override
							public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
								super.clicked(event, x, y);
								ConfirmTable.remove();
								//save game state
								//go to main menu
								parent.changeScreen("MAIN");
							}
						});

						buttonNo.addListener(new ClickListener(){
							@Override
							public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
								super.clicked(event, x, y);
								ConfirmTable.remove();
								parent.changeScreen("MAIN");
							}
						});

					}
				});





			}
		});

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}

	@Override
	public void hide() {

	}
}