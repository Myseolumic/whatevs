package tiles;

import server.Player;

public class House implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/house.png";
    }

    @Override
    public void enteredTile(Player player) {
        int randomEvent = (int) Math.ceil(Math.random()*3);

        switch(randomEvent) {
            case 1:
                System.out.println(player.getName() + " event1 from house");
                break;
            case 2://loot
                System.out.println(player.getName() + " event2 from house");
                break;
            case 3://
                System.out.println(player.getName() + " event3 from house");
                break;

        }

    }

    @Override
    public String toString(){
        return "HouseTile";
    }

}
