package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.mapObjects.Door;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player extends Actor {
    private int maxHealth;

    private final List<Item> inventory;

    private boolean hasKey = false;

    public Player(Cell cell) {
        super(cell, 5, 30);
        this.maxHealth = 30;
        this.inventory = new ArrayList<>();
    }

    private boolean hasSword = false;

    public String getTileName() {
        if (this.hasSword) {
            return "player+sword";
        } else {
            return "player";
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public List<Item> getInventory() {
        return inventory;
    }


    public void move(int dx, int dy) {

        if (cell.getActor().getHealth() >= 1) {
            // move
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (nextCell.getTileName().equals("wall") || nextCell.getActor() != null) {
                // Door
                interactWithDoor(nextCell);
                // Attack

                if (nextCell.getActor() instanceof Enemy) {
                    Enemy enemy = (Enemy) nextCell.getActor();
                    attackEnemy(enemy);
                }

            } else {
                // FrostDamage
                if (nextCell.getFrost() != null) {
                    cell.getActor().setHealth(cell.getActor().getHealth() - 1);
                }
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
                        }
                        if (item.getName().equals("enchanted ring")) {
                            // Double the current health and max health
                            this.setHealth(this.getHealth() * 2);
                            this.setMaxHealth(this.getMaxHealth() * 2);
                            System.out.println(this.getHealth());
                            System.out.println(this.getMaxHealth());
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

        } else {
            return;
        }

        if (cell.getActor().getHealth() < 1) {
            cell.setActor(null);
            cell.setDeadPlayer(new DeadPlayer(cell));
        }
    }
}
