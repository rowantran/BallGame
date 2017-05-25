package com.rowan.ballgame;

import java.util.List;

interface Level {
    int ballX();
    int ballY();

    List<Wall> walls();
    List<MovingBall> balls();

    StartZone startZone();
    EndZone endZone();
}
