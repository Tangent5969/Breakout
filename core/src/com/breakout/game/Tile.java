package com.breakout.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;

public class Tile {
    int x, y, health;
    Color colour;

    Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 3;
        this.changeColour();
    }

    static void generate() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 6; j++) {
                Game.tiles.add(new Tile(i * 50 + i * 4, j * 20 + 560 + j * 4));
            }
        }
    }

    public void changeColour() {
        Random rand = new Random();
        if (health == 3) {
            colour = new Color(0, rand.nextFloat(0.1f, 1), 0, 1);
        }
        else if (health == 2) {
            colour = new Color(1, rand.nextFloat(0.1f, 1), 0, 1);
        }
        else {
            colour = new Color(rand.nextFloat(0.1f, 1), 0, 0, 0);
        }
    }

    public void collision() {
        if (health > 1) {
            health--;
            changeColour();
        } else Game.tiles.remove(this);
    }

    static void render(ShapeRenderer sr) {
        for (Tile tile : Game.tiles) {
            sr.setColor(tile.colour);
            sr.rect(tile.x, tile.y, 50, 20);
        }
    }
}
