package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Hero {
    private final int width;
    private final int height;
    private final Object x;
    private final int y;
    public int curPos;
    public float pos = 45f;
    public TextureAtlas idleStance;
    public Animation animation;
    public static final float animationFrame = .05f;
    public TextureRegion firstTexture;

    TextureRegion currentFrame(float time) {
        return null;
    }

    public Hero (Demo demo, int startpos){
        this.width = 300;
        this.height = 300;
        this.x = 50 + pos * curPos;
        this.y = 300;
        idleStance = new TextureAtlas(Gdx.files.internal("Sprites/Hero/Idle.png"));
        Array<TextureAtlas.AtlasRegion> idlingFrames = idleStance.findRegions("idling");
        animation = new Animation(animationFrame, idlingFrames, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        firstTexture = idlingFrames.first();
    }
}
