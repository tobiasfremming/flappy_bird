package com.tobia.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tobia.game.states.GameStateManager;
import com.tobia.game.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "FLAPPY BIRD";

	private GameStateManager gameStateManager;

	private SpriteBatch batch;
	//Texture img;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		Gdx.gl.glClearColor(1,0,0,1);
		gameStateManager.push(new MenuState(gameStateManager));



		//img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		//ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
