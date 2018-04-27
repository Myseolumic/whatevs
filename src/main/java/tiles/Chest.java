package tiles;

import client.PlayerStats;

public class Chest implements Tile {
    private boolean isActivated = false;

    @Override
    public String getResourcePath() {
        return "TileSprites/chest.png";
    }

    @Override
    public String enteredTile(PlayerStats player) {
        if (!isActivated) {
            //add new item to inventory
            return "Oh sweet baby jesus! Someone left their goods behind. Now they are yours.";
        } else {
            return "You see before you a ravaged bag of goodies. It's empty.";
        }


    }

    @Override
    public String toString() {
        return "ChestTile";
    }

    @Override
    public void Activate() {
        isActivated = true;
    }


}
