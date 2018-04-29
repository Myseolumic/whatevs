package tiles;
import client.ItemList;
import client.PlayerStats;

public interface Tile {
    String enteredTile(PlayerStats player, ItemList itemList);

    String getResourcePath();

    void activate();

}
