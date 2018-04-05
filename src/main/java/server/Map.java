package server;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Main;

public class Map {

    public static void visualizeMap(GridPane map, Tile[][] miniMap) {
        for (int i = 0; i < miniMap.length; i++) {
            for (int j = 0; j < miniMap.length; j++) {
                map.add(new ImageView(new Image(Main.class.getClassLoader().getResourceAsStream(miniMap[i][j].getResourcePath()))), i, j);
            }
        }
    }

    public static Tile[][] generateMap(int size) {
        Tile[][] map = new Tile[size][size];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
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
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
