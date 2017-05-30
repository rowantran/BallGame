package com.rowan.ballgame;

import java.util.ArrayList;
import java.util.List;

public class Level4 implements Level{
    public int ballX() {return 100;}

    public int ballY() { return 180;}

    public List<Wall> walls() {
        List<Wall> wallList = new ArrayList<Wall>();

        wallList.add(new Wall(0, 0, 800, 60));
        wallList.add(new Wall(0, 60, 60, 540));
        wallList.add(new Wall(180, 60, 620, 80));
        wallList.add(new Wall(180, 140, 160, 160));
        wallList.add(new Wall(60, 400,280, 200));
        wallList.add(new Wall(340, 560,460, 40));
        wallList.add(new Wall(440, 220,140, 260));
        wallList.add(new Wall(660, 220,40, 100));
        wallList.add(new Wall(660, 400,40, 80));


        return wallList;
    }

    public List<MovingBall> balls() {
        List<MovingBall> ballList = new ArrayList<>();

        ballList.add(new MovingBall(110, 60, 0, 0){
            void updateSpeed() {
                if (x < 20 || x > 300) {
                    dx = -dx;
                }}
        });

         ballList.add(new MovingBall(110, 60, 0, 0){
            void updateSpeed() {
                if (x < 20 || x > 300) {
                    dx = -dx;
                }}
        });


        return ballList;
    }


    public StartZone startZone() {
        return new StartZone(60, 160, 120, 100);
    }

    public EndZone endZone() {
        return new EndZone(720, 140, 80, 420);
    }
}
