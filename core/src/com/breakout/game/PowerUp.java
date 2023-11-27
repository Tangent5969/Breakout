package com.breakout.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;

public class PowerUp {
    int x, y;
    static int width = 30;
    static int height = 40;
    static int bigPaddleTimer = 0;
    String type;
    Color colour;
    static Random rand = new Random();

    PowerUp(String type) {
        this.x = rand.nextInt(0, 806 - width);
        this.y = 700 - height;
        this.colour = new Color(rand.nextFloat(0.1f, 1), rand.nextFloat(0.1f, 1), rand.nextFloat(0.1f, 1), 1);
        this.type = type;
    }

    static void generate() {
        String type;
        int chance = rand.nextInt(1, 100);
        if (chance <= 20) {
            type = "addBall";
        } else if (chance <= 25) {
            type = "doubleBalls";
        } else if (chance <= 35) {
            type = "bigPaddle";
        }
        else return;
        Game.powers.add(new PowerUp(type));
    }

    public void activate() {
        Game.powers.remove(this);
        switch (type) {
            case "addBall":
                addBall();
                break;
            case "doubleBalls":
                doubleBalls();
                break;
            case "bigPaddle":
                bigPaddle(true);
        }
    }

    public void move() {
        y -= 10;
        // collision with paddle
        if (y <= Paddle.y + Paddle.height && y + height >= Paddle.y) {
            if (x + width >= Paddle.x && x <= Paddle.x + Paddle.width) {
                activate();
            }
        }
    }

    static void addBall() {
        Game.balls.add(new Ball(Paddle.x + Paddle.width / 2, 100, 0, 1));
    }

    static void doubleBalls() {
        int size = Game.balls.size();
        for (int i = 0; i < size; i++) {
            Ball ball = Game.balls.get(i);
            Game.balls.add(new Ball(ball.x, ball.y, - ball.velocityX, ball.speed));
        }
    }

    static void bigPaddle(boolean power) {
        if (power) bigPaddleTimer = 300;
        if (bigPaddleTimer == 0) Paddle.width = 150;
        else {
            Paddle.width = 250;
            bigPaddleTimer--;
        }
    }

    static void render(ShapeRenderer sr) {
        for (PowerUp power : Game.powers) {
            sr.setColor(power.colour);
            sr.rect(power.x, power.y, width, height);
        }
    }
}
