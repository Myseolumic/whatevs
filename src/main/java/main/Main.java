package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import server.Map;
import server.Tile;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane client = new BorderPane();
        GridPane map = new GridPane();
        Tile[][] miniMap = Map.generateMap(16);
        Map.reduceMapSize(miniMap);
        Map.reduceMapSize(miniMap);

        Map.visualizeMap(map, miniMap);
        //client.setBottom();


        client.setCenter(map);
        primaryStage.setTitle("CreepyPasta");
        primaryStage.setScene(new Scene(client, 1000, 800));
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
