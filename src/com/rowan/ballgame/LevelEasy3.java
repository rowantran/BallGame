package com.rowan.ballgame;

import java.util.ArrayList;
import java.util.List;
public class LevelEasy3 implements Level{
    public int ballX() {
        return 380;
    }

    public int ballY() { return 80; }

    public List<Wall> walls() {
        List<Wall> wallList = new ArrayList<Wall>();

        wallList.add(new Wall(0, 0, 600, 40));
        wallList.add(new Wall(0, 40, 80, 560));
        wallList.add(new Wall(80, 40, 220, 100));
        wallList.add(new Wall(80, 520, 220, 80));
        wallList.add(new Wall(520, 40,280, 100));
        wallList.add(new Wall(520, 520,280, 80));
        wallList.add(new Wall(740, 140,60, 380));
        wallList.add(new Wall(200, 220,420, 220));


        return wallList;
    }

    public StartZone startZone() {
        return new StartZone(300, 40, 220, 100);
    }
}
