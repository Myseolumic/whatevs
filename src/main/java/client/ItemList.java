package client;

import inventory.Item;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private List<Item> itemArray = new ArrayList<>();
    private GridPane itemGridPane;

    public ItemList(GridPane itemGridPane) {
        this.itemGridPane = itemGridPane;
    }

    public void addItem(Item item, GridPane itemsGridPane) {
        if (itemArray.size() < 5) {
            itemArray.add(item);
            placeItem(item, itemsGridPane, itemArray);
        } else {
            System.out.println("Su inventory on täis");
        }
    }

    private static void placeItem(Item item, GridPane itemsGridPane, List<Item> itemArray) {
        Platform.runLater(() -> {
            itemsGridPane.add(new ImageView(new Image(item.getResourcePath())), itemArray.size() - 1, 0);
        });
    }


    public void removeItem(GridPane itemsGridPane) {
        if (itemArray.size() > 0) {
            displaceItem(itemsGridPane, itemArray);
        }
    }

    private static void displaceItem(GridPane itemsGridPane, List<Item> itemArray) {
        Platform.runLater(() -> {
            itemsGridPane.add(new ImageView(new Image("TileSprites/inventorySlot.png")), itemArray.size() - 1, 0);
            itemArray.remove(itemArray.size() - 1);
        });
    }

    public GridPane getItemGridPane() {
        return itemGridPane;
    }

    public Item getItem() {
            return itemArray.get(itemArray.size() - 1);
    }

    public int getSize() {
        return itemArray.size();
    }
}
