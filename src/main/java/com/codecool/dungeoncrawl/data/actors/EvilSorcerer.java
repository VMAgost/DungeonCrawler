package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class EvilSorcerer extends Actor {
    public EvilSorcerer(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "evilSorcerer";
    }
}
