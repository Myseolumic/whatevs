package server;

import com.google.gson.Gson;
import common.ClientMovementRequest;
import common.MapData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = (args.length != 0) ? Integer.parseInt(args[0]) : 1337;
        System.out.println("Opening server on port: " + port);

        try (
                ServerSocket ss = new ServerSocket(port)
        ) {
            Gson gson = new Gson();

            Random rand = new Random();
            int size = 16;
            Tile[][] map = Map.generateMap(size);
            String[][] mapString = new String[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    mapString[i][j] = map[i][j].toString();
                }
            }
            System.out.println("Map generated.\nWaiting for clients...");

            Socket clientSocket = ss.accept();
            System.out.println("Client received! Id: " + clientSocket.toString());
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

            dos.writeUTF(gson.toJson(new MapData(mapString)));
            System.out.println("Sent mapString.");

            int y = rand.nextInt(map[0].length);
            Player player = new Player(0,y);
            dos.writeUTF(gson.toJson(player));
            List<String> availableDirections = processMap(map, 0, y);
            dos.writeUTF(gson.toJson(new ClientMovementRequest(availableDirections)));
            while (clientSocket.isConnected()) {
                roundStart(gson, map, rand, dos, dis);
                //int f = dis.readInt();
                //processAllInput(f, dis.readUTF());
            }
            dis.close();
            dos.close();
        }
    }

    private static void processAllInput(int id, String json, HashMap<Integer, Class<?>> inputTypes){

    }

    private static void processTextInput(String str, DataOutputStream dos) throws Exception {
        dos.writeUTF("[Client]: " + str);
    }

    private static void roundStart(Gson gson, Tile[][] map, Random rand, DataOutputStream dos, DataInputStream dis) throws Exception {
        //just to start things off, going to need a way for tracking player location
        dos.writeUTF(gson.toJson(new Player(0, 2)));
        List<String> availableDirections = processMap(map, 0, 2);
        dos.writeUTF(gson.toJson(new ClientMovementRequest(availableDirections)));
        dis.readUTF();

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
