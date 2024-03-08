package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.mapObjects.Door;
import com.codecool.dungeoncrawl.logic.SoundPlayer;

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

    private void attackEnemy(Enemy enemy) {
        enemy.setHealth(enemy.getHealth() - this.attack);
        if (hasSword) {
            SoundPlayer.playSound("src/main/resources/swordhit2.wav");
        }else{
            SoundPlayer.playSound("src/main/resources/punch.wav");
        }
        if (enemy.getHealth() <= 0) {
            enemy.getCell().setActor(null);
            SoundPlayer.playSound("src/main/resources/monsterdeath.wav");
        } else {
            this.setHealth(this.getHealth() - enemy.attack);
            SoundPlayer.playSound("src/main/resources/playerhurt.wav");
        }
    }

    private void interactWithDoor(Cell nextCell) {
        Door door = nextCell.getDoor();
        if (door != null) {
            if (this.hasKey) {
                door.setOpen();
                SoundPlayer.playSound("src/main/resources/opendoor.wav");
            } else if (this.hasSword) {
                door.attackDoor();
                if (door.getBreakAttempts() == 5) {
                    door.breakOpen();
                    SoundPlayer.playSound("src/main/resources/doorbreak.wav");
                }
            }
        }
    }

    private void pickupItem(Cell nextCell) {
        if (nextCell.getItem() != null) {
            Item item = nextCell.getItem();
            inventory.add(item);
            nextCell.setItem(null);

            switch (item.getName()) {
                case "black sword":
                    hasSword = true;
                    this.setAttack(10);
                    SoundPlayer.playSound("src/main/resources/swordpickup.wav");
                    break;
                case "enchanted ring":
                    // Double the current health and max health
                    this.setHealth(this.getHealth() * 2);
                    this.setMaxHealth(this.getMaxHealth() * 2);
                    SoundPlayer.playSound("src/main/resources/hpring.wav");
                    break;
                case "black key":
                    hasKey = true;
                    SoundPlayer.playSound("src/main/resources/key.wav");
                    break;
                case "teleport":
                    inventory.remove(item);
                    SoundPlayer.playSound("src/main/resources/teleport.wav");
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
                default:
                    break;
            }
        }
    }


    public void move(int dx, int dy) {
        if (cell.getActor().getHealth() < 1) {
            return;
        }
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
            // Pick up item
            pickupItem(nextCell);
        }
        if (cell.getActor().getHealth() < 1) {
            SoundPlayer.playSound("src/main/resources/playerdead.wav");
            cell.setActor(null);
            cell.setDeadPlayer(new DeadPlayer(cell));
        }
    }
}
