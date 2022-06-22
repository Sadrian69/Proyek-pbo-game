package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelScreen implements Screen {
    final TheGame game;
    OrthographicCamera camera;

    public LevelScreen(final TheGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 720);
    }

    @Override
    public void show() {
        game.mainTheme.play();
        game.mainTheme.setLooping(true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.05f, 0.44f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "[1] Level 1 (10 kill)", 480, 500);
        game.font.draw(game.batch, "[2] Level 2 (20 kill)", 480, 400);
        game.font.draw(game.batch, "[3] Level 3 (50 kill)", 480, 300);
        game.batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            game.setScreen(new Level(game, 10));
            dispose();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
            game.setScreen(new Level(game, 20));
            dispose();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
            game.setScreen(new Level(game, 50));
            dispose();
        }
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
        game.mainTheme.dispose();
    }
}
