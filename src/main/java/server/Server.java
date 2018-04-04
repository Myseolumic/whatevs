package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = (args.length != 0) ? Integer.parseInt(args[0]) : 1337;
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
            while (clientSocket.isConnected()) {
                processInput(dis.readUTF(),dos);
            }
            dis.close();
            dos.close();
        }
    }

    private static void processInput(String str, DataOutputStream dos) throws Exception {
        dos.writeUTF("Client: "+str);
    }
}
