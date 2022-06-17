package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class EnemyMelee extends Enemy{
   // hanya menyerang 1 kotak di depan
   // menyerang setiap 2 giliran
   private int distanceOffset = 300;

   public EnemyMelee(Hero hero) {
      this.width = 300;
      this.height = 300;
      chargeLimit = 2;
      curCharge = 0;
      curPos = 7;
      lastAttack = -100f;
      this.x = distanceOffset + pos * curPos;
      this.y = 300;
      this.hero = hero;

      deathSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/monsterDeath.wav"));

      idling = new TextureAtlas(Gdx.files.internal("Sprites/Melee/Idle.atlas"));
      Array<TextureAtlas.AtlasRegion> idlingFrames = idling.findRegions("idling");
      idleAnimation = new Animation(FRAME_DURATION, idlingFrames, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);

      attacking = new TextureAtlas(Gdx.files.internal("Sprites/Melee/Attack.atlas"));
      Array<TextureAtlas.AtlasRegion> attackingFrames = attacking.findRegions("attacking");
      attackAnimation = new Animation(FRAME_DURATION, attackingFrames, Animation.PlayMode.NORMAL);

      firstTexture = idlingFrames.first();
   }

   @Override
   public void move(float time){
      curPos--;
      this.x = distanceOffset + curPos * pos;
      act(time);
   }

   @Override
   public void attack(float time) {
      if(curPos == 1 && !hero.isBlocking()) hero.Hurt();
      lastAttack = time;
   }


}
