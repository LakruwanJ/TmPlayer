package player.tmplayer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class openNew {

    public static void onlyOpen(String x) throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(player.class.getResource(x));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }



}
