package com.breakout.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;

public class Game extends ApplicationAdapter {
	ShapeRenderer sr;
	static ArrayList<Tile> tiles = new ArrayList<>();
	static ArrayList<Ball> balls = new ArrayList<>();

	public void setGame() {
		Tile.generate();
		balls.add(new Ball(405, 75, 7));
	}

	public void collision(Ball ball, Tile tile) {
		if (tile.health > 1) {
			tile.health--;
			tile.changeColour();
		} else tiles.remove(tile);
		ball.velocityX *= -1;
		ball.velocityY *= -1;
	}

	public void logic() {
		Paddle.move();
		for (int i = 0; i < balls.size(); i++) {
			Ball ball = balls.get(i);
			ball.move();

			// checks for ball collision with tile
			if (ball.y >= 550) {
				for (int j = 0; j < tiles.size(); j++) {
					Tile tile = tiles.get(j);
					if (ball.y + 10 >= tile.y && ball.y - 10 <= tile.y + 20 && ball.x + 10 >= tile.x && ball.x - 10 <= tile.x + 50) {
						collision(ball, tile);
					}
				}
			}
		}
		if (balls.isEmpty()) {
			tiles.clear();
			setGame();
		}
	}
	
	@Override
	public void create () {
		sr = new ShapeRenderer();
		setGame();
	}

	@Override
	public void render () {
		logic();
		ScreenUtils.clear(0, 0, 0, 1);
		sr.begin(ShapeRenderer.ShapeType.Filled);
		Tile.render(sr);
		Paddle.render(sr);
		Ball.render(sr);
		sr.end();
	}
	
	@Override
	public void dispose () {
		sr.dispose();
	}
}