package server;

import main.Player;

public class Tree implements Tile{
    char symbol = '⇞';

    @Override
    public void enteredTile(Player player) {

    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
