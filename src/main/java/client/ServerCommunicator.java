package client;

import battle.Battle;
import com.google.gson.Gson;
import common.ClientMovementRequest;
import common.Direction;
import common.DirectionHolder;
import common.MapData;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import server.Map;
import server.Player;
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
    private StatLabels statLabels;
    private StatsAndPort portrait;
    private Player player;
    private PlayerStats stats;
    private DataInputStream dis;
    private DataOutputStream dos;
    private volatile boolean running = true;
    private ItemList itemslots;

    public ServerCommunicator(GridPane mapArea, TextArea textArea, TextField textField, Buttons buttons, StatLabels statLabels, StatsAndPort portrait, ItemList itemslots) throws Exception {
        this.itemslots = itemslots;
        this.serverSocket = new Socket("127.0.0.1", 1337);
        this.dis = new DataInputStream(serverSocket.getInputStream());
        this.dos = new DataOutputStream(serverSocket.getOutputStream());
        this.mapArea = mapArea;
        this.textArea = textArea;
        this.textField = textField;
        this.buttons = buttons;
        this.statLabels = statLabels;
        this.portrait = portrait;
    }

    public void close(boolean quit) throws IOException {
        dos.writeInt(404);
        dos.writeUTF("Client ragequit.");
        dis.close();
        dos.close();
        serverSocket.close();
        if (quit) Platform.exit();
    }


    @Override
    public void run() {
        try {
            Gson gson = new Gson();

            Tile[][] mapTiles = gson.fromJson(dis.readUTF(), MapData.class).getMapTiles();
            boolean[][] cordMatrix = Map.generateBoolMatrix(mapTiles.length);
            DirectionHolder directionHolder = new DirectionHolder();

            buttons.init(textArea, directionHolder, itemslots, stats);

            int turn = 0;
            Map.visualizeMap(mapArea, mapTiles, cordMatrix);

            textField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        //dos.writeInt(0);
                        //dos.writeUTF(textField.getText());
                        textArea.appendText(textField.getText());
                        textField.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            while (running) {
                turn += 1;
                if (turn % 6 == 0) {
                    mapTiles = Map.reduceMapSize(mapTiles);
                }


                directionHolder.setDirection(Direction.STOP);
                player = gson.fromJson(dis.readUTF(), Player.class);
                if(turn == 1){
                    this.stats = new PlayerStats(player.getAnimalClass());
                    statLabels.setName(stats.getName(), stats.getAnimalClass());
                    statLabels.setDamage(String.valueOf(stats.getDmg()));
                    statLabels.setDefence(String.valueOf(stats.getDefence()));
                    portrait.setImage(new ImageView(stats.getPortraitPath()));
                }

                boolean isUsed = dis.readBoolean(); //works
                boolean battleHappens = dis.readBoolean();

                System.out.println("Your turn has started.");
                if (battleHappens){
                    System.out.println("You are fighting someone else!!!");
                    calculateDamage(gson.fromJson(dis.readUTF(), Battle.class)); //implement pls
                }
                Tile currentTile = mapTiles[player.getX()][player.getY()];

                for (int i = 0; i < itemslots.getSize(); i++) {
                    if (itemslots.getItemArray()[i] != null) {
                        if (itemslots.getItemArray()[i].getName().equals("a really light latern")) {
                            itemslots.getItemArray()[i].getBonus(stats, cordMatrix, player);
                        }
                    }
                }

                if (isUsed) {
                    currentTile.activate();
                }
                String eventInfo = currentTile.enteredTile(stats, itemslots, cordMatrix, player);
                textArea.appendText(eventInfo + "\n");
                if (!isUsed) {
                    currentTile.activate();
                }

                statLabels.setHp(String.valueOf(stats.getHealth()), String.valueOf(stats.getMaxHealth()), stats.isAlive());
                statLabels.setDamage(String.valueOf(stats.getDmg()));
                statLabels.setDefence(String.valueOf(stats.getDefence()));

                cordMatrix[player.getX()][player.getY()] = true;
                Map.visualizeMap(mapArea, mapTiles, cordMatrix);
                Map.placePlayer(mapArea, player);
                System.out.println("X:" + player.getX() + " Y.:" + player.getY());

                List<String> availableDirections = gson.fromJson(dis.readUTF(), ClientMovementRequest.class).getDirections();
                updateButtons(availableDirections);

                if (itemslots.hasLaternToRemove())
                    itemslots.removeLatern(stats);


                running = stats.isAlive();
                if (!running) {
                    textArea.appendText("You are dead now, gg. ");
                    this.close(false);
                    break;
                }
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

                scoutAround(cordMatrix, directionHolder);
                textArea.appendText("--------------------------------------------------\n\n");

                System.out.println(directionHolder.getDirection());
                dos.writeInt(1);
                dos.writeUTF(gson.toJson(directionHolder.getDirection()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        textArea.appendText("Close the window.");
        buttons.disableAll();
    }

    private void scoutAround(boolean[][] cordMatrix, DirectionHolder directionHolder) {
        if (directionHolder.getDirection().equals(Direction.SEARCH)) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (player.getY() + i >= 0 && player.getY() < cordMatrix.length && player.getX() + j >= 0 && player.getX() + j < cordMatrix.length)
                        cordMatrix[player.getX() + j][player.getY() + i] = true;
                }
            }
        }
    }
    private void calculateDamage(Battle battle){
        System.out.println(battle.getTarget());

        //TODO: remove health etc.
        String animalClass = battle.getTarget().getAnimalClass();
        System.out.println(animalClass);
        if (animalClass.equals("Hedgehog")){
            textArea.appendText("You battled a Hedgehog! Damn it's pointy!");
            int dmg = stats.getHealth()+stats.getDefence()-6;
            stats.setHealth(dmg);
            textArea.appendText("You took "+dmg+" points of damage");
        }
        if (animalClass.equals("Moose")){
            textArea.appendText("You battled a Moose! I thought they were vegan...");
            int dmg = stats.getHealth()+stats.getDefence()-6;
            stats.setHealth(dmg);
            textArea.appendText("You took "+dmg+" points of damage");
        }
        if (animalClass.equals("Wolf")){
            textArea.appendText("OH F*** THAT'S A WOLF. RIP.");
            int dmg = stats.getHealth()+stats.getDefence()-7;
            stats.setHealth(dmg);
            textArea.appendText("You took "+dmg+" points of damage");
        }
    }
    public void stopRunning() {
        this.running = false;
    }

    public boolean isRunning() {
        return running;
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
