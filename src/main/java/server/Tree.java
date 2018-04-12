package server;

import common.Player;

public class Tree implements Tile{
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
