package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.Random;

public class Ghost extends Enemy {


    public Ghost(Cell cell) {
        super(cell, 4, 15);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public void move(int dx, int dy) {

        Random random = new Random();
        int direction = random.nextInt(4);

        if (this.getHealth() > 0) {
            switch (direction) {
                case 0:
                    super.move(-1, 0);
                    break;
                case 1:
                    super.move(1, 0);
                    break;
                case 2:
                    super.move(0, -1);
                    break;
                case 3:
                    super.move(0, 1);
                    break;
                default:
                    break;
            }
        }
    }
}
