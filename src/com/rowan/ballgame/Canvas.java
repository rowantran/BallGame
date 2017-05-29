package com.rowan.ballgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class Canvas extends JPanel implements ActionListener {
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
        registerKeyStrokes("W", "released W", "S", "released S", "A", "released A", "D", "released D");

        setFocusable(true);
        setDoubleBuffered(true);

        Timer timer = new Timer(Resources.FRAME_TIME, this);
        timer.start();
    }

    void registerKeyStrokes(String... keyCodes) {
        // Registers each keyCode defined in keyCodes using MoveAction to call PlayerBall.move(keyCode)
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        for (String keyCode : keyCodes) {
            im.put(KeyStroke.getKeyStroke(keyCode), keyCode);
            getActionMap().put(keyCode, new MoveAction(keyCode));
        }
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

        JPanel current = new JPanel();
        for (Component comp : wrapper.getComponents()) {
            if (comp.isVisible()) {
                current = (JPanel) comp;
            }
        }

        if (current.getName().equals(wrapper.PLAY_STATE)) {
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

        if (current.getName().equals(wrapper.PLAY_STATE)) {
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
