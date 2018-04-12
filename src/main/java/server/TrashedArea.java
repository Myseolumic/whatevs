package server;

import common.Player;

public class TrashedArea implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/wastedLand.png";
    }

    @Override
    public void enteredTile(Player player) {

    }

    @Override
    public String toString(){
        return "TrashedTile";
    }

}
