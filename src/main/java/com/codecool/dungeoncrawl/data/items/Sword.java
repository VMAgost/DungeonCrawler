package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Sword extends Item{
    public Sword(Cell cell, String name) {
        super(cell, name);
    }

    public String getTileName(){
        return "black sword";
    }
}
