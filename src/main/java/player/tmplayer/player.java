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
import java.sql.ResultSet;
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
    private Label vlevel;
    @FXML
    private MenuItem rl1;
    @FXML
    private MenuItem rl2;
    @FXML
    private MenuItem rl3;
    @FXML
    private MenuItem rl4;
    @FXML
    private MenuItem rl5;
    //fxml component area end

    public static String path;
    public static float sound;
    public static String fname;

    public static int modeSet;
    public String[] rl = {"","","","",""};
    public String[] rlPath = {"","","","",""};
    public MediaPlayer player;
    playTime Slidertime = new playTime();

    public player() throws SQLException, IOException {
    }

    //-------------------DataBase _ Start-------------------

    public static Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public static int nTime;
    int id;

    LocalDate sDate = LocalDate.now(),eDate = LocalDate.now();
    LocalTime sTime = LocalTime.now(),eTime = LocalTime.now();
    String fomat = ".mp4",fa="x",aa="x",lid;

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
        id = id + 1 ;
    }

    //get last id
    private String lastid() throws SQLException {
        String p = "select * from watchvideo order by ID desc limit 1";
        con = connectDB.connect();
        pst = con.prepareStatement(p);
        rs = pst.executeQuery();
        while (rs.next()){
            lid = rs.getString("ID");
        }
        return lid;
    }



    //change db end time
    public void cedt() throws SQLException {
        int k = id - 1;
        String q = "UPDATE watchvideo SET E_date = '" + LocalDate.now() +"', E_time = '" + LocalTime.now() +"' WHERE id = '"+ k +"'" ;
        getcon();
        pst = con.prepareStatement(q);
        pst.execute();
    }

    //-------------------DataBase _ End-------------------

    //get path
    public void getPath(){
        //choose
        FileChooser file = new FileChooser();
        File file1 = file.showOpenDialog(null);

        path = file1.toURI().toString();
        fname = file1.getName();
    }

    //load video
    public void loadVideo() throws SQLException {

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
                    nTime = (int) newValue.toSeconds();
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
                    sound = (float) (soundbar.getValue()/100);
                    int i = (int) soundbar.getValue();
                    vlevel.setText(String.valueOf(i));
                }
            });

            mode.setText("Playing");

            //db
            String[] wv = {String.valueOf(id),String.valueOf(fname),String.valueOf(fomat), String.valueOf(path),String.valueOf(sDate), String.valueOf(sTime), String.valueOf(eDate), String.valueOf(eTime),fa,aa};
            String p = "INSERT INTO watchvideo VALUES ('"+ wv[0] +"', '"+ wv[1] +"', '"+ wv[2] +"', '"+ wv[3] +"', '"+ wv[4] +"', '"+ wv[5] +"', '"+ wv[6] +"',  '"+ wv[7] +"', '"+ wv[8] +"', '"+ wv[8] +"')" ;
            execute_(p);

        }
    }

    //open file
    public void openfile() throws SQLException {
        getPath();
        loadVideo();
    }

    //repeat
    public void rep(ActionEvent event) throws SQLException {
        loadVideo();
        mode.setText("Playing");
    }

    //start again
    public void sAgain(ActionEvent event) throws SQLException {
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

    //menu item playlist
    public void gotoplaylist() throws IOException {
        if (path!=null) {
            player.stop();
        }
        mode.getScene().getWindow().hide();
        openNew.onlyOpen("Playlist.fxml");
    }

    //menu item miniview 240
    public void goto240() throws IOException {
        sound =  (float) soundbar.getValue()/100;
        player.stop();
        mode.getScene().getWindow().hide();
        openNew.onlyOpen("MiniView240p.fxml");
    }

    //menu item miniview 320
    public void goto320() throws IOException {
        sound = (int) soundbar.getValue();
        player.stop();
        mode.getScene().getWindow().hide();
        openNew.onlyOpen("MiniView320p.fxml");
    }

    //menu item Analize
    public void gotoAnalize() throws IOException {
        modeSet = 1;
        openNew.onlyOpen("Login.fxml");
    }

    //menu item History
    public void gotodata() throws IOException {
        modeSet = 2;
        if (path != null) {
            openNew.onlyOpen("DataList.fxml");
        }
    }

    //menu item exit
    public void close() {
        if (path!=null) {
            player.stop();
        }

        mode.getScene().getWindow().hide();
    }

    //set items to recent list
    public void rl() throws SQLException {

        String p = "select * from watchvideo order by ID desc limit 5";
        con = connectDB.connect();
        pst = con.prepareStatement(p);
        rs = pst.executeQuery();

        int x = 0;
        while (rs.next()){
            rl[x] = rs.getString("VideoName");
            rlPath[x] = rs.getString("Path");
            x=x+1;
        }

        rl1.setText(rl[0]);
        rl2.setText(rl[1]);
        rl3.setText(rl[2]);
        rl4.setText(rl[3]);
        rl5.setText(rl[4]);
    }

    //play resent list videos
    public void play1() throws SQLException {
        path = rlPath[0];
        loadVideo();
    }
    public void play2() throws SQLException {
        path = rlPath[1];
        loadVideo();
    }
    public void play3() throws SQLException {
        path = rlPath[2];
        loadVideo();
    }
    public void play4() throws SQLException {
        path = rlPath[3];
        loadVideo();
    }
    public void play5() throws SQLException {
        path = rlPath[4];
        loadVideo();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //set correct id to db
        try {
            lastid();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (lid == null) {
            id = 0;
        } else if (Integer.parseInt(lid)<0){
            id = 0;
        }else {
            id = Integer.parseInt(lid) + 1 ;
        }

        try {
            rl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //check path
        if (path != null){
            try {
                loadVideo();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(nTime);
            skipAndBack(60);
            mode.setText("Playing");
        }

    }
}