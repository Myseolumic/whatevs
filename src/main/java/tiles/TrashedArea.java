package tiles;

import client.PlayerStats;

public class TrashedArea implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/wastedLand.png";
    }

    @Override
    public void enteredTile(PlayerStats player) {
        System.out.println(player.getName() + " entered trashed area");
    }

    @Override
    public String toString(){
        return "TrashedTile";
    }

}
