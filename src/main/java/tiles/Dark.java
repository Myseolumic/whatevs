package tiles;

import client.PlayerStats;

public class Dark implements Tile {
    @Override
    public String enteredTile(PlayerStats player) {
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
