package inventory;

import client.PlayerStats;
import server.Player;

public class Pistol implements Item {
    @Override
    public int isActive() {
        return 0;
    }

    @Override
    public void getBonus(PlayerStats stats, boolean[][] cordMatrix, Player player) {
            stats.setDmg(stats.getDmg() + 2);
    }

    @Override
    public String getResourcePath() {
        return "Items/pewpew.png";
    }

    @Override
    public String getName() {
        return "PowPow pistol";
    }

    @Override
    public void removeBonus(PlayerStats player) {
        player.setDmg(player.getDmg() - 2);
    }
}
