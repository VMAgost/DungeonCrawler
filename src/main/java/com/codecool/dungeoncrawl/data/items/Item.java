package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public  abstract class Item implements Drawable {
    private final String name;
    private Cell cell;

    public Item(Cell cell, String name) {
        this.cell = cell;
        this.name = name;
        this.cell.setItem(this);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", cell=" + cell +
                '}';
    }

    public String getName() {
        return name;
    }

    public Cell getCell() {
        return cell;
    }
}
