package com.rowan.ballgame;

import java.awt.*;

class Wall extends Sprite {
    Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
    }

    void update(double fps) {}

    void draw(Graphics g) {
        g.drawRect((int) x, (int) y, width, height);
    }
}
