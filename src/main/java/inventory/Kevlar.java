package inventory;

import client.PlayerStats;

public class Kevlar implements Item {
    @Override
    public void getBonus(PlayerStats player) {
        player.setMaxHealth(player.getMaxHealth()+3);
        player.setHealth(player.getHealth() + 3);
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
        player.setMaxHealth(player.getMaxHealth()-3);
        player.setHealth(player.getHealth());
    }
}
