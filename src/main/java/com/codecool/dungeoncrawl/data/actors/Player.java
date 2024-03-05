package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();
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
            nextCell = cell;
        } else {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            if(nextCell.getItem() != null){
                inventory.add(nextCell.getItem());
                nextCell.setItem(null);
                System.out.println(inventory.get(inventory.size() -1 ).getName());
            }
        }
    }

}
