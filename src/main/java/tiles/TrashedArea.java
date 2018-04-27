package tiles;

import client.PlayerStats;

public class TrashedArea implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/wastedLand.png";
    }

    @Override
    public String enteredTile(PlayerStats player) {
        player.setHealth(player.getHealth() - 5);
        return "You should not play with radioactive waste. Took 5 damage.";
    }

    @Override
    public String toString() {
        return "TrashedTile";
    }

    @Override
    public void Activate() {
    }

}
