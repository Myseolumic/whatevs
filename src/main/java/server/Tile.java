package server;

import common.Player;

public interface Tile {
    void enteredTile(Player player);
    String getResourcePath();
}
