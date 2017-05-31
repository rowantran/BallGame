package com.rowan.ballgame;

public class RotatingBall extends MovingBall {
    private int centerX;
    private int centerY;
    private double angle;
    private int rotationTime;
    private double radius;

    RotatingBall(int x, int y, double initialAngle, int rotationTime, int radius) {
        super(x + radius, y, 0, 0);
        angle = initialAngle;
        centerX = x;
        centerY = y;
        this.rotationTime = rotationTime;
        this.radius = radius;
    }

    @Override
    void update(double fps) {
        angle += (Math.PI / rotationTime * 2);
        x = centerX + (int) (radius * Math.cos(angle));
        y = centerY + (int) (radius * Math.sin(angle));
    }
}
