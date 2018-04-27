package client;

import Invenoty.Item;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import tiles.Tile;
import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private List<Item> itemArray = new ArrayList<>();
    private GridPane itemGridPane;

    public ItemList(GridPane itemGridPane) {
        this.itemGridPane = itemGridPane;
    }

    public void addItem(Tile tile, Item item, GridPane map) {
            if (itemArray.size() < 5) {
                itemArray.add(item);
                placeItem(item,map,itemArray);
            }else {
                System.out.println("Su inventory on täis");
            }
    }
    public static void placeItem(Item item, GridPane map, List<Item> itemArray){
        Platform.runLater(()->{
            try {
                    map.add(new ImageView(new Image(item.getResourcePath())),itemArray.size()-1,0);
            }catch (NullPointerException e){

            }
        });
    }

    public GridPane getItemGridPane() {
        return itemGridPane;
    }
}
