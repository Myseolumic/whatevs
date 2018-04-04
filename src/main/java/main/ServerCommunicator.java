package main;

import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerCommunicator implements Runnable{
    private Socket serverSocket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private TextArea textArea;
    private TextField textField;

    public ServerCommunicator(TextArea textArea, TextField textField) throws Exception{
        serverSocket = new Socket("127.0.0.1", 1337);
        dis = new DataInputStream(serverSocket.getInputStream());
        dos = new DataOutputStream(serverSocket.getOutputStream());
        this.textArea = textArea;
        this.textField = textField;

        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    try {
                        dos.writeUTF(textField.getText());
                        textField.setText("");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void close() throws Exception{
        dis.close();
        dos.close();
        serverSocket.close();
    }

    @Override
    public void run() {
        while (true) {
            try {
                textArea.appendText(dis.readUTF()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
