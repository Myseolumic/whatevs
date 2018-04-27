package tiles;

import client.PlayerStats;

public interface Tile {
    String enteredTile(PlayerStats player);

    String getResourcePath();

    void Activate();


}
