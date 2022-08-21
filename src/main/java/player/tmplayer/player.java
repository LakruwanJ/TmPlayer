package player.tmplayer;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXSlider;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class player implements Initializable {

    //fxml component area start
    @FXML
    private JFXSlider playbar;
    @FXML
    private JFXSlider soundbar;
    @FXML
    private MediaView play;
    @FXML
    private Label fulltime;
    @FXML
    private Label mode;
    @FXML
    private Label name;
    @FXML
    private Label nowtime;
    @FXML
    private Label status;
    @FXML
    private Label vlevel;
    @FXML
    private MenuItem mv240;
    @FXML
    private MenuItem mv320;
    //fxml component area end


    private String path,fname;
    private MediaPlayer player;
    playTime Slidertime = new playTime();


    //get path
    public void getPath(){
        //choose
        FileChooser file = new FileChooser();
        File file1 = file.showOpenDialog(null);

        //
        path = file1.toURI().toString();
        fname = file1.getName();
    }

    //load video
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
            vlevel.setText("100");

            name.setText(fname);

            //slider
            player.currentTimeProperty().addListener(new ChangeListener<javafx.util.Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    playbar.setValue(newValue.toSeconds());
                    nowtime.setText(Slidertime.Time(newValue.toSeconds()));
                    fulltime.setText(Slidertime.Time(player.getTotalDuration().toSeconds()));
                }
            });
            playbar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    player.seek(javafx.util.Duration.seconds(playbar.getValue()));
                }
            });

            playbar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    player.seek(javafx.util.Duration.seconds(playbar.getValue()));
                }
            });

            player.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total = media.getDuration();
                    playbar.setMax(total.toSeconds());

                }
            });

            soundbar.setValue(player.getVolume()*100);
            soundbar.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    player.setVolume(soundbar.getValue()/100);
                    //set value to label
                    int i = (int) (soundbar.getValue());
                    vlevel.setText(String.valueOf(i));
                }
            });

        }


    }

    //open file
    public void openfile(ActionEvent event){
        getPath();
        loadVideo();
        mode.setText("Playing");
    }

    //repeat
    public void rep(ActionEvent event){
        loadVideo();
        mode.setText("Playing");
    }

    //start again
    public void sAgain(ActionEvent event){
        loadVideo();
        mode.setText("Playing");
    }

    //play
    public void play(ActionEvent event){
        player.play();
        mode.setText("Playing");
    }

    //stop
    public void stop(ActionEvent event){
        player.stop();
        mode.setText("Stoped");
    }

    //pause
    public void pause(ActionEvent event){
        player.pause();
        mode.setText("Pause");
    }

    //slow
    public void slow(ActionEvent event){
        player.setRate(0.5);
        mode.setText("Speed x0.5");
    }

    //fast
    public void fast(ActionEvent event){
        player.setRate(2);
        mode.setText("Speed x2");
    }

    //speed normal
    public void sNomal(ActionEvent event){
        player.setRate(1);
        mode.setText("Playing");

    }

    //skip and back
    public void skipAndBack(int t){
        player.seek(player.getCurrentTime().add(javafx.util.Duration.seconds(t)));
    }
    //-10
    public void back10(ActionEvent event){
        skipAndBack(-10);
    }
    //-60
    public void back60(ActionEvent event){
        skipAndBack(-60);
    }
    //+10
    public void skip10(ActionEvent event){
        skipAndBack(10);
    }
    //+60
    public void skip60(ActionEvent event) {
        skipAndBack(60);
    }

    //
    public void gotoplaylist() throws IOException {

    }

    public void goto240() throws IOException {

    }

    public void goto320() throws IOException {

    }

    public void gotologin() throws IOException {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}