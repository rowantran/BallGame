package com.rowan.ballgame;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

class Canvas extends JPanel {
    private Timer timer;
    private int x = 200;
    private int y = 200;

    Canvas() {
        init();
    }

    private void init() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(Resources.RES_WIDTH, Resources.RES_HEIGHT));
        setDoubleBuffered(true);

        timer = new Timer();
        timer.scheduleAtFixedRate(new GameLoop(), Resources.FRAME_TIME, Resources.FRAME_TIME);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBall(g);
    }

    private void drawBall(Graphics g) {
        g.drawOval(x, y, 50, 50);
        Toolkit.getDefaultToolkit().sync();
    }

    private class GameLoop extends TimerTask {
        @Override
        public void run() {
            x += (100 * (1 / Resources.FPS));
            y += (100 * (1 / Resources.FPS));

            repaint();
        }
    }
}
