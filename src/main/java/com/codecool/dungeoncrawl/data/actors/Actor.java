package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health ;
    protected int attack;

    public Actor(Cell cell, int attack, int health) {
        this.cell = cell;
        this.cell.setActor(this);
        this.attack = attack;
        this.health = health;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getY() {
        return cell.getY();
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
