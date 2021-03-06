package server;

import battle.Battle;
import com.google.gson.Gson;
import common.ClientAction;
import common.NextTurnData;
import tiles.Tile;

import java.net.ServerSocket;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = (args.length != 0) ? Integer.parseInt(args[0]) : 1337;
        System.out.println("Opening server on port: " + port);

        try (
                ServerSocket ss = new ServerSocket(port)
        ) {
            //using gson for server-client protocol.
            Gson gson = new Gson();
            Random rand = new Random();

            //java.util.concurrent stuff
            HashMap<Integer, BlockingQueue<NextTurnData>> childQueues = new HashMap<>();
            HashMap<Integer, Player> childLocations = new HashMap<>();
            HashMap<Integer, NextTurnData> turnDatas = new HashMap<>();
            BlockingQueue<ClientAction> clientActions = new ArrayBlockingQueue<>(4);
            CountDownLatch cdl = new CountDownLatch(1);
            Semaphore semaphore = new Semaphore(0, true);

            //map related generation
            int size = 16;
            Tile[][] map = Map.generateMap(size);
            boolean[][] usedTiles = Map.generateBoolMatrix(size);
            System.out.println("Map generated!");

            //generate x and y for players
            int players = 2;
            int[][] spawnLocations = generateLocations(players, size, rand);
            System.out.println("Set spawn locations for "+players+" player(s).");

            //pre-game queue for connecting
            System.out.println("Waiting for clients...");
            List<Thread> threads = new ArrayList<>();
            String[] classes = {"Hedgehog", "Moose", "Wolf"};
            while (threads.size() != players) {
                Player player = new Player(threads.size(),
                        spawnLocations[threads.size()][0],
                        spawnLocations[threads.size()][1]);
                int randomClass = (int) Math.floor(Math.random() * classes.length);
                player.setAnimalClass(classes[randomClass]);

                usedTiles[spawnLocations[threads.size()][1]][spawnLocations[threads.size()][0]] = true;
                BlockingQueue<NextTurnData> childQueue = new ArrayBlockingQueue<>(1);
                childQueues.put(threads.size(), childQueue);

                Thread clientThread = new Thread(new ClientHandler(ss.accept(), gson, map, player, clientActions,
                        cdl, semaphore, childQueue));
                threads.add(clientThread);
                clientThread.start();
            }
            cdl.countDown();

            //main game loop
            System.out.println("No longer accepting more clients");
            while (true) {
                int finishedClients = 0;
                while (finishedClients < players) {
                    ClientAction next = clientActions.take();
                    if (next.isFinished()) {
                        int queueId = next.getLocation().getId();
                        turnDatas.put(queueId, new NextTurnData(null,isTileUsed(next.getLocation(), usedTiles)));
                        childLocations.put(queueId,next.getLocation());
                        usedTiles[next.getLocation().getY()][next.getLocation().getX()] = true;
                    } else {
                        usedTiles[next.getLocation().getY()][next.getLocation().getX()] = true;
                        System.out.println("Removed the salty client.");
                        players--;
                    }
                    finishedClients++;

                }
                if (players == 0){
                    System.out.println("no more players, shutting down server.");
                    break;
                }
                for (Player player :childLocations.values()){
                    for(Player other: childLocations.values()){
                        if( player.getId() != other.getId() && player.getX() == other.getX() && player.getY() == other.getY()){
                            System.out.println();
                            turnDatas.get(player.getId()).setBattle(new Battle(other));
                            turnDatas.get(other.getId()).setBattle(new Battle(player));
                        }
                    }
                }

                //send turndata
                for(Integer id: turnDatas.keySet()){
                    childQueues.get(id).put(turnDatas.get(id));
                }

                System.out.println("The turn has ended.");
                semaphore.release(players);
            }

            //kills all threads after the game is finished or everyone has ragequit
            for (Thread thread: threads){
                if(thread.isAlive()) thread.interrupt();
            }
        }
    }

    private static int[][] generateLocations(int playerCount, int mapSize, Random rand) {
        int[][] locations = new int[playerCount][2];
        for (int i = 0; i < playerCount; i++) {
            if (i % 2 == 0) {
                locations[i][0] = (i == 0) ? 0 : mapSize - 1;
                locations[i][1] = rand.nextInt(mapSize);
            } else {
                locations[i][0] = rand.nextInt(mapSize);
                locations[i][1] = (i == 1) ? 0 : mapSize - 1;
            }

        }
        System.out.println(Arrays.toString(locations[0]));
        return locations;
    }

    private static boolean isTileUsed(Player location, boolean[][] usedTiles){
        return usedTiles[location.getY()][location.getX()];
    }

    private static void printTileArray(boolean[][] usedTiles){
        for(boolean[] usedTile : usedTiles){
            System.out.println(Arrays.toString(usedTile));
        }
    }
}
