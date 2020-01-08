package ui;

import apiConnection.BuildHttpRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import okhttp3.*;
import querryResponse.YouTubeResponse;
import responseAll.ResponseVideoAPI;
import responseAll.components.Items;
import result.SearchResult;
import services.IuiElements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserUI implements IuiElements {

    public void setupWindow(Stage stage) {
        Scene scene = new Scene(rootHeader, stage.getWidth(), stage.getHeight(), Color.CYAN);
        stage.setScene(scene);
        stage.show();
//        setupUI(root);

        //---------------------------------------
        //* set up main stage
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);

        stage.setMaxWidth(WIDTH);
        stage.setMaxHeight(HEIGHT);
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
        searchText.setMaxWidth(900);
        searchText.setMinWidth(200);
        searchText.setPrefWidth(stage.getWidth() - 240);

        //* setting up search box properties, contain search field and search button + advanced button
        HBox searchBox = new HBox(searchText, searchButton, advancedSearchButton);
        searchBox.resize(scene.getWidth(), scene.getHeight());
        searchBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        searchBox.setSpacing(15);
        searchBox.setPadding(new Insets(15, 15, 10, 10));

        // setting up search results box
        resultsBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: cyan;");
        resultsBox.setLayoutY(100);
        resultsBox.setMaxSize(750, 550);

        rootHeader.setPrefWidth(scene.getWidth());
        rootHeader.setMaxWidth(scene.getWidth());
        rootHeader.getChildren().addAll(/*gridPane, */searchBox, resultsBox);

//        VBox searchBoxFull = new VBox(searchBox, searchBoxExtend, text);

//        searchBoxFull.setSpacing(15);
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
                        ResponseVideoAPI responseYoutube = mapper.readValue(response.body().bytes(), new TypeReference<ResponseVideoAPI>() {
                        });

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
            System.out.println("on implementation stage");
        });
    }

    // get response from click on search button
    private void showSearchResults(ResponseVideoAPI responseYoutube) {
//        text.setText(responseYoutube.toString() + "\n");

//        System.out.println(responseYoutube.toString() + "\n");

        // components from response we need: will be thread safe with internal builder
        List<SearchResult> searchResults = new ArrayList<>();
        SearchResult result = new SearchResult("vv", "bb", "123456", "http", "url");

        searchResults.add(result);
        List<Items> items = responseYoutube.getItems();
        System.out.println("items.size() = " + items.size() + "\n"
                + "responseYoutube.getItems().size() = " + responseYoutube.getItems().size() + "\n");
        for (int i = 0; i < items.size(); i++) {
            result = new SearchResult.Builder()
                    .setVideoName(items.get(i).getSnippet().getTitle())
                    .setChannelName(items.get(i).getSnippet().getChannelTitle())
                    .setPublicationDate(items.get(i).getSnippet().getPublishedAt())
                    .setUrlID(items.get(i).getId().getVideoId())
                    .setUrlIDChannel(items.get(i).getId().getChannelId())
                    .build();
        items.get(0).getSnippet().getThumbnails().
            searchResults.add(result);
        }
        text.appendText(searchResults.toString());


        List<GridPane> sample = new ArrayList<>();
        for (int i = 0; i < searchResults.size(); i++) {
            sample.add(new SearchResultView(searchResults.get(i)).newList());
        }

        ObservableList<GridPane> observableList = FXCollections.observableList(sample);
        resultsList.setItems(observableList);

//        SearchResultView test = new SearchResultView(searchResults.get(0));
//        rootHeader.getChildren().addAll(test);
    }
}