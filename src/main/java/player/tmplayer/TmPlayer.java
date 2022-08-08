package player.tmplayer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class TmPlayer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TmPlayer.class.getResource("player.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("TrustMe Media Player");

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() ==2){
                    stage.setFullScreen(true);
                }
            }
        });


        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}