package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Enemy extends Rectangle implements EnemyAble{
   public static float pos = 120;
   public int chargeLimit;
   public int curCharge;
   public int curPos;
   public TextureAtlas idling;
   public static final float FRAME_DURATION = .05f;
   public Animation animation;
   public TextureRegion firstTexture;

   public TextureRegion currentFrame(float time){
      return (TextureRegion) animation.getKeyFrame(time);
   }

   @Override
   public void act() {
      curCharge++;
      if(curCharge == chargeLimit) {
         attack();
         curCharge = 0;
      }
   }
}
