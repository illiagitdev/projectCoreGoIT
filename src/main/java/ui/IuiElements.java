package ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import okhttp3.OkHttpClient;

public interface IuiElements {
    //stage settings
    int HEIGHT = 800;
    int WIDTH = 950;

    ObjectMapper mapper = new ObjectMapper();
    OkHttpClient client = new OkHttpClient();

    Pane root = new Pane();

    //search set
    Button searchButton = new Button();
    Button advancedSearchButton = new Button();
    TextField searchText = new TextField();
    HBox searchBox = new HBox(searchText, searchButton, advancedSearchButton);


    //advanced components
    Label maxResLabel = new Label("Max Results");
    TextField maxRes = new TextField();
    Label daysPublishedLabel = new Label("Days published");
    TextField daysPublished = new TextField();
    Button searchButtonAdvanced = new Button("Search");
    HBox searchBoxExtend = new HBox(maxRes, daysPublished, searchButtonAdvanced);

    // search results
    ListView resultsList = new ListView();
    TextArea text = new TextArea();
    VBox resultsBox = new VBox(text, resultsList);

    Label labelMaxRes = new Label("Max Results");
}
