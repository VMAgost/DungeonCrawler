package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.*;
import com.codecool.dungeoncrawl.data.items.EnchantedRing;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.data.items.Sword;
import com.codecool.dungeoncrawl.data.mapObjects.Door;
import com.codecool.dungeoncrawl.data.items.TeleportCrystal;
import com.codecool.dungeoncrawl.data.mapObjects.Frost;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell, "black key");
                            break;
                        case 'i':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell, "black sword");
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            new EnchantedRing(cell, "enchanted ring");
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            map.addEnemy(new Ghost(cell));
                            break;
                        case 'e':
                            cell.setType(CellType.FLOOR);
                            map.addEnemy(new EvilSorcerer(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'd':
                                cell.setType(CellType.WALL);
                                new Door(cell);
                            if (cell.getTileName().equals("open door")){
                                cell.setType(CellType.FLOOR);
                                new Door(cell);
                                break;
                            }
                            cell.setType(CellType.WALL);
                            new Door(cell);
                            break;
                        case 't':
                            cell.setType(CellType.FLOOR);
                            new TeleportCrystal(cell, "teleport");
                            break;
                        case 'f':
                            cell.setType(CellType.FLOOR);
                            new Frost(cell);
                            break;
                        case 'z':
                            cell.setType(CellType.FLOOR);
                            map.addEnemy(new Troll(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
