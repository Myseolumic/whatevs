package server;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import client.Main;
import tiles.*;

public class Map {

    public static void visualizeMap(GridPane map, Tile[][] miniMap, boolean[][] cordMatrix) {
        Platform.runLater(() -> {
            map.getChildren().clear();

            for (int i = 0; i < miniMap.length; i++) {
                for (int j = 0; j < miniMap.length; j++) {
                    if (cordMatrix[i][j]) {
                        map.add(new ImageView(new Image(Main.class.getClassLoader().getResourceAsStream(miniMap[i][j].getResourcePath()))), i, j);
                    } else {
                        map.add(new ImageView(new Image(new Dark().getResourcePath())), i, j);
                    }
                }
            }
        });
    }

    public static void placePlayer(GridPane map, Player location) {
        Platform.runLater(() -> map.add(new ImageView(new Image(location.getResourcePath())), location.getX(), location.getY()));
    }

    public static Tile[][] stringToTiles(String[][] miniMap) {
        int size = miniMap.length;
        Tile[][] mapTiles = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mapTiles[i][j] = stringToTile(miniMap[i][j]);
            }
        }
        return mapTiles;
    }


    public static Tile stringToTile(String tileName) {
        switch (tileName) {
            case "DarkTile":
                return new Dark();
            case "HouseTile":
                return new House();
            case "TrapTile":
                return new Trap();
            case "TrashedTile":
                return new TrashedArea();
            case "TreeTile":
                return new Tree();
            case "ChestTile":
                return new Chest();
        }
        return null;
    }

    public static boolean[][] generateBoolMatrix(int size) {
        boolean[][] map = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = true;/// NEEDS TO BE FALSE
            }
        }
        return map;
    }


    public static Tile[][] generateMap(int size) {
        Tile[][] map = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                while (true) {
                    Tile[] events = new Tile[]{new House(), new Chest(), new Trap(), new Tree()};
                    int randomEvent = (int) Math.floor(Math.random() * events.length);
                    Tile chosen = events[randomEvent];
                    if (surroundCheck(map, chosen, i, j)) {
                        map[i][j] = chosen;
                        break;
                    } else if (chosen instanceof Tree) {
                        map[i][j] = chosen;
                        break;
                    }
                }
            }
        }


        return map;
    }

    public static Tile[][] reduceMapSize(Tile[][] map) {
        int currentReduction = 0;
        for (int i = 0; i < map.length / 2; i++) {
            if (map[i][i] instanceof TrashedArea) {
                currentReduction += 1;
            }
        }
        for (int i = 0; i < map.length; i++) {
            map[currentReduction][i] = new TrashedArea();
            map[map.length - currentReduction - 1][i] = new TrashedArea();
            map[i][currentReduction] = new TrashedArea();
            map[i][map.length - currentReduction - 1] = new TrashedArea();
        }

        return map;
    }

    public static boolean surroundCheck(Tile[][] map, Tile c, int i, int j) {
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if (i + k >= 0 && j + l >= 0 && i + k < map.length && j + l < map.length && map[i + k][j + l] != null) {
                    if (map[i + k][j + l].getClass().equals(c.getClass())) {
                        return true;
                    }
                }
            }
        }
        return true;
    }
}
