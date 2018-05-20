package battle;

import server.Player;

public class Battle {
    private Player target;

    public Battle(Player target) {
        this.target = target;
    }

    public Player getTarget() {
        return target;
    }
}
