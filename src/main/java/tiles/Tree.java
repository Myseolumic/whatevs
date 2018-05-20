package tiles;

import client.ItemList;
import client.PlayerStats;
import inventory.Item;

public class Tree implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/tree.png";
    }


    @Override
    public String enteredTile(PlayerStats player, ItemList itemList) {
        String event = "";
        int randomEvent = (int) Math.ceil(Math.random() * 5);
        switch (randomEvent) {
            case 1: // bear
                int result = (int) Math.ceil(Math.random() * 2);
                if (result == 1) {
                    player.setDefence(player.getDefence() + 1);
                    event = "You found a bear in the forest and you whooped his butt. \nTestosterone levels are through the roof. Defence +1";
                } else {
                    player.setDefence(player.getDefence() - 1);
                    event = "\"You found a bear in the forest and ran like a coward. \nTestosterone levels are sad. Defence -1";
                }
                break;
            case 2:
                player.setMaxHealth(player.getMaxHealth() + 1);
                event = "You found a partner and had a bloody good time. \nFeelin like a baller. Max hp+1";
                break;
            case 3: //naturelover
                player.setHealth(player.getHealth() + 3);
                event = "You found a hippie hugging a tree. \nHe gave you food. Regained 3 hp";
                break;
            case 4://hunter
                Item loot = Chest.getItem();
                itemList.addItem(loot, itemList.getItemGridPane(), player);
                int damage = (int) Math.ceil(Math.random() * 8) + 1;
                player.setHealth(player.getHealth() - damage);
                if (player.isAlive()) {
                    event = "A hunter shot you for " + damage + " damage as you were about to pee...\nYou found " + loot.getName() + " from his corpse";
                } else {
                    event = "A hunter killed you";
                }
                break;
            case 5: //nothing
                event = "You see trees of green... red roses too.";
                break;

        }
        return event;

    }

    @Override
    public String toString() {
        return "TreeTile";
    }


    @Override
    public void activate() {
    }
}
