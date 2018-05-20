package tiles;

import client.ItemList;
import client.PlayerStats;
import server.Player;

public class Trap implements Tile {
    private boolean isActivated = false;

    @Override
    public String getResourcePath() {
        return "TileSprites/trap.png";
    }

    @Override
    public String enteredTile(PlayerStats stats, ItemList itemList, boolean[][] cordMatrix, Player player) {
        if (!isActivated) {
            for (int i = 0; i < itemList.getSize(); i++) {
                if (itemList.getItemArray()[i] != null && ( i == itemList.getSize()-1 || itemList.getItemArray()[i+1] == null)) {
                    itemList.removeItem(itemList.getItemGridPane(), stats, i);
                    break;
                }
            }
            return "You have stumbled upon a queer looking contraption..";
        } else {
            return "You see a pile of blood on the ground. Someone got fucked up here";
        }
    }

    @Override
    public String toString() {
        return "TrapTile";
    }

    @Override
    public void activate() {
        isActivated = true;
    }
}
