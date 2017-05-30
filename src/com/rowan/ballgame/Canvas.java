package com.rowan.ballgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

class Canvas extends JPanel implements ActionListener {
    private PlayerBall player;

    private List<MovingBall> balls;
    private List<Wall> walls;

    private StartZone startZone;
    private EndZone endZone;

    private WrapperPanel wrapper;

    private boolean levelMarker;
    private int levelMarkerFrames;
    private float levelMarkerAlpha;

    Canvas(WrapperPanel wrapper) {
        this.wrapper = wrapper;

        player = new PlayerBall();

        levelMarker = false;
        levelMarkerFrames = 0;
        levelMarkerAlpha = 0f;

        init();
    }

    private void init() {
        registerKeyStrokes("W", "released W", "S", "released S", "A", "released A", "D", "released D");
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        im.put(KeyStroke.getKeyStroke("ESCAPE"), "ESCAPE");
        getActionMap().put("ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) wrapper.getLayout();
                layout.show(wrapper, WrapperPanel.LEVEL_SELECT_STATE);
            }
        });

        setFocusable(true);
        setDoubleBuffered(true);

        Timer timer = new Timer(Resources.FRAME_TIME, this);
        timer.start();
    }

    private void registerKeyStrokes(String... keyCodes) {
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

        levelMarker = true;
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

        if (current.getName().equals(WrapperPanel.PLAY_STATE)) {
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

            if (levelMarker) {
                levelMarkerFrames++;
                if (levelMarkerFrames < 60) {
                    levelMarkerAlpha += 1/60f;
                } else if (levelMarkerFrames > 60 && levelMarkerFrames < 120) {
                    levelMarkerAlpha -= 1/60f;
                } else if (levelMarkerFrames > 120) {
                    levelMarker = false;
                    levelMarkerFrames = 0;
                }

                if (levelMarkerAlpha > 1.0f) {
                    levelMarkerAlpha = 1.0f;
                } else if (levelMarkerAlpha < 0.0f) {
                    levelMarkerAlpha = 0.0f;
                }

                Composite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, levelMarkerAlpha);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setComposite(alpha);
                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/PressStart2P.ttf")).deriveFont(32f);
                    g2d.setFont(font);
                    FontMetrics metrics = g2d.getFontMetrics();

                    String levelMarkerText = "Level " + (wrapper.currentLevelIndex + 1);
                    int levelMarkerTextWidth = metrics.stringWidth(levelMarkerText);
                    int levelMarkerTextHeight = metrics.getAscent();
                    int levelMarkerTextX = (Resources.RES_WIDTH - levelMarkerTextWidth) / 2;
                    int levelMarkerTextY = (Resources.RES_HEIGHT - levelMarkerTextHeight) / 2;

                    g2d.drawString("Level " + (wrapper.currentLevelIndex + 1), levelMarkerTextX, levelMarkerTextY);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            Toolkit.getDefaultToolkit().sync();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Main loop method

        JPanel current = new JPanel();
        for (Component comp : wrapper.getComponents()) {
            if (comp.isVisible()) {
                current = (JPanel) comp;
            }
        }

        if (current.getName().equals(WrapperPanel.PLAY_STATE)) {
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
