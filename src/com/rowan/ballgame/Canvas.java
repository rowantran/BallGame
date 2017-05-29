package com.rowan.ballgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

class Canvas extends JPanel implements ActionListener {
    private boolean menuActive;

    private Timer timer;

    private PlayerBall player;

    private List<MovingBall> balls;
    private List<Wall> walls;

    private StartZone startZone;
    private EndZone endZone;

    private WrapperPanel wrapper;

    Canvas(WrapperPanel wrapper) {
        this.wrapper = wrapper;

        player = new PlayerBall();

        init();
    }

    private void init() {
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        im.put(KeyStroke.getKeyStroke("W"), "MOVE_UP");
        getActionMap().put("MOVE_UP", new MoveAction("W"));

        im.put(KeyStroke.getKeyStroke("released W"), "MOVE_UP_RELEASE");
        getActionMap().put("MOVE_UP_RELEASE", new MoveAction("released W"));

        im.put(KeyStroke.getKeyStroke("S"), "MOVE_DOWN");
        getActionMap().put("MOVE_DOWN", new MoveAction("S"));

        im.put(KeyStroke.getKeyStroke("released S"), "MOVE_DOWN_RELEASE");
        getActionMap().put("MOVE_DOWN_RELEASE", new MoveAction("released S"));

        im.put(KeyStroke.getKeyStroke("A"), "MOVE_LEFT");
        getActionMap().put("MOVE_LEFT", new MoveAction("A"));

        im.put(KeyStroke.getKeyStroke("released A"), "MOVE_LEFT_RELEASE");
        getActionMap().put("MOVE_LEFT_RELEASE", new MoveAction("released A"));

        im.put(KeyStroke.getKeyStroke("D"), "MOVE_RIGHT");
        getActionMap().put("MOVE_RIGHT", new MoveAction("D"));

        im.put(KeyStroke.getKeyStroke("released D"), "MOVE_RIGHT_RELEASE");
        getActionMap().put("MOVE_RIGHT_RELEASE", new MoveAction("released D"));

        setFocusable(true);
        setDoubleBuffered(true);
        timer = new Timer(Resources.FRAME_TIME, this);
        timer.start();
    }

    void loadData(Level level) {
        walls = level.walls();
        balls = level.balls();

        player.x = level.ballX();
        player.y = level.ballY();
        player.walls = walls;

        startZone = level.startZone();
        endZone = level.endZone();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        CardLayout layout = (CardLayout) wrapper.getLayout();
        JPanel current = new JPanel();
        for (Component comp : wrapper.getComponents()) {
            if (comp.isVisible()) {
                current = (JPanel) comp;
            }
        }

        if (current.getName() == wrapper.PLAY_STATE) {
            for (Sprite s : walls) {
                s.draw(g);
            }

            startZone.draw(g);
            endZone.draw(g);

            for (Sprite s : balls) {
                s.draw(g);

                if (player.getBounds().intersects(s.getBounds())) {
                    player.deaths++;
                    player.resetPosition(wrapper.currentLevel);
                }
            }

            player.draw(g);

            Toolkit.getDefaultToolkit().sync();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Main loop method

        CardLayout layout = (CardLayout) wrapper.getLayout();
        JPanel current = new JPanel();
        for (Component comp : wrapper.getComponents()) {
            if (comp.isVisible()) {
                current = (JPanel) comp;
            }
        }

        if (current.getName() == wrapper.PLAY_STATE) {
            player.update(Resources.FPS);
            for (Sprite s : balls) {
                s.update(Resources.FPS);
            }

            repaint();

            if (player.getBounds().intersects(endZone.getBounds())) {
                if (wrapper.currentLevelIndex < wrapper.levels.size() - 1) {
                    wrapper.currentLevelIndex++;
                    wrapper.setLevel(wrapper.currentLevelIndex);

                    loadData(wrapper.currentLevel);
                }
            }
        }
    }

    private class MoveAction extends AbstractAction {
        String key;

        MoveAction(String keyPressed) {
            key = keyPressed;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            player.move(key);
        }
    }
}
