package tiles;

import client.PlayerStats;

public class Dark implements Tile {
    @Override
    public void enteredTile(PlayerStats player) {

    }

    @Override
    public String getResourcePath() {
        return "TileSprites/dark.png";
    }

    @Override
    public String toString(){
        return "DarkTile";
    }
}
