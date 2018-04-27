package Invenoty;

public class TestItem implements Item {

    @Override
    public int getBonus() {
        return 2;
    }

    @Override
    public String getResourcePath() {
        return "Items/pewpew.png";
    }

    @Override
    public String getName() {
        return "PowPow";
    }

}
