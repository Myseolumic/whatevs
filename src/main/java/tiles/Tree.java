package tiles;
import client.ItemList;
import client.PlayerStats;
import javafx.scene.layout.GridPane;

public class Tree implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/tree.png";
    }


    @Override
    public String enteredTile(PlayerStats player, ItemList itemList) {
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
