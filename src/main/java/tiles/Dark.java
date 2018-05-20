package tiles;

import client.ItemList;
import client.PlayerStats;
import server.Player;

public class Dark implements Tile {
    @Override
    public String enteredTile(PlayerStats stats, ItemList itemList, boolean[][] cordMatrix, Player player) {
        return "";
    }

    @Override
    public String getResourcePath() {
        return "TileSprites/dark.png";
    }

    @Override
    public String toString() {
        return "DarkTile";
    }

    @Override
    public void activate() {
    }
}
