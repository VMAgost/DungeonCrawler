package com.codecool.dungeoncrawl.data.mapObjects;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public class Door implements Drawable {

    private boolean isOpen = false;

    private Cell cell;

    public Door(Cell cell){
        this.cell = cell;
        this.cell.setDoor(this);
    };

    @Override
    public String getTileName() {
        return "door";
    }

    public void setOpen() {
        isOpen = true;
    }
}
