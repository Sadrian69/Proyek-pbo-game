package com.mygdx.game;

public class EnemyMage extends Enemy{
   // menyerang hero dari mana saja
   // menyerang setiap 16 giliran, udah worst case
   // serangan tidak bisa diblok
   private int distanceOffset = 300;
   
   public EnemyMage(Hero hero){
      this.width = 300;
      this.height = 300;
      chargeLimit = 16;
      curCharge = 0;
      curPos = 7;
      lastAttack = -100f;
      this.x = distanceOffset + pos * curPos;
      this.y = 300;
      this.hero = hero;
      
      deathSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/monsterDeath.wav"));
      
      idling = new TextureAtlas(Gdx.files.internal("Sprites/Wizard/Idle.atlas"));
      Array<TextureAtlas.AtlasRegion> idlingFrames = idling.findRegions("idling");
      idleAnimation = new Animation(FRAME_DURATION, idlingFrames, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);

      attacking = new TextureAtlas(Gdx.files.internal("Sprites/Wizard/Attack.atlas"));
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
      lastAttack = time;

   }
}
