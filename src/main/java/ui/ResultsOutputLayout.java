package ui;

import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ResultsOutputLayout {

    private ListView<GridPane> resultsList = new ListView<>();
    private VBox resultsBox = new VBox(resultsList);

    public VBox displaResults(){

        return resultsBox;
    }
}
