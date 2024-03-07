package com.codecool.dungeoncrawl.data.mapObjects;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public class Frost implements Drawable {

    private Cell cell;

    public Frost(Cell cell){
        this.cell = cell;
        this.cell.setFrost(this);
    }

    @Override
    public String getTileName() {
        return "frost";
    }


}
