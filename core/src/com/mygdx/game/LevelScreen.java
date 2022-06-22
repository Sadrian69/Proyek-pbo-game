package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelScreen implements Screen {
    final TheGame game;
    OrthographicCamera camera;
    TextureAtlas textureAtlas;
    Skin skin;
    Stage stage;
    TextButton level1, level2, level3;

    public LevelScreen(final TheGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 720);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
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
        game.mainTheme.dispose();
    }

    private void initButtons(){
        level1 = new TextButton("Level 1 (10 kills)", skin, "default");
        level1.setPosition(440, 450);
        level1.setSize(200, 60);
        level1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Level(game, 10));
                dispose();
            }
        });

        level2 = new TextButton("Level 2 (20 kills)", skin, "default");
        level2.setPosition(440, 350);
        level2.setSize(200, 60);
        level2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Level(game, 20));
                dispose();
            }
        });

        level3 = new TextButton("Level 3 (50 kills)", skin, "default");
        level3.setPosition(440, 250);
        level3.setSize(200, 60);
        level3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Level(game, 50));
                dispose();
            }
        });

        stage.addActor(level1);
        stage.addActor(level2);
        stage.addActor(level3);
    }
}
