package tiles;

import inventory.*;
import client.ItemList;
import client.PlayerStats;
import server.Player;

public class Chest implements Tile {
    private boolean isActivated = false;

    @Override
    public String getResourcePath() {
        return "TileSprites/chest.png";
    }

    static Item getItem() {
        int randomEvent = (int) Math.ceil(Math.random() * 3);
        Item loot;
        if (randomEvent == 1)
            loot = new Lantern();
        else if (randomEvent == 2)
            loot = new Pistol();
        else
            loot = new Kevlar();
        return loot;
    }

    @Override
    public String enteredTile(PlayerStats stats, ItemList itemList, boolean[][] cordMatrix, Player player) {
        if (!isActivated) {
            Item loot = Chest.getItem();
            itemList.addItem(loot, itemList.getItemGridPane(), stats, cordMatrix, player);
            return "Oh sweet baby jesus! Someone left their goods behind. You found " + loot.getName() + ".";
        } else {
            return "You see before you a ravaged bag of goodies. It's empty.";
        }
    }

    @Override
    public String toString() {
        return "ChestTile";
    }

    @Override
    public void activate() {
        isActivated = true;
    }

}
