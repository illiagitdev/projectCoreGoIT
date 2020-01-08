package services;

import apiConnection.BuildHttpRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import responseAll.ResponseVideoAPI;
import responseAll.components.Items;
import result.SearchResult;
import ui.ConsoleColors;
import ui.IuiElements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controls implements IuiElements {
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
                    if (response.isSuccessful() && isSuccess(response)) {
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
        // components from response we need: will be thread safe with internal builder
        List<SearchResult> searchResults = new ArrayList<>();
        SearchResult result;
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
//        items.get(0).getSnippet().getThumbnails().
            searchResults.add(result);
        }
        text.appendText(searchResults.toString());

        List<GridPane> sample = new ArrayList<>();
        for (SearchResult searchResult : searchResults) {
            sample.add(new SearchResultView(searchResult).newList());
        }

        ObservableList<GridPane> observableList = FXCollections.observableList(sample);
        resultsList.setItems(observableList);
    }

    private boolean isSuccess(Response response) {
        int code = response.code();
        switch (code){
            case 200:
                return true;
            case 400:{
                System.out.println("something went wrond");
                return false;
            }
            default:{
                System.out.println("not best result");
                return false;
            }
        }
    }
}
