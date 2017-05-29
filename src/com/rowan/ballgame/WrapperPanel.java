package com.rowan.ballgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

public class WrapperPanel extends JPanel {
    java.util.List<Level> levels;

    final static String MENU_STATE = "MENU";
    final static String LEVEL_SELECT_STATE = "LEVEL_SELECT";
    final static String PLAY_STATE = "PLAY";

    private JPanel menu;
    private JPanel levelSelect;
    private Canvas gameCanvas;

    private CardLayout layout;

    Level currentLevel;
    int currentLevelIndex;

    WrapperPanel() {
        layout = new CardLayout();
        setLayout(layout);

        levels = new ArrayList<>();
        loadLevels();

        menu = new JPanel();
        menu.setName(MENU_STATE);
        add(menu, MENU_STATE);
        initMenu();

        levelSelect = new JPanel();
        levelSelect.setName(LEVEL_SELECT_STATE);
        add(levelSelect, LEVEL_SELECT_STATE);
        initLevelSelect();

        gameCanvas = new Canvas(this);
        gameCanvas.setName(PLAY_STATE);
        add(gameCanvas, PLAY_STATE);

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(Resources.RES_WIDTH, Resources.RES_HEIGHT));

        layout.show(this, MENU_STATE);
    }

    private void loadLevels() {
        levels.add(new LevelEasy1());
        levels.add(new LevelEasy2());
        levels.add(new LevelEasy3());
        levels.add(new LevelHard1());
        levels.add(new LevelHard2());
    }

    private void initMenu() {
        JPanel panel = this;

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/PressStart2P.ttf")).deriveFont(32f);

            JLabel gameTitle = new JLabel(Resources.APPLICATION_TITLE);
            gameTitle.setFont(font);
            gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            menu.add(Box.createRigidArea(new Dimension(0, 20)));
            menu.add(gameTitle);
            menu.add(Box.createRigidArea(new Dimension(0, 200)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button playButton = new Button("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLevel(0);
                layout.show(panel, PLAY_STATE);
            }
        });

        menu.add(playButton);
        menu.add(Box.createRigidArea(new Dimension(0, 150)));
    }

    private void initLevelSelect() {
        JPanel panel = this;

        for (int i = 0; i < levels.size(); i++) {
            int levelIndex = i;

            Button b = new Button("Level " + (i+1));
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setLevel(levelIndex);
                    layout.show(panel, PLAY_STATE);
                }
            });

            levelSelect.add(b);
        }
    }

    void setLevel(int index) {
        currentLevelIndex = index;
        currentLevel = levels.get(index);

        gameCanvas.loadData(currentLevel);
    }
}
