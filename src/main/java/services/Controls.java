package services;

import apiConnection.BuildHttpRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
import okhttp3.*;
import responseAll.ResponseVideoAPI;
import responseAll.components.Items;
import responseAll.components.Thumbnails;
import result.SearchResult;
import ui.ConsoleColors;
import ui.IuiElements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controls implements IuiElements {
    private static HttpUrl http;
    public void simpleSearch() {
        searchButton.setOnMouseClicked(event -> {
            String str = searchText.getText();
            // skipp search if no text for empty search
            if (str.isEmpty()) {
                System.out.println("No search text!!!" + this.getClass().getSimpleName());
                return;
            }
            http = BuildHttpRequest.buildHttpUrl(searchText.getText());
            searchEngine(http);
        });
    }

    public void advancedSearch() {
        searchButtonAdvanced.setOnMouseClicked(event -> {
            String str = searchText.getText();
            // skipp search if no text for empty search
            if (str.isEmpty()) {
                System.out.println("No search text!!!" + this.getClass().getSimpleName());
                return;
            }

            String value1 = maxRes.getText();
            if (!isPositiveInteger(value1)){
                System.out.println("No maxRes defined!!!" + this.getClass().getSimpleName());
                return;
            }

            String value2 = daysPublished.getText();
            if (!isPositiveInteger(value2)){
                System.out.println("No daysPublished defined!!!" + this.getClass().getSimpleName());
                return;
            }

            LocalDate newDate = LocalDate.now().minusDays(Integer.valueOf(value2));
            LocalDateTime seekingDate = LocalDateTime.of(newDate, LocalTime.MIDNIGHT);
            String publishedAfter = seekingDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));

            System.out.println("on implementation stage" + this.getClass().getSimpleName()
                    + "\nmaxRes = " + value1 + "\tdaysPublished = " + value2 + " : " + publishedAfter);

            http = BuildHttpRequest.buildHttpUrl(searchText.getText(), value1, publishedAfter);
            searchEngine(http);
        });
    }

    private boolean isPositiveInteger(String text) {
        if(text.isEmpty()) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if(i == 0 && text.charAt(i) == '-'){
                if(text.length() == 1){
                    return false;
                }
            }
            if(Character.digit(text.charAt(i), 10) < 0){
                return false;
            }
        }
        return true;
    }

    private void searchEngine(HttpUrl http) {
        Call call = client.newCall(new Request.Builder().
                url(http)
                .get()
                .build());
        // asynchronous call
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && isSuccess(response)) {
                    // response from YouTube
                    ResponseVideoAPI responseYoutube = mapper.readValue(response.body().bytes(), new TypeReference<ResponseVideoAPI>() {
                    });

                    System.out.println(ConsoleColors.BLUE_BOLD + "Search request: " + searchText.getText() + ConsoleColors.RESET +
                            "\nResponse code: " + ConsoleColors.RED_BOLD_BRIGHT + response.code() + ConsoleColors.RESET + "\n");

                    showSearchResults(responseYoutube);
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Error:");
                System.out.println(e.getStackTrace());
            }
        });
    }

    // get response from click on search button
    private void showSearchResults(ResponseVideoAPI responseYoutube) {
        // components from response we need: will be thread safe with internal builder
        List<SearchResult> searchResults = new ArrayList<>();
        SearchResult result;
        List<Items> items = responseYoutube.getItems();
        System.out.println("items.size() = " + items.size() + "\n"
                + "responseYoutube.getItems().size() = " + responseYoutube.getItems().size() + "\n");
        for (Items item : items) {
            result = new SearchResult.Builder()
                    .setVideoName(item.getSnippet().getTitle())
                    .setChannelName(item.getSnippet().getChannelTitle())
                    .setPublicationDate(item.getSnippet().getPublishedAt())
                    .setUrlID(item.getId().getVideoId())
                    .setUrlIDChannel(item.getId().getChannelId())
                    .setUrlPathToImage(getFirstUrl(item.getSnippet().getThumbnails()))
                    .build();
            searchResults.add(result);
        }

        List<GridPane> sample = new ArrayList<>();
        for (SearchResult searchResult : searchResults) {
            sample.add(new SearchResultView(searchResult).newList());
        }

        ObservableList<GridPane> observableList = FXCollections.observableList(sample);

        //make task run later in main FX thread save from - "IllegalStateException: Not on FX application thread"
        Platform.runLater(() -> {
            resultsList.setItems(observableList);
        });
    }

    //todo: select image to video
    private String getFirstUrl(Thumbnails thumbnails) {
//        if (!thumbnails.getStandard().getUrl().equals("")) {
//            System.out.println("thumbnails.getStandard().getUrl() = " + thumbnails.getStandard().getUrl() +
//                    " || getFirstUrl: " + thumbnails.getClass().getSimpleName());
//            return thumbnails.getStandard().getUrl();
//        } else if (!thumbnails.getRandom().getUrl().equals("")) {
//            System.out.println("thumbnails.getStandard().getUrl() = " + thumbnails.getRandom().getUrl() +
//                    " || getFirstUrl: " + thumbnails.getClass().getSimpleName());
//            return thumbnails.getRandom().getUrl();
//        } else if (!thumbnails.getMedium().getUrl().equals("")) {
//            System.out.println("thumbnails.getStandard().getUrl() = " + thumbnails.getMedium().getUrl() +
//                    " || getFirstUrl: " + thumbnails.getClass().getSimpleName());
//            return thumbnails.getMedium().getUrl();
//        }
        return "https://i.ytimg.com/vi/yWpKll3G_a0/default.jpg";
    }

    //todo: what to do with this???
    private boolean isSuccess(Response response) {
        int code = response.code();
        switch (code) {
            case 200:
                return true;
            case 400: {
                System.out.println("something went wrong");
                return false;
            }
            default: {
                System.out.println("not best result");
                return false;
            }
        }
    }
}
