package tiles;

import inventory.*;
import client.ItemList;
import client.PlayerStats;
import server.Player;

public class House implements Tile {

    @Override
    public String getResourcePath() {
        return "TileSprites/house.png";
    }

    private int roomsUnchecked = 3;
    private boolean isActivated = false;

    @Override
    public String enteredTile(PlayerStats stats, ItemList itemList, boolean[][] cordMatrix, Player player) {
        String event = "";
        if (!isActivated) {
            int randomEvent = (int) Math.ceil(Math.random() * 3);
            switch (randomEvent) {
                case 1://trap
                    stats.setHealth(stats.getHealth() - 5);
                    event = "A shotgun triggered as you opened the door. Took 5 damage. " + (roomsUnchecked - 1) + " rooms unchecked.";
                    break;
                case 2://loot
                    Item loot = Chest.getItem();
                    itemList.addItem(loot, itemList.getItemGridPane(), stats, cordMatrix, player);
                    event = "You see Walmart bags in the corner. Found " + loot.getName() + "! " + (roomsUnchecked - 1) + " rooms unchecked.";
                    break;
                case 3://nothing
                    event = "Did not find anything from this room. " + (roomsUnchecked - 1) + " rooms unchecked.";
                    break;

            }
        } else {
            event = "This house has already been explored";
        }


        return event;

    }

    @Override
    public String toString() {
        return "HouseTile";
    }

    @Override
    public void activate() {
        if (!isActivated) {
            roomsUnchecked -= 1;
            if (roomsUnchecked == 0) {
                isActivated = true;
            }
        }
    }


}
