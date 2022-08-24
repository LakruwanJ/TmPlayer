package player.tmplayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private Label e_all;
    @FXML
    private Label e_pw;
    @FXML
    private Label e_uname;
    @FXML
    private PasswordField pw;
    @FXML
    private TextField uname;


    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void Login(){
        //connect db
        con = connectDB.connect();
    }

    //check username and password
    public void check(String userName, String passWord) throws SQLException, IOException {
        Login();
        String q = "SELECT * FROM login where username = ? and password = ?";
        pst.setString(1, userName);
        pst.setString(2, passWord);

        rs = pst.executeQuery();

        if (rs.next()){
            uname.getScene().getWindow().hide();
            openNew.onlyOpen("Analize.fxml");
        }else {
            e_all.setVisible(true);
        }
    }


    public void login(ActionEvent event) throws IOException, SQLException {

        String userName = uname.getText();
        String passWord = pw.getText();

        String x = "abc";
        String y = "123";
         if (userName.equals("")){
             e_uname.setVisible(true);
         } else if (passWord.equals("")) {
             e_pw.setVisible(true);
         }else {
             check(userName,passWord);
         }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
