package tiles;

import Invenoty.Item;
import Invenoty.TestItem;
import client.ItemList;
import client.PlayerStats;
import javafx.scene.layout.GridPane;

public class House implements Tile {

    @Override
    public String getResourcePath() {
        return "TileSprites/house.png";
    }
    private int roomsUnchecked = 3;
    private boolean isActivated = false;

    @Override
    public String enteredTile(PlayerStats player, ItemList itemList) {
        String event = "";
        if(!isActivated) {
            int randomEvent = (int) Math.ceil(Math.random() * 3);
            switch (randomEvent) {
                case 1://trap
                    player.setHealth(player.getHealth() - 5);
                    try {
                        itemList.getItem().removeBonus(player);
                        itemList.removeItem(itemList.getItem(),itemList.getItemGridPane());
                    }catch (Exception ignored){

                    }
                    event = "A shotgun triggered as you opened the door. Took 5 damage. "+ (roomsUnchecked-1) + " rooms unchecked.";
                    break;
                case 2://loot
                    Item loot = new TestItem();
                    loot.getBonus(player);
                    itemList.addItem(loot,itemList.getItemGridPane());
                    event = "You see Walmart bags in the corner. Found"+ loot.getName()+ " (roomsUnchecked-1) +  rooms unchecked.";
                    break;
                case 3://nothing
                    event = "Did not find anything from this room. " + (roomsUnchecked-1) + " rooms unchecked.";
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
    public void Activate() {
        if(!isActivated) {
            roomsUnchecked-=1;
            if(roomsUnchecked == 0) {
                isActivated = true;
            }
        }
    }


}
