package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserUI implements IuiElements {
    private static boolean clickCount = false;

    public void setupWindow(Stage stage) {
        stage.setOnCloseRequest(event -> {
            client.dispatcher().executorService().shutdown();
            System.out.println("client OkHTTP was closed");
        });
        //-----------------------------------------------
        // set up main stage
        //-----------------------------------------------
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);

        stage.setMaxWidth(WIDTH);
        stage.setMaxHeight(HEIGHT);
        stage.setTitle("YouTube Search");
        //-----------------------------------------------

        //-----------------------------------------------
        //* search UI: buttons and fields
        //-----------------------------------------------
        //* setting up search button properties
        searchButton.setMinWidth(75);
        searchButton.setMaxWidth(75);
        searchButton.setText("Search");

        //* setting up advanced button properties
        advancedSearchButton.setMinWidth(100);
        advancedSearchButton.setMaxWidth(100);
        advancedSearchButton.setText("Advanced");

        VBox searchBoxFull = new VBox(searchBox, searchBoxExtend);

        searchBoxExtend.setVisible(clickCount);
        advancedSearchButton.setOnMouseClicked(event -> {
            clickCount = !clickCount;
            if(clickCount){
                searchBoxExtend.setVisible(true);
                System.out.println("show advanced fields");
            }else {
                searchBoxExtend.setVisible(false);
                System.out.println("hide advanced fields");
            }
        });

        //* setting search text field
        searchText.setPrefWidth(stage.getWidth() - 260);


        searchBoxFull.setSpacing(10);
        //-----------------------------------------------

        //* setting up search box properties, contain search field and search button + advanced button
        searchBoxFull.resize(scene.getWidth(), scene.getHeight());
        searchBoxFull.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        searchBox.setSpacing(15);
        searchBox.setPadding(new Insets(10, 5, 5, 5));
        searchBoxExtend.setSpacing(15);
        searchBoxExtend.setPadding(new Insets(5, 5, 5, 5));

        // setting up search results box
        resultsList.setPrefWidth(WIDTH - 50);
        resultsBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: cyan;");
        resultsBox.setLayoutY(110);
        resultsBox.setMaxSize(WIDTH, HEIGHT - 110);

        root.setPrefWidth(scene.getWidth());
        root.setMaxWidth(scene.getWidth());
        root.getChildren().addAll(searchBoxFull, resultsBox);

//        text.setWrapText(true);
    }
}