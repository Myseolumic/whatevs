package main;

import com.google.gson.Gson;
import common.ClientMovementRequest;
import common.MapData;
import server.Player;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import server.Map;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

public class ServerCommunicator implements Runnable {
    private Socket serverSocket;
    private GridPane mapArea;
    private TextArea textArea;
    private TextField textField;
    private Buttons buttons;
    private Player location;

    public ServerCommunicator(GridPane mapArea, TextArea textArea, TextField textField, Buttons buttons) throws Exception {
        serverSocket = new Socket("127.0.0.1", 1337);
        this.mapArea = mapArea;
        this.textArea = textArea;
        this.textField = textField;
        this.buttons = buttons;
    }

    @Override
    public void run() {
        try (DataInputStream dis =new DataInputStream(serverSocket.getInputStream());
             DataOutputStream dos = new DataOutputStream(serverSocket.getOutputStream())
        ){
            Gson gson = new Gson();
            /*
            textField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        dos.writeInt(0);
                        dos.writeUTF(textField.getText());
                        textField.setText("");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            */
            Map.visualizeMap(mapArea, gson.fromJson(dis.readUTF(),MapData.class).getMapTiles());

            while (true) {
                location = gson.fromJson(dis.readUTF(),Player.class);
                System.out.println("X:"+location.getX()+" Y.:"+location.getY());
                List<String> availableDirections = gson.fromJson(dis.readUTF(), ClientMovementRequest.class).getDirections();
                updateButtons(availableDirections);
                /*
                try {
                    textArea.appendText(dis.readUTF()+"\n");
                } catch (IOException e) {
                    e.printStackTrace(); //tõesti tahaks teada mis võib valesti minna...
                }
                */
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void updateButtons(List<String> directions){
        if(!directions.contains("up")){
            buttons.disableUp();
        }
        if(!directions.contains("down")){
            buttons.disableDown();
        }
        if(!directions.contains("left")){
            buttons.disableLeft();
        }
        if(!directions.contains("right")){
            buttons.disableRight();
        }
    }
}
