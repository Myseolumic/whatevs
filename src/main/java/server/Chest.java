package server;

import main.Player;

public class Chest implements Tile {
    char symbol = '▦';

    @Override
    public void enteredTile(Player player) {

    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
