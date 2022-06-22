package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TheGame extends Game { // ini tak ganti TheGame classnya karena karena Game nabrak sama import Game
	SpriteBatch batch;
	public BitmapFont title;
	public BitmapFont font;
	public BitmapFont winFont;
	public BitmapFont loseFont;
	public Music mainTheme;
	public Music battleTheme;
	public Music loseTheme;
	public Music winTheme;
	Texture stoneFloor;
	@Override
	public void create () {
		batch = new SpriteBatch();
		title = new BitmapFont();
		title.setColor(Color.YELLOW);
		title.getData().setScale(6);
		font = new BitmapFont();
		winFont = new BitmapFont();
		winFont.setColor(Color.YELLOW);
		winFont.getData().setScale(5);
		loseFont = new BitmapFont();
		loseFont.setColor(Color.RED);
		loseFont.getData().setScale(5);
		mainTheme = Gdx.audio.newMusic(Gdx.files.internal("Sounds/mainTheme.wav"));
		battleTheme = Gdx.audio.newMusic(Gdx.files.internal("Sounds/battleTheme.mp3"));
		loseTheme = Gdx.audio.newMusic(Gdx.files.internal("Sounds/loseTheme.wav"));
		winTheme = Gdx.audio.newMusic(Gdx.files.internal("Sounds/winTheme.wav"));
		stoneFloor = new Texture(Gdx.files.internal("Background/stoneFloor.jpg"));
		setScreen(new MainMenuScreen(this));
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