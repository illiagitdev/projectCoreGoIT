import javafx.application.Application;
import javafx.stage.Stage;
import services.Operational;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //will do everything with main screen
        Operational operational = new Operational();
        operational.run(primaryStage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
