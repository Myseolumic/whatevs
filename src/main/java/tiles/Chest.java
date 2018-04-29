package tiles;

import inventory.Item;
import inventory.TestItem;
import client.ItemList;
import client.PlayerStats;

public class Chest implements Tile {
    private boolean isActivated = false;

    @Override
    public String getResourcePath() {
        return "TileSprites/chest.png";
    }

    @Override
    public String enteredTile(PlayerStats player, ItemList itemList) {
        if (!isActivated) {
            Item loot = new TestItem();
            itemList.addItem(loot,itemList.getItemGridPane(),player);
            return "Oh sweet baby jesus! Someone left their goods behind. You found "+loot.getName()+".";
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
