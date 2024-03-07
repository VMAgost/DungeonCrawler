package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public class DeadPlayer extends Actor {

    private Cell cell;

    public DeadPlayer(Cell cell){
       super(cell,0,0);
    }
    @Override
    public String getTileName() {
        return "dead";
    }

}
