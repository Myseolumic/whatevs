package tiles;

import server.Player;

public class TrashedArea implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/wastedLand.png";
    }

    @Override
    public void enteredTile(Player player) {
        System.out.println(player.getName() + " entered trashed area");
    }

    @Override
    public String toString(){
        return "TrashedTile";
    }

}
