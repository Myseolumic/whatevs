package server;

public class Player {

    private final int id;
    private int x, y;
    //private String type?

    public Player(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void modX(int value) {
        this.x += value;
    }

    public void modY(int value) {
        this.y += value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getResourcePath() {
        return "TileSprites/hedgehogPort.png";
    }
}
