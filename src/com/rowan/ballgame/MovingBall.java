package com.rowan.ballgame;

import java.awt.*;

abstract class MovingBall extends Sprite {
    MovingBall(int x, int y) {
        width = 20;
        height = 20;
        this.x = x;
        this.y = y;
    }

    void updateSpeed() {}
    void update(double fps) {
        updateSpeed();
        x += dx;
        y += dy;
    }

    void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(x, y, width, height);
    }
}
