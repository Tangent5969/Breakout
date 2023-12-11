package com.breakout.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;

public class Game extends ApplicationAdapter {
	ShapeRenderer sr;
	BitmapFont font;
	SpriteBatch batch;
	static ArrayList<Tile> tiles = new ArrayList<>();
	static ArrayList<Ball> balls = new ArrayList<>();
	static ArrayList<PowerUp> powers = new ArrayList<>();
	static int multiplier = 10;
	static int score = 0;

	public void setGame() {
		powers.clear();
		Tile.generate();
		balls.add(new Ball(405, 100, 0,1));
	}

	public void logic() {
		Paddle.move();
		for (int p = 0; p < powers.size(); p++){
			PowerUp power = powers.get(p);
			power.move();
		}

		// multiplier controls game speed
		for (int m = 0; m < multiplier; m++) {
			for (int i = 0; i < balls.size(); i++) {
				Ball ball = balls.get(i);
				ball.move();
			}
		}

		if (balls.isEmpty()) {
			multiplier = 10;
			score = 0;
			tiles.clear();
			setGame();
		} else if (tiles.isEmpty()) {
			multiplier += 5;
			balls.clear();
			setGame();
		}
	}
	
	@Override
	public void create () {
		sr = new ShapeRenderer();
		font = new BitmapFont(Gdx.files.internal("assets/font.fnt"), Gdx.files.internal("assets/font.png"), false);
		batch = new SpriteBatch();
		setGame();
	}

	@Override
	public void render () {
		logic();
		ScreenUtils.clear(0, 0, 0, 1);
		sr.begin(ShapeRenderer.ShapeType.Filled);
		Tile.render(sr);
		PowerUp.render(sr);
		Paddle.render(sr);
		Ball.render(sr);
		sr.end();

		batch.begin();
		font.draw(batch, "score : " + score, 11, 35);
		batch.end();
	}
	
	@Override
	public void dispose () {
		sr.dispose();
		batch.dispose();
	}
}
