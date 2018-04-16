package tiles;

import server.Player;

public class Dark implements Tile {
    @Override
    public void enteredTile(Player player) {

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
