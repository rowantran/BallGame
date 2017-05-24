package com.rowan.ballgame;

import java.awt.*;

public class EndZone extends Sprite {
    EndZone(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    void update(double fps) {}

    void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}
