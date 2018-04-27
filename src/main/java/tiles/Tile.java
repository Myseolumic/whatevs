package tiles;
import client.ItemList;
import client.PlayerStats;
import javafx.scene.layout.GridPane;

public interface Tile {
    String enteredTile(PlayerStats player, ItemList itemList);

    String getResourcePath();

    void Activate();

}
