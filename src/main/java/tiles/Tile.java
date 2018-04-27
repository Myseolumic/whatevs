package tiles;

import client.PlayerStats;

public interface Tile {
    void enteredTile(PlayerStats player);
    String getResourcePath();
    boolean isActivated = false;
}
