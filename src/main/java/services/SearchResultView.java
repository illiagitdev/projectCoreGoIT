package services;

import apiConnection.BuildHttpRequest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import result.SearchResult;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        published = new Label(searchResult.getPublicationDate());
        gridPane = new GridPane();
        urlID = searchResult.getUrlID();
        urlIDChannel = searchResult.getUrlIDChannel();
        imageView = loadImage(searchResult.getUrlPathToImage());

        onClick(BuildHttpRequest.buildYouTubeWatchUrl(searchResult.getUrlID()));
        view.setOnMouseClicked(event -> {
            System.out.println("we see some movie" + this.getClass());
        });

        gridPane.add(imageView, 0, 0);
        gridPane.add(view, 1, 0);
        gridPane.add(videoName, 2, 0);
        gridPane.add(channelName, 3, 0);
        gridPane.add(published, 4, 0);
    }

    private ImageView loadImage(String urlPathToImage) {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Callable<ImageView> imageViewCallable = new Callable<ImageView>() {
//            @Override
//            public ImageView call() throws Exception {
//                ImageView imageView = ImageViewBuilder.create()
//                        .image(new Image(urlPathToImage))
//                        .build();
//                return imageView;
//            }
//        };
//        Future<ImageView> future = executor.submit(imageViewCallable);
//        return (ImageView)future;


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
            });
            stage.show();
        });
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

    public GridPane newList() {
        gridPane.setHgap(15);
        return gridPane;
    }
}
