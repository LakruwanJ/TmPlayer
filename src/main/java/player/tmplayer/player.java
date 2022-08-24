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
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXSlider;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
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



    //-------------------DataBase _ Start-------------------

    Connection con = null;
    PreparedStatement pst = null;
    int id = 8;

    LocalDate sDate = LocalDate.now(),eDate = LocalDate.now();
    LocalTime sTime = LocalTime.now(),eTime = LocalTime.now();
    String fomat = ".mp4",fa="x",aa="x";

    //create connection
    public void getcon(){
        //connect db
        con = connectDB.connect();
    }

    //execute
    public void execute_(String q) throws SQLException {
        getcon();
        pst = con.prepareStatement(q);
        pst.execute();
    }



    //change db end time
    public void cedt() throws SQLException {
        String q = "UPDATE watchvideo SET E_date = '" + LocalDate.now() +"', E_time = '" + LocalTime.now() +"' WHERE id = '"+ id +"'" ;
        pst = con.prepareStatement(q);
        pst.execute();
    }

    //-------------------DataBase _ End-------------------




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
                    nowtime.setText(Slidertime.displayTime(newValue.toSeconds()));
                    fulltime.setText(Slidertime.displayTime(player.getTotalDuration().toSeconds()));
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
    public void openfile(ActionEvent event) throws SQLException {
        getPath();
        loadVideo();
        mode.setText("Playing");

        //db
        String[] wv = {String.valueOf(id),String.valueOf(fname),String.valueOf(fomat), String.valueOf(sDate), String.valueOf(sTime), String.valueOf(eDate), String.valueOf(eTime),fa,aa};
        String p = "INSERT INTO watchvideo VALUES ('"+ wv[0] +"', '"+ wv[1] +"', '"+ wv[2] +"', '"+ wv[3] +"', '"+ wv[4] +"', '"+ wv[5] +"', '"+ wv[6] +"',  '"+ wv[7] +"', '"+ wv[8] +"')" ;
        execute_(p);
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
    public void stop(ActionEvent event) throws SQLException {
        player.stop();
        mode.setText("Stoped");
        cedt();
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
        openNew.onlyOpen("Playlist.fxml");
    }

    public void goto240() throws IOException {
        Duration d = Duration.seconds(playbar.getValue());
        mode.getScene().getWindow().hide();
        openNew.onlyOpen("MiniView240p.fxml");
    }

    public void goto320() throws IOException {
        mode.getScene().getWindow().hide();
        openNew.onlyOpen("MiniView320p.fxml");
    }

    public void gotologin() throws IOException {
        openNew.onlyOpen("Login.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}