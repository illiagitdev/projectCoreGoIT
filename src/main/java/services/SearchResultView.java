package services;

import apiConnection.BuildHttpRequest;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import result.SearchResult;

public class SearchResultView extends ListCell<String> {
    private Button view;
    private GridPane gridPane;
    private Label videoName;
    private Label channelName;
    private Label published;
    private ImageView imageView;
    private String urlID;
    private String urlIDChannel;

    public SearchResultView(SearchResult searchResult) {
        view = new Button("View");
        videoName = new Label(searchResult.getVideoName());
        channelName = new Label(searchResult.getChannelName());
        channelName.setStyle("-fx-font-weight: bold;");
        published = new Label(searchResult.getPublicationDate());
        imageView = new ImageView();
        gridPane = new GridPane();
        urlID = searchResult.getUrlID();
        urlIDChannel = searchResult.getUrlIDChannel();
        channelNameActions(urlIDChannel);

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

    private void channelNameActions(String urlIDChannel) {
        channelName.setOnMouseClicked(event -> {
            Stage stage = new Stage();
            stage.setTitle(String.valueOf(channelName));
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(new ChannelView().newChannelPane());
            stage.setScene(scene);
            stage.setOnCloseRequest(event1 -> System.out.println("Channel watch terminated!"));
        });
    }

    private void loadImage(String urlPathToImage) {
            System.out.println("URL for images - " + urlPathToImage + " | " + this.getClass().getSimpleName());
        new Thread(new ImageLoader(imageView,urlPathToImage)).start();
    }

    public void onClick(String videoURL) {
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
