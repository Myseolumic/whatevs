package inventory;

import client.PlayerStats;

public class Kevlar implements Item {
    @Override
    public void getBonus(PlayerStats player) {
        player.setHealth(player.getHealth() + 3);
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
        playerStats.setHealth(playerStats.getHealth()-3);
    }
}
