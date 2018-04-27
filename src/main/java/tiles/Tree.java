package tiles;

import client.PlayerStats;

public class Tree implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/tree.png";
    }


    @Override
    public void enteredTile(PlayerStats player) {
        System.out.println(player.getName() + " entered Tree area");
    }

    @Override
    public String toString(){
        return "TreeTile";
    }
}
