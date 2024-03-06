package com.codecool.dungeoncrawl.data.mapObjects;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;

public class Door implements Drawable {

    private boolean isOpen = false;

    private String name = "closed door";

    private Cell cell;

    public Door(Cell cell){
        this.cell = cell;
        this.cell.setDoor(this);
    };

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTileName() {
        return name;
    }

    public void setOpen() {
        System.out.println("You opened the door");
        isOpen = true;
        name = "open door";
        cell.setType(CellType.FLOOR);
    }
}
