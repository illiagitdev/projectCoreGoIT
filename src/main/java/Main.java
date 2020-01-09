import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.Operational;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        //------------------------------------------
        String responseDate = "2013-06-19T12:05:22.000Z";
        String responseUrl = "https://yt3.ggpht.com/-CwNoyH82d6s/AAAAAAAAAAI/AAAAAAAAAAA/8dAabLfhwMI/s88-c-k-no-mo-rj-c0xffffff/photo.jpg";
        System.out.println("Response from YouTube Api: " + responseDate);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ");
        String result = responseDate;
        try {
            Date date = format.parse(responseDate);
            System.out.println("Parsed date = " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Stage newStage = new Stage();
        Pane newPane = new Pane();
        Scene newScene = new Scene(newPane);
        newStage.setScene(newScene);

        ImageView imageView = new ImageView(new Image(responseUrl));
        newPane.getChildren().addAll(imageView);
        newStage.show();
        //------------------------------------------
    }
}
