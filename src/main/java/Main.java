import javafx.application.Application;
import javafx.stage.Stage;
import services.OperationalFX;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
//        Operational operational = new Operational();
//        operational.run(primaryStage);
//        primaryStage.show();

        //redo in new fashion
        OperationalFX entryPoint = new OperationalFX();
        entryPoint.run(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
