package tiles;

import server.Player;

public interface Tile {
    void enteredTile(Player player);
    String getResourcePath();
    boolean isActivated = false;
}
