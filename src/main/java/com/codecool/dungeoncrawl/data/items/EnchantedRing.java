package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class EnchantedRing extends Item{
    public EnchantedRing(Cell cell, String name) {
        super(cell, name);
    }

    public String getTileName(){
        return "enchanted ring";
    }
}
