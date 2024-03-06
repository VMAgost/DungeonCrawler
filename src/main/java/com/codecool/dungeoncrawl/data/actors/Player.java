package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();

    private boolean hasKey = false;

    public Player(Cell cell) {
        super(cell, 5, 30);
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
            // Attack
            if (nextCell.getActor() instanceof Enemy) {
                System.out.println("Skeleton found");
                nextCell.getActor().setHealth(nextCell.getActor().getHealth() - this.attack);
                System.out.println(nextCell.getActor().getHealth());
                if (nextCell.getActor().getHealth() <= 0) {
                    nextCell.setActor(null);   // Monster dies
                } else {
                    this.setHealth(this.getHealth() - nextCell.getActor().attack);  // Player lose health
                    System.out.println(this.getHealth());
                }
            }
        } else {
            // Movement
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            if (nextCell.getItem() != null) {
                inventory.add(nextCell.getItem());
                nextCell.setItem(null);
                for (Item item : inventory) {
                    if (item.getName().equals("black sword")) {
                        this.setAttack(10);
                        System.out.println("Attack: " + this.attack);
                        break;
                    }
                }
                for (Item item : inventory) {
                    if (item.getName().equals("black key")) {
                        hasKey = true;
                        System.out.println("Now can open doors");
                        break;
                    }
                }
            }
        }
    }
}
