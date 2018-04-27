package client;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class StatLabels {

    Label name;
    Label hp;
    Label damage;

    public StatLabels(Label name, Label hp, Label damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

    public void setHp(String input, String maxhp, boolean isAlive) {
        Platform.runLater(
                () -> {
                    if(isAlive) {
                        this.hp.setText("Health: " + input + "/" + maxhp);
                    } else {
                        this.hp.setText("Health: DEAD");
                    }

                }
        );

    }

    public void setName(String name, String animal) {
        this.name.setText("Name: " + name + " The " + animal);
    }

    public void setDamage(String input) {
        Platform.runLater(
                () -> this.damage.setText("Damage: " + input)
        );
    }
}
