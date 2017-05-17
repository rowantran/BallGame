package com.rowan.ballgame;

import javax.swing.*;
import java.awt.*;

abstract class Sprite extends JComponent {
    double x;
    double y;

    int dx;
    int dy;

    int width;
    int height;

    void move(double fps) {
        x += ((1 / fps) * dx);
        y += ((1 / fps) * dy);
    }

    void setSpeed(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    boolean collidingWith(Sprite other) {
        return getBounds().intersects(other.getBounds());
    }

    abstract void draw(Graphics g);
}
