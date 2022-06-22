package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final TheGame game;
    OrthographicCamera camera;

    public MainMenuScreen(final TheGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 720);
    }

    @Override
    public void show() {
        game.mainTheme.setVolume(0.1f);
        game.mainTheme.play();
        game.mainTheme.setLooping(true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.05f, 0.44f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);


        game.batch.begin();
        game.title.draw(game.batch, "Welcome to RPG! ", 420, 600);
        game.font.draw(game.batch, "Press Enter to Start Game", 450, 500);
        game.batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new LevelScreen(game));
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
    }
}
