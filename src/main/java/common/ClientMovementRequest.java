package common;

import java.io.Serializable;
import java.util.List;

public class ClientMovementRequest implements Serializable{

    private List<String> directions;

    public ClientMovementRequest(List<String> directions){
        this.directions = directions;
    }

    public List<String> getDirections() {
        return directions;
    }
}
