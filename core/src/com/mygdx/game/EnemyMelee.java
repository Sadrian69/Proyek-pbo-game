package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class EnemyMelee extends Enemy{

   public EnemyMelee(GameScreen gameScreen) {
      this.width = 200;
      this.height = 200;
      this.gameScreen = gameScreen;
      chargeLimit = 2;
      curCharge = 0;
      idling = new TextureAtlas(Gdx.files.internal("Sprites/Melee/Idle.atlas"));
      Array<TextureAtlas.AtlasRegion> idlingFrames = idling.findRegions("idling");
      animation = new Animation(FRAME_DURATION, idlingFrames, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
      firstTexture = idlingFrames.first();
   }

   @Override
   public void attack() {

   }


}
