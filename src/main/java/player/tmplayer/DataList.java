package player.tmplayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DataList implements Initializable {


    @FXML
    private TableView<TableData1> rs_T;
    @FXML
    private TableColumn<TableData1, String> t1;
    @FXML
    private TableColumn<TableData1, String> t2;
    @FXML
    private TableColumn<TableData1, String> t3;
    @FXML
    private TableColumn<TableData1, String> t4;
    @FXML
    private TableColumn<TableData1, String> t5;
    @FXML
    private Label vname;
    @FXML
    private Label info;

    public static Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String x;
    int count;
    public String name;

    public void loadTb() throws SQLException {

        //sql part
        String p = "select S_date,S_time,E_date,S_time,(E_time-S_time)/60 from watchvideo where VideoName = '"+ player.fname +"'";
        con = connectDB.connect();
        pst = con.prepareStatement(p);
        rs = pst.executeQuery();

        String[] temp = {"","","","","",""};
        ObservableList<TableData1> list =  FXCollections.observableArrayList();

        while (rs.next()) {

            temp[0] = rs.getString("S_date");
            temp[1] = rs.getString("S_time");
            temp[2] = rs.getString("E_date");
            temp[3] = rs.getString("S_time");
            temp[4] = rs.getString("(E_time-S_time)/60");

            list.add(new TableData1(temp[0],temp[1],temp[2],temp[3],temp[4]));
            rs_T.setItems(list);

            count = count + 1;

        }

        t1.setCellValueFactory(new PropertyValueFactory<TableData1, String>("tr1"));
        t2.setCellValueFactory(new PropertyValueFactory<TableData1, String>("tr2"));
        t3.setCellValueFactory(new PropertyValueFactory<TableData1, String>("tr3"));
        t4.setCellValueFactory(new PropertyValueFactory<TableData1, String>("tr4"));
        t5.setCellValueFactory(new PropertyValueFactory<TableData1, String>("tr5"));

    }

    public void mainInfo() throws SQLException, IOException {
        vname.setText(player.fname);
        info.setText("     Played times : "+ count + "\tFull played duration : ");
    }

    public void txtfile() throws IOException, SQLException {

        //file generate
        name = player.fname + "_" + LocalDate.now() + ".txt";
        File f = new File(name);
        f.createNewFile();

        FileWriter fw = new FileWriter(name);
        BufferedWriter fww = new BufferedWriter(fw);

        //file write
        fww.write("Video Name : "+ player.fname + "\n");
        fww.write("Played times : "+ count + "\nFull played duration : \n\n");
        fww.write("+-----------------+-----------------+-----------------+-----------------+-----------+\n");
        fww.write("| Starting Date" + "\t| Starting Time" + "\t| Ending Date" + "\t| Ending Time" + "\t| Duration \t|\n");
        fww.write("+-----------------+-----------------+-----------------+-----------------+-----------+\n");

        //sql part
        String p = "select S_date,S_time,E_date,S_time,(E_time-S_time)/60 from watchvideo where VideoName = '" + player.fname + "'";
        con = connectDB.connect();
        pst = con.prepareStatement(p);
        rs = pst.executeQuery();

        //write the data witch are in db
        while (rs.next()) {
            fww.write(
                "| " + rs.getString("S_date") +
                "\t| " + rs.getString("S_time") +
                "\t\t| " + rs.getString("E_date") +
                "\t| " + rs.getString("S_time") +
                "\t\t| " + rs.getString("(E_time-S_time)/60") +
                "\t|\n");
        }
        fww.write("+-----------------+-----------------+-----------------+-----------------+-----------+\n");
        fww.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {
            mainInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            loadTb();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
