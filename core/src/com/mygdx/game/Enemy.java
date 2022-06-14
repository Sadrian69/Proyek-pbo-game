package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.math.MathUtils.lerp;

public abstract class Enemy extends Rectangle implements EnemyAble{
   float pos = 40;
   public int chargeLimit;
   public int curCharge;
   public int curPos;
   public TextureAtlas idling;
   public static final float FRAME_DURATION = .05f;
   public Animation animation;
   public TextureRegion firstTexture;
   public float originX, originY;

   public TextureRegion currentFrame(float time){
      return (TextureRegion) animation.getKeyFrame(time);
   }
}
