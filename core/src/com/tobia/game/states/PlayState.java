package com.tobia.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.tobia.game.FlappyDemo;
import com.tobia.game.sprites.Bird;
import com.tobia.game.sprites.Tube;

public class PlayState extends State{
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private static final int GROUND_Y_OFFSET = -50;
    private Bird bird;
    private Texture backGround;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Array<Tube> tubes;//import of array may be wrong

    protected PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(50,200);
        cam.setToOrtho(false, FlappyDemo.WIDTH/2, FlappyDemo.HEIGHT/2);
        backGround = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2) + ground.getWidth(), GROUND_Y_OFFSET);



        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            bird.jump();

    }

    @Override
    protected void update(float deltaTime) {
        handleInput();
        updateGround();
        bird.update(deltaTime);
        cam.position.x = bird.getPosition().x + 80;

        for (int i = 0; i<tubes.size; i++){
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth/2) > tube.getPosTopTube().x + tube.getBottomTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING)*TUBE_COUNT));
            }

            if(tube.collides(bird.getBounds())) {
                gameStateManager.set(new PlayState(gameStateManager));
            }

        }

        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gameStateManager.set(new PlayState(gameStateManager));

        cam.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(backGround, cam.position.x- (cam.viewportWidth/2), 0);
        spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for(Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        spriteBatch.draw(ground, groundPos1.x, groundPos1.y);
        spriteBatch.draw(ground, groundPos2.x, groundPos2.y);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        backGround.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube: tubes )
            tube.dispose();
        System.out.println("PLay state disposed");
    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth/2)> groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth()*2, 0);
        }
        if(cam.position.x - (cam.viewportWidth/2)> groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth()*2, 0);
        }
    }


}
