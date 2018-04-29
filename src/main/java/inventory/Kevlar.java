package inventory;

import client.PlayerStats;

public class Kevlar implements Item {
    @Override
    public void getBonus(PlayerStats player) {
        player.setDefence(player.getDefence() + 3);
    }

    @Override
    public String getResourcePath() {
        return null;
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
