package common;

public class DirectionHolder {
    private Direction direction;

    public DirectionHolder(){
        direction = Direction.STOP;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public Direction getDirection(){
        return direction;
    }
}
