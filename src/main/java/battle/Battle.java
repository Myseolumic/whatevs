package battle;

import client.PlayerStats;
import java.util.List;

public class Battle {
    List<PlayerStats> fighters;

    public Battle(List<PlayerStats> fighters) {
        this.fighters = fighters;
    }
    public void addFighter(PlayerStats player){
        fighters.add(player);
    }
    public void removeFighter(PlayerStats player){
        fighters.remove(player);
    }
    public void attackPlayer(PlayerStats attacker, PlayerStats defender){
        defender.setHealth(defender.getHealth()-attacker.getDmg());
    }
}
