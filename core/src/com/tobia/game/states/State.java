package com.tobia.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected GameStateManager gameStateManager;
    protected OrthographicCamera cam;
    protected Vector3 mouse;

    protected State(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 240, 400);
    }

    protected abstract void handleInput();
    protected abstract void update(float deltaTime);

    public abstract void render(SpriteBatch spriteBatch);

    public abstract void dispose();
}
