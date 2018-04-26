package server;

import com.google.gson.Gson;
import tiles.Tile;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = (args.length != 0) ? Integer.parseInt(args[0]) : 1337;
        System.out.println("Opening server on port: " + port);

        try (
                ServerSocket ss = new ServerSocket(port)
        ) {
            Gson gson = new Gson();
            Random rand = new Random();
            BlockingQueue<Boolean> areFinished = new ArrayBlockingQueue<>(4);
            BlockingQueue<Player> locations = new ArrayBlockingQueue<>(4);
            CountDownLatch cdl = new CountDownLatch(1);

            int size = 16;
            Tile[][] map = Map.generateMap(size);
            System.out.println("Map generated!");

            //generate x and y for players
            int[][] spawnLocations = generateLocations(4, size, rand);
            System.out.println("Set spawn locations for 4 players.");

            System.out.println("Waiting for clients...");
            List<Thread> threads = new ArrayList<>();
            while (threads.size() != 4) {
                Player player = new Player(threads.size(),
                        spawnLocations[threads.size()][0],
                        spawnLocations[threads.size()][1]);

                Thread clientThread = new Thread(new ClientHandler(ss.accept(), gson, map, player, areFinished, locations, cdl));
                threads.add(clientThread);
                clientThread.start();
                cdl.countDown();
            }

            System.out.println("No longer accepting more clients");

            while(true){
                //start waiting for input and output if turn is finished or not
                //also process chat
            }
        }
    }

    private static int[][] generateLocations(int playerCount, int mapSize, Random rand) {
        int[][] locations = new int[playerCount][2];
        for (int i = 0; i < playerCount; i++) {
            if (i % 2 == 0) {
                locations[i][0] = (i == 0) ? 0 : mapSize - 1;
                locations[i][1] = rand.nextInt(mapSize);
            }else{
                locations[i][0] = rand.nextInt(mapSize);
                locations[i][1] = (i == 1) ? 0: mapSize - 1;
            }

        }
        System.out.println(Arrays.toString(locations[0]));
        return locations;
    }
}
