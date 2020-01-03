package ui;

import apiConnection.BuildHttpRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import okhttp3.*;
import responseAll.ResponseVideoAPI;
import responseAll.components.Items;
import result.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserUI {
    //stage settings
    private static final int HEIGHT = 500;
    private static final int WIDTH = 650;

    private static ObjectMapper mapper = new ObjectMapper();
    private OkHttpClient client = new OkHttpClient();

    //search set
    private Button searchButton = new Button();
    private Button advancedSearchButton = new Button();
    private TextField searchText = new TextField();

    //advanced components
    private TextField maxRes = new TextField();
    private TextField daysPublished = new TextField();
    private HBox searchBoxExtend = new HBox(maxRes, daysPublished);

    private TextArea text = new TextArea();
    private Label labelMaxRes = new Label("Max Results");

    public void setupWindow(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        setupUI(root);

        //---------------------------------------
        //* set up main stage
        stage.setWidth(Double.MAX_VALUE);
        stage.setHeight(Double.MAX_VALUE);

        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.setTitle("YouTube Search");

        //-----------------------------------------
        //* setting up search button properties
        searchButton.setMinWidth(75);
        searchButton.setMaxWidth(75);
        searchButton.setText("Search");

        //* setting up advanced button properties
        advancedSearchButton.setMinWidth(100);
        advancedSearchButton.setMaxWidth(100);
        advancedSearchButton.setText("Advanced");

        //* setting search text field
        searchText.setPrefWidth(Double.MAX_VALUE);

        HBox searchBox = new HBox(searchText, searchButton, advancedSearchButton);

        //* setting up search box properties, contain search field and search button + advanced button
        searchBox.setSpacing(15);
//        searchBox.setLayoutX(35);
//        searchBox.setLayoutY(25);
//        searchBox.setPrefWidth(300);
        searchBox.setPadding(new Insets(15,20,10,10));root.getChildren().addAll(searchBox);

        VBox searchBoxFull = new VBox(searchBox, searchBoxExtend, text);

        searchBoxFull.setSpacing(15);
        text.setWrapText(true);
//
    }

    public void setupUI(Group root) {

        labelMaxRes.setLabelFor(maxRes);
        Pane paneHeader = new Pane();
        Pane paneHeaderAdv = new Pane();
        Pane paneBody = new Pane();

        paneHeader.setMaxHeight(75);
        paneHeader.setMinHeight(75);

//        paneHeader.getChildren().addAll(searchBox);

        paneHeaderAdv.setMaxHeight(75);
        paneHeaderAdv.setMinHeight(75);

//        paneHeaderAdv.getChildren().addAll(searchBoxFull);

        paneBody.setLayoutY(75);
        paneBody.getChildren().addAll(text);

        root.getChildren().addAll(paneHeader, paneBody);
    }

    public void setViewWindow(List<SearchResult> searchResults, Group root) {

        ObservableList<SearchResult> observableList = FXCollections.observableList(searchResults);
        ListView<SearchResult> resultListView = new ListView<>();
        root.getChildren().addAll(resultListView);
//        StackPane pane = new StackPane();
//        Scene scene = new Scene(pane, 300, 150);
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

    public void simpleSearch() {

        searchButton.setOnMouseClicked(event -> {
            String str = searchText.getText();

            // skipp search if no text for empty search
            if (str.equals("")) {
                System.out.println("No search text!!!");
                return;
            }

            Call call = client.newCall(new Request.Builder().
                    url(BuildHttpRequest.buildHttpUrl(searchText.getText()))
                    .get()
                    .build());
            // asynchronous call
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful() && (response.code() == 200)) {
                        // response from YouTube
                        ResponseVideoAPI responseYoutube = mapper.readValue(response.body().bytes(), new TypeReference<ResponseVideoAPI>() {});

                        System.out.println(ConsoleColors.BLUE_BOLD + "Search request: " + str + ConsoleColors.RESET +
                                "\nResponse code: " + ConsoleColors.RED_BOLD_BRIGHT + response.code() + ConsoleColors.RESET + "\n");

                        //show response in separate text field
                        showSearchResults(responseYoutube);
                    }
                    // appears exception in dispatcher
//                    client.dispatcher().executorService().shutdown();
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("Error:");
                    System.out.println(e.getStackTrace());
                }
            });
        });
    }

    public void advancedSearch() {
        // advanced search - //todo: write UI and implementation
        advancedSearchButton.setOnMouseClicked(event -> {

        });
    }

    // get response from click on search button
    private void showSearchResults(ResponseVideoAPI responseYoutube) {
        text.setText(responseYoutube.toString() + "\n");

        // components from response we need: will be thread safe with internal builder
        List<SearchResult> searchResults = new ArrayList<>();
        SearchResult result;
        List<Items> items = responseYoutube.getItems();
        for (int i = 0; i < items.size(); i++) {
            result = new SearchResult.Builder()
                    .setVideoName(items.get(i).getSnippet().getTitle())
                    .setChannelName(items.get(i).getSnippet().getChannelTitle())
                    .setPublicationDate(items.get(i).getSnippet().getPublishedAt())
                    .setUrlID(items.get(i).getId().getVideoId())
                    .setUrlIDChannel(items.get(i).getId().getChannelId())
                    .build();

            searchResults.add(result);
            for (SearchResult x : searchResults) {
                System.out.println(x.toString());
            }
        }
    }
}