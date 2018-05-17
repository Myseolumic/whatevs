package client;

import inventory.Item;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private Item[] itemArray = new Item[4];
    private GridPane itemGridPane;

    public ItemList(GridPane itemGridPane) {
        this.itemGridPane = itemGridPane;
    }

    private static void placeItem(Item item, GridPane itemsGridPane, int i) {
        Platform.runLater(() -> {
            itemsGridPane.add(new ImageView(new Image(item.getResourcePath())), i, 0);
        });
    }

    private static void displaceItem(GridPane itemsGridPane, Item [] itemArray, int place) {
        Platform.runLater(() -> {
            itemsGridPane.add(new ImageView(new Image("TileSprites/inventorySlot.png")),place, 0);
            itemArray[place] = null;
        });
    }

    public void addItem(Item item, GridPane itemsGridPane, PlayerStats player) {
        for (int i = 0; i < itemArray.length; i++) {
            System.out.println(itemArray[i]);
            if (itemArray[i] == null){
                itemArray[i] = item;
                item.getBonus(player);
                placeItem(item, itemsGridPane, i);
                break;
            }
        }
    }

    public void removeItem(GridPane itemsGridPane, PlayerStats player, int place) {
        if (itemArray[place] != null) {
            displaceItem(itemsGridPane, itemArray, place);
            itemArray[place].removeBonus(player);
        }
    }

    public GridPane getItemGridPane() {
        return itemGridPane;
    }

    public int getSize() {
        return itemArray.length;
    }
    public Item[] getItemArray(){
        return itemArray;
    }
}
