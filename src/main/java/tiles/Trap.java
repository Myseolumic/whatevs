package tiles;

import client.PlayerStats;

public class Trap implements Tile {
    private boolean isActivated = false;

    @Override
    public String getResourcePath() {
        return "TileSprites/trap.png";
    }

    @Override
    public String enteredTile(PlayerStats player) {
        if (!isActivated) {
            player.setHealth(player.getHealth() - 5);
            return "You have stumbled upon a queer looking contraption. Took 5 damage.";
        } else {
            return "You see a pile of blood on the ground. Someone got fucked up here";
        }
    }

    @Override
    public String toString() {
        return "TrapTile";
    }

    @Override
    public void Activate() {
        isActivated = true;
    }
}
