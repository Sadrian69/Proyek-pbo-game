package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
   BitmapFont font = new BitmapFont();

   public Demo(final TheGame game) {
      this.game = game;
      batch = new SpriteBatch();
      camera = new OrthographicCamera();
      Gdx.graphics.setWindowedMode(1080,720);
      camera.setToOrtho(false, 1080, 720);
      font.getData().setScale(2.5f);

      enemy1 = new EnemyMelee(7);
      enemy0 = new EnemyMelee(0);
      enemy0.x = 20;


   }

   @Override
   public void render(float delta) {
      ScreenUtils.clear(0, 0, 0.9f, 1);
      batch.setProjectionMatrix(camera.combined);
      //camera.update();
      elapsedTime += Gdx.graphics.getDeltaTime();

      batch.begin();

      batch.draw(enemy1.currentFrame(elapsedTime), enemy1.x, enemy1.y, 0, 0, enemy1.width, enemy1.height,-1,1,0);
      font.draw(batch, Integer.toString(enemy1.chargeLimit-enemy1.curCharge),enemy1.x - 160,enemy1.y + 250);
      batch.draw(enemy0.currentFrame(elapsedTime), enemy0.x, enemy0.y, 0, 0, enemy0.width, enemy0.height,1,1,0);

      batch.end();

      if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
         enemy1.move();
         enemy1.act();
      }
      else if(Gdx.input.isButtonJustPressed(Input.Keys.DOWN)){
         // hero attack
         enemy1.act();
      }
      else if(Gdx.input.isButtonJustPressed(Input.Keys.LEFT)){
         // hero block
         enemy1.act();
      }
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
