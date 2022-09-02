package player.tmplayer;

import com.jfoenix.controls.JFXSlider;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MiniView extends player{

    @FXML
    private MediaView play;
    @FXML
    private JFXSlider playbar;
    private MediaPlayer player;
    int x;
    //constructor
    public MiniView() throws SQLException, IOException {
    }

    //play video
    public void loadVideo(){
        if(path != null) {
            Media media = new Media(path);
            player = new MediaPlayer(media);
            play.setMediaPlayer(player);

            final DoubleProperty width = play.fitWidthProperty();
            final DoubleProperty height = play.fitHeightProperty();

            width.bind(Bindings.selectDouble(play.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(play.sceneProperty(), "height"));

            player.play();
            player.setVolume(sound);

            //slider
            player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    playbar.setValue(newValue.toSeconds());
                    x = (int) newValue.toSeconds();
                    //db
                    try {
                        cedt();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            playbar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    player.seek(Duration.seconds(playbar.getValue()));
                }
            });

            playbar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    player.seek(Duration.seconds(playbar.getValue()));
                }
            });

            player.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total = media.getDuration();
                    playbar.setMax(total.toSeconds());
                }
            });

        }

    }

    //play
    public void play(ActionEvent event){
        player.play();
    }

    //pause
    public void pause(ActionEvent event){
        player.pause();
    }

    //back
    public void gotomain() throws IOException {
        player.stop();
        nTime = x;
        playbar.getScene().getWindow().hide();
        openNew.onlyOpen("player.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectDB.connect();
        loadVideo();
        System.out.println(sound);
    }

}


