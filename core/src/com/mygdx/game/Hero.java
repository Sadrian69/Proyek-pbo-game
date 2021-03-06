package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;


public class Hero extends Rectangle {
    private boolean blocking;
    public int health;
    public TextureAtlas idling;
    public TextureAtlas attacking;
    public TextureAtlas running;
    public static final float FRAME_DURATION = 0.05f;
    public Animation idleAnimation;
    public Animation attackAnimation;
    public Animation runningAnimation;
    public TextureRegion firstTexture;
    Sound takeDamageSound;
    Sound deathSound;
    Sound heroAttack;
    public float lastAttack;
    public float lastMove;
    ArrayList<Enemy> enemies;

    public Hero(ArrayList<Enemy> enemies) {
        health = 3;
        blocking = false;
        lastAttack = -100;
        lastMove = -100;
        this.x = 0;
        this.y = 315;
        this.width = 300;
        this.height = 300;
        this.enemies = enemies;

        takeDamageSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/takeDamage.wav"));
        deathSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Death.wav"));
        heroAttack = Gdx.audio.newSound(Gdx.files.internal("Sounds/heroAttack.wav"));

        idling = new TextureAtlas(Gdx.files.internal("Sprites/Hero/idle.atlas"));
        Array<TextureAtlas.AtlasRegion> idlingFrames = idling.findRegions("idling");
        idleAnimation = new Animation(FRAME_DURATION, idlingFrames, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);

        attacking = new TextureAtlas(Gdx.files.internal("Sprites/Hero/attacking.atlas"));
        Array<TextureAtlas.AtlasRegion> attackingFrames = attacking.findRegions("attacking");
        attackAnimation = new Animation(FRAME_DURATION, attackingFrames, Animation.PlayMode.NORMAL);

        running = new TextureAtlas(Gdx.files.internal("Sprites/Hero/running.atlas"));
        Array<TextureAtlas.AtlasRegion> runningFrames = running.findRegions("running");
        runningAnimation = new Animation(FRAME_DURATION, runningFrames, Animation.PlayMode.NORMAL);
    }


    public TextureRegion currentFrame(float time){
        if(time - lastAttack < 20*Gdx.graphics.getDeltaTime()) return (TextureRegion)  attackAnimation.getKeyFrame(time-lastAttack);
        else if (time - lastMove < 10*Gdx.graphics.getDeltaTime()) return (TextureRegion)  runningAnimation.getKeyFrame(time-lastMove);
        else return (TextureRegion) idleAnimation.getKeyFrame(time);
    }


    public void Move (float time){
        lastMove = time;
    }

    public void Attack(float time){
        heroAttack.play();
        lastAttack = time;
        if(enemies.get(0).curPos == 1){ // meninggal
            enemies.get(0).die();
            enemies.remove(0);
            Level.win++;
        }
    }
    public void Block(){
        blocking = true;
    }
    public void Unblock(){
        blocking = false;
    }

    public void Hurt(){
        health = health - 1;
        if(health == 0){
            die();
        }
        else takeDamageSound.play();
    }

    public void die(){
        //ganti screen??
        deathSound.play();
    }

    public boolean isBlocking() {
        return blocking;
    }
}