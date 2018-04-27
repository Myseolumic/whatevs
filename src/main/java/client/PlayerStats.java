package client;

public class PlayerStats {
    private String name;
    private String animalClass;
    private String portraitPath;
    private int health;
    private int maxHealth;
    private int dmg;
    private boolean isAlive = true;


    public PlayerStats(String name, String animalClass,String portraitPath, int Health, int dmg) {
        this.portraitPath = portraitPath;
        this.name = name;
        this.animalClass = animalClass;
        this.health = Health;
        this.maxHealth = Health;
        this.dmg = dmg;
    }

    public String getName() {
        return name;
    }

    public String getPortraitPath() {
        return portraitPath;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            isAlive = false;
        }
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public String getAnimalClass() {
        return animalClass;
    }

    public int getHealth() {
        return health;
    }

    public int getDmg() {
        return dmg;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
