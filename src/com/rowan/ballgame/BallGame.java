package com.rowan.ballgame;

import javax.swing.*;
import java.awt.*;

public class BallGame extends JFrame {
    private BallGame() {
        initUI();
    }

    private void initUI() {
        add(new WrapperPanel());

        setResizable(false);
        pack();

        setTitle(Resources.APPLICATION_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                BallGame game = new BallGame();
                game.setVisible(true);
            }
        });
    }
}
