package main;

import javafx.scene.control.Button;

public class Buttons {
    private Button up;
    private Button down;
    private Button left;
    private Button right;

    public Buttons(Button up, Button down, Button left, Button right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public void disableUp(){
        up.disableProperty().set(true);
    }

    public void disableDown(){
        down.disableProperty().set(true);
    }

    public void disableLeft(){
        left.disableProperty().set(true);
    }

    public void disableRight(){
        right.disableProperty().set(true);
    }
}
