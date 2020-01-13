package messageBoxes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MessageBoxYesNo {

    private static boolean status;

    public static boolean display(String title, String message){
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        Label labelTitle = new Label(message);
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        HBox buttons = new HBox(yesButton, noButton);
        VBox layout = new VBox(15);
        layout.getChildren().addAll(labelTitle, buttons);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

        yesButton.setOnAction(e -> {
            status = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            status = false;
            window.close();
        });

        return status;
    }
}
