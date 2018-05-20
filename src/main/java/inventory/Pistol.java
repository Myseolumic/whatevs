package inventory;

import client.PlayerStats;

public class Pistol implements Item {
    @Override
    public void getBonus(PlayerStats player) {
            player.setDmg(player.getDmg() + 2);
    }

    @Override
    public String getResourcePath() {
        return "Items/pewpew.png";
    }

    @Override
    public String getName() {
        return "a 44 magnum handgun";
    }

    @Override
    public void removeBonus(PlayerStats player) {
        player.setDmg(player.getDmg() - 2);
    }
}
