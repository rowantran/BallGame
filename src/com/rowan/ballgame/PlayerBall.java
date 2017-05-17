package com.rowan.ballgame;

import java.awt.*;
import java.awt.event.KeyEvent;

class PlayerBall extends Sprite {
    PlayerBall() {
        x = 200;
        y = 200;

        width = 20;
        height = 20;

        setSpeed(0, 0);
    }

    void draw(Graphics g) {
        g.drawOval((int) x, (int) y, width, height);
    }

    void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = -100;
        }

        if (key == KeyEvent.VK_D) {
            dx = 100;
        }

        if (key == KeyEvent.VK_W) {
            dy = -100;
        }

        if (key == KeyEvent.VK_S) {
            dy = 100;
        }
    }

    void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
            dx = 0;
        } else if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
            dy = 0;
        }
    }
}
