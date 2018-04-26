package tiles;

import server.Player;

public class Chest implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/chest.png";
    }

    @Override
    public void enteredTile(Player player) {
        System.out.println(player.getName() + " entered chest area");
    }

    @Override
    public String toString(){
        return "ChestTile";
    }

}
