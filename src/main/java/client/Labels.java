package client;

import javafx.scene.control.Label;

public class Labels {

    Label character;
    Label hp;
    Label damage;

    public Labels(Label character, Label hp, Label damage) {
        this.character = character;
        this.hp = hp;
        this.damage = damage;
    }

    public void setCharacter(String input) {
        this.character.setText(input);
    }

    public void setHp(String input) {
        this.hp.setText(input);
    }

    public void setDamage(String input) {
        this.damage.setText(input);
    }
}
