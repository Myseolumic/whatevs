package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            while (true) {
                //loop for processing clients movements and other shite
            }
        }
    }
}
