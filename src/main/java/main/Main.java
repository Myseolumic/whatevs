package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane client = new BorderPane();

        HBox topSection = new HBox();
        GridPane map = new GridPane();
        map.setMinHeight(640);
        map.setMinWidth(640);
        BorderPane topRight = new BorderPane();

        HBox inventory = new HBox();
        Rectangle inventor_1 = new Rectangle(80,80, Color.GREEN);
        Rectangle inventor_2 = new Rectangle(80,80, Color.BLUEVIOLET);
        Rectangle inventor_3 = new Rectangle(80,80, Color.BROWN);
        Rectangle inventor_4 = new Rectangle(80,80, Color.KHAKI);
        inventory.getChildren().addAll(inventor_1,inventor_2,inventor_3,inventor_4);
        topRight.setBottom(inventory);
        topSection.getChildren().addAll(map,topRight);

        TextArea textArea = new TextArea();
        TextField textField = new TextField();
        BorderPane midLeft = new BorderPane();
        midLeft.setTop(textArea);
        midLeft.setCenter(textField);

        GridPane midRight = new GridPane();
        Button left = new Button("LEFT");
        Button right = new Button("RIGHT");
        Button up = new Button("UP");
        Button down = new Button("DOWN");
        Buttons buttons = new Buttons(up,down,left,right);
        midRight.add(up,1,0);
        midRight.add(left,0,1);
        midRight.add(right,2,1);
        midRight.add(down, 1, 2);

        HBox middleSection = new HBox();
        middleSection.getChildren().addAll(midLeft,midRight);

        client.setTop(topSection);
        client.setCenter(middleSection);
        primaryStage.setTitle("CreepyPasta");
        primaryStage.setScene(new Scene(client));

        //insert all needed IO/other Threads
        new Thread(new ServerCommunicator(map,textArea,textField, buttons)).start();

        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
