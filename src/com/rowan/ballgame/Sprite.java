package com.rowan.ballgame;

import javax.swing.*;
import java.awt.*;

abstract class Sprite extends JComponent {
    int x;
    int y;

    int dx = 0;
    int dy = 0;

    int width;
    int height;

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    abstract void update(double fps);
    abstract void draw(Graphics g);
}
