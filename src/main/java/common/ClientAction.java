package common;

import server.Player;

public class ClientAction {

    private boolean finished;
    private Player location;

    public ClientAction(boolean finished, Player location) {
        this.finished = finished;
        this.location = location;
    }

    public boolean isFinished() {
        return finished;
    }

    public Player getLocation() {
        return location;
    }

    public void setLocation(Player location) {
        this.location = location;
    }
}
