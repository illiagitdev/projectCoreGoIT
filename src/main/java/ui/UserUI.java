package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import result.SearchResult;

import java.util.List;

public class UserUI {
    private static final int HEIGHT = 500;
    private static final int WIDTH = 650;
    public static Button searchButton = new Button();
    public static Button advancedSearchButton = new Button();
    public static TextField searchText = new TextField();
    public static TextArea text = new TextArea();
    public static HBox searchBox = new HBox(searchText, searchButton, advancedSearchButton);
    public static Label labelMaxRes = new Label("Max Results");
    public static TextField maxRes = new TextField();
    public static TextField daysPublished = new TextField();
    public static HBox searchBoxExtend = new HBox(maxRes, daysPublished);
    public static VBox searchBoxFull = new VBox(searchBox, searchBoxExtend, text);

    public void setupWindow(Stage stage) {
        //* set up main stage
        stage.setMaxWidth(WIDTH * 2);
        stage.setMaxHeight(HEIGHT * 2);

        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.setTitle("YouTube Search");

        //* setting up search button properties
        searchButton.setMinWidth(75);
        searchButton.setMaxWidth(75);
        searchButton.setText("Search");

        //* setting up advanced button properties
        advancedSearchButton.setMinWidth(100);
        advancedSearchButton.setMaxWidth(100);
        advancedSearchButton.setText("Advanced");

        //* setting up search box properties, contain search field and search button + advanced button
        searchBox.setSpacing(15);
        searchBox.setLayoutX(35);
        searchBox.setLayoutY(25);
        searchBox.setPrefWidth(300);

        searchBoxFull.setSpacing(15);
        text.setWrapText(true);
//
    }

    public void setupUI(Group root) {
        root.getChildren().addAll(searchBox);

        labelMaxRes.setLabelFor(maxRes);
        Pane paneHeader = new Pane();
        Pane paneHeaderAdv = new Pane();
        Pane paneBody = new Pane();

        paneHeader.setMaxHeight(75);
        paneHeader.setMinHeight(75);

        paneHeader.getChildren().addAll(searchBox);

        paneHeaderAdv.setMaxHeight(75);
        paneHeaderAdv.setMinHeight(75);

        paneHeaderAdv.getChildren().addAll(searchBoxFull);

        paneBody.setLayoutY(75);
        paneBody.getChildren().addAll(text);

        root.getChildren().addAll(paneHeader, paneBody);
    }

    public void setViewWindow(List<SearchResult> searchResults, Group root) {

        ObservableList<SearchResult> observableList = FXCollections.observableList(searchResults);
        ListView<SearchResult> resultListView = new ListView<>();
        root.getChildren().addAll(resultListView);
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, 300, 150);
//        ObservableList<String> list = FXCollections.observableArrayList(
//                "Item 1", "Item 2", "Item 3", "Item 4");
//        ListView<String> lv = new ListView<>(list);
//        lv.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
//            @Override
//            public ListCell<String> call(ListView<String> param) {
//                return new SearchResult();
//            }
//        });
//        pane.getChildren().add(lv);
    }
}