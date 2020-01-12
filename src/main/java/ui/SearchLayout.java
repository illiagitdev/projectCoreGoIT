package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class SearchLayout {
    private static boolean clickCount = false;

    //search set
    private Button searchButton = new Button("Search");
    private Button advancedSearchButton = new Button("Advanced");
    private TextField searchText = new TextField();
    private HBox searchBox = new HBox(searchText, searchButton, advancedSearchButton);

    //advanced components
    private TextField maxRes = new TextField();
    private TextField daysPublished = new TextField();
    private Button searchButtonAdvanced = new Button("Search");
    private HBox searchBoxExtend = new HBox(maxRes, daysPublished, searchButtonAdvanced);

    public Button getSearchButton() {
        return searchButton;
    }

    public TextField getSearchText() {
        return searchText;
    }

    public TextField getMaxRes() {
        return maxRes;
    }

    public TextField getDaysPublished() {
        return daysPublished;
    }

    public Button getSearchButtonAdvanced() {
        return searchButtonAdvanced;
    }

    public GridPane searchPanel(){
        GridPane result = new GridPane();
        result.setAlignment(Pos.CENTER);
        result.setPadding(new Insets(10, 10, 10, 10));
        result.add(searchBox, 0 , 0);
        result.add(searchBoxExtend, 1, 0);

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

        return result;
    }
}
