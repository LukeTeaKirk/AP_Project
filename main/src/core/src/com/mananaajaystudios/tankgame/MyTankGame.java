package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyTankGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite sprite, ground, background;
	Texture img;
	TextureAtlas atlas;
	TextureRegion region, groundRegion, backgroundRegion;
	Stage stage;
	
	public void create () {
		ScreenViewport viewport = new ScreenViewport();
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);

		World world = new World();
		stage.addActor(world);
		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");

	}

	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
//		batch.begin();
//		background.draw(batch);
//		ground.draw(batch);
//		sprite.draw(batch);
//		batch.end();
	}}