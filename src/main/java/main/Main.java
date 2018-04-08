package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import server.Map;
import server.Moveable;
import server.Tile;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane client = new BorderPane();
        GridPane map = new GridPane();
        Tile[][] miniMap = Map.generateMap(16);
        //Map.reduceMapSize(miniMap);
        Moveable[][] characters = Map.generatePlayersAndAI(miniMap, 4);
        Map.visualizeMap(map, miniMap, characters);

        TextArea textArea = new TextArea();
        TextField textField = new TextField();

        client.setTop(map);
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
