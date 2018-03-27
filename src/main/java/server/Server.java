package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = (args.length != 0) ? Integer.parseInt(args[0]) : 7777;
        System.out.println("Opening server on port: " + port);

        try (
                ServerSocket ss = new ServerSocket(port)
        ) {
            //map generation here
            System.out.println("Map generated.\nWaiting for clients...");
            Socket clientSocket = ss.accept();
            System.out.println("Client received! Id: "+clientSocket.toString());
            while (true) {
                //loop for processing clients movements and other shite
            }
        }
    }
}
