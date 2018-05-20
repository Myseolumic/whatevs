package tiles;
import client.ItemList;
import client.PlayerStats;
import server.Player;

public class Tree implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/tree.png";
    }


    @Override
    public String enteredTile(PlayerStats stats, ItemList itemList, boolean[][] cordMatrix, Player player) {
        return "You see trees of green... red roses too.";
    }

    @Override
    public String toString() {
        return "TreeTile";
    }

    @Override
    public void activate() {
    }
}
