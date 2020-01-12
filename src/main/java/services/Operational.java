package services;

import controlers.SearchControls;
import javafx.stage.Stage;
import ui.WindowUI;

public class Operational {

    public void run(Stage primaryStage) {
        Stage window = primaryStage;
        Stage channelWindow;
        // configure iu: all things on main screen
        WindowUI userUI = new WindowUI();

        userUI.setupWindow(window);

        SearchControls controls = new SearchControls();
        controls.simpleSearch();
        controls.advancedSearch();
    }
}
