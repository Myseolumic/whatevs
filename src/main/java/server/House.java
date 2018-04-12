package server;

import common.Player;

public class House implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/house.png";
    }

    @Override
    public void enteredTile(Player player) {

    }

    @Override
    public String toString(){
        return "HouseTile";
    }

}
