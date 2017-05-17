package com.rowan.ballgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class Canvas extends JPanel implements ActionListener {
    private Timer timer;

    List<Sprite> balls;
    PlayerBall ball;

    Canvas() {
        init();

        balls = new ArrayList<>();

        ball = new PlayerBall();
        balls.add(ball);
    }

    private void init() {
        addKeyListener(new TAdapter());

        setFocusable(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(Resources.RES_WIDTH, Resources.RES_HEIGHT));
        setDoubleBuffered(true);

        timer = new Timer();
        timer.scheduleAtFixedRate(new GameLoop(), Resources.FRAME_TIME, Resources.FRAME_TIME);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Sprite s : balls) {
            s.draw(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }


    private class GameLoop extends TimerTask {
        @Override
        public void run() {
            for (Sprite s : balls) {
                s.move(Resources.FPS);
            }

            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            ball.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            ball.keyPressed(e);
        }
    }
}
