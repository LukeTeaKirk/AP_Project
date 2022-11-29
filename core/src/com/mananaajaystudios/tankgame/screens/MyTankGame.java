package com.mananaajaystudios.tankgame.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mananaajaystudios.tankgame.Game;
import com.mananaajaystudios.tankgame.*;

//pause menu button to populate screen and close it
public class MyTankGame extends ApplicationAdapter implements Screen, InputProcessor {
	private Stage stage;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private final float TIMESTEP = 1 / 60f;
	private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
	private TextureAtlas atlas;
	private Skin skin,textSkin,buttonSkin;
	private Tank tank1;
	private Tank tank2;
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
		world = new World(new Vector2(0, -9.81f), true);
		debugRenderer = new Box2DDebugRenderer();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		//Ground
		//body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(0, 0);

		ChainShape groundShape = new ChainShape();
		groundShape.createChain(new Vector2[]{
				new Vector2(-640, -37),
				new Vector2(-600, -50),
				new Vector2(-550, -55),
				new Vector2(-500, -70),
				new Vector2(-450, -85),
				new Vector2(-420, -90),
				new Vector2(-400, -85),
				new Vector2(-350, -75),
				new Vector2(-300, -65),
				new Vector2(-250, -50),
				new Vector2(-220, -45),
				new Vector2(-200, -47),
				new Vector2(-150, -60),
				new Vector2(-125, -68),
				new Vector2(-100, -68),
				new Vector2(-50, -54),
				new Vector2(-35, -52),
				new Vector2(-20, -53),
				new Vector2(0, -57),
				new Vector2(20, -65),
				new Vector2(50, -68),
				new Vector2(70, -75),
				new Vector2(85, -77),
				new Vector2(100, -75),
				new Vector2(120, -72),
				new Vector2(150, -63),
				new Vector2(200, -48),
				new Vector2(220, -46),
				new Vector2(230, -45),
				new Vector2(250, -40),
				new Vector2(275, -37),
				new Vector2(300, -40),
				new Vector2(320, -45),
				new Vector2(350, -55),
				new Vector2(400, -68),
				new Vector2(425, -71),
				new Vector2(450, -67),
				new Vector2(500, -52),
				new Vector2(525, -50),
				new Vector2(550, -52),
				new Vector2(600, -69),
				new Vector2(640, -80),
		});

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = groundShape;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0;

		world.createBody(bodyDef).createFixture(fixtureDef);
		groundShape.dispose();


		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor( stage );
		multiplexer.addProcessor( this ); // Your screen
		Gdx.input.setInputProcessor( multiplexer );
		atlas = new TextureAtlas(Gdx.files.internal("Spritesheets/Spritesheet1.atlas"));
		skin = new Skin(atlas);
		table = new Table();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		pauseButton = atlas.findRegion("Menu");
		final ImageButton pauseIcon = new ImageButton(new TextureRegionDrawable(pauseButton));
		//set position of pause button to top left corner
		pauseIcon.setPosition(-10, Gdx.graphics.getHeight() - pauseIcon.getHeight());
		pauseIcon.setSize(75, 75);
		final GameWorld GameWorld = new GameWorld();
		stage.addActor(GameWorld);
		stage.addActor(pauseIcon);
		tank1 = this.player1.getTank();
		tank2 = this.player2.getTank();
		stage.addActor(tank1);
		stage.addActor(tank2);

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
//				PauseTable.debug();

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
						ConfirmTable.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()/2 - 50, 300, 200);
//						ConfirmTable.debug();

						ConfirmTable.setBackground(PauseMenuDrawable);

//						Label label = new Label("Do you Want to Save the Game?", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
//						ConfirmTable.add(label).size(200, 50).padBottom(20).expand(200,0);
//						ConfirmTable.row();
						Texture SaveGame = new Texture(Gdx.files.internal("SaveGame.png"));
						TextureRegionDrawable SaveGameDrawable = new TextureRegionDrawable(new TextureRegion(SaveGame));
						final ImageButton SaveGameImage = new ImageButton(SaveGameDrawable);
						ConfirmTable.add(SaveGameImage).size(300, 75).center();
						ConfirmTable.row();

						TextButton buttonYes = new TextButton("Yes", textButtonStyle1);
						buttonYes.setSize(100, 50);
						ConfirmTable.add(buttonYes).size(buttonYes.getWidth(), buttonYes.getHeight());
						ConfirmTable.row();

						TextButton buttonNo = new TextButton("No", textButtonStyle1);
						buttonNo.setSize(100, 50);
						ConfirmTable.add(buttonNo).size(buttonNo.getWidth(), buttonNo.getHeight());

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
		debugRenderer.render(world, camera.combined);
		world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
	}
	@Override
	public void dispose() {
		stage.dispose();
		world.dispose();
		debugRenderer.dispose();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

}