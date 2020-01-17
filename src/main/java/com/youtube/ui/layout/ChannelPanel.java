package com.youtube.ui.layout;

import com.youtube.controls.search.Search;
import com.youtube.ui.components.ImageLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ChannelPanel {
    private String channelName;
    private String channelDescription;
    private String urlIDChannel;
    private String imageUrl;

    public ChannelPanel(String channelName, String channelDescription, String urlIDChannel, String imageUrl) {
        System.out.println("New channel created!! " + Thread.currentThread().getName()
                + " channelID: " + urlIDChannel);
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.urlIDChannel = urlIDChannel;
        this.imageUrl = imageUrl;
    }

    //todo: do nice layout - userUI
    public Pane newChannelPane() {
        VideoList channelView = new VideoList(700, 850, 210);

        Label name = new Label(channelName);
        Label description = new Label(channelDescription);
        VBox channelInfo = new VBox(name, description);

//        ImageView channelImage = new ImageView(new Image("https://i.ytimg.com/vi/yWpKll3G_a0/default.jpg"));
        ImageView image = new ImageView();
        HBox header = new HBox(image, channelInfo);

        //loadImage:
        System.out.println("URL for images - " + imageUrl + " | " + this.getClass().getSimpleName());
        new Thread(new ImageLoader(image, imageUrl)).start();

        //handle content: fill last videos
        Search controls = new Search();
        controls.channelSearch(urlIDChannel, channelView.getResultsList());

        Pane pane = new Pane();
        pane.getChildren().addAll(header, channelView.getResultsBox());
        return pane;
    }
}
