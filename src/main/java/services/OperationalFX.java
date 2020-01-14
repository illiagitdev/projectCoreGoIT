package services;

import controlers.SearchControlsFX;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.ChannelView;
import ui.ResultsOutputLayoutFX;
import ui.SearchLayoutFX;

public class OperationalFX {
    private static final int HEIGHT = 700;
    private static final int WIDTH = 850;
    private SearchControlsFX controls = new SearchControlsFX();


    public void run(Stage window) {
        setupStage(window);
//todo: scenes to switch if channel is implemented
        Scene mainScene, channelScene;

        // version 0.0.2
        SearchLayoutFX searchLayout = new SearchLayoutFX();
        ResultsOutputLayoutFX searchView = new ResultsOutputLayoutFX(WIDTH, HEIGHT, 110);
        VBox layout1 = new VBox();
        layout1.getChildren().addAll(searchLayout.searchPanel());//, searchView.getResultsBox());

        Pane pane1 = new Pane();
        pane1.getChildren().add(layout1);
        mainScene = new Scene(pane1);

        // configure iu: all things on main screen
        window.setScene(mainScene);

        // butons functionality
        searchLayout.getSearch().setOnMouseClicked(e -> controls.simpleSearch(searchLayout.getSearchText().getText(),
                searchView.getResultsList()));

        searchLayout.getSearchAdvanced().setOnMouseClicked(e -> controls.advancedSearch(searchLayout.getSearchText().getText(),
                searchLayout.getMaxRes().getText(),
                searchLayout.getDaysPublished().getText(),
                searchView.getResultsList()));

        window.setOnCloseRequest(event -> {
            SearchControlsFX.getClient().dispatcher().executorService().shutdown();
            System.out.println("client OkHTTP was closed");
        });

        // channel layout
        ChannelView channelLayout = new ChannelView();
        ResultsOutputLayoutFX channelView = new ResultsOutputLayoutFX(WIDTH, HEIGHT, 210);

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(searchLayout.searchPanel(), channelLayout.newChannelPane(), channelView.getResultsBox());

        Pane pane2 = new Pane();
        pane2.getChildren().add(layout2);
        channelScene = new Scene(pane2);

    }

    private void setupStage(Stage stage) {
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);

        stage.setMaxWidth(Double.MAX_VALUE);
        stage.setMaxHeight(Double.MAX_VALUE);
        stage.setTitle("YouTube Search");
    }
}
