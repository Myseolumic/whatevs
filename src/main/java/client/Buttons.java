package client;

import common.Direction;
import common.DirectionHolder;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Buttons {
    private Button drop4;
    private Button drop3;
    private Button drop2;
    private Button up;
    private Button down;
    private Button left;
    private Button right;
    private Button stop;
    private Button drop1;

    public Buttons(Button up, Button down, Button left, Button right, Button stop, Button drop1,Button drop2, Button drop3, Button drop4) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.stop = stop;
        this.drop1 = drop1;
        this.drop2 = drop2;
        this.drop3 = drop3;
        this.drop4 = drop4;
    }

    public void init(TextArea target, DirectionHolder directionHolder, ItemList itemList, PlayerStats player) {
        up.setOnAction((ActionEvent event) -> {
            target.appendText("You will move North next turn.\n");
            directionHolder.setDirection(Direction.UP);
        });
        down.setOnAction(event -> {
            target.appendText("You will move South next turn.\n");
            directionHolder.setDirection(Direction.DOWN);
        });
        right.setOnAction(event -> {
            target.appendText("You will move East next turn.\n");
            directionHolder.setDirection(Direction.RIGHT);
        });
        left.setOnAction(event -> {
            target.appendText("You will move West next turn.\n");
            directionHolder.setDirection(Direction.LEFT);
        });
        stop.setOnAction(event -> {
            target.appendText("You will scout the area around you next turn.\n");
            directionHolder.setDirection(Direction.STOP);
        });
        drop1.setOnAction(event -> {
            if (itemList.getItemArray()[0] != null) {
                target.appendText("You just dropped **** item.\n");
                itemList.removeItem(itemList.getItemGridPane(), player,0);
            }
        });
        drop2.setOnAction(event -> {
            if (itemList.getItemArray()[1] != null) {
                target.appendText("You just dropped **** item.\n");
                itemList.removeItem(itemList.getItemGridPane(), player,1);
            }
        });
        drop3.setOnAction(event -> {
            if (itemList.getItemArray()[2] != null) {
                target.appendText(itemList.getSize()+ "");
                target.appendText("You just dropped **** item.\n");
                itemList.removeItem(itemList.getItemGridPane(), player,2);
            }
        });
        drop4.setOnAction(event -> {
            if (itemList.getItemArray()[3] != null) {
                target.appendText("You just dropped **** item.\n");
                itemList.removeItem(itemList.getItemGridPane(), player,3);
            }
        });
    }

    public void enableAll() {
        up.disableProperty().set(false);
        down.disableProperty().set(false);
        left.disableProperty().set(false);
        right.disableProperty().set(false);
    }

    public void disableUp() {
        up.disableProperty().set(true);
    }

    public void disableDown() {
        down.disableProperty().set(true);
    }

    public void disableLeft() {
        left.disableProperty().set(true);
    }

    public void disableRight() {
        right.disableProperty().set(true);
    }
}
