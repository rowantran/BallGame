package com.rowan.ballgame;

import java.util.List;

interface Level {
    int ballX();
    int ballY();

    List<Wall> walls();

    StartZone startZone();
    EndZone endZone();
}
