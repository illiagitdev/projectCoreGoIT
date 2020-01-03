import apiConnection.BuildHttpRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.stage.Stage;
import okhttp3.*;
import querryResponse.YouTubeResponse;
import querryResponse.components.Items;
import result.SearchResult;
import ui.ConsoleColors;
import ui.UserUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static ObjectMapper mapper = new ObjectMapper();
    private OkHttpClient client = new OkHttpClient();
    private UserUI userUI = new UserUI();

    @Override
    public void start(Stage primaryStage) {
        // configure iu: all things on main screen
        userUI.setupWindow(primaryStage);
        primaryStage.show();

        services();
    }

    private void services() {
        // advanced search - //todo: write UI and implementation
        UserUI.advancedSearchButton.setOnMouseClicked(event -> {

        });

        UserUI.searchButton.setOnMouseClicked(event -> {
            String str = UserUI.searchText.getText();

            // skipp search if no text for empty search
            if (str.equals("")) {
                System.out.println("No search text!!!");
                return;
            }

            Call call = client.newCall(new Request.Builder().
                    url(BuildHttpRequest.buildHttpUrl(UserUI.searchText.getText()))
                    .get()
                    .build());
            // asynchronous call
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful() && (response.code() == 200)) {
                        // response from YouTube
                        YouTubeResponse responseYoutube = mapper.readValue(response.body().bytes(), new TypeReference<YouTubeResponse>() {});

                        System.out.println(ConsoleColors.BLUE_BOLD + "Search request: " + str + ConsoleColors.RESET +
                                "\nResponse code: " + ConsoleColors.RED_BOLD_BRIGHT + response.code() + ConsoleColors.RESET + "\n");

                        //show response in separate text field
                        userUI.showSearchResults(responseYoutube);
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
//        root.getChildren().addAll(UserUI.vBox);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
