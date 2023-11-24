package com.breakout.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    float x, y, speed, velocityX, velocityY;
    static int radius = 10;

    Ball(float x, float y, float velocityX, float speed) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = speed;
        this.speed = speed;
    }

    public void move() {
        x += velocityX;
        y += velocityY;

        if (y - radius <= 0) Game.balls.remove(this);
        if (x - radius <= 0) {
            velocityX *= -1;
        } else if(x + radius >= 806) {
            velocityX *= -1;
        }
        if (y >= 700) velocityY *= -1;
        else if (y >= 560 - radius) {
            // checks for ball collision with tile
            for (int j = 0; j < Game.tiles.size(); j++) {
                Tile tile = Game.tiles.get(j);
                if (y + radius >= tile.y && y - radius <= tile.y + Tile.height && x + radius >= tile.x && x - radius <= tile.x + Tile.width) {
                    tile.collision();
                    bounce(tile);
                    PowerUp.generate();
                }
            }
        }
        else if (y - radius <= Paddle.y + Paddle.height && y + radius >= Paddle.y) {
            if (x + radius >= Paddle.x && x - radius <= Paddle.x + Paddle.width) {
                // calculates velocity from paddle
                float distance = x - (Paddle.x + Paddle.width / 2);
                velocityX = distance / 110 * speed;
                velocityY = (float) Math.sqrt(speed * speed - velocityX * velocityX);
            }
        }
    }

    public void bounce (Tile tile) {
        // calculates velocity from tile (works good enough)
        double angle = Math.atan2(tile.y + (double) Tile.height /2 - y, tile.x + (double) Tile.height /2 - x);
        double sideAngle = Math.atan((double) 10 /25);
        double topAngle = Math.atan((double) 25 /10);
        if (angle >= - sideAngle && angle < sideAngle) {
            velocityX *= -1;
            x = tile.x - radius - 1;
        } else if (angle >= sideAngle && angle < sideAngle + 2 * topAngle) {
            velocityY *= -1;
            y = tile.y - radius - 1;
        } else if (angle >= - sideAngle - 2 * topAngle && angle < - sideAngle) {
            velocityY *= -1;
            y = tile.y + Tile.height + radius + 1;
        } else {
            velocityX *= -1;
            x = tile.x + Tile.width + radius + 1;
        }
    }

    static void render(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        for (Ball ball : Game.balls) {
            sr.circle(ball.x, ball.y, radius);
        }
    }
}