package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.Random;

public abstract class  Enemy extends Actor {
    Random random = new Random();
    public Enemy(Cell cell, int attack, int health) {
        super(cell, attack, health);
    }
}
