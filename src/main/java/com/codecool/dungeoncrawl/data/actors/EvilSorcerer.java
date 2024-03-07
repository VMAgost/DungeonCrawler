package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.Random;

public class EvilSorcerer extends Enemy {
    public EvilSorcerer(Cell cell) {
        super(cell, 6, 20);
    }

    @Override
    public String getTileName() {
        return "evilSorcerer";
    }

    @Override
    public void move(int dx, int dy) {
        Random random = new Random();
        int x, y;
        Cell newCell;

        do {
            x = random.nextInt(cell.getGameMap().getWidth());
            y = random.nextInt(cell.getGameMap().getHeight());

            newCell = cell.getGameMap().getCell(x, y);
        } while (newCell.getActor() != null || !newCell.getTileName().equals("floor"));

        cell.setActor(null);
        newCell.setActor(this);
        cell = newCell;
    }

}

