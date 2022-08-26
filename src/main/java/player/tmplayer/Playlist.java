package player.tmplayer;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.SynchronousQueue;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class Playlist extends player implements Initializable {
    @FXML
    private JFXButton btp;

    @FXML
    private JFXButton v1;

    @FXML
    private JFXButton v2;

    @FXML
    private JFXButton v3;

    @FXML
    private JFXButton v4;

    @FXML
    private JFXButton v5;

    @FXML
    private JFXButton v6;

    @FXML
    private JFXButton v7;

    @FXML
    private JFXButton v8;

    @FXML
    private JFXButton v9;

    @FXML
    private JFXButton v10;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String[] te = {"","","","","","","","","",""};
    String[] patharr = {"","","","","","","","","",""};


    public void getLastId() throws SQLException {
        String p = "select * from watchvideo order by ID desc limit 10";
        con = connectDB.connect();
        pst = con.prepareStatement(p);
        rs = pst.executeQuery();
        int x = 0;
        while (rs.next()){
            te[x] = rs.getString("VideoName");
            patharr[x] = rs.getString("Path");
            x=x+1;
        }
    }

    public void setname() throws SQLException {
        getLastId();
        v1.setText(te[0]);
        v2.setText(te[1]);
        v3.setText(te[2]);
        v4.setText(te[3]);
        v5.setText(te[4]);
        v6.setText(te[5]);
        v7.setText(te[6]);
        v8.setText(te[7]);
        v9.setText(te[8]);
        v10.setText(te[9]);

    }

    public Playlist() throws SQLException {
        getLastId();
    }


    //play video
    public void b_v1() throws IOException, SQLException {
        setname();
    }

    //play video
    public void b_v3() throws IOException {
        path = patharr[3];
        System.out.println(path);

        v9.getScene().getWindow().hide();
        openNew.onlyOpen("player.fxml");
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
