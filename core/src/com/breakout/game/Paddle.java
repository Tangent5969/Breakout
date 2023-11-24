package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    static int x = 407;
    static int y = 75;

    static void move() {
        x = Gdx.input.getX() - 75;
        if (x < 0) x = 0;
        else if (x > 664) x = 664;
    }

    static void render(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.rect(x, y, 150, 15);
    }
}
