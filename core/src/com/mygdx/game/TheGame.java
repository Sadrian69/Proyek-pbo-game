package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TheGame extends Game { // ini tak ganti TheGame classnya karena karena Game nabrak sama import Game
	SpriteBatch batch;
	public BitmapFont font;
	public BitmapFont winFont;
	public BitmapFont loseFont;
	public Music mainTheme;


	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		winFont = new BitmapFont();
		winFont.setColor(Color.YELLOW);
		winFont.getData().setScale(2);
		loseFont = new BitmapFont();
		loseFont.setColor(Color.RED);
		loseFont.getData().setScale(2);
		mainTheme = Gdx.audio.newMusic(Gdx.files.internal("mainTheme.wav"));
		this.setScreen(new Demo(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}