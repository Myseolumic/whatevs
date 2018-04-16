package main;

import com.google.gson.Gson;
import common.ClientMovementRequest;
import common.MapData;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import server.Player;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import server.Map;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ServerCommunicator implements Runnable {
    private Socket serverSocket;
    private GridPane mapArea;
    private TextArea textArea;
    private TextField textField;
    private Buttons buttons;
    private Player location;

    public ServerCommunicator(GridPane mapArea, TextArea textArea, TextField textField, Buttons buttons) throws Exception {
        serverSocket = new Socket("127.0.0.1", 1337);
        this.mapArea = mapArea;
        this.textArea = textArea;
        this.textField = textField;
        this.buttons = buttons;
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        try (
                DataInputStream dis = new DataInputStream(serverSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(serverSocket.getOutputStream())
        ) {
            Gson gson = new Gson();
            Map.visualizeMap(mapArea, gson.fromJson(dis.readUTF(), MapData.class).getMapTiles());
            Direction direction = new Direction();
            buttons.init(textArea, direction);
            textField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        dos.writeInt(0);
                        dos.writeUTF(textField.getText());
                        textArea.appendText(textField.getText());
                        textField.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            while (true) {
                location = gson.fromJson(dis.readUTF(), Player.class);
                Map.placePlayer(mapArea, location);
                System.out.println("X:" + location.getX() + " Y.:" + location.getY());
                List<String> availableDirections = gson.fromJson(dis.readUTF(), ClientMovementRequest.class).getDirections();
                updateButtons(availableDirections);
                //waits turn to end.
                Thread.sleep(10000);
                textArea.appendText("Turn is over.\n");

                System.out.println(direction.getDirection());
                dos.writeInt(1);
                dos.writeUTF(direction.getDirection());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateButtons(List<String> directions) {
        buttons.enableAll();
        System.out.println(directions.toString());

        if (!directions.contains("up")) {
            buttons.disableUp();
        }
        if (!directions.contains("down")) {
            buttons.disableDown();
        }
        if (!directions.contains("left")) {
            buttons.disableLeft();
        }
        if (!directions.contains("right")) {
            buttons.disableRight();
        }
    }
}
