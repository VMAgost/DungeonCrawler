package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Enemy;
import com.codecool.dungeoncrawl.data.actors.EvilSorcerer;
import com.codecool.dungeoncrawl.data.actors.Ghost;
import com.codecool.dungeoncrawl.data.actors.Player;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    private Ghost ghost;

    private EvilSorcerer evilSorcerer;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }



    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public Ghost getGhost() {
        return ghost;
    }
    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }
    public EvilSorcerer getEvilSorcerer() {
        return evilSorcerer;
    }
    public void setEvilSorcerer(EvilSorcerer evilSorcerer) {
        this.evilSorcerer = evilSorcerer;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
