package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;

public class Troll extends Enemy {
    public Troll(Cell cell) {
        super(cell, 10, 30);
    }

    @Override
    public String getTileName() {
        return "troll";
    }

    @Override
    public void move() {
        Cell playerCell = cell.getGameMap().getPlayer().getCell();
        int distanceX = Math.abs(cell.getX() - playerCell.getX());
        int distanceY = Math.abs(cell.getY() - playerCell.getY());
        int totalDistance = distanceX + distanceY;

        if (totalDistance <= 5) {
            int moveX = Integer.compare(playerCell.getX(), cell.getX());
            int moveY = Integer.compare(playerCell.getY(), cell.getY());

            Cell nextCell = cell.getNeighbor(moveX, moveY);
            if (!nextCell.getType().equals(CellType.WALL) && nextCell.getActor() == null) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }
        }
    }
}