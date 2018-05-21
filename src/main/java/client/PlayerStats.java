package client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerStats {
    private String name;
    private String animalClass;
    private String portraitPath;
    private int health;
    private int maxHealth;
    private int dmg;
    private int defence;
    private boolean isAlive = true;


    public PlayerStats(String animalClass) {
        setFields(animalClass);
    }

    private void setFields(String animalClass) {
        String[] names = {"Sofia", "Maria", "Alisa", "Anna", "Arina", "Eliise", "Adeele", "Lenna", "Aleksandra", "Mia", "Robin", "Rasmus", "Artjom", "Artur", "Daniel", "Robert", "Oliver", "Maksim", "Oskar", "Sebastian"};
        int randomName = (int) Math.floor(Math.random() * names.length);
        this.name = names[randomName];
        String[] possible = {"Hedgehog", "Moose", "Wolf"};
        List<String> classes = new ArrayList<>(Arrays.asList(possible));
        this.animalClass = animalClass;

        switch (classes.indexOf(animalClass)) {
            case 0:
                this.dmg = 6;
                this.health = 10;
                this.maxHealth = 10;
                this.portraitPath = "TileSprites/hedgehog.png";
                this.defence = 5;
                break;
            case 1:
                this.dmg = 6;
                this.health = 20;
                this.maxHealth = 20;
                this.portraitPath = "TileSprites/moose.png";
                this.defence = 2;
                break;
            case 2:
                this.dmg = 7;
                this.health = 14;
                this.maxHealth = 14;
                this.portraitPath = "TileSprites/wolf.png";
                this.defence = 3;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public String getPortraitPath() {
        return portraitPath;
    }

    public void setHealth(int health) {
        if (health >= maxHealth) {
            this.health = this.maxHealth;
        } else {
            this.health = health;
        }
        if (this.health <= 0) {
            isAlive = false;
            this.health = 0;
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

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
}
