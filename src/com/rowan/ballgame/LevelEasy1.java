package com.rowan.ballgame;

import java.util.ArrayList;
import java.util.List;

public class LevelEasy1 implements Level {
    public int ballX() {
        return 80;
    }

    public int ballY() {
        return 60;
    }

    public List<Wall> walls() {
        List<Wall> wallList = new ArrayList<Wall>();

        wallList.add(new Wall(0, 0, 60, 800));
        wallList.add(new Wall(60, 0, 740, 60));
        wallList.add(new Wall(140, 60, 40, 400));
        wallList.add(new Wall(180, 420, 420, 40));
        wallList.add(new Wall(60, 540,740, 60));
        wallList.add(new Wall(600, 180,40, 280));
        wallList.add(new Wall(720, 60,80, 480));
        wallList.add(new Wall(260, 140,380, 40));
        wallList.add(new Wall(260, 180,120, 160));
        wallList.add(new Wall(380, 260,140, 80));

        return wallList;
    }

    public List<MovingBall> balls() {
        List<MovingBall> ballList = new ArrayList<>();

        ballList.add(new MovingBall(20, 30, 0, 5) {});

        return ballList;
    }

    public StartZone startZone() {
        return new StartZone(60, 60, 80, 60);
    }
    public EndZone endZone() {
        return new EndZone(60, 100, 30, 30);
    }
}
