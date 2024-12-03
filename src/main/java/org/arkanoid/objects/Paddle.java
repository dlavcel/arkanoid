package org.arkanoid.objects;

import org.arkanoid.Field;
import org.arkanoid.interfaces.Renderable;

public class Paddle extends GameObject implements Renderable {
    private static final int POSITION_Y = Field.getHEIGHT() - 2;
    private static final int WIDTH = 5;

    public Paddle(int x) {
        super(x, POSITION_Y);
    }

    public void moveLeft() {
        if (x > 1) {
            x--;
        }
    }

    public void moveRight(int fieldWidth) {
        if (x + WIDTH < fieldWidth - 1) {
            x++;
        }
    }

    public int getPOSITION_Y() {
        return POSITION_Y;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    @Override
    public char getSymbol() {
        return '=';
    }
}
