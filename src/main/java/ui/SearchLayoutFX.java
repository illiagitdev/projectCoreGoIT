package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchLayoutFX {
    private static boolean clickCount = false;

    //search set
    private Button search = new Button("Search");
    private Button advanced = new Button("Advanced");
    private TextField searchText = new TextField();
    private HBox searchBox = new HBox(searchText, search, advanced);

    //advanced components
    private TextField maxRes = new TextField();
    private TextField daysPublished = new TextField();
    private Button searchAdvanced = new Button("Search");
    private HBox searchBoxExtend = new HBox(maxRes, daysPublished, searchAdvanced);

    private VBox result;

    public SearchLayoutFX() {
        initUI();
    }

    private void initUI() {
        //* setting up search button properties
        search.setMinWidth(75);
        search.setMaxWidth(75);
        search.setText("Search");

        //* setting up advanced button properties
        advanced.setMinWidth(100);
        advanced.setMaxWidth(100);
        advanced.setText("Advanced");

        searchText.setPrefWidth(300);

        searchBox.setSpacing(15);
        searchBox.setPadding(new Insets(10, 5, 5, 5));

        searchAdvanced.setMinWidth(75);
        searchAdvanced.setMaxWidth(75);

        maxRes.setPrefWidth(142);
        daysPublished.setPrefWidth(142);

        searchBoxExtend.setSpacing(15);
        searchBoxExtend.setPadding(new Insets(5, 5, 5, 5));

        result = new VBox();
        result.setAlignment(Pos.CENTER);
        result.setSpacing(10);
        result.setPadding(new Insets(10, 10, 10, 10));
        result.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        result.getChildren().addAll(searchBox, searchBoxExtend);

        searchBoxExtend.setVisible(clickCount);
        advanced.setOnMouseClicked(event -> {
            clickCount = !clickCount;
            if(clickCount){
                searchBoxExtend.setVisible(true);
                System.out.println("show advanced fields");
            }else {
                searchBoxExtend.setVisible(false);
                System.out.println("hide advanced fields");
            }
        });
    }

    public Button getSearch() {
        return search;
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

    public Button getSearchAdvanced() {
        return searchAdvanced;
    }

    public VBox searchPanel() {
        searchBoxExtend.setVisible(clickCount);
        advanced.setOnMouseClicked(event -> {
            clickCount = !clickCount;
            if (clickCount) {
                searchBoxExtend.setVisible(true);
                System.out.println("show advanced fields");
            } else {
                searchBoxExtend.setVisible(false);
                System.out.println("hide advanced fields");
            }
        });
        return result;
    }
}
