package com.rowan.ballgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

class Canvas extends JPanel implements ActionListener {
    private boolean menuActive;

    private Timer timer;

    private PlayerBall player;

    private List<MovingBall> balls;
    private List<Wall> walls;

    private List<Level> levels;

    private Level currentLevel;
    private int currentLevelIndex;
    private StartZone startZone;
    private EndZone endZone;

    Canvas() {
        levels = new ArrayList<>();
        levels.add(new LevelEasy1());
        levels.add(new LevelEasy2());
        levels.add(new LevelEasy3());
        levels.add(new LevelHard1());
        levels.add(new LevelHard2());

        player = new PlayerBall();

        setMenu(true);

        init();
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

    private void setMenu(boolean menuActive) {
        if (this.menuActive == menuActive) {
            return;
        }

        this.menuActive = menuActive;
        if (menuActive) {
            repaint();
            for (int i = 0; i < levels.size(); i++) {
                int levelIndex = i;

                Button b = new Button("Level " + (i+1));
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setLevel(levelIndex);

                        setMenu(false);
                    }
                });

                this.add(b);
            }

            revalidate();
        } else {
            this.removeAll();

            loadLevel();
        }

    }

    private void setLevel(int index) {
        currentLevelIndex = index;
        currentLevel = levels.get(index);
    }

    private void loadLevel() {
        walls = currentLevel.walls();
        balls = currentLevel.balls();

        player.x = currentLevel.ballX();
        player.y = currentLevel.ballY();
        player.walls = walls;

        startZone = currentLevel.startZone();
        endZone = currentLevel.endZone();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!menuActive) {
            for (Sprite s : walls) {
                s.draw(g);
            }

            startZone.draw(g);
            endZone.draw(g);

            for (Sprite s : balls) {
                s.draw(g);

                if (player.getBounds().intersects(s.getBounds())) {
                    player.deaths++;
                    player.resetPosition(currentLevel);
                }
            }

            player.draw(g);

            Toolkit.getDefaultToolkit().sync();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Main loop method

        if (!menuActive) {
            player.update(Resources.FPS);
            for (Sprite s : balls) {
                s.update(Resources.FPS);
            }

            repaint();

            if (player.getBounds().intersects(endZone.getBounds())) {
                if (currentLevelIndex < levels.size() - 1) {
                    currentLevelIndex++;
                    setLevel(currentLevelIndex);
                    loadLevel();
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                setMenu(true);
            }

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}
