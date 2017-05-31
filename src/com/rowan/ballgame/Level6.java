package com.rowan.ballgame;
import java.util.ArrayList;
import java.util.List;

public class Level6 implements Level {
    public int ballX() {
        return 60;
    }

    public int ballY() { return 100;}

    public List<Wall> walls() {
        List<Wall> wallList = new ArrayList<Wall>();

        wallList.add(new Wall(0, 0, 800, 60));
        wallList.add(new Wall(0, 60, 10, 540));
        wallList.add(new Wall(120, 60, 80, 120));
        wallList.add(new Wall(720, 60, 80, 200));
        wallList.add(new Wall(280, 140,360, 20));
        wallList.add(new Wall(280, 180,40, 420));
        wallList.add(new Wall(20, 260,260, 320));
        wallList.add(new Wall(400, 260,80, 180));
        wallList.add(new Wall(320, 520,260, 80));
        wallList.add(new Wall(480, 260,320, 20));
        wallList.add(new Wall(560, 460,120, 40));
        wallList.add(new Wall(560, 400,20, 120));
        wallList.add(new Wall(760, 280,40, 320));
        wallList.add(new Wall(400, 260,80, 180));
        wallList.add(new Wall(660, 480,100, 20));
        wallList.add(new Wall(580, 580,180, 20));
        wallList.add(new Wall(740, 500,20, 80));

        return wallList;
    }

    public List<MovingBall> balls() {
        List<MovingBall> ballList = new ArrayList<>();

        ballList.add(new MovingBall(400, 60, 0, 0){
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

        ballList.add(new RotatingBall(300, 300, 0,80, 50));
        ballList.add(new RotatingBall(300, 300, 0,80, 100));
        ballList.add(new RotatingBall(300, 300, Math.PI / 2, 80, 50));
        ballList.add(new RotatingBall(300, 300, Math.PI / 2, 80, 100));
        ballList.add(new RotatingBall(300, 300, Math.PI, 80, 50));
        ballList.add(new RotatingBall(300, 300, Math.PI, 80, 100));
        ballList.add(new RotatingBall(300, 300, 3*Math.PI / 2, 80, 50));
        ballList.add(new RotatingBall(300, 300, 3*Math.PI/2, 80, 100));


        return ballList;
    }

    public StartZone startZone() {
        return new StartZone(20, 60, 100, 80);
    }

    public EndZone endZone() {
        return new EndZone(660, 500, 80, 80);
    }
}
