package server;

import com.google.gson.Gson;
import common.ClientAction;
import common.ClientMovementRequest;
import common.Direction;
import common.MapData;
import javafx.application.Platform;
import jdk.nashorn.internal.ir.Block;
import tiles.Tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BlockingQueue<ClientAction> turnFinished;
    private BlockingQueue<Boolean> serverQueue;
    private CountDownLatch cdl;
    private final Semaphore semaphore;
    private Gson gson;
    private Tile[][] map;
    private Player location;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean clientDisconnected;

    public ClientHandler(Socket clientSocket, Gson gson, Tile[][] map, Player location,
                         BlockingQueue<ClientAction> turnFinished,
                         CountDownLatch cdl,
                         Semaphore semaphore,
                         BlockingQueue<Boolean> serverQueue
    ) throws IOException {
        this.clientSocket = clientSocket;
        this.dis = new DataInputStream(clientSocket.getInputStream());
        this.dos = new DataOutputStream(clientSocket.getOutputStream());

        this.gson = gson;
        this.map = map;
        this.clientDisconnected = false;
        this.turnFinished = turnFinished;
        this.cdl = cdl;
        this.semaphore = semaphore;
        this.location = location;
        this.serverQueue = serverQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client received! Id: " + clientSocket.toString());
            int size = map.length;
            String[][] mapString = new String[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    mapString[i][j] = map[i][j].toString();
                }
            }

            dos.writeUTF(gson.toJson(new MapData(mapString)));
            System.out.println("Sent mapString.");
            cdl.await();
            boolean isVisited = false;
            while (!clientDisconnected) {
                dos.writeUTF(gson.toJson(location));
                dos.writeBoolean(isVisited);
                List<String> availableDirections = processMap(map, location);
                dos.writeUTF(gson.toJson(new ClientMovementRequest(availableDirections)));
                processInput(dis.readInt(), dis.readUTF());
                isVisited = isNextTileVisited();
                semaphore.acquire(); //wait for other players to finish
            }
        } catch (InterruptedException e){
            System.err.println("Shutting down ClientHandler for port: "+clientSocket.getPort());

        } catch (IOException e) { //incase connnection with client is lost.
            try {
                turnFinished.put(new ClientAction(true, location));
            } catch (InterruptedException f) {
                e.printStackTrace();
                f.printStackTrace();
            }
        }
    }

    private void processInput(int id, String str) throws IOException, InterruptedException {
        if (id == 1) { //movement
            Direction direction = gson.fromJson(str, Direction.class);
            switch (direction){
                case STOP:
                    System.out.println("Client didn't move.");
                    break;
                case UP:
                    location.modY(-1);
                    break;
                case DOWN:
                    location.modY(1);
                    break;
                case LEFT:
                    location.modX(-1);
                    break;
                case RIGHT:
                    location.modX(1);
                    break;
            }
            turnFinished.put(new ClientAction(true, location));
        }
        if (id == 404) {
            System.out.println(str);
            turnFinished.put(new ClientAction(false, location));
            this.close();
        }
    }

    private boolean isNextTileVisited() throws InterruptedException {
        return serverQueue.take();
    }

    private void close() throws IOException {
        clientDisconnected = true;
        dos.close();
        dis.close();
        clientSocket.close();
        Platform.exit();
    }

    private static List<String> processMap(Tile[][] map, Player player) {
        List<String> directions = new ArrayList<>();
        if (player.getX() > 0) {
            directions.add("left");
        }
        if (player.getX() < 15) {
            directions.add("right");
        }
        if (player.getY() > 0) {
            directions.add("up");
        }
        if (player.getY() < 15) {
            directions.add("down");
        }
        return directions;
    }
}
