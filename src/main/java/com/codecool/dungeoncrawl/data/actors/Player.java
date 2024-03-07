package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Sword;
import com.codecool.dungeoncrawl.data.mapObjects.Door;
import com.codecool.dungeoncrawl.ui.Tiles;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.items.TeleportCrystal;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();

    private boolean hasKey = false;
    private boolean hasSword = false;

    public Player(Cell cell) {
        super(cell, 5, 30);
    }

    public String getTileName() {
        if (this.hasSword){
            return "player+sword";
        } else {
        return "player";
        }
    }

    public List<Item> getInventory() {
        return inventory;
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getTileName().equals("wall") || nextCell.getActor() != null) {
            // Door
            if (nextCell.getDoor() != null && this.hasKey){
                nextCell.getDoor().setOpen();
            } else if (nextCell.getDoor() != null && this.hasSword){
                nextCell.getDoor().attackDoor();
                if (nextCell.getDoor().getBreakAttempts() == 5){
                    nextCell.getDoor().breakOpen();
                }
            }
            // Attack
            if (nextCell.getActor() instanceof Enemy) {
                System.out.println("Enemy found");
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
                        hasSword = true;
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
                for (Item item : inventory) {
                    if (item.getName().equals("teleport")) {
                        System.out.println("...teleporting...");
                        inventory.remove(item);
                        boolean teleporting = true;
                        while (teleporting) {
                            int randX = new Random().nextInt(19);
                            int randY = new Random().nextInt(5);
                            if (cell.getNeighbor(randX, randY).getTileName().equals("floor")) {
                                nextCell = cell.getNeighbor(randX, randY);
                                teleporting = false;
                            }
                        }
                        nextCell.setActor(this);
                        cell.setActor(null);
                        cell = nextCell;
                        break;
                    }
                }
            }
        }
    }
}
