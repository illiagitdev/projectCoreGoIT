package messageBoxes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class AlertBox {

    public static void display(String title, String message){
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
//        window.setWidth(250);

        Label labelTitle = new Label(message);
        Button button = new Button("Ok!");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(8, 8, 8, 8));
        layout.setPrefWidth(250);
        layout.getChildren().addAll(labelTitle, button);
        layout.setAlignment(Pos.CENTER);

        button.setOnMouseClicked(e -> {
            System.out.println("Alert: " + message);
            window.close();
        });

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
