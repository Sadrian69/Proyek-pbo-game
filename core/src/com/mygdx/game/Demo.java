package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class Demo implements Screen {
   final TheGame game;
   private SpriteBatch batch;
   OrthographicCamera camera;
   private float elapsedTime = 0f;
   private Enemy enemy1, enemy0;

   public Demo(final TheGame game) {
      this.game = game;
      batch = new SpriteBatch();
      camera = new OrthographicCamera();
      Gdx.graphics.setWindowedMode(1080,720);
      camera.setToOrtho(false, 1080, 720);
      enemy1 = new EnemyMelee(7);
      enemy0 = new EnemyMelee(0);
      enemy0.x = 20;

   }

   @Override
   public void render(float delta) {
      ScreenUtils.clear(0, 0, 0.2f, 1);
      batch.setProjectionMatrix(camera.combined);
      //camera.update();
      elapsedTime += Gdx.graphics.getDeltaTime();

      batch.begin();

      batch.draw(enemy1.currentFrame(elapsedTime), enemy1.x, enemy1.y, enemy1.x, enemy1.y, enemy1.width, enemy1.height,-1,1,0);
      batch.draw(enemy0.currentFrame(elapsedTime), enemy0.x, enemy0.y, enemy0.x, enemy0.y, enemy0.width, enemy0.height,1,1,0);

      batch.end();

      if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) enemy1.move();

   }

   @Override
   public void resize(int width, int height) {
   }

   @Override
   public void show() {
      // start the playback of the background music
      // when the screen is shown
   }

   @Override
   public void hide() {
   }

   @Override
   public void pause() {
   }

   @Override
   public void resume() {
   }

   @Override
   public void dispose() {
   }
}
