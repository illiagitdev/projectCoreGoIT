package controlers;

import apiConnection.BuildHttpRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import messageBoxes.AlertBox;
import okhttp3.*;
import responseAll.ResponseVideoAPI;
import responseAll.components.Items;
import responseAll.components.Thumbnails;
import result.SearchResult;
import ui.ChannelView;
import ui.SearchResultView;
import ui.ConsoleColors;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchControlsFX implements Controls{
    private static HttpUrl http;
    private static ObjectMapper mapper = new ObjectMapper();

    public static OkHttpClient getClient() {
        return client;
    }

    public void channelSearch(String channelID, ListView<GridPane> listView){
        http = BuildHttpRequest.buildChannelHttpUrl(channelID);
        searchEngine(http, listView, false);
    }

    public void simpleSearch(String searchText, ListView<GridPane> listView) {
        // skipp search if no text for empty search
        if (searchText.isEmpty()) {
            System.out.println("No search text!!!" + this.getClass().getSimpleName());
            AlertBox.display("Search text", "There is no text in search field!");
            return;
        }
        System.out.println(ConsoleColors.BLUE_BOLD + "Search request: " + searchText + ConsoleColors.RESET);
        http = BuildHttpRequest.buildHttpUrl(searchText);
        searchEngine(http, listView, true);
    }

    public void advancedSearch(String searchText, String maxRes, String daysPublished, ListView<GridPane> resultsList) {
            // skipp search if no text for empty search
            if (searchText.isEmpty()) {
                System.out.println("No search text!!!" + this.getClass().getSimpleName());
                AlertBox.display("Search text", "There is no text in search field!");
                return;
            }

            if (notPositiveInteger(maxRes)) {
                System.out.println("No maxRes defined!!!" + this.getClass().getSimpleName());
                AlertBox.display("Max Results", "Max results not indicated!");
                return;
            }

            if (notPositiveInteger(daysPublished)) {
                System.out.println("No daysPublished defined!!!" + this.getClass().getSimpleName());
                AlertBox.display("Days published", "Days published not indicated!");
                return;
            }

            //reformat date for search request to 1970-01-01T00:00:00Z
            LocalDate newDate = LocalDate.now().minusDays(Integer.parseInt(daysPublished));
            LocalDateTime seekingDate = LocalDateTime.of(newDate, LocalTime.MIDNIGHT);
            String publishedAfter = seekingDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));

            System.out.println("on implementation stage" + this.getClass().getSimpleName()
                    + "\nmaxRes = " + maxRes + "\tdaysPublished = " + daysPublished + " : " + publishedAfter);

            http = BuildHttpRequest.buildHttpUrl(searchText, maxRes, publishedAfter);
            searchEngine(http, resultsList, true);
    }

    private boolean notPositiveInteger(String text) {
        if (text.isEmpty()) {
            return true;
        }
        for (int i = 0; i < text.length(); i++) {
            if (i == 0 && text.charAt(i) == '-') {
                if (text.length() == 1) {
                    return true;
                }
            }
            if (Character.digit(text.charAt(i), 10) < 0) {
                return true;
            }
        }
        return false;
    }

    private void searchEngine(HttpUrl http, ListView<GridPane> listView, boolean generalSearch) {
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
                    assert response.body() != null : "response.body() = null";
                    ResponseVideoAPI responseYoutube = mapper.readValue(response.body().bytes(), new TypeReference<ResponseVideoAPI>() {
                    });
                    System.out.println("Response code: " + ConsoleColors.RED_BOLD_BRIGHT + response.code() + ConsoleColors.RESET + "\n");

                    // components from response we need: will be thread safe with internal builder
                    List<SearchResult> searchResults = new ArrayList<>();
                    System.out.println("ResponseYoutube.getItems().size() = " + responseYoutube.getItems().size() + "\n");
                    SearchResult result;

                    for (Items item : responseYoutube.getItems()) {
                        if (item.getId().getVideoId() != null) {
                            result = new SearchResult.Builder()
                                    .setVideoName(item.getSnippet().getTitle())
                                    .setChannelName(item.getSnippet().getChannelTitle())
                                    .setPublicationDate(item.getSnippet().getPublishedAt())
                                    .setUrlID(item.getId().getVideoId())
                                    .setUrlIDChannel(item.getId().getChannelId())
                                    .setUrlPathToImage(getFirstUrl(item.getSnippet().getThumbnails()))
                                    .build();
                            searchResults.add(result);

                            //test
                            System.out.println("\tRetrieved: video = " + item.getId().getVideoId() + " channel = " + item.getId().getChannelId());
                            System.out.println("\tResponseAPI: video = " + result.getUrlID() + " channel = " + result.getUrlIDChannel());
                        }
                    }

                    // return either objects for general search or channel search
                    // current solution
                    List<GridPane> sample = new ArrayList<>();
                    if(generalSearch) {
                        for (SearchResult searchResult : searchResults) {
                            sample.add(new SearchResultView(searchResult).newList());
                        }
                    }else {
                        for (SearchResult searchResult : searchResults) {
                            sample.add(new ChannelView(searchResult).newList());
                        }
                    }

                    //make task run later in main FX thread save from - "IllegalStateException: Not on FX application thread"
                    ObservableList<GridPane> observableList = FXCollections.observableList(sample);
                    Platform.runLater(() -> listView.setItems(observableList));
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                try {
                    if(call.execute().code() >= 400)
                        System.out.println("Error on client side!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Error:");
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        });
    }

    private String getFirstUrl(Thumbnails thumbnails) {
        String url;
        if (thumbnails.getRandom() != null) {
            url = thumbnails.getRandom().getUrl();
            System.out.println("thumbnails.getStandard().getUrl() = " + url +
                    " || getFirstUrl: " + thumbnails.getClass().getSimpleName());
            return url;
        } else if (thumbnails.getMedium() != null) {
            url = thumbnails.getMedium().getUrl();
            System.out.println("thumbnails.getStandard().getUrl() = " + url +
                    " || getFirstUrl: " + thumbnails.getClass().getSimpleName());
            return url;
        } else if (thumbnails.getStandard() != null) {
            url = thumbnails.getStandard().getUrl();
            System.out.println("thumbnails.getStandard().getUrl() = " + url +
                    " || getFirstUrl: " + thumbnails.getClass().getSimpleName());
            return url;
        } else if (thumbnails.getHigh() != null) {
            url = thumbnails.getHigh().getUrl();
            System.out.println("thumbnails.getStandard().getUrl() = " + url +
                    " || getFirstUrl: " + thumbnails.getClass().getSimpleName());
            return url;
        } else if (thumbnails.getMaxres() != null) {
            url = thumbnails.getMaxres().getUrl();
            System.out.println("thumbnails.getStandard().getUrl() = " + url +
                    " || getFirstUrl: " + thumbnails.getClass().getSimpleName());
            return url;
        }
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
