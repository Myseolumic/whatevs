package client;

import com.google.gson.Gson;
import common.ClientMovementRequest;
import common.MapData;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import server.Player;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import server.Map;
import tiles.Tile;

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
    private Labels labels;
    private Player player;
    private DataInputStream dis;
    private DataOutputStream dos;
    private volatile boolean running = true;

    public ServerCommunicator(GridPane mapArea, TextArea textArea, TextField textField, Buttons buttons, Labels labels) throws Exception {
        this.serverSocket = new Socket("127.0.0.1", 1337);
        this.dis = new DataInputStream(serverSocket.getInputStream());
        this.dos = new DataOutputStream(serverSocket.getOutputStream());

        this.mapArea = mapArea;
        this.textArea = textArea;
        this.textField = textField;
        this.buttons = buttons;
        this.labels = labels;
    }

    public void close() throws IOException {
        dos.writeInt(404);
        dos.writeUTF("Client ragequit.");
        dis.close();
        dos.close();
        serverSocket.close();
        Platform.exit();
    }

    @Override
    public void run() {
        try {
            Gson gson = new Gson();
            Tile[][] mapTiles = gson.fromJson(dis.readUTF(), MapData.class).getMapTiles();
            boolean[][] cordMatrix = Map.generateBoolMatrix(mapTiles.length);
            Direction direction = new Direction();
            buttons.init(textArea, direction);
            int turn = 0;
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

            while (running) {
                /*turn+=1;
                if(turn%5 == 0) {
                    mapTiles = Map.reduceMapSize(mapTiles);
                }*/
                direction.setDirection("stop");
                player = gson.fromJson(dis.readUTF(), Player.class);
                labels.setCharacter(player.getName());
                labels.setHp(String.valueOf(player.getHealth()));
                cordMatrix[player.getX()][player.getY()] = true;
                Map.visualizeMap(mapArea, mapTiles, cordMatrix);
                Map.placePlayer(mapArea, player);
                System.out.println("X:" + player.getX() + " Y.:" + player.getY());
                List<String> availableDirections = gson.fromJson(dis.readUTF(), ClientMovementRequest.class).getDirections();
                updateButtons(availableDirections);
                mapTiles[player.getX()][player.getY()].enteredTile(player);
                //waits turn to end.
                Thread.sleep(7000);
                textArea.appendText("3...\n");
                Thread.sleep(1000);
                textArea.appendText("2...\n");
                Thread.sleep(1000);
                textArea.appendText("1...\n");
                Thread.sleep(1000);
                if(!running){
                    break;
                }
                textArea.appendText("Turn is over.\n");

                System.out.println(direction.getDirection());
                dos.writeInt(1);
                dos.writeUTF(direction.getDirection());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRunning(){
        this.running = false;
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
