package tiles;
import client.ItemList;
import client.PlayerStats;
import server.Player;

public interface Tile {
    String enteredTile(PlayerStats stats, ItemList itemList, boolean[][] cordMatrix, Player player);

    String getResourcePath();

    void activate();

}
