package tiles;

import server.Player;
import tiles.Tile;

public class Tree implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/tree.png";
    }


    @Override
    public void enteredTile(Player player) {

    }

    @Override
    public String toString(){
        return "TreeTile";
    }
}
