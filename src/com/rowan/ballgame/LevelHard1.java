package com.rowan.ballgame;

import java.util.ArrayList;
import java.util.List;

public class LevelHard1 implements Level{
    public int ballX() {
        return 100;
    }

    public int ballY() { return 70;}

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

    public StartZone startZone() {
        return new StartZone(60, 60, 120, 60);
    }
}
