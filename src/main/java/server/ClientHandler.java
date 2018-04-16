package server;

import com.google.gson.Gson;
import common.ClientMovementRequest;
import common.MapData;
import tiles.Tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BlockingQueue<Boolean> turnFinished;
    private Gson gson;
    private Random random;
    private Tile[][] map;
    private int x,y;

    public ClientHandler(Socket clientSocket, Gson gson, Tile[][] map, int x, int y, BlockingQueue<Boolean> turnFinished) {
        this.clientSocket = clientSocket;
        this.gson = gson;
        this.map = map;
        this.turnFinished = turnFinished;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        try (
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream())
        ) {
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

            while (clientSocket.isConnected()) {
                Player player = new Player(x, y);
                dos.writeUTF(gson.toJson(player));
                List<String> availableDirections = processMap(map, x, y);
                dos.writeUTF(gson.toJson(new ClientMovementRequest(availableDirections)));
                processInput(dis.readInt(), dis.readUTF());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processInput(int id, String str){
        if(id == 0){ //text
            System.out.println("[Client]: "+str);
        }
        if(id == 1){ //movement
            System.out.println("client is being moved "+str);
            if(str.equals("up")){
                y--;
            }
            if(str.equals("down")){
                y++;
            }
            if(str.equals("right")){
                x++;
            }
            if(str.equals("left")){
                x--;
            }
        }
    }

    private static List<String> processMap(Tile[][] map, int x, int y) {
        List<String> directions = new ArrayList<>();
        if (x > 0) {
            directions.add("left");
        }
        if (x < 16) {
            directions.add("right");
        }
        if (y > 0) {
            directions.add("up");
        }
        if (y < 16) {
            directions.add("down");
        }
        return directions;
    }
}
