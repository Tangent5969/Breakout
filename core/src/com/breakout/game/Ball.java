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
        if (x - 10 <= 0) {
            velocityX *= -1;
            x = 10;
        } else if(x + 10 >= 814) {
            velocityX *= -1;
            x = 804;
        }
        if (y >= 700) velocityY *= -1;
        else if (y - 10 <= 90 && y + 10 >= 75) {
            if (x + 10 >= Paddle.x && x - 10 <= Paddle.x + 150) {
                // calculates velocity from paddle
                float distance = x - (Paddle.x + 75);
                velocityX = distance / 110 * speed;
                if (distance > 0) {
                    velocityY = speed - velocityX;
                } else {
                    velocityY = speed + velocityX;
                }
            }
        }
    }

    public void bounce (Tile tile) {
        double angle = Math.toDegrees(Math.atan2(tile.y + (double) Tile.height /2 - y, tile.x + (double) Tile.height /2 - x));
        System.out.println(angle);
        if (angle < -45 && angle > -135) {
            System.out.println("TOP");
            velocityY *= -1;
        } else if (angle < 45) {
            System.out.println("LEFT");
            velocityX *= -1;

            velocityX = 0;
            velocityY = 0;
        } else if (angle < 135) {
            System.out.println("BOTTOM");
            velocityY *= -1;
        } else {
            System.out.println("RIGHT");
            velocityX *= -1;

            velocityX = 0;
            velocityY = 0;
        }
    }
    static void render(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        for (Ball ball : Game.balls) {
            sr.circle(ball.x, ball.y, 10);
        }
    }
}