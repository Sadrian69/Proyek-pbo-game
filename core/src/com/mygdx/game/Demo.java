package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

public class Demo implements Screen {
   final TheGame game;
   private SpriteBatch batch;
   OrthographicCamera camera;
   private float elapsedTime = 0f;
   private Hero hero;
   private ArrayList<Enemy> enemies;
   BitmapFont font = new BitmapFont();

   public Demo(final TheGame game) {
      this.game = game;
      batch = new SpriteBatch();
      camera = new OrthographicCamera();
      Gdx.graphics.setWindowedMode(1080,720);
      camera.setToOrtho(false, 1080, 720);
      font.getData().setScale(2.5f);

      enemies = new ArrayList<>();
      hero = new Hero(enemies);

      enemies.add(new EnemyMelee(hero));
   }

   @Override
   public void render(float delta) {
      ScreenUtils.clear(0, 0.05f, 0.44f, 1);
      batch.setProjectionMatrix(camera.combined);
      camera.update();
      elapsedTime += Gdx.graphics.getDeltaTime();

      batch.begin();

      batch.draw(hero.currentFrame(elapsedTime), hero.x, hero.y, hero.width, hero.height);
      font.setColor(Color.WHITE);
      font.draw(batch, Integer.toString(hero.health), hero.x + 140, 550);

      for(Enemy enemy:enemies) {
         batch.draw(enemy.currentFrame(elapsedTime), enemy.x, enemy.y, 0, 0, enemy.width, enemy.height,-1,1,0);
         float fontx = enemy.x - 160;
         float fonty = 550;
         if(enemy instanceof EnemyRanged){
            fontx = enemy.x - 130;
         }
         else if(enemy instanceof EnemyMage){
            fontx = enemy.x - 220;
         }
         if(enemy.chargeLimit-enemy.curCharge == 1){
            font.setColor(Color.RED);
            font.draw(batch, "!",fontx,fonty);
         }
         else {
            font.setColor(Color.WHITE);
            font.draw(batch, Integer.toString(enemy.chargeLimit - enemy.curCharge), fontx, fonty);
         }
      }

      batch.end();

      if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
         // hero lompat kecil atau gausah gapapa
         hero.Unblock();

         if(!enemies.isEmpty() && enemies.get(0).curPos == 1){
            // sfx tetot
         }
         else{
            for (Enemy enemy : enemies) {
               enemy.move(elapsedTime);
            }
            if (enemies.size() < 3) { // spawner
               Random random = new Random(System.nanoTime());
               int newEnemy = random.nextInt(999);
               int x = 3-enemies.size();

               if (newEnemy < 111*x) {
                  Enemy enemy = new EnemyMelee(hero);
                  enemies.add(enemy);
               } else if (newEnemy < 222*x) {
                  Enemy enemy = new EnemyRanged(hero);
                  enemies.add(enemy);
               } else if (newEnemy < 333*x) {
                  Enemy enemy = new EnemyMage(hero);
                  enemies.add(enemy);
               }
            }
         }
      }
      if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
         hero.Unblock();
         for (Enemy enemy : enemies) {
            enemy.act(elapsedTime);
         }
         hero.Attack(elapsedTime);
      }
      if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
         hero.Block();
         for (Enemy enemy : enemies) {
            enemy.act(elapsedTime);
         }
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
      batch.dispose();
      font.dispose();
   }
}
