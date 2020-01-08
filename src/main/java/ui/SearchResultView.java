package ui;

import apiConnection.BuildHttpRequest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import okhttp3.HttpUrl;
import result.SearchResult;

public class SearchResultView extends ListCell<String> {
    private Button view;
    private GridPane gridPane;
    private Label videoName;
    private Label channelName;
    private Label published;
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

        onClick(BuildHttpRequest.buildYouTubeWatchUrl(searchResult.getUrlID()));
//        view.setOnMouseClicked(event -> {
//            System.out.println("we see some movie");
//        });
        gridPane.add(view, 0, 0);
        gridPane.add(videoName, 1, 0);
        gridPane.add(channelName, 2, 0);
        gridPane.add(published, 3, 0);
    }

    public void onClick(HttpUrl videoURL) {
        view.setOnMouseClicked(event -> {
            WebView webView = new WebView();
            webView.getEngine().load(videoURL.toString());
            webView.setPrefSize(640, 390);

            Stage stage = new Stage();
            stage.setTitle("Watch video form");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(webView));
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
