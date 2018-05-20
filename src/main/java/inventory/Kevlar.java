package inventory;

import client.PlayerStats;
import server.Player;

public class Kevlar implements Item {
    @Override
    public void getBonus(PlayerStats stats, boolean[][] cordMatrix, Player player) {
        stats.setMaxHealth(stats.getMaxHealth() + 3);
        stats.setHealth(stats.getHealth() + 3);
    }

    public int isActive() {
        return 0;
    }

    @Override
    public String getResourcePath() {
        return "Items/kevlar.png";
    }

    @Override
    public String getName() {
        return "a strong-ass kevlar ";
    }

    @Override
    public void removeBonus(PlayerStats player) {
        player.setMaxHealth(player.getMaxHealth() - 3);
        player.setHealth(player.getHealth());
    }
}
