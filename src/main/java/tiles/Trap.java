package tiles;

import server.Player;
import tiles.Tile;

public class Trap implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/trap.png";
    }

    @Override
    public void enteredTile(Player player) {

    }

    @Override
    public String toString(){
        return "TrapTile";
    }
}
