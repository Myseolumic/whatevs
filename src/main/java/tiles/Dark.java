package tiles;

import client.ItemList;
import client.PlayerStats;
import javafx.scene.layout.GridPane;

public class Dark implements Tile {
    @Override
    public String enteredTile(PlayerStats player, ItemList itemList) {
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
    public void Activate() {
    }
}
