package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.items.Item;

public class GameLogic {
    public Object getPlayerMaxHealth;
    private GameMap map;

    public GameLogic() {
        this.map = MapLoader.loadMap();
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }

    public String getPlayerMaxHealth() {
        return Integer.toString(map.getPlayer().getMaxHealth());
    }

    public String getPlayerInventory(){
        StringBuilder inventoryItems = new StringBuilder();
        for(Item item : map.getPlayer().getInventory()){
            inventoryItems.append(item.getName()).append("\n");
        }
        return inventoryItems.toString();
    };


    public GameMap getMap() {
        return map;
    }
}
