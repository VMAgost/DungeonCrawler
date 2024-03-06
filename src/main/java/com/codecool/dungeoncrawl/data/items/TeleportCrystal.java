package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class TeleportCrystal extends Item{
    public TeleportCrystal(Cell cell, String name) {
        super(cell, name);
    }

    public String getTileName(){
        return "teleport";
    }
}
