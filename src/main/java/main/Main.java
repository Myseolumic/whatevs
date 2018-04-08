package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import server.Map;
import server.Moveable;
import server.Tile;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane client = new BorderPane();
        HBox üleval = new HBox();
        GridPane map = new GridPane();
        Tile[][] miniMap = Map.generateMap(16);
        //Map.reduceMapSize(miniMap);
        Moveable[][] characters = Map.generatePlayersAndAI(miniMap, 4);
        Map.visualizeMap(map, miniMap, characters);

        BorderPane paremal = new BorderPane();
        HBox inventory = new HBox();
        Rectangle inventor_1 = new Rectangle(80,80, Color.GREEN);
        Rectangle inventor_2 = new Rectangle(80,80, Color.BLUEVIOLET);
        Rectangle inventor_3 = new Rectangle(80,80, Color.BROWN);
        Rectangle inventor_4 = new Rectangle(80,80, Color.KHAKI);


        TextArea textArea = new TextArea();
        TextField textField = new TextField();

        inventory.getChildren().addAll(inventor_1,inventor_2,inventor_3,inventor_4);
        paremal.setBottom(inventory);

        üleval.getChildren().addAll(map,paremal);
        client.setTop(üleval);
        client.setCenter(textArea);
        client.setBottom(textField);
        primaryStage.setTitle("Forest battle arena");
        primaryStage.setScene(new Scene(client, 1000, 800));

        //insert all needed IO/other Threads

        new Thread(new ServerCommunicator(textArea,textField)).start();

        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
