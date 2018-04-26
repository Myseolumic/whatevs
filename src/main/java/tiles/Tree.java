package tiles;

import server.Player;

public class Tree implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/tree.png";
    }


    @Override
    public void enteredTile(Player player) {
        System.out.println(player.getName() + " entered Tree area");
    }

    @Override
    public String toString(){
        return "TreeTile";
    }
}
