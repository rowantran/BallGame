package com.rowan.ballgame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.awt.*;

class PlayerBall extends Sprite {
    List<Wall> walls;

    private boolean W_PRESSED = false;
    private boolean A_PRESSED = false;
    private boolean S_PRESSED = false;
    private boolean D_PRESSED = false;

    private BufferedImage skullIcon;
    int deaths;

    PlayerBall() {
        width = 30;
        height = 30;

        deaths = 0;

        try {
            skullIcon = ImageIO.read(new File("res/skull.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Rectangle nextFramePosition() {
        return new Rectangle(x + dx, y + dy, width, height);
    }

    void update(double fps) {
        Rectangle nextPos = nextFramePosition();

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

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/PressStart2P.ttf")).deriveFont(32f);
            g.setFont(font);

            FontMetrics metrics = g.getFontMetrics();

            String deathsString = Integer.toString(deaths);
            g.drawString(deathsString, 780 - metrics.stringWidth(deathsString), 20 + metrics.getAscent());

            // Set skull icon size to text's height - 10
            int skullIconSize = metrics.getAscent() - 10;
            g.drawImage(skullIcon, 780 - metrics.stringWidth(deathsString) - skullIconSize - 20,
                    20, skullIconSize, skullIconSize, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void move(String key) {
        if (key.equals("A")) {
            dx = -3;
            A_PRESSED = true;
        }

        if (key.equals("D")) {
            dx = 3;
            D_PRESSED = true;
        }

        if (key.equals("W")) {
            dy = -3;
            W_PRESSED = true;
        }

        if (key.equals("S")) {
            dy = 3;
            S_PRESSED = true;
        }

        if (key.equals("released A")) {
            if (!D_PRESSED) {
                dx = 0;
            }

            A_PRESSED = false;
        }

        if (key.equals("released D")) {
            if (!A_PRESSED) {
                dx = 0;
            }

            D_PRESSED = false;
        }

        if (key.equals("released W")) {
            if (!S_PRESSED) {
                dy = 0;
            }

            W_PRESSED = false;
        }

        if (key.equals("released S")) {
            if (!W_PRESSED) {
                dy = 0;
            }

            S_PRESSED = false;
        }

    }
}
