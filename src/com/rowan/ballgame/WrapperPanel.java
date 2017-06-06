package com.rowan.ballgame;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

public class WrapperPanel extends JPanel {
    java.util.List<Level> levels;
    private boolean[] levelsCompleted;
    private java.util.List<JButton> levelButtons;

    final static String MENU_STATE = "MENU";
    final static String LEVEL_SELECT_STATE = "LEVEL_SELECT";
    final static String VICTORY_STATE = "VICTORY";
    final static String PLAY_STATE = "PLAY";

    private JPanel menu;
    private JPanel levelSelect;
    private JPanel victory;
    private Canvas gameCanvas;

    private CardLayout layout;

    Level currentLevel;
    int currentLevelIndex;

    WrapperPanel() {
        layout = new CardLayout();
        setLayout(layout);

        levels = new ArrayList<>();
        loadLevels();

        levelsCompleted = new boolean[levels.size()];
        levelButtons = new ArrayList<>();

        menu = new JPanel();
        menu.setName(MENU_STATE);
        add(menu, MENU_STATE);
        initMenu();

        levelSelect = new JPanel();
        levelSelect.setName(LEVEL_SELECT_STATE);
        add(levelSelect, LEVEL_SELECT_STATE);
        initLevelSelect();

        victory = new JPanel();
        victory.setName(VICTORY_STATE);
        add(victory, VICTORY_STATE);
        initVictory();

        gameCanvas = new Canvas(this);
        gameCanvas.setName(PLAY_STATE);
        add(gameCanvas, PLAY_STATE);

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(Resources.RES_WIDTH, Resources.RES_HEIGHT));

        layout.show(this, MENU_STATE);
    }

    private void loadLevels() {
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
        levels.add(new Level5());
        levels.add(new Level6());
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

        JButton playButton = new JButton("Play");
        playButton.setMinimumSize(new Dimension(240, 135));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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

            JButton b = new JButton("Level " + (i+1));
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setLevel(levelIndex);
                    layout.show(panel, PLAY_STATE);
                }
            });

            if (i > 0 && !Resources.DEV_MODE) {
                b.setEnabled(false);
            }

            levelButtons.add(b);
            levelSelect.add(b);
        }
    }

    private void initVictory() {
        victory.setLayout(new BoxLayout(victory, BoxLayout.Y_AXIS));

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/PressStart2P.ttf")).deriveFont(32f);

            JLabel youWon = new JLabel("You won the game!");
            youWon.setFont(font);
            youWon.setAlignmentX(Component.CENTER_ALIGNMENT);

            victory.add(Box.createRigidArea(new Dimension(0, 20)));
            victory.add(youWon);
            victory.add(Box.createRigidArea(new Dimension(0, 200)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void playVictorySound() {
        try {
            File soundFile = new File("res/victory.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void enableLevel(int index) {
        levelButtons.get(index).setEnabled(true);
    }

    void setLevel(int index) {
        currentLevelIndex = index;
        currentLevel = levels.get(index);

        gameCanvas.loadData(currentLevel);
    }
}
