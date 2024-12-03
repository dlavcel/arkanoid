package org.arkanoid.interfaces;

import org.arkanoid.objects.Ball;

public interface Collidable {
    void handleCollision(Ball ball);
}
