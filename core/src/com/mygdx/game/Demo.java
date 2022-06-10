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
   public Demo(final TheGame game) {
      this.game = game;
      batch = new SpriteBatch();
      camera = new OrthographicCamera();
      Gdx.graphics.setWindowedMode(1080,720);
      camera.setToOrtho(false, 1080, 720);


   }

   @Override
   public void render(float delta) {
      ScreenUtils.clear(0, 0, 0.2f, 1);
      batch.setProjectionMatrix(camera.combined);
      //camera.update();
      elapsedTime += Gdx.graphics.getDeltaTime();

      batch.begin();

      Enemy enemy1 = new EnemyMelee(this,7);
      batch.draw(enemy1.currentFrame(elapsedTime), enemy1.x, enemy1.y, enemy1.x, enemy1.y, enemy1.width, enemy1.height,-1,1,0);
      Enemy enemy2 = new EnemyMelee(this,6);
      batch.draw(enemy2.currentFrame(elapsedTime), enemy2.x, enemy2.y, enemy2.x, enemy2.y, enemy2.width, enemy2.height,-1,1,0);
      Enemy enemy3 = new EnemyMelee(this,5);
      batch.draw(enemy3.currentFrame(elapsedTime), enemy3.x, enemy3.y, enemy3.x, enemy3.y, enemy3.width, enemy3.height,-1,1,0);
      Enemy enemy4 = new EnemyMelee(this,4);
      batch.draw(enemy4.currentFrame(elapsedTime), enemy4.x, enemy4.y, enemy4.x, enemy4.y, enemy4.width, enemy4.height,-1,1,0);
      Enemy enemy5 = new EnemyMelee(this,3);
      batch.draw(enemy5.currentFrame(elapsedTime), enemy5.x, enemy5.y, enemy5.x, enemy5.y, enemy5.width, enemy5.height,-1,1,0);
      Enemy enemy6 = new EnemyMelee(this,2);
      batch.draw(enemy6.currentFrame(elapsedTime), enemy6.x, enemy6.y, enemy6.x, enemy6.y, enemy6.width, enemy6.height,-1,1,0);
      Enemy enemy7 = new EnemyMelee(this,1);
      enemy7.x = -20;
      batch.draw(enemy7.currentFrame(elapsedTime), enemy7.x, enemy7.y, enemy7.x, enemy7.y, enemy7.width, enemy7.height,1,1,0);

      batch.end();

      if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) enemy1.move();

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
