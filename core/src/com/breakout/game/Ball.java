package com.breakout.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    float x, y, speed, velocityX, velocityY;

    Ball(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = speed;
        this.speed = speed;
    }

    public void move() {
        x += velocityX;
        y += velocityY;

        if (y <= 0) Game.balls.remove(this);
        if (x - 10 <= 0 || x >= 806) velocityX *= -1;
        if (y >= 700) velocityY *= -1;

        else if (y - 10 <= 90 && y + 10 >= 75) {
            if (x + 10 >= Paddle.x && x - 10 <= Paddle.x + 150) {
                // calculates velocity from paddle
                float distance = x - (Paddle.x + 75);
                velocityX = distance / 75 * speed;
                velocityY = speed - velocityX;
            }
        }
    }

    static void render(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        for (Ball ball : Game.balls) {
            sr.circle(ball.x, ball.y, 10);
        }
    }
}