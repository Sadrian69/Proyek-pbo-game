package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface EnemyAble {
   void move();
   void attack();
   void act();
   TextureRegion currentFrame(float time);
}
