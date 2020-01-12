package messageBoxes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.*;

public class AlertBox {

    public static void display(String title, String message){
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        Label labelTitle = new Label(message);
        Button button = new Button("Yes");
        HBox layout = new HBox(10);
        layout.getChildren().addAll(labelTitle, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);

        button.setOnMouseClicked(e -> {
            System.out.println("Alert: " + message);
            window.close();
        });

        window.setScene(scene);
        window.showAndWait();
    }
}
