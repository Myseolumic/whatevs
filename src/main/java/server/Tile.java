package server;

import main.Player;

public interface Tile {
    void enteredTile(Player player);
    char getSymbol();

}
