package client;

import Invenoty.Item;
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
                placeItem(item,itemsGridPane,itemArray);
            }else {
                System.out.println("Su inventory on täis");
            }
    }
    private static void placeItem(Item item, GridPane itemsGridPane, List<Item> itemArray){
        Platform.runLater(()->{
            try {
                    itemsGridPane.add(new ImageView(new Image(item.getResourcePath())),itemArray.size()-1,0);
            }catch (NullPointerException ignored){

            }
        });
    }
    public void removeItem(Item item, GridPane itemsGridPane){
        if (itemArray.size() > 0){
            displaceItem(itemsGridPane,itemArray);
        }
    }
    private static void displaceItem( GridPane itemsGridPane, List<Item> itemArray){
        Platform.runLater(()->{
            try {
                itemsGridPane.add(new ImageView(new Image("TileSprites/inventorySlot.png")),itemArray.size()-1,0);
            } catch (Exception ignored){

            }
            itemArray.remove(itemArray.size()-1);
        });
    }

    public GridPane getItemGridPane() {
        return itemGridPane;
    }
    public Item getItem(){
        if (itemArray.size() > 0)
            return itemArray.get(itemArray.size()-1);
        return null;
    }
}
