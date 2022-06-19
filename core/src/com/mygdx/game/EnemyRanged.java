package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class EnemyRanged extends Enemy{
   // menyerang hero dari mana saja
   // menyerang setiap 5 giliran
   
   private int distanceOffset = 270;

   public EnemyRanged(Hero hero) {
      this.width = 250;
      this.height = 250;
      chargeLimit = 5;
      curCharge = 0;
      curPos = 7;
      lastAttack = -100f;
      this.x = distanceOffset + pos * curPos;
      this.y = 340;
      this.hero = hero;
      
      deathSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/monsterDeath.wav"));
      
      idling = new TextureAtlas(Gdx.files.internal("Sprites/Ranged/Idle.atlas"));
      Array<TextureAtlas.AtlasRegion> idlingFrames = idling.findRegions("idling");
      idleAnimation = new Animation(FRAME_DURATION, idlingFrames, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);

      attacking = new TextureAtlas(Gdx.files.internal("Sprites/Ranged/Attack.atlas"));
      Array<TextureAtlas.AtlasRegion> attackingFrames = attacking.findRegions("attacking");
      attackAnimation = new Animation(FRAME_DURATION, attackingFrames, Animation.PlayMode.NORMAL);

      firstTexture = idlingFrames.first();

   }

   @Override
   public void move(float time) {
      curPos--;
      this.x = distanceOffset + curPos * pos;
      act(time);

   }

   @Override
   public void attack(float time) {
      if(!hero.isBlocking()) hero.Hurt();
      lastAttack = time;

   }
}
