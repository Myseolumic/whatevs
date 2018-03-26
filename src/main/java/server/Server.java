package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = (args.length != 0) ? Integer.parseInt(args[0]) : 7777;
        try (
                ServerSocket ss = new ServerSocket(port);
        ) {
            Socket clientSocket = ss.accept();
            while(true){

            }
        }
    }
}
