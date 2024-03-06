package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell, 2, 10);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
