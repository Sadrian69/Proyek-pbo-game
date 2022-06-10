package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class EnemyMelee extends Enemy{
   // hanya menyerang 1 kotak di depan
   // menyerang setiap 2 giliran

   public EnemyMelee(Demo demo, int startpos) {
      this.width = 300;
      this.height = 300;
      this.demo = demo;
      chargeLimit = 2;
      curCharge = 0;
      curPos = startpos;
      this.x = 50 + pos * curPos;
      this.y = 300;
      idling = new TextureAtlas(Gdx.files.internal("Sprites/Melee/Idle.atlas"));
      Array<TextureAtlas.AtlasRegion> idlingFrames = idling.findRegions("idling");
      animation = new Animation(FRAME_DURATION, idlingFrames, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
      firstTexture = idlingFrames.first();
   }

   @Override
   public void attack() {

   }


}
