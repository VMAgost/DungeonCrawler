package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Key extends Item{
    public Key(Cell cell, String name) {
        super(cell, name);
    }

    public String getTileName(){
        return "black key";
    }
}
