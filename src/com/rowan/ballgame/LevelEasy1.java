package com.rowan.ballgame;

import java.util.ArrayList;
import java.util.List;

public class LevelEasy1 implements Level {
    public List<Wall> walls() {
        List<Wall> wallList = new ArrayList<Wall>();

        wallList.add(new Wall(20, 20, 30, 100));
        wallList.add(new Wall(150, 20, 30, 100));

        return wallList;
    }
}
