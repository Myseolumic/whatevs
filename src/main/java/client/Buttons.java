package client;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Buttons {
    private Button up;
    private Button down;
    private Button left;
    private Button right;
    private Button stop;
    private Button drop1;

    public Buttons(Button up, Button down, Button left, Button right, Button stop, Button drop1) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.stop = stop;
        this.drop1 = drop1;
    }

    public void init(TextArea target, Direction direction, ItemList itemList,PlayerStats player) {
        up.setOnAction((ActionEvent event) -> {
            target.appendText("You will move North next turn.\n");
            direction.setDirection("up");
        });
        down.setOnAction(event -> {
            target.appendText("You will move South next turn.\n");
            direction.setDirection("down");
        });
        right.setOnAction(event -> {
            target.appendText("You will move East next turn.\n");
            direction.setDirection("right");
        });
        left.setOnAction(event -> {
            target.appendText("You will move West next turn.\n");
            direction.setDirection("left");
        });
        stop.setOnAction(event -> {
            target.appendText("You will scout the area around you next turn.\n");
            direction.setDirection("stop");
        });
        drop1.setOnAction(event -> {
            target.appendText("You just dropped **** item.\n");
            itemList.removeItem(itemList.getItemGridPane(),player);
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
