package client;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class StatLabels {

    private Label name;
    private Label hp;
    private Label damage;
    private Label defence;

    public StatLabels(Label name, Label hp, Label damage, Label defence) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.defence = defence;
    }

    public void setHp(String input, String maxhp, boolean isAlive) {
        Platform.runLater(
                () -> {
                    if (isAlive) {
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

    public void setDefence(String input){
        Platform.runLater(
                () -> this.defence.setText("Defence: " + input)
        );
    }

}
