package tiles;

import client.ItemList;
import client.PlayerStats;
import server.Player;

public class TrashedArea implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/wastedLand.png";
    }

    @Override
    public String enteredTile(PlayerStats stats, ItemList itemList, boolean[][] cordMatrix, Player player) {
        stats.setHealth(stats.getHealth() - 5);
        return "You should not play with radioactive waste. Took 5 damage.";
    }

    @Override
    public String toString() {
        return "TrashedTile";
    }

    @Override
    public void activate() {
    }

}
