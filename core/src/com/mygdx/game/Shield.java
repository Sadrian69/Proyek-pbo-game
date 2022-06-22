package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Shield extends Rectangle {
   public Texture texture;

   public Shield() {
      this.width = 50;
      this.height = 100;
      this.x = 180;
      this.y = 420;
      texture = new Texture(Gdx.files.internal("Sprites/Shield.png"));
   }
}
