package inventory;

import client.PlayerStats;
import server.Player;

public class Lantern implements Item {
    public int isActive = 2;

    @Override
    public int isActive() {
        return isActive;
    }

    @Override
    public void getBonus(PlayerStats stats, boolean[][] cordMatrix, Player player) {
        if (isActive > 0) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (player.getY() + i >= 0 && player.getY() < cordMatrix.length && player.getX() + j >= 0 && player.getX() + j < cordMatrix.length)
                        cordMatrix[player.getX() + j][player.getY() + i] = true;
                }
            }
            isActive -= 1;
        }
    }

    @Override
    public String getResourcePath() {
        return "Items/lantern.png";
    }

    @Override
    public String getName() {
        return "really light latern";
    }

    @Override
    public void removeBonus(PlayerStats playerStats) {
    }
}
