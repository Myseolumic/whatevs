package server;

public class Map {
    public static void main(String[] args) {
        Tile[][] mapike = generateMap(16);
        displayMap(mapike);
        reduceMapSize(mapike);
        displayMap(mapike);

    }

    private static void displayMap(Tile[][] mapike) {
        for (int i = 0; i < mapike.length; i++) {
            for (int j = 0; j < mapike.length; j++) {
                System.out.print("[" + mapike[i][j].getSymbol() + "]");
            }
            System.out.println();
        }
    }

    public static Tile[][] generateMap(int size) {
        Tile[][] map = new Tile[size][size];
        // ⇞  - Tree(no event)
        // ▦ - chest(random loot)
        // ◎ - Trap(get dmg)
        // ▲ - House(random event)
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                while (true) {
                    Tile[] events = new Tile[]{new House(), new Chest(), new Trap(), new Tree()};
                    int randomEvent = (int) Math.floor(Math.random() * events.length);
                    Tile chosen = events[randomEvent];
                    if (surroundCheck(map, chosen, i, j)) {
                        map[i][j] = chosen;
                        break;
                    } else if (chosen.getSymbol() == '⇞') {
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
            if (map[i][i].getSymbol() == 'X') {
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

    private static boolean surroundCheck(Tile[][] map, Tile c, int i, int j) {
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if (i + k >= 0 && j + l >= 0 && i + k < map.length && j + l < map.length && map[i + k][j + l] != null) {
                    if (map[i + k][j + l].getSymbol() == c.getSymbol()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
