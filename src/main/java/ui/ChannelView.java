package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ChannelView {
    private Label name;
    private Label description;

    public Pane newChannelPane() {
        Pane pane = new Pane();
        return pane;
    }
}
