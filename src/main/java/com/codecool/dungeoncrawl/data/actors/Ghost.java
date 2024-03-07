package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.Random;

public class Ghost extends Enemy {


    public Ghost(Cell cell) {
        super(cell, 4, 15);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public void move(int dx, int dy) {
        Random random = new Random();
        int newX, newY;
        Cell newCell;

        do {
            int randomX = random.nextInt(3) - 1;
            int randomY = random.nextInt(3) - 1;

            newX = cell.getX() + randomX;
            newY = cell.getY() + randomY;

            newX = Math.max(0, Math.min(cell.getGameMap().getWidth() - 1, newX));
            newY = Math.max(0, Math.min(cell.getGameMap().getHeight() - 1, newY));

            newCell = cell.getGameMap().getCell(newX, newY);
        } while (newCell == null);

        cell.setActor(null);
        newCell.setActor(this);
        cell = newCell;
    }



}
