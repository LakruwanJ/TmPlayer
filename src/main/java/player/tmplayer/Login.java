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
import java.time.LocalDate;
import java.time.LocalTime;
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

    //-------------------DataBase Area Start-------------------
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String status="a",name,pword;
    String[] info = {name, pword, String.valueOf(LocalDate.now()), String.valueOf(LocalTime.now()), status};

    public void Login(){
        //connect db
        con = connectDB.connect();
    }

    //check username and password
    public void check(String userName, String passWord) throws SQLException, IOException {

        con = connectDB.connect();
        String q = "SELECT * FROM login where username=? and password=?";
        pst = con.prepareStatement(q);


        pst.setString(1, userName);
        pst.setString(2, passWord);
        rs = pst.executeQuery();

        if (rs.next()){
            status = "Successful";
            uname.getScene().getWindow().hide();
            openNew.onlyOpen("Analize.fxml");
        }else {
            e_all.setVisible(true);
            status = "Unsuccessful";
        }
    }

    //add status to table
    public void addStatus(String[] temp) throws SQLException {
        con = connectDB.connect();
        String p = "INSERT INTO userlogin VALUES ('"+ temp[0] +"', '"+ temp[1] +"', '"+ temp[2] +"', '"+ temp[3] +"', '"+ temp[4] +"')";
        pst = con.prepareStatement(p);
        pst.execute();
    }

    //-------------------DataBase Area End-------------------


    public void clearerror(){
        e_pw.setVisible(false);
        e_uname.setVisible(false);
        e_all.setVisible(false);
    }

    public void login(ActionEvent event) throws IOException, SQLException {

        String userName = uname.getText();
        String passWord = pw.getText().toString();

        name = userName;
        pword = passWord;

        if (userName.equals("")){
             e_uname.setVisible(true);
             status = "Unsuccessful";
             addStatus(info);
         } else if (passWord.equals("")) {
             e_pw.setVisible(true);
             status = "Unsuccessful";
             addStatus(info);
         }else {
            check(userName,passWord);
            addStatus(info);
         }


    }

    public void reset(){
        clearerror();
        uname.setText("");
        pw.setText("");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
