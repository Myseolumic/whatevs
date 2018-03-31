package server;

import main.Player;

public class TrashedArea implements Tile {
    @Override
    public void enteredTile(Player player) {

    }

    @Override
    public char getSymbol() {
        return 'X';
    }
}
