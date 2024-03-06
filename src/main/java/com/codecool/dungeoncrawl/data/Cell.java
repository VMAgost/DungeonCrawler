package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.mapObjects.Door;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
    private Door door;
    private GameMap gameMap;
    private int x, y;

    public Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Item getItem() {
        return item;
    }

    public Door getDoor() {
        return door;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "type=" + type +
                ", actor=" + actor +
                ", item=" + item +
                ", gameMap=" + gameMap +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
