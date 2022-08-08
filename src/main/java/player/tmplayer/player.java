package player.tmplayer;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXSlider;
import javafx.scene.control.Label;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

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
    //fxml component area end


    private String path;
    private MediaPlayer player;


    /*/open file
    public void openfile(ActionEvent event){
        //choose
        FileChooser file = new FileChooser();
        File file1 = file.showOpenDialog(null);

        //
        path = file1.toURI().toString();

        if(path != null){
            Media media = new Media(path);
            player = new MediaPlayer(media);
            play.setMediaPlayer(player);

            final DoubleProperty width = play.fitWidthProperty();
            final DoubleProperty height = play.fitHeightProperty();

            width.bind(Bindings.selectDouble(play.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(play.sceneProperty(), "height"));

            player.play();
        }
    }

    */

    //grt path
    public void getPath(){
        //choose
        FileChooser file = new FileChooser();
        File file1 = file.showOpenDialog(null);

        //
        path = file1.toURI().toString();
    }

    //load video
    public void loadVideo(){
        if(path != null){
            Media media = new Media(path);
            player = new MediaPlayer(media);
            play.setMediaPlayer(player);

            final DoubleProperty width = play.fitWidthProperty();
            final DoubleProperty height = play.fitHeightProperty();

            width.bind(Bindings.selectDouble(play.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(play.sceneProperty(), "height"));

            player.play();
        }
    }

    //open file
    public void openfile(ActionEvent event){
        getPath();
        loadVideo();
    }

    //repeat
    public void rep(ActionEvent event){
        loadVideo();
    }

    //start again
    public void sAgain(ActionEvent event){
        loadVideo();
    }

    //play
    public void play(ActionEvent event){
        player.play();
    }

    //stop
    public void stop(ActionEvent event){
        player.stop();
    }

    //pause
    public void pause(ActionEvent event){
        player.pause();
    }

    //slow
    public void slow(ActionEvent event){
        player.setRate(0.5);
    }

    //fast
    public void fast(ActionEvent event){
        player.setRate(2);
    }

    //speed normal
    public void sNomal(ActionEvent event){
        player.setRate(2);
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
    public void skip60(ActionEvent event){
        skipAndBack(60);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}