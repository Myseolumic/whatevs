package inventory;

import client.PlayerStats;

public class Lantern implements Item {
    @Override
    public void getBonus(PlayerStats player) {
        // show tiles around you

    }

    @Override
    public String getResourcePath() {
        return "Items/lantern.png";
    }

    @Override
    public String getName() {
        return "a really light latern";
    }

    @Override
    public void removeBonus(PlayerStats playerStats) {}
}
