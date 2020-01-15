package ui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import result.ImageLoader;
import result.SearchResult;

public class ChannelUI {
    private String urlIDChannel;
    private ImageView imageView;

    public ChannelUI(String urlIDChannel) {
        this.urlIDChannel = urlIDChannel;
    }

    public Pane newChannelPane() {
//        ChannelView channelLayout = new ChannelView(new SearchResult(...));
        ResultsOutputLayoutFX channelView = new ResultsOutputLayoutFX(700, 85, 310);

        Pane pane = new Pane();
        VBox channelInfo = new VBox();

        ImageView channelImage = new ImageView();

        HBox header = new HBox(channelImage, channelInfo);

        return pane;
    }

    private void loadImage(String urlPathToImage) {
        System.out.println("URL for images - " + urlPathToImage + " | " + this.getClass().getSimpleName());
        new Thread(new ImageLoader(imageView,urlPathToImage)).start();
    }
}
