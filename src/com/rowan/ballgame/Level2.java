package com.rowan.ballgame;

import java.util.ArrayList;
import java.util.List;
public class Level2 implements Level{
    public int ballX() {
        return 380;
    }

    public int ballY() { return 80; }

    public List<Wall> walls() {
        List<Wall> wallList = new ArrayList<Wall>();

        wallList.add(new Wall(0, 0, 800, 40));
        wallList.add(new Wall(0, 40, 80, 560));
        wallList.add(new Wall(80, 40, 220, 100));
        wallList.add(new Wall(80, 520, 220, 80));
        wallList.add(new Wall(520, 40,280, 100));
        wallList.add(new Wall(520, 520,280, 80));
        wallList.add(new Wall(740, 140,60, 380));
        wallList.add(new Wall(200, 220,420, 220));


        return wallList;
    }

    public List<MovingBall> balls() {
        List<MovingBall> ballList = new ArrayList<>();

        ballList.add(new MovingBall(20, 280, 5, 0){
            void updateSpeed() {
                if (x < 20 || x > 300) {
                    dx = -dx;
                }}
        });

        ballList.add(new MovingBall(20, 310, 5, 0){
            void updateSpeed() {
                if (x < 20 || x > 300) {
                    dx = -dx;
                }}
        });

        ballList.add(new MovingBall(20, 340, 5, 0){
            void updateSpeed() {
                if (x < 20 || x > 300) {
                    dx = -dx;
                }}
        });

        ballList.add(new MovingBall(650, 200, 0, 5){
            void updateSpeed() {
                if (y < 80 || y > 570) {
                    dy = -dy;
                }}
        });

        ballList.add(new MovingBall(690, 200, 0, 5){
            void updateSpeed() {
                if (y < 80 || y > 570) {
                    dy = -dy;
                }}
        });

        return ballList;
    }

    public StartZone startZone() {
        return new StartZone(300, 40, 220, 100);
    }

    public EndZone endZone() {
        return new EndZone(300, 520, 220, 80);
    }
}
