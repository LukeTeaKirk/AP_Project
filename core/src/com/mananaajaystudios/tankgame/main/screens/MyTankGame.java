package com.mananaajaystudios.tankgame.main.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mananaajaystudios.tankgame.main.*;
import com.mananaajaystudios.tankgame.main.Game;

import java.io.IOException;

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
	private Table table, PauseTable, ConfirmTable, SwitchWeaponTable,EndGameTable,AngleForceTable;
	private TextButton buttonPause, buttonResume,buttonSave,buttonExit;
	private SpriteBatch batch;
	private Sprite sprite, ground, background;
	private TextureRegion pauseButton, PauseMenu, SwitchWeaponMenu,EndGameMenu;
	private TextureRegionDrawable pauseButtonDrawable, PauseMenuDrawable, EndGameMenuDrawable;
	ImageButton pauseButtonImage;
	private player player1, player2;
	private Game game;
	private TopDog parent;

	private BitmapFont black;


	public MyTankGame(TopDog temp, Game game) {
		parent = temp;
		if(game.loadedGame == true){
			//call readobject method in player tank objects in game class
			game.getPlayer1().getTank().readObject();
			game.getPlayer2().getTank().readObject();
		}
		this.player1 = game.getPlayer1();
		player1.setCurrentTurn(true);
		this.player2 = game.getPlayer2();
		player2.setCurrentTurn(false);
		this.game = game;
		System.out.println("loaded");
		/// create stage and set it as input processor
		stage = new Stage(new ScreenViewport());
	}


	@Override
	public void show() {
		world = new World(new Vector2(0, -9.81f), true);
		this.world.setContactListener(new CollisionDetector(world));
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


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
		final GameWorld GameWorld = new GameWorld(world);
		stage.addActor(GameWorld);
		stage.addActor(pauseIcon);
		tank1 = this.player1.getTank();
		tank2 = this.player2.getTank();
		tank1.setBody(world);
		tank2.setBody(world);
		stage.addActor(tank1);
		stage.addActor(tank2);

		buttonSkin= new Skin(atlas);
		textButtonStyle1 = new TextButton.TextButtonStyle();
		textButtonStyle1.up = buttonSkin.getDrawable("button_up");
		textButtonStyle1.down = buttonSkin.getDrawable("button_down");
		textButtonStyle1.pressedOffsetX = 1;
		textButtonStyle1.pressedOffsetY = -1;
		black = new BitmapFont(Gdx.files.internal("fonts/black.fnt"), false);
		textButtonStyle1.font = black;

		//create change weapon button
		TextButton buttonChangeWeapon = new TextButton("Change Weapon", textButtonStyle1);
		buttonChangeWeapon.setPosition(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/2 -200, 25);
		buttonChangeWeapon.setSize(240, 50);
		stage.addActor(buttonChangeWeapon);

		TextButton buttonFireWeapon = new TextButton("Fire!", textButtonStyle1);
		buttonFireWeapon.setPosition(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/3 - 150, 25);
		buttonFireWeapon.setSize(120, 100);
		stage.addActor(buttonFireWeapon);

		buttonFireWeapon.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(AngleForceTable != null){
					AngleForceTable.remove();
				}
				if (player1.isCurrentTurn()) {
					player1.setCurrentTurn(false);
					player1.getTank().FireWeapon(world, player1.getTank().getTankSprite(), stage);
					player1.getTank().disableTank();
					player2.getTank().disableTank();
					if(player1.getTank().getProjectile() != null) {
						player1.getTank().getProjectile().setPlayer2(player2);
					}
				} else {
					player2.setCurrentTurn(false);
					player2.getTank().FireWeapon(world, player2.getTank().getTankSprite(), stage);
					player2.getTank().disableTank();
					player1.getTank().disableTank();
					if(player2.getTank().getProjectile() != null) {
						player2.getTank().getProjectile().setPlayer2(player1);
					}
				}
			}
		});

		buttonChangeWeapon.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				SwitchWeaponTable = new Table();
				SwitchWeaponTable.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()/2 - 125, 250, 300);

				SwitchWeaponMenu = atlas.findRegion("PausePopUp");
				TextureRegionDrawable SwitchWeaponMenuDrawable = new TextureRegionDrawable(SwitchWeaponMenu);
				SwitchWeaponTable.setBackground(SwitchWeaponMenuDrawable);

				if (player1.isCurrentTurn()) {
					for (int i = 0; i < player1.getTank().getWeapons().size(); i++) {
						final TextButton button = new TextButton(player1.getTank().getWeapons().get(i).getName(), textButtonStyle1);
						button.addListener(new ClickListener() {
							@Override
							public void clicked(InputEvent event, float x, float y) {
								super.clicked(event, x, y);
								player1.getTank().switchWeapon(button.getText().toString());
								SwitchWeaponTable.remove();
							}
						});
						SwitchWeaponTable.add(button).width(200).height(50).padBottom(10);
						SwitchWeaponTable.row();
					}
				} else {
					for (int i = 0; i < player2.getTank().getWeapons().size(); i++) {
						final TextButton button = new TextButton(player2.getTank().getWeapons().get(i).getName(), textButtonStyle1);
						button.addListener(new ClickListener() {
							@Override
							public void clicked(InputEvent event, float x, float y) {
								super.clicked(event, x, y);
								player2.getTank().switchWeapon(button.getText().toString());
								SwitchWeaponTable.remove();
							}
						});
						SwitchWeaponTable.add(button).width(200).height(50).padBottom(10);
						SwitchWeaponTable.row();
					}
				}
				stage.addActor(SwitchWeaponTable);
			}
		});

		pauseIcon.addListener(new ClickListener(){
			@Override
			public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				PauseTable = new Table();
				PauseTable.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()/2 - 125, 300, 300);

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
						player1.setTank(tank1);
						player2.setTank(tank2);
						game.setPlayers(player1, player2);
						try {
							GamesDatabase.saveGame(game);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
						parent.changeScreen("MAIN");

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

						ConfirmTable.setBackground(PauseMenuDrawable);


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
		//debugRenderer.render(world, camera.combined);
		world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
		//loop through all the bodies and search for projectiles
		Array<Body> bodies = new Array<Body>();
		for(world.getBodies(bodies); bodies.size > 0; bodies.pop()){
			Body body = bodies.peek();
			if(body.getUserData() != null && body.getUserData() instanceof Projectile){
				((Projectile) body.getUserData()).syncSprite();
				if(((Projectile)body.getUserData()).isHit()){
					((Projectile) body.getUserData()).remove();
					world.destroyBody(body);
				}
			}
			if(body.getUserData() != null && body.getUserData() instanceof Tank){
				if(((Tank)body.getUserData()).isDead()) {
					endGame();
				}
				if((Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D))){
					if(((Tank)body.getUserData()).getCanMove()==1) {
							((Tank) body.getUserData()).reduceFuel(1);
//						}
					}
				}

			}
		}
		tank1.updateBodyPosition();
		tank2.updateBodyPosition();
	}

	public void endGame(){
		tank1.disableTank();
		tank2.disableTank();
		EndGameTable = new Table();
		EndGameTable.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()/2 - 100, 250, 250);

		EndGameMenu = atlas.findRegion("PausePopUp");
		EndGameMenuDrawable = new TextureRegionDrawable(EndGameMenu);
		final ImageButton PauseMenuImage = new ImageButton(EndGameMenuDrawable);
		EndGameTable.setBackground(EndGameMenuDrawable);


		//add restart game TextButton
		TextButton buttonRestart = new TextButton("Restart", textButtonStyle1);
		buttonRestart.setSize(150, 75);
		EndGameTable.add(buttonRestart).size(buttonRestart.getWidth(), buttonRestart.getHeight());
		EndGameTable.row();

		//add exit game TextButton
		TextButton buttonExit = new TextButton("Exit", textButtonStyle1);
		buttonExit.setSize(150, 75);
		EndGameTable.add(buttonExit).size(buttonExit.getWidth(), buttonExit.getHeight());

		stage.addActor(EndGameTable);

		buttonRestart.addListener(new ClickListener(){
			@Override
			public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				parent.changeScreen("TANKP1");
				//restart game
			}
		});

		buttonExit.addListener(new ClickListener(){
			@Override
			public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				EndGameTable.remove();
				parent.changeScreen("MAIN");
			}
		});

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
		//get the coordinates of the touch and print them
		Vector3 touchPos = new Vector3();
		touchPos.set(screenX, screenY, 0);
		camera.unproject(touchPos);
		System.out.println("x: " + (touchPos.x+640) + " y: " + (touchPos.y+360));
		if(AngleForceTable != null){
			AngleForceTable.remove();
		}
		//create an image button to display angle and force
		AngleForceTable = new Table();
		Texture Background = new Texture(Gdx.files.internal("yellowRegion.png"));
		Group Angle = new Group();
		Group Force = new Group();
		Image AngleImage = new Image(Background);
		Image ForceImage = new Image(Background);
		AngleImage.setSize(500, 280);
		ForceImage.setSize(500, 280);
		if(player1.isCurrentTurn()){
			System.out.println("Tank1 x: " + (tank1.getTankSprite().getX()+50) + " y: " + (tank1.getTankSprite().getY()+50));

			float forceX = (touchPos.x +640) - (tank1.getTankSprite().getX()+50);

			float forceY = (touchPos.y +360) - (tank1.getTankSprite().getY()+50);
			System.out.println("forceX: " + forceX + " forceY: " + forceY);

			float angle = (float) Math.toDegrees(Math.atan2(forceY, forceX));

			if((angle <= 90 && angle >=0) || (angle >= -90 && angle <= 0)){
				if(tank1.getTankSprite().isFlipX()){
					tank1.getTankSprite().flip(true, false);
				}
			}
			else if((angle <= 180 && angle >= 90) || (angle >= -180 && angle <= -90)){
				if(!tank1.getTankSprite().isFlipX()){
					tank1.getTankSprite().flip(true, false);
				}
			}
			//calculate the equivalent force
			float force = (float) Math.sqrt(Math.pow(forceX, 2) + Math.pow(forceY, 2));
			System.out.println("angle: " + angle + " force: " + force);

			AngleForceTable.setBounds(50, 90, 200, 100);
			//AngleForceTable.debug();
			AngleImage.setPosition(-150, -125);
			ForceImage.setPosition(-150, -130);
			Angle.addActor(AngleImage);
			Force.addActor(ForceImage);
			//import label style
			Label.LabelStyle labelStyle = new Label.LabelStyle();
			labelStyle.font = black;
			int angleInt = (int) angle;
			int forceInt = (int) force;
			Label angleLabel = new Label("Angle: " + angleInt,labelStyle);
			Label forceLabel = new Label("Force: " + forceInt,labelStyle);
			angleLabel.setPosition(25, 0);
			forceLabel.setPosition(25, -5);
			Angle.addActor(angleLabel);
			Force.addActor(forceLabel);
			AngleForceTable.add(Angle).size(100, 50);
			AngleForceTable.row();
			AngleForceTable.add(Force).size(100, 50);
			AngleForceTable.row();

			stage.addActor(AngleForceTable);

			tank1.setAngleAndPower(forceX, forceY);
		}
		else{
			System.out.println("Tank2 x: " + (tank2.getTankSprite().getX()+50) + " y: " + (tank2.getTankSprite().getY()+50));

			float forceX = (touchPos.x +640) - (tank2.getTankSprite().getX()+50);

			float forceY = (touchPos.y +360) - (tank2.getTankSprite().getY()+50);
			System.out.println("forceX: " + forceX + " forceY: " + forceY);


			float angle = (float) Math.toDegrees(Math.atan2(forceY, forceX));
			float force = (float) Math.sqrt(Math.pow(forceX, 2) + Math.pow(forceY, 2));
			System.out.println("angle: " + angle + " force: " + force);
			if((angle <= 90 && angle >=0) || (angle >= -90 && angle <= 0)){
				if(tank2.getTankSprite().isFlipX()){
					tank2.getTankSprite().flip(true, false);
				}
			}
			else if((angle <= 180 && angle >= 90) || (angle >= -180 && angle <= -90)){
				if(!tank2.getTankSprite().isFlipX()){
					tank2.getTankSprite().flip(true, false);
				}
			}
			AngleForceTable.setBounds(1100, 90, 150, 100);
			//AngleForceTable.debug();
			AngleImage.setPosition(-280, -125);
			ForceImage.setPosition(-280, -130);
			Angle.addActor(AngleImage);
			Force.addActor(ForceImage);
			//import label style
			Label.LabelStyle labelStyle = new Label.LabelStyle();
			labelStyle.font = black;
			int angleInt = (int) angle;
			int forceInt = (int) force;
			Label angleLabel = new Label("Angle: " + angleInt,labelStyle);
			Label forceLabel = new Label("Force: " + forceInt,labelStyle);
			angleLabel.setPosition(-95, 0);
			forceLabel.setPosition(-95, -5);
			Angle.addActor(angleLabel);
			Force.addActor(forceLabel);
			AngleForceTable.add(Angle).size(100, 50);
			AngleForceTable.row();
			AngleForceTable.add(Force).size(100, 50);
			AngleForceTable.row();

			stage.addActor(AngleForceTable);

			tank2.setAngleAndPower(forceX, forceY);
		}

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
