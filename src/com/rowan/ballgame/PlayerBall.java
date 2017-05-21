package com.rowan.ballgame;

import java.security.Key;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;

class PlayerBall extends Sprite {
    List<Wall> walls;

    private boolean W_PRESSED = false;
    private boolean A_PRESSED = false;
    private boolean S_PRESSED = false;
    private boolean D_PRESSED = false;

    PlayerBall() {
        x = 80;
        y = 60;

        width = 40;
        height = 40;
    }

    private Rectangle nextFramePosition(double fps) {
        double framePercentage = (1 / fps);
        return new Rectangle((int) (x + (framePercentage * dx)), (int) (y + (framePercentage * dy)), width, height);
    }

    void update(double fps) {
        Rectangle nextPos = nextFramePosition(fps);

        for (Wall wall : walls) {
            if (nextPos.intersects(wall.getBounds())) {
                return;
            }
        }

        x += ((1 / fps) * dx);
        y += ((1 / fps) * dy);
    }

    void draw(Graphics g) {
        g.drawOval((int) x, (int) y, width, height);
    }

    void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = -100;
            A_PRESSED = true;
        }

        if (key == KeyEvent.VK_D) {
            dx = 100;
            D_PRESSED = true;
        }

        if (key == KeyEvent.VK_W) {
            dy = -100;
            W_PRESSED = true;
        }

        if (key == KeyEvent.VK_S) {
            dy = 100;
            S_PRESSED = true;
        }
    }

    void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            if (!D_PRESSED) {
                dx = 0;
            }

            A_PRESSED = false;
        } else if (key == KeyEvent.VK_D) {
            if (!A_PRESSED) {
                dx = 0;
            }

            D_PRESSED = false;
        } else if (key == KeyEvent.VK_W) {
            if (!S_PRESSED) {
                dy = 0;
            }

            W_PRESSED = false;
        } else if (key == KeyEvent.VK_S) {
            if (!W_PRESSED) {
                dy = 0;
            }

            S_PRESSED = false;
        }
    }
}
