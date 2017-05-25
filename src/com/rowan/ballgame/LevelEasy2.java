package com.rowan.ballgame;

import java.util.ArrayList;
import java.util.List;

public class LevelEasy2 implements Level{
    public int ballX() {
        return 60;
    }

    public int ballY() { return 50;}

    public List<Wall> walls() {
        List<Wall> wallList = new ArrayList<Wall>();

        wallList.add(new Wall(0, 0, 40, 600));
        wallList.add(new Wall(40, 0, 760, 40));
        wallList.add(new Wall(120, 40, 680, 60));
        wallList.add(new Wall(40, 180, 620, 40));
        wallList.add(new Wall(120, 300,620, 40));
        wallList.add(new Wall(40, 420,620, 40));
        wallList.add(new Wall(120, 540,620, 60));
        wallList.add(new Wall(740, 100,60, 500));


        return wallList;
    }

    public List<MovingBall> balls() {
        List<MovingBall> ballList = new ArrayList<>();

        return ballList;
    }

    public StartZone startZone() {
        return new StartZone(40, 40, 80, 60);
    }
    public EndZone endZone() {
        return new EndZone(40, 580, 80, 60);
    }
}
