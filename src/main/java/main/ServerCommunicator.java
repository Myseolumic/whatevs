package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerCommunicator {
    private Socket serverSocket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public ServerCommunicator() throws Exception{
        serverSocket = new Socket("127.0.0.1", 1337);
        dis = new DataInputStream(serverSocket.getInputStream());
        dos = new DataOutputStream(serverSocket.getOutputStream());
    }

    public String getData(){
        //pings server for something
        //waits for server input
        //returns the data
        return "";
    }

    public void close() throws Exception{
        dis.close();
        dos.close();
        serverSocket.close();
    }
}
