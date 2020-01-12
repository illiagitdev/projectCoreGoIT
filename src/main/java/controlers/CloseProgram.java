package controlers;

import javafx.stage.Stage;
import messageBoxes.MessageBoxYesNo;
import okhttp3.OkHttpClient;

public class CloseProgram {

    public static void close(OkHttpClient client, Stage stage){
        boolean answer = MessageBoxYesNo.display("Close confirmation", "Are you sure, you want to close the program?");
        if (answer) {
            client.dispatcher().executorService().shutdown();
            System.out.println("client OkHTTP was closed");
            System.out.println("Program was closed!");
            stage.close();
        }
        System.out.println("Program wasn't closed!");
    }
}
