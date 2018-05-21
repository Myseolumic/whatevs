package client;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class StatsAndPort {

    private GridPane statsAndPort;
    private VBox stats;

    public StatsAndPort(GridPane statsAndPort, VBox stats) {
        this.statsAndPort = statsAndPort;
        this.stats = stats;
    }

    public void setImage(ImageView image) {
        Platform.runLater(() -> {
            statsAndPort.getChildren().clear();
            statsAndPort.add(image,0,0);
            statsAndPort.add(stats,0,0);
        });
    }
}
