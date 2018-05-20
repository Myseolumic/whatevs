package inventory;

import client.PlayerStats;
import server.Player;

public class Kevlar implements Item {
    @Override
    public int isActive() {
        return 0;
    }

    @Override
    public void getBonus(PlayerStats stats, boolean[][] cordMatrix, Player player) {
        stats.setDefence(stats.getDefence() + 3);
    }

    @Override
    public String getResourcePath() {
        return "Items/kevlar.png";
    }

    @Override
    public String getName() {
        return "Strong-ass kevlar ";
    }

    @Override
    public void removeBonus(PlayerStats playerStats) {
        playerStats.setDefence(playerStats.getDefence()-3);
    }
}
