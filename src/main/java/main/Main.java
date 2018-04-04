package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.Map;
import server.Tile;


public class Main extends Application {
    private TextArea messages = new TextArea();
    private boolean isServer = false;
    private NetworkConnection connection = isServer ? createServer(): createClient();

    @Override
    public void init() throws Exception {
        connection.startConnection();
    }

    public void stop() throws Exception{
        connection.closeConnection();
    }

    private Server createServer(){
        return new Server(1337, data ->{
            Platform.runLater(() -> {
                messages.appendText(data.toString() + "\n");
            });
        });
    }

    private Client createClient(){
        return new Client("127.0.0.1",1337,data ->{
            Platform.runLater(() -> {
                messages.appendText(data.toString() + "\n");
            });
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane client = new BorderPane();
        GridPane map = new GridPane();
        Tile[][] miniMap = Map.generateMap(16);
        Map.reduceMapSize(miniMap);
        Map.visualizeMap(map, miniMap);

        ////////////////////////////////////
        messages.setPrefHeight(600);
        TextField input = new TextField();
        input.setOnAction(event -> {
            String message = isServer ? "Server: " : "Client: ";
            message += input.getText();
            input.clear();

            messages.appendText(message + "\n");

            try {
                connection.send(message);
            } catch (Exception e) {
                messages.appendText("Exeption happened\n");
            }
        });
        VBox vBox = new VBox(20,messages,input);
        vBox.setPrefSize(60,300);
        ////////////////////////////////////////////////////7

        client.setCenter(map);
        client.setBottom(vBox);
        primaryStage.setTitle("CreepyPasta");
        primaryStage.setScene(new Scene(client, 1000, 800));
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
