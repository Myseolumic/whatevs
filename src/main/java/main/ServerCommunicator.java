package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerCommunicator {
    private Socket serverSocket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public ServerCommunicator() throws IOException{
        serverSocket = new Socket("127.0.0.1", 1337);
        dis = new DataInputStream(serverSocket.getInputStream());
        dos = new DataOutputStream(serverSocket.getOutputStream());
    }

    public String getData(){
        return "";
    }
}
