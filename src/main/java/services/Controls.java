package services;

import apiConnection.BuildHttpRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import responseAll.ResponseVideoAPI;
import responseAll.components.Items;
import responseAll.components.Thumbnails;
import result.SearchResult;
import ui.ConsoleColors;
import ui.IuiElements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controls implements IuiElements {
    public void simpleSearch() {

        searchButton.setOnMouseClicked(event -> {
            searchEngine("25");
        });
    }

    public void advancedSearch() {
        // advanced search - //todo: write UI and implementation
        searchButtonAdvanced.setOnMouseClicked(event -> {
            //todo: test for int value
            String value1 = isInt(maxRes.getText()) ? maxRes.getText() : "0";
            String value2 = isInt(daysPublished.getText()) ? daysPublished.getText() : "";
            System.out.println("on implementation stage" + this.getClass().getSimpleName()
                    + "\nmaxRes = " + value1 + "\tdaysPublished = " + value2);

            searchEngine(value1);
        });
    }

    private boolean isInt(String text) {
        try {
            Integer intValue = Integer.valueOf(text);
        } catch (NumberFormatException e) {
            System.out.println("not number " + "isInt() from " + this.getClass().getSimpleName());
            return false;
        }
        return true;
    }

    private void searchEngine(String value1) {
        String str = searchText.getText();
        // skipp search if no text for empty search
        if (str.equals("")) {
            System.out.println("No search text!!!" + this.getClass().getSimpleName());
            return;
        }

        Call call = client.newCall(new Request.Builder().
                url(BuildHttpRequest.buildHttpUrl(searchText.getText(), value1))
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

                    System.out.println(ConsoleColors.BLUE_BOLD + "Search request: " + str + ConsoleColors.RESET +
                            "\nResponse code: " + ConsoleColors.RED_BOLD_BRIGHT + response.code() + ConsoleColors.RESET + "\n");

                    //show response in separate text field

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
        Platform.runLater(()->{
            resultsList.setItems(observableList);
        });
    }

    //todo: select image to video
    private String getFirstUrl(Thumbnails thumbnails) {
//        if (!thumbnails.getStandard().getUrl().equals(null)){
//            return thumbnails.getStandard().getUrl();
//        }
        return "https://i.ytimg.com/vi/yWpKll3G_a0/default.jpg";
    }

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
