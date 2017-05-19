package com.rowan.ballgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

class Canvas extends JPanel implements ActionListener {
    private Timer timer;

    private PlayerBall ball;

    private List<Sprite> balls;
    private List<Wall> walls;

    Canvas() {
        init();

        balls = new ArrayList<>();

        Level currentLevel = new LevelEasy1();
        walls = currentLevel.walls();

        ball = new PlayerBall();
        ball.walls = walls;

        balls.add(ball);
    }

    private void init() {
        addKeyListener(new TAdapter());

        setFocusable(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(Resources.RES_WIDTH, Resources.RES_HEIGHT));
        setDoubleBuffered(true);

        timer = new Timer(Resources.FRAME_TIME, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Sprite s : balls) {
            s.draw(g);
        }

        for (Sprite s : walls) {
            s.draw(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Main loop method

        for (Sprite s : balls) {
            s.update(Resources.FPS);
        }

        repaint();
    }

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
