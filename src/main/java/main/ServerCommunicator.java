package main;

import common.Player;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerCommunicator implements Runnable {
    private Socket serverSocket;
    private TextFlow textArea;
    private TextField textField;
    private Player location;

    public ServerCommunicator(TextFlow textArea, TextField textField) throws Exception {
        serverSocket = new Socket("127.0.0.1", 1337);
        this.textArea = textArea;
        this.textField = textField;
    }

    @Override
    public void run() {
        try (DataInputStream dis =new DataInputStream(serverSocket.getInputStream());
             DataOutputStream dos = new DataOutputStream(serverSocket.getOutputStream())
        ){
            textField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        dos.writeUTF(textField.getText());
                        textField.setText("");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            while (true) {
                try {
                    String text = dis.readUTF();
                    System.out.println(text);
                    Text row = new Text();
                    row.setStyle("-fx-fill: #fdff18;-fx-font-weight:bold;");
                    row.setText(text);
                    textArea.getChildren().add(row);
                } catch (IOException e) {
                    e.printStackTrace(); //tõesti tahaks teada mis võib valesti minna...
                }
            }
        }catch (Exception e){
            //whatevs
        }
    }
}
