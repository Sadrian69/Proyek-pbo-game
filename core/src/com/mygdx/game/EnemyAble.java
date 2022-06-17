package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface EnemyAble {
   void move(float time);
   void attack(float time);
   void act(float time);
   void die();
   TextureRegion currentFrame(float time);
}
