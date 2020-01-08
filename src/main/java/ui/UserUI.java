package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserUI implements IuiElements {
    private static int clickCount = 0;

    public void setupWindow(Stage stage) {
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
//        advancedSearchButton.setOnMouseClicked(event -> {
//            if(clickCount % 2 != 0){
//                clickCount++;
//                searchBoxFull.getChildren().remove(2);
//                System.out.println("without advanced fields");
//            }else {
//                searchBoxFull.getChildren().add(searchBoxExtend);
//                System.out.println("without advanced fields");
//                clickCount++;
//            }
//        });

        //* setting search text field
        searchText.setPrefWidth(stage.getWidth() - 250);


        searchBoxFull.setSpacing(15);
        //-----------------------------------------------

        //* setting up search box properties, contain search field and search button + advanced button
        searchBoxFull.resize(scene.getWidth(), scene.getHeight());
        searchBoxFull.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        searchBox.setSpacing(15);
        searchBox.setPadding(new Insets(10, 10, 5, 10));
        searchBoxExtend.setSpacing(15);
        searchBoxExtend.setPadding(new Insets(5, 10, 5, 10));

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

        text.setWrapText(true);
    }

//    public void setupUI(Group root) {
//
//        labelMaxRes.setLabelFor(maxRes);
//        Pane paneHeader = new Pane();
//        Pane paneHeaderAdv = new Pane();
//        Pane paneBody = new Pane();
//
//        paneHeader.setMaxHeight(75);
//        paneHeader.setMinHeight(75);
//
////        paneHeader.getChildren().addAll(searchBox);
//
//        paneHeaderAdv.setMaxHeight(75);
//        paneHeaderAdv.setMinHeight(75);
//
////        paneHeaderAdv.getChildren().addAll(searchBoxFull);
//
//        paneBody.setLayoutY(75);
//        paneBody.getChildren().addAll(text);
//
//        root.getChildren().addAll(paneHeader, paneBody);
//    }

}