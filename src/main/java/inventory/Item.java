package inventory;

import client.PlayerStats;

public interface Item {
    void getBonus(PlayerStats player);
    String getResourcePath();
    String getName();
    void removeBonus(PlayerStats playerStats);
}
