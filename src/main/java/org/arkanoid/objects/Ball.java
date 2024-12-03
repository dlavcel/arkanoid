package org.arkanoid.objects;

import org.arkanoid.interfaces.Renderable;

public class Ball extends GameObject implements Renderable {
    private int dx = 1;
    private int dy = -1;

    public Ball(int x, int y) {
        super(x, y);
    }

    public void updatePosition() {
        x += dx;
        y += dy;
    }

    public void bounce(String direction) {
        if ("horizontal".equals(direction)) {
            dx = -dx;
        } else if ("vertical".equals(direction)) {
            dy = -dy;
        }
    }

    @Override
    public char getSymbol() {
        return 'O';
    }
}
