package ui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class ChannelUI {
    private Scene channelScene;
    SearchLayout searchLayout;

    public Scene design(){
        GridPane gridPane = searchLayout.searchPanel();



        return new Scene(gridPane);
    }
}
