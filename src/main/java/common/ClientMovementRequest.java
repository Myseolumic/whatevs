package common;

import java.io.Serializable;

public class ClientMovementRequest implements Serializable{

    private String[] directions;

    public ClientMovementRequest(String[] directions){
        this.directions = directions;
    }

    public String[] getDirections() {
        return directions;
    }
}
