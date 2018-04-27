package tiles;

import client.PlayerStats;

public class Trap implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/trap.png";
    }

    @Override
    public void enteredTile(PlayerStats player) {
        player.setHealth(player.getHealth()-5);
        System.out.println(player.getHealth());
    }

    @Override
    public String toString(){
        return "TrapTile";
    }
}
