package tiles;

import Invenoty.Item;
import Invenoty.TestItem;
import client.ItemList;
import client.PlayerStats;
import javafx.scene.layout.GridPane;

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
            player.setDmg(player.getDmg()+ loot.getBonus());
            itemList.addItem(this,loot,itemList.getItemGridPane());
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
