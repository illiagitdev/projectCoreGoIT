package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import okhttp3.OkHttpClient;

public interface IuiElements {
    //stage settings
    int HEIGHT = 700;
    int WIDTH = 850;

    Pane rootHeader = new Pane();

    ObjectMapper mapper = new ObjectMapper();
    OkHttpClient client = new OkHttpClient();

    //search set
    Button searchButton = new Button();
    Button advancedSearchButton = new Button();
    TextField searchText = new TextField();

    //advanced components
    TextField maxRes = new TextField();
    TextField daysPublished = new TextField();
    HBox searchBoxExtend = new HBox(maxRes, daysPublished);

    // search results
    ListView resultsList = new ListView();
    TextArea text = new TextArea();
    VBox resultsBox = new VBox(text, resultsList);

    Label labelMaxRes = new Label("Max Results");
}
