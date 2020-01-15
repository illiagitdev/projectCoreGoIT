package ui;

import apiConnection.BuildHttpRequest;
import controlers.Controls;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import result.ImageLoader;
import result.SearchResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchResultView extends ListCell<String> implements Controls {
    private Button view;
    private GridPane gridPane;
    private Label videoName;
    private Label channelName;
    private ImageView imageView;

    public SearchResultView(SearchResult searchResult) {
        view = new Button("View");
        videoName = new Label(searchResult.getVideoName());
        channelName = new Label(searchResult.getChannelName());
        channelName.setStyle("-fx-font-weight: bold;");
        Label published = new Label(LocalDateTime.parse(searchResult.getPublicationDate(),
                DateTimeFormatter.ofPattern(DATE_FORMAT)).format(DateTimeFormatter.ofPattern(DATE_FORMAT_SHOW)));
        imageView = new ImageView();
        gridPane = new GridPane();
        String urlID = searchResult.getUrlID();
        String urlIDChannel = searchResult.getUrlIDChannel();
        channelNameActions(urlIDChannel, searchResult.getChannelName());

        onClick(BuildHttpRequest.buildYouTubeWatchUrl(searchResult.getUrlID()));

        imageView.setFitHeight(30);
        imageView.setFitWidth(45);
        gridPane.add(imageView, 0, 0);
        gridPane.add(view, 1, 0);
        gridPane.add(videoName, 2, 0);
        gridPane.add(channelName, 3, 0);
        gridPane.add(published, 4, 0);
        gridPane.setHgap(10);
        gridPane.setVgap(8);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        loadImage(searchResult.getUrlPathToImage());
    }

    private void channelNameActions(String urlIDChannel, String name) {
        this.channelName.setCursor(Cursor.HAND);
        this.channelName.setOnMouseClicked(event -> {
            Stage stage = new Stage();
            stage.setTitle(String.valueOf(name));
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(new ChannelUI(urlIDChannel).newChannelPane());
            stage.setScene(scene);
            stage.setOnCloseRequest(event1 -> System.out.println("Channel watch terminated!"));
            stage.show();
        });
    }

    private void loadImage(String urlPathToImage) {
            System.out.println("URL for images - " + urlPathToImage + " | " + this.getClass().getSimpleName());
        new Thread(new ImageLoader(imageView,urlPathToImage)).start();
    }

    private void onClick(String videoURL) {
        view.setOnMouseClicked(event -> {
            WebView webView = new WebView();
            System.out.println("videoURL" + videoURL + "  " + this.getClass());
            webView.getEngine().load(videoURL);
            webView.setPrefSize(640, 390);

            Stage stage = new Stage();
            stage.setTitle("Watch video form");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(webView));
            stage.setOnCloseRequest(event1 -> {
                webView.getEngine().load("");
                System.out.println("video stream was closed");
            });
            stage.show();
        });
    }

    public GridPane newList() {
        gridPane.setHgap(15);
        return gridPane;
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setEditable(false);
        if (videoName != null || channelName != null) {
            setGraphic(gridPane);
        } else {
            setGraphic(null);
        }
    }
}
