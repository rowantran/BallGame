package com.rowan.ballgame;

public class RotatingBall extends MovingBall {
    private int centerX;
    private int centerY;
    private double angle;
    private double dAngle;
    private double radius;

    RotatingBall(int x, int y, double dAngle, int radius) {
        super(x + radius, y, 0, 0);
        angle = 0.0;
        centerX = x;
        centerY = y;
        this.dAngle = dAngle;
        this.radius = radius;
    }

    @Override
    void update(double fps) {
        angle += dAngle;
        x = centerX + (int) (radius * Math.cos(angle));
        y = centerY + (int) (radius * Math.sin(angle));
    }
}
