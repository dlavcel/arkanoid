package org.arkanoid.objects;

import org.arkanoid.interfaces.Collidable;
import org.arkanoid.interfaces.Renderable;

public class Block extends GameObject implements Collidable, Renderable {
    private boolean destroyed;

    public Block(int x, int y) {
        super(x, y);
        this.destroyed = false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void destroy() {
        this.destroyed = true;
    }

    @Override
    public void handleCollision(Ball ball) {
        if (!destroyed) {
            ball.bounce("vertical");
            destroy();
        }
    }

    @Override
    public char getSymbol() {
        return destroyed ? ' ' : '#';
    }
}
