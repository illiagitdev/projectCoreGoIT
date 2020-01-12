package services;

import controlers.SearchControls;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.SearchLayout;
import ui.WindowUI;

public class Operational {

    public void run(Stage primaryStage) {
        //todo: scenes to switch if channel is implemented
        Stage window = primaryStage;
        Scene mainScene, channelScene;

        // configure iu: all things on main screen
        WindowUI userUI = new WindowUI();
        // version 0.0.1
        SearchLayout searchLayout = new SearchLayout();
        mainScene = new Scene(searchLayout.searchPanel());

        userUI.setupWindow(window);

        SearchControls controls = new SearchControls();
        controls.simpleSearch();
        controls.advancedSearch();
    }
}
