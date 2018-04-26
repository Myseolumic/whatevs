package tiles;

import server.Player;

public class Trap implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/trap.png";
    }

    @Override
    public void enteredTile(Player player) {
        System.out.println(player.getName() + " Entered a Trap tile");
    }

    @Override
    public String toString(){
        return "TrapTile";
    }
}
