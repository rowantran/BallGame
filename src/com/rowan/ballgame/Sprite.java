package com.rowan.ballgame;

import javax.swing.*;

abstract class Sprite extends JPanel {
    private int x;
    private int y;

    private int dx;
    private int dy;

    void move(double fps) {
        x += (int) ((1 / fps) * dx);
        y += (int) ((1 / fps) * dy);
    }

    void setSpeed(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    boolean collidingWith(Sprite other) {
        
    }
}
