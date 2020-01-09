package services;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;

public class ImageLoader implements Runnable {
    private ImageView imageView;
    private String urlPathToImage;

    public ImageLoader(ImageView imageView, String urlPathToImage) {
        this.imageView = imageView;
        this.urlPathToImage = urlPathToImage;
    }

    @Override
    public void run() {

        imageView = ImageViewBuilder.create()
                .image(new Image(urlPathToImage))
                .build();
    }
}
