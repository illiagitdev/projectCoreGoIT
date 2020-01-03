import apiConnection.BuildHttpRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Popup;
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
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        setupUI(root);

        userUI.setupWindow(primaryStage);
    }

    private void setupUI(Group root) {
        userUI.setupUI(root);

        userUI.advancedSearchButton.setOnMouseClicked(event -> {

        });

        userUI.searchButton.setOnMouseClicked(event -> {
            Call call = client.newCall(new Request.Builder().
                    url(BuildHttpRequest.buildHttpUrl(UserUI.searchText.getText()))
                    .get()
                    .build());
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful() && (response.code() == 200)) {

                        String str = UserUI.searchText.getText();
                        YouTubeResponse responseYoutube = mapper.readValue(response.body().bytes(), new TypeReference<YouTubeResponse>() {
                        });

                        System.out.println(ConsoleColors.BLUE_BOLD + "Search request: " + str + ConsoleColors.RESET +
                                "\n\tResponse code: " + ConsoleColors.RED_BOLD_BRIGHT + response.code() + ConsoleColors.RESET + "\n");
                        UserUI.text.setText(responseYoutube.toString() + "\n");

                        List<SearchResult> searchResults = new ArrayList<>();
                        SearchResult result;
                        List<Items> items = responseYoutube.getItems();
                        for (int i = 0; i < items.size(); i++) {
                            result = new SearchResult();
                            result.setVideoName(items.get(i).getSnippet().getChannelTitle());
                            result.setChannelName(items.get(i).getSnippet().getChannelTitle());
                            result.setPublicationDate(items.get(i).getSnippet().getPublishedAt());
                            result.setUrlID(items.get(i).getId().getVideoId());
                            result.setUrlIDChannel(items.get(i).getId().getChannelId());

                            searchResults.add(result);
                            for (SearchResult x : searchResults) {
                                System.out.println(x.toString());
                            }
                            client.dispatcher().executorService().shutdown();
                        }
                    }
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
//        Название видео
//        Название канала
//        Дата публикации
//        Кнопка - View. При нажатии на которую воспроизводиться видео в окне программы.
//        изображение из видео
//        Кол-во дней. Если видео было опубликовано раньше чем Х дней назад, значит его не надо отображать в поиске.
//
//        Аватарка канала
//        Название канала
//        Описание канала

    public static void main(String[] args) {
        launch(args);
    }
}
