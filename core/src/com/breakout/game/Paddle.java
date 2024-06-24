package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    static final int initialWidth = 150;
    static int width = initialWidth;
    static int height = 15;
    static int x = 403;
    static int y = 75;

    static void move() {
        PowerUp.bigPaddle(false);
        x = Gdx.input.getX() - width / 2;
        if (x < 0) x = 0;
        else if (x + width> 806) x = 806 - width;
    }

    static void render(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.rect(x, y, width, height);
    }
}
