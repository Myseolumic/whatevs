package inventory;

import client.PlayerStats;
import server.Player;

public interface Item {
    int isActive();
    void getBonus(PlayerStats stats, boolean[][] cordMatrix, Player player);
    String getResourcePath();
    String getName();
    void removeBonus(PlayerStats player);
}
