package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.Random;

public class Ghost extends Enemy {
    public Ghost(Cell cell) {
        super(cell,4, 15);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public void move(int dx, int dy) {
        Random random = new Random();
        int direction = random.nextInt(4);

        switch (direction) {
            case 0: // Move left
                super.move(-1, 0);
                break;
            case 1: // Move right
                super.move(1, 0);
                break;
            case 2: // Move up
                super.move(0, -1);
                break;
            case 3: // Move down
                super.move(0, 1);
                break;
            default:
                break;
        }
    }
}

