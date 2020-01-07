import javafx.application.Application;
import javafx.stage.Stage;
import ui.UserUI;

public class Main extends Application {
    private UserUI userUI = new UserUI();

    @Override
    public void start(Stage primaryStage) {
        // configure iu: all things on main screen
        userUI.setupWindow(primaryStage);
//        primaryStage.show();

        userUI.simpleSearch();
        userUI.advancedSearch();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
