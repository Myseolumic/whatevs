package client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane client = new BorderPane();

        HBox topSection = new HBox();
        GridPane map = new GridPane();
        map.setMinHeight(640);
        map.setMinWidth(640);
        BorderPane topRight = new BorderPane();

        GridPane inventory = new GridPane();
        ImageView inventor_1 = new ImageView(new Image("TileSprites/inventorySlot.png"));
        ImageView inventor_2 = new ImageView(new Image("TileSprites/inventorySlot.png"));
        ImageView inventor_3 = new ImageView(new Image("TileSprites/inventorySlot.png"));
        ImageView inventor_4 = new ImageView(new Image("TileSprites/inventorySlot.png"));
        inventory.add(inventor_1,0,0);
        inventory.add(inventor_2,1,0);
        inventory.add(inventor_3,2,0);
        inventory.add(inventor_4,3,0);
        topRight.setBottom(inventory);
        topSection.getChildren().addAll(map, topRight);
        ItemList inventoryArray = new ItemList(inventory);

        TextArea textArea = new TextArea();
        TextField textField = new TextField();

        BorderPane midLeft = new BorderPane();
        midLeft.setTop(textArea);
        midLeft.setCenter(textField);

        Label name = new Label();
        Label hpBar = new Label();
        Label damage = new Label();
        Label defence = new Label();
        StatLabels info = new StatLabels(name, hpBar, damage, defence);
        ImageView character = new ImageView(new Image("TileSprites/characterPortrait.png"));
        VBox stats = new VBox();
        GridPane statsAndPortrait = new GridPane();
        stats.getChildren().addAll(name, hpBar, damage,defence);
        statsAndPortrait.add(character,0 ,0);
        statsAndPortrait.add(stats,0,0);
        topRight.setCenter(statsAndPortrait);
        StatsAndPort portrait = new StatsAndPort(statsAndPortrait, stats);



        GridPane midRight = new GridPane();
        ImageView upArrow = new ImageView(new Image("TileSprites/up.png"));
        ImageView downArrow = new ImageView(new Image("TileSprites/down.png"));
        ImageView leftArrow = new ImageView(new Image("TileSprites/left.png"));
        ImageView rightArrow = new ImageView(new Image("TileSprites/right.png"));
        ImageView scoutButton = new ImageView(new Image("TileSprites/search.png"));
        Button left = new Button("", leftArrow);
        Button right = new Button("", rightArrow);
        Button up = new Button("", upArrow);
        Button down = new Button("", downArrow);
        Button stop = new Button("",scoutButton);
        Button drop1 = new Button("   DROP   ");
        Button drop2 = new Button("     DROP     ");
        Button drop3 = new Button("     DROP     ");
        Button drop4 = new Button("     DROP     ");
        Buttons buttons = new Buttons(up, down, left, right, stop,drop1,drop2,drop3,drop4);
        midRight.add(up, 1, 0);
        midRight.add(left, 0, 1);
        midRight.add(stop, 1, 1);
        midRight.add(right, 2, 1);
        midRight.add(down, 1, 2);
        midRight.add(drop1,3,0);
        midRight.add(drop2,4,0);
        midRight.add(drop3,5,0);
        midRight.add(drop4,6,0);

        HBox middleSection = new HBox();
        middleSection.getChildren().addAll(midLeft, midRight);

        client.setTop(topSection);
        client.setCenter(middleSection);
        primaryStage.setTitle("Forest battle arena");
        primaryStage.setScene(new Scene(client));

        //insert all needed IO/other Threads
        ServerCommunicator comm;
        Thread ioThread;
        try {
            comm = new ServerCommunicator(map, textArea, textField, buttons, info, portrait, inventoryArray);
            ioThread = new Thread(comm);
            ioThread.start();
        } catch (ConnectException e) {
            System.err.println("Server is not running!\nExiting game...");
            Platform.exit();
            return; // comm.close() viskab näkku muidu
        }


        primaryStage.setOnCloseRequest(event -> {
            try {
                if (comm.isRunning()){
                    comm.close(true);
                    comm.stopRunning();
                    ioThread.interrupt();
                }
                Platform.exit();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Platform.setImplicitExit(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
