package tiles;

import client.PlayerStats;

public class Tree implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/tree.png";
    }


    @Override
    public String enteredTile(PlayerStats player) {
        return "You see trees of green... red roses too.";
    }

    @Override
    public String toString() {
        return "TreeTile";
    }

    @Override
    public void Activate() {
    }
}
