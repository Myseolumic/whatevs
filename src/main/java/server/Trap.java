package server;

public class Trap implements Tile {
    @Override
    public String getResourcePath() {
        return "TileSprites/trap.png";
    }

    @Override
    public void enteredTile(Player player) {

    }

    @Override
    public String toString(){
        return "TrapTile";
    }
}
