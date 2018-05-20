package tiles;

import client.ItemList;
import client.PlayerStats;
import inventory.Item;
import server.Player;

public class Tree implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/tree.png";
    }


    @Override
    public String enteredTile(PlayerStats stats, ItemList itemList, boolean[][] cordMatrix, Player player) {
        String event = "";
        int randomEvent = (int) Math.ceil(Math.random() * 5);
        switch (randomEvent) {
            case 1: // bear
                int result = (int) Math.ceil(Math.random() * 2);
                if (result == 1) {
                    stats.setDefence(stats.getDefence() + 1);
                    event = "You found a bear in the forest and you whooped his butt. \nTestosterone levels are through the roof. Defence +1";
                } else {
                    stats.setDefence(stats.getDefence() - 1);
                    event = "\"You found a bear in the forest and ran like a coward. \nTestosterone levels are sad. Defence -1";
                }
                break;
            case 2:
                stats.setMaxHealth(stats.getMaxHealth() + 1);
                event = "You found a partner and had a bloody good time. \nFeelin like a baller. Max hp+1";
                break;
            case 3: //naturelover
                stats.setHealth(stats.getHealth() + 3);
                event = "You found a hippie hugging a tree. \nHe gave you food. Regained 3 hp";
                break;
            case 4://hunter
                Item loot = Chest.getItem();
                itemList.addItem(loot, itemList.getItemGridPane(), stats, cordMatrix, player);
                int damage = (int) Math.ceil(Math.random() * 8) + 1;
                stats.setHealth(stats.getHealth() - damage);
                if (stats.isAlive()) {
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
