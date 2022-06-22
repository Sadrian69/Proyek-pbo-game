package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Enemy extends Rectangle implements EnemyAble{
   public static float pos = 120;
   public int chargeLimit;
   public int curCharge;
   public int curPos;
   public float lastAttack;
   public TextureAtlas idling;
   public TextureAtlas attacking;
   public static final float FRAME_DURATION = 0.05f;
   public Animation idleAnimation;
   public Animation attackAnimation;
   public TextureRegion firstTexture;
   public Sound deathSound;
   public Sound blockedSound;
   public Hero hero;

   public TextureRegion currentFrame(float time){
      if(time - lastAttack < 20*Gdx.graphics.getDeltaTime()) return (TextureRegion)  attackAnimation.getKeyFrame(time-lastAttack);
      else return (TextureRegion) idleAnimation.getKeyFrame(time);
   }

   @Override
   public void act(float time) {
      curCharge++;
      if(curCharge == chargeLimit) {
         attack(time);
         curCharge = 0;
      }
   }

   @Override
   public void die() {
      deathSound.play();
   }
}
