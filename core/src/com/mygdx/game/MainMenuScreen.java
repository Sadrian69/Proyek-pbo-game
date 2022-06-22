package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final TheGame game;
    OrthographicCamera camera;
    TextureAtlas textureAtlas;
    Skin skin;
    Stage stage;
    TextButton startGameButton, exitButton;

    public MainMenuScreen(final TheGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 720);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        game.mainTheme.setVolume(0.1f);
        game.mainTheme.play();
        game.mainTheme.setLooping(true);

        textureAtlas = new TextureAtlas(Gdx.files.internal("Buttons/uiskin.atlas"));
        skin = new Skin(Gdx.files.internal("Buttons/uiskin.json"), textureAtlas);

        initButtons();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.05f, 0.44f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(game.stoneFloor, 0, 0);
        game.title.draw(game.batch, "Welcome to RPG! ", 200, 630);
        game.batch.end();

        update(delta);

        stage.draw();
    }

    public void update(float delta){
        stage.act(delta);
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

    private void initButtons(){
        startGameButton = new TextButton("Start Game", skin, "default");
        startGameButton.setPosition(440, 400);
        startGameButton.setSize(200, 60);
        startGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LevelScreen(game));
            }
        });

        exitButton = new TextButton("Exit", skin, "default");
        exitButton.setPosition(440, 200);
        exitButton.setSize(200, 60);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });

        stage.addActor(startGameButton);
        stage.addActor(exitButton);
    }
}
