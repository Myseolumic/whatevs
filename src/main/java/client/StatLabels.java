package client;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class StatLabels {

    Label name;
    Label character;
    Label hp;
    Label damage;

    public StatLabels(Label name, Label character, Label hp, Label damage) {
        this.name = name;
        this.character = character;
        this.hp = hp;
        this.damage = damage;
    }

    public void setCharacter(String input) {
        this.character.setText("Animal: " + input);
    }

    public void setHp(String input, String maxhp) {
        Platform.runLater(
                () -> {
                    this.hp.setText("Health: " + input + "/" + maxhp);
                }
        );

    }

    public void setName(String input) {
        this.name.setText("Name: " + input);
    }

    public void setDamage(String input) {
        this.damage.setText("Damage: " + input);
    }
}
