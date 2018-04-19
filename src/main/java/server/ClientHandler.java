package server;

import com.google.gson.Gson;
import common.ClientMovementRequest;
import common.MapData;
import tiles.Tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BlockingQueue<Boolean> turnFinished;
    private BlockingQueue<Player> playerLocation;
    private Gson gson;
    private Tile[][] map;
    private Player location;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean clientDisconnected;

    public ClientHandler(Socket clientSocket, Gson gson, Tile[][] map, Player location,
                         BlockingQueue<Boolean> turnFinished,
                         BlockingQueue<Player> playerLocation
    ) throws IOException{
        this.clientSocket = clientSocket;
        this.dis = new DataInputStream(clientSocket.getInputStream());
        this.dos = new DataOutputStream(clientSocket.getOutputStream());

        this.gson = gson;
        this.map = map;
        this.clientDisconnected = false;
        this.turnFinished = turnFinished;
        this.playerLocation = playerLocation;
        this.location = location;
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

            while (!clientDisconnected) {
                dos.writeUTF(gson.toJson(location));
                List<String> availableDirections = processMap(map,location);
                dos.writeUTF(gson.toJson(new ClientMovementRequest(availableDirections)));
                processInput(dis.readInt(), dis.readUTF());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processInput(int id, String str) throws IOException{
        if(id == 0){ //text
            System.out.println("[Client]: "+str);
        }
        if(id == 1){ //movement
            System.out.println("client is being moved "+str);
            if(str.equals("up")){
                location.modY(-1);
            }
            if(str.equals("down")){
                location.modY(1);
            }
            if(str.equals("right")){
                location.modX(1);
            }
            if(str.equals("left")){
                location.modX(-1);
            }
        }
        if(id == 404){
            System.out.println(str);
            this.close();
        }
    }

    private void close() throws IOException{
        clientDisconnected = true;
        dos.close();
        dis.close();
        clientSocket.close();
    }

    private static List<String> processMap(Tile[][] map, Player player) {
        List<String> directions = new ArrayList<>();
        if (player.getX() > 0) {
            directions.add("left");
        }
        if (player.getX() < 16) {
            directions.add("right");
        }
        if (player.getY() > 0) {
            directions.add("up");
        }
        if (player.getY() < 16) {
            directions.add("down");
        }
        return directions;
    }
}
