package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class GameScreen implements Screen {
   final TheGame game;
   private SpriteBatch batch;
   OrthographicCamera camera;
   private float elapsedTime = 0f;
   public GameScreen(final TheGame game) {
      this.game = game;
      batch = new SpriteBatch();
      camera = new OrthographicCamera();
      camera.setToOrtho(false, 1080, 720);


   }

   @Override
   public void render(float delta) {
      ScreenUtils.clear(0, 0, 0.2f, 1);
      camera.update();
      elapsedTime += Gdx.graphics.getDeltaTime();

      batch.begin();

      Enemy enemy1 = new EnemyMelee(this);
      enemy1.x = 200;
      enemy1.y = 200;
      batch.draw(enemy1.currentFrame(elapsedTime), enemy1.x, enemy1.y, enemy1.x, enemy1.y, enemy1.width, enemy1.height,-1,1,0);

      batch.end();


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
