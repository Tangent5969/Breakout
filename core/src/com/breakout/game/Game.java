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
		balls.add(new Ball(405, 75, 10));
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
						tile.collision();

						// calculates velocity from tile
						float distance = ball.x - (tile.x + 25);
						float height = ball.y - (tile.y + 10);
						double angle = Math.toDegrees(Math.atan2(tile.y + 10 - ball.y, tile.x + 25 - ball.x));
						if (angle < -45 && angle > -135) {
							System.out.println("TOP");
						} else if (angle < 45) {
							System.out.println("LEFT");
						} else if (angle < 135) {
							System.out.println("BOTTOM");
						} else {
							System.out.println("RIGHT");
						}

						ball.velocityX = (distance / 20) * ball.speed;
						if (distance > 0) {
							ball.velocityY = ball.speed - ball.velocityX;
						} else {
							ball.velocityY = ball.speed + ball.velocityX;
						}
						if (height <= 0) {
							ball.velocityY *= -1;
							ball.y = tile.y - 10;
						} else {
							ball.y = tile.y + 30;
						}
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
