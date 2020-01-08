package services;

import javafx.stage.Stage;
import ui.UserUI;

public class Operational {

    public void run(Stage primaryStage) {
        // configure iu: all things on main screen
        UserUI userUI = new UserUI();

        userUI.setupWindow(primaryStage);

        Controls controls = new Controls();
        controls.simpleSearch();
        controls.advancedSearch();
    }
}
