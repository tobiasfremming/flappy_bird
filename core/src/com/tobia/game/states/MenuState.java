package com.tobia.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.tobia.game.FlappyDemo;

public class MenuState extends State{
    private Texture backGround;
    private Texture playBtn;
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        backGround = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    protected void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        spriteBatch.begin();
        spriteBatch.draw(backGround, 0,0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        spriteBatch.draw(playBtn, (FlappyDemo.WIDTH/2)-playBtn.getWidth()/2, (FlappyDemo.HEIGHT/2));
        spriteBatch.end();


    }

    @Override
    public void dispose() {
        backGround.dispose();
        playBtn.dispose();
        System.out.println("Menu state disåposed");
    }
}
