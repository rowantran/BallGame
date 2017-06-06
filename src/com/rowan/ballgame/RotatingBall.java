package com.rowan.ballgame;

public class RotatingBall extends MovingBall {
    private int centerX;
    private int centerY;
    private double angle;
    private double dRotation;
    private double radius;

    RotatingBall(int x, int y, double initialAngle, double dRotation, int radius) {
        super(x + radius, y, 0, 0);
        angle = initialAngle;
        centerX = x;
        centerY = y;
        this.dRotation = dRotation;
        this.radius = radius;
    }

    @Override
    void update(double fps) {
        angle += dRotation;
        x = centerX + (int) (radius * Math.cos(angle));
        y = centerY + (int) (radius * Math.sin(angle));
    }
}
