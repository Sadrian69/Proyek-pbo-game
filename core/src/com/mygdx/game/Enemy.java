package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.math.MathUtils.lerp;

public abstract class Enemy extends Rectangle implements EnemyAble{
   float pos = 45;
   public int chargeLimit;
   public int curCharge;
   public int curPos;
   private final float moveTime = 0.5f;
   public Demo demo;
   public TextureAtlas idling;
   public static final float FRAME_DURATION = .05f;
   public Animation animation;
   public TextureRegion firstTexture;
   public float originX, originY;

   public void move(){
      if(curPos > 1) {
         curPos -= 1;
         float tempx = this.x;
         float alpha = 0;
         float currentTime = 0;
         while (alpha < 1) {
            currentTime += Gdx.graphics.getDeltaTime();
            alpha = moveTime / currentTime;
            this.x = lerp(tempx, pos * curPos, alpha);
         }
      }
   }


   public TextureRegion currentFrame(float time){
      return (TextureRegion) animation.getKeyFrame(time);
   }
}
