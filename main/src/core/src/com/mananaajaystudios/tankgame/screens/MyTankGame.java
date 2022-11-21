package com.mananaajaystudios.tankgame.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mananaajaystudios.tankgame.Tank;
import com.mananaajaystudios.tankgame.TopDog;
import com.mananaajaystudios.tankgame.World;
import org.w3c.dom.Text;

import java.awt.*;

//pause menu button to populate screen and close it
public class MyTankGame implements Screen {
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin,textSkin;
	private Table table, PauseTable;
	private TextButton buttonPause, buttonResume;
	private SpriteBatch batch;
	private Sprite sprite, ground, background;
	private TopDog parent;

//	private Texture img;


	private TextureRegion pauseButton, PauseMenu;
	private TextureRegionDrawable pauseButtonDrawable, PauseMenuDrawable;
	ImageButton pauseButtonImage;
	public MyTankGame(TopDog temp){
		parent = temp;

		/// create stage and set it as input processor
		stage = new Stage(new ScreenViewport());
	}
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas(Gdx.files.internal("Spritesheets/Spritesheet1.atlas"));
		skin = new Skin(atlas);
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		pauseButton = atlas.findRegion("Menu");
		final ImageButton pauseIcon = new ImageButton(new TextureRegionDrawable(pauseButton));
		//set position of pause button to top left corner
		pauseIcon.setPosition(0, Gdx.graphics.getHeight() - pauseIcon.getHeight());
		pauseIcon.setSize(50, 50);

		final World world = new World();
		stage.addActor(world);

		final Tank tank1 = new Tank(1);
		stage.addActor(tank1);
		final Tank tank2 = new Tank(2);
		stage.addActor(tank2);
		stage.addActor(pauseIcon);

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
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}