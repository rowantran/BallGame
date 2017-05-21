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

    private PlayerBall player;

    private List<Sprite> balls;
    private List<Wall> walls;

    private StartZone startZone;

    Canvas() {
        init();

        balls = new ArrayList<>();

        Level currentLevel = new LevelEasy2();
        walls = currentLevel.walls();

        player = new PlayerBall();
        player.x = currentLevel.ballX();
        player.y = currentLevel.ballY();
        player.walls = walls;

        startZone = currentLevel.startZone();
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

        for (Sprite s : walls) {
            s.draw(g);
        }

        startZone.draw(g);

        for (Sprite s : balls) {
            s.draw(g);
        }

        player.draw(g);

        Toolkit.getDefaultToolkit().sync();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Main loop method

        player.update(Resources.FPS);
        for (Sprite s : balls) {
            s.update(Resources.FPS);
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}
