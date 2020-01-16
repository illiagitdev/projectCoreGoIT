package ui;

import controlers.SearchControlsFX;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import result.ImageLoader;

public class ChannelUI {
    private String urlIDChannel;
    private String imageUrl;
    private ImageView image;

    public ChannelUI(String urlIDChannel) {
        System.out.println("New channel created!! " + Thread.currentThread().getName()
        + " channelID: " + urlIDChannel);
        this.urlIDChannel = urlIDChannel;
    }

    public Pane newChannelPane() {
        ResultsOutputLayoutFX channelView = new ResultsOutputLayoutFX(700, 850, 210);

        Label name = new Label("name");
        Label description = new Label("description");
        VBox channelInfo = new VBox(name, description);

        ImageView channelImage = new ImageView(new Image("https://i.ytimg.com/vi/yWpKll3G_a0/default.jpg"));
        HBox header = new HBox(channelImage, channelInfo);

        //loadImage:
//        System.out.println("URL for images - " + imageUrl + " | " + this.getClass().getSimpleName());
//        new Thread(new ImageLoader(image, imageUrl)).start();

        //handle content: fill last videos
        SearchControlsFX controls = new SearchControlsFX();
        controls.channelSearch(urlIDChannel, channelView.getResultsList());

        Pane pane = new Pane();
        pane.getChildren().addAll(header, channelView.getResultsBox());
        return pane;
    }
}
