package server;

public class Player implements Moveable {

    private final int id;
    private int x,y;
    private int health = 20;
    private String name = "jaanus";

    public Player(int id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void modX(int value){
        this.x += value;
    }

    public void modY(int value){
        this.y += value;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String getResourcePath() {
        return "TileSprites/Playertest.png";
    }
}
