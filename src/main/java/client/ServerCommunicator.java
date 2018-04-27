package client;

import Invenoty.Item;
import com.google.gson.Gson;
import common.ClientMovementRequest;
import common.MapData;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import server.Map;
import server.Player;
import tiles.Tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ServerCommunicator implements Runnable {
    private Socket serverSocket;
    private GridPane mapArea;
    private TextArea textArea;
    private TextField textField;
    private Buttons buttons;
    private StatLabels statLabels;
    private Player player;
    private DataInputStream dis;
    private DataOutputStream dos;
    private volatile boolean running = true;
    private ItemList itemslots;

    public ServerCommunicator(GridPane mapArea, TextArea textArea, TextField textField, Buttons buttons, StatLabels statLabels, ItemList itemslots) throws Exception {
        this.itemslots = itemslots;
        this.serverSocket = new Socket("127.0.0.1", 1337);
        this.dis = new DataInputStream(serverSocket.getInputStream());
        this.dos = new DataOutputStream(serverSocket.getOutputStream());

        this.mapArea = mapArea;
        this.textArea = textArea;
        this.textField = textField;
        this.buttons = buttons;
        this.statLabels = statLabels;
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


            PlayerStats stats = createStats("Jaanus");
            statLabels.setName(stats.getName(), stats.getAnimalClass());
            statLabels.setDamage(String.valueOf(stats.getDmg()));
            Tile[][] mapTiles = gson.fromJson(dis.readUTF(), MapData.class).getMapTiles();
            boolean[][] cordMatrix = Map.generateBoolMatrix(mapTiles.length);
            Direction direction = new Direction();
            buttons.init(textArea, direction);
            int turn = 0;
            Map.visualizeMap(mapArea, mapTiles, cordMatrix);

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
                System.out.println("Your turn has started.");
                Tile currentTile = mapTiles[player.getX()][player.getY()];
                String eventInfo = currentTile.enteredTile(stats,itemslots);
                textArea.appendText(eventInfo+"\n");
                currentTile.Activate();
                statLabels.setHp(String.valueOf(stats.getHealth()),String.valueOf(stats.getMaxHealth()),stats.isAlive());
                statLabels.setDamage(String.valueOf(stats.getDmg()));
                cordMatrix[player.getX()][player.getY()] = true;
                Map.visualizeMap(mapArea, mapTiles, cordMatrix);
                Map.placePlayer(mapArea, player);
                System.out.println("X:" + player.getX() + " Y.:" + player.getY());

                List<String> availableDirections = gson.fromJson(dis.readUTF(), ClientMovementRequest.class).getDirections();
                updateButtons(availableDirections);

                //waits turn to end.
                Thread.sleep(7000);
                textArea.appendText("3...\n");
                Thread.sleep(1000);
                textArea.appendText("2...\n");
                Thread.sleep(1000);
                textArea.appendText("1...\n");
                Thread.sleep(1000);
                if (!running) {
                    break;
                }
                textArea.appendText("--------------------------------------------------\n");

                System.out.println(direction.getDirection());
                dos.writeInt(1);
                dos.writeUTF(direction.getDirection());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRunning() {
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

    private PlayerStats createStats(String name) {
        List<String> classes = Arrays.asList("Hedgehog", "Giraffe", "Wolf");
        int damage=0;
        int health=0;
        int randomClass = (int) Math.floor(Math.random()*classes.size());
        switch(randomClass) {
            case 0:
                damage = 5;
                health = 14;
                break;
            case 1:
                damage = 4;
                health = 20;
                break;
            case 2:
                damage = 7;
                health = 10;
                break;
        }

        return new PlayerStats(name, classes.get(randomClass),health, damage);
    }
}
