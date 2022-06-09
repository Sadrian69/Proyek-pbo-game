package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;

class MainMenuScreen implements Screen {
	TheGame game;
	OrthographicCamera camera;

	public MainMenuScreen(TheGame game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
	}

	@Override
	public void show() {
		game.mainTheme.play();
		game.mainTheme.setLooping(true);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);


		game.batch.begin();
		game.font.draw(game.batch, "Welcome to RPG! ", 100, 150);
		game.font.draw(game.batch, "Tap anywhere to begin", 100, 100);
		game.batch.end();

		if(Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		game.mainTheme.dispose();
	}
}

public class TheGame extends Game {
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
		this.setScreen(new MainMenuScreen(this));
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

class GameScreen implements Screen {
	final TheGame game;

	Texture dropImage;
	Texture bucketImage;
	Sound takeDamage;
	Sound monsterTakeDamage;
	Sound death;
	Sound monsterDeath;
	Sound winTheme;
	Sound loseTheme;
	Music battleTheme;
	OrthographicCamera camera;
	Rectangle bucket;
	Array<Rectangle> raindrops;
	long lastDropTime;
	int dropsGathered;
	int heroHp = 100;
	int monsterHp = 100;

	public GameScreen(final TheGame game) {
		this.game = game;

		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture(Gdx.files.internal("drop.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));

		// load the drop sound effect and the rain background "music"
		takeDamage = Gdx.audio.newSound(Gdx.files.internal("takeDamage.wav"));
		monsterTakeDamage = Gdx.audio.newSound(Gdx.files.internal("monsterTakeDamage.wav"));
		death = Gdx.audio.newSound(Gdx.files.internal("death.wav"));
		monsterDeath = Gdx.audio.newSound(Gdx.files.internal("monsterDeath.wav"));
		winTheme = Gdx.audio.newSound(Gdx.files.internal("winTheme.wav"));
		loseTheme = Gdx.audio.newSound(Gdx.files.internal("loseTheme.wav"));
		battleTheme = Gdx.audio.newMusic(Gdx.files.internal("battleTheme.mp3"));
		battleTheme.setLooping(true);

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// create a Rectangle to logically represent the bucket
		bucket = new Rectangle();
		bucket.x = 400 - 32; // center the bucket horizontally
		bucket.y = 20; // bottom left corner of the bucket is 20 pixels above
		// the bottom screen edge
		bucket.width = 64;
		bucket.height = 64;

		// create the raindrops array and spawn the first raindrop
		raindrops = new Array<>();
		spawnRaindrop();

	}

	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800 - 64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}

	@Override
	public void render(float delta) {
		if(monsterHp == 0 || heroHp == 0){
			battleTheme.stop();
			game.batch.begin();
			if(monsterHp == 0) {
				game.winFont.draw(game.batch, "You Win", 325, 250);
			} else {
				game.loseFont.draw(game.batch, "You Lose", 325, 250);
			}
			game.font.draw(game.batch, "Press Space to Retry", 325, 225);
			game.font.draw(game.batch, "Press Enter to go back to Main Menu", 275, 200);
			game.batch.end();

			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				game.setScreen(new GameScreen(game));
				dispose();
			}

			if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
				game.setScreen(new MainMenuScreen(game));
				dispose();
			}
		} else {
			// clear the screen with a dark blue color. The
			// arguments to clear are the red, green
			// blue and alpha component in the range [0,1]
			// of the color to be used to clear the screen.
			ScreenUtils.clear(0, 0, 0.2f, 1);

			// tell the camera to update its matrices.
			camera.update();

			// tell the SpriteBatch to render in the
			// coordinate system specified by the camera.
			game.batch.setProjectionMatrix(camera.combined);

			// begin a new batch and draw the bucket and
			// all drops
			game.batch.begin();
			game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, 480);
			game.font.draw(game.batch, "Hero's HP : " + heroHp, 0, 400);
			game.font.draw(game.batch, "Monster's HP: " + monsterHp, 650, 400);
			game.batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height);
			for (Rectangle raindrop : raindrops) {
				game.batch.draw(dropImage, raindrop.x, raindrop.y);
			}
			game.batch.end();

			// process user input
			if (Gdx.input.isTouched()) {
				Vector3 touchPos = new Vector3();
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPos);
				bucket.x = (int) (touchPos.x - 64 / 2);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
				bucket.x -= 200 * Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
				bucket.x += 200 * Gdx.graphics.getDeltaTime();

			// make sure the bucket stays within the screen bounds
			if (bucket.x < 0)
				bucket.x = 0;
			if (bucket.x > 800 - 64)
				bucket.x = 800 - 64;

			// check if we need to create a new raindrop
			if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
				spawnRaindrop();

			// move the raindrops, remove any that are beneath the bottom edge of
			// the screen or that hit the bucket. In the later case we increase the
			// value our drops counter and add a sound effect.
			Iterator<Rectangle> iter = raindrops.iterator();
			while (iter.hasNext()) {
				Rectangle raindrop = iter.next();
				raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
				if (raindrop.y + 64 < 0) {
					iter.remove();
					takeDamage.play();
					if(heroHp == 10){
						death.play();
						loseTheme.play();
					}
					heroHp -= 10;
				}
				if (raindrop.overlaps(bucket)) {
					dropsGathered++;
					iter.remove();
					if(monsterHp == 10){
						monsterDeath.play();
						winTheme.play();
					} else {
						monsterTakeDamage.play();
					}
					monsterHp -= 10;
				}
			}
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
		battleTheme.play();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		dropImage.dispose();
		bucketImage.dispose();
		takeDamage.dispose();
		death.dispose();
		monsterTakeDamage.dispose();
		monsterDeath.dispose();
		battleTheme.dispose();
	}
}
