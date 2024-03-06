package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Ghost extends Enemy {
    public Ghost(Cell cell) {
        super(cell,4, 15);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
