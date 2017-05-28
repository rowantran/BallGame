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
        width = 30;
        height = 30;
    }

    private Rectangle nextFramePosition(double fps) {
        return new Rectangle(x + dx, y + dy, width, height);
    }

    void update(double fps) {
        Rectangle nextPos = nextFramePosition(fps);

        for (Wall wall : walls) {
            if (nextPos.intersects(wall.getBounds())) {
                return;
            }
        }

        x += dx;
        y += dy;
    }

    void resetPosition(Level level) {
        x = level.ballX();
        y = level.ballY();
    }

    void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, width, height);

        g.setColor(Color.BLACK);
        g.drawOval(x, y, width, height);
    }

    void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = -3;
            A_PRESSED = true;
        }

        if (key == KeyEvent.VK_D) {
            dx = 3;
            D_PRESSED = true;
        }

        if (key == KeyEvent.VK_W) {
            dy = -3;
            W_PRESSED = true;
        }

        if (key == KeyEvent.VK_S) {
            dy = 3;
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
