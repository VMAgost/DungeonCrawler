package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class  Enemy extends Actor {
    public Enemy(Cell cell, int attack, int health) {
        super(cell, attack, health);
    }
}
