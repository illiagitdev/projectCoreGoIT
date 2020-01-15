package ui;

import apiConnection.BuildHttpRequest;
import controlers.Controls;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import result.SearchResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChannelView extends ListCell<String> implements Controls {
    private Button view;
    private GridPane gridPane;
    private Label videoName;

    public ChannelView(SearchResult searchResult) {
        view = new Button("View");
        videoName = new Label(searchResult.getVideoName());
        Label published = new Label(LocalDateTime.parse(searchResult.getPublicationDate(),
                DateTimeFormatter.ofPattern(DATE_FORMAT)).format(DateTimeFormatter.ofPattern(DATE_FORMAT_SHOW)));
        gridPane = new GridPane();
        String urlID = searchResult.getUrlID();

        onClick(BuildHttpRequest.buildYouTubeWatchUrl(urlID));

        gridPane.add(view, 0, 0);
        gridPane.add(videoName, 1, 0);
        gridPane.add(published, 2, 0);
        gridPane.setHgap(10);
        gridPane.setVgap(8);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
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
        if (videoName != null) {
            setGraphic(gridPane);
        } else {
            setGraphic(null);
        }
    }
}
