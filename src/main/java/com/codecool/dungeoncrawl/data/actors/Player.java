package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();

    private boolean hasKey = false;

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public List<Item> getInventory() {
        return inventory;
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getTileName().equals("wall") || nextCell.getActor() != null) {
            //Attack
            if (nextCell.getActor() instanceof Skeleton) {
                System.out.println("Skeleton found");
                nextCell.getActor().setHealth(nextCell.getActor().getHealth() - 5);
                System.out.println(nextCell.getActor().getHealth());
                if (nextCell.getActor().getHealth() <= 0) {
                    nextCell.setActor(null);   // Monster dies
                } else {
                    this.setHealth(this.getHealth() - 2);  // Player lose health
                    System.out.println(this.getHealth());
                }
            }
        } else {
            //Movement
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            if (nextCell.getItem() != null) {
                inventory.add(nextCell.getItem());
                nextCell.setItem(null);
                System.out.println(inventory.get(inventory.size() - 1).getName());
            }
        }
    }
}
