package player.tmplayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Analyze2 {

    Connection conn = null;
    static PreparedStatement pst = null;
    static ResultSet rs = null;

    //variable for search
    public static String name;
    public static String[] info = {"","","","",""};

    //sql part
    public void getCon(){
        conn = connectDB.connect();
    }

    //video table
    public void an(String q) throws SQLException {

        //sql part
        int c = 0;
        conn = connectDB.connect();
        pst = conn.prepareStatement(q);
        rs = pst.executeQuery();

        //printing part
        System.out.println("+---------------+---------------+---------------+---------------+---------------+-------------------------");
        System.out.println("| Starting Date"  + "\t| Starting Time" + "\t| End Date\t" + "\t| End Time\t" + "\t| Duration\t" + "\t| Video Name");
        System.out.println("+---------------+---------------+---------------+---------------+---------------+-------------------------");

        while (rs.next()) {
            System.out.println(
                "| " +
                rs.getString("S_date")+"\t| "+
                rs.getString("S_time")+"\t\t| "+
                rs.getString("E_date")+"\t| "+
                rs.getString("E_time")+"\t\t| "+
                "\t\t\t\t| "+
                rs.getString("VideoName")
            );
            c = c + 1;
        }
        if (c==0){
            System.out.println("|\t\t No content found");
        }
        System.out.println("+---------------+---------------+---------------+---------------+---------------+-------------------------");
    }

    //login data table
    public void an2(String q) throws SQLException {

        int c = 0;
        conn = connectDB.connect();
        pst = conn.prepareStatement(q);
        rs = pst.executeQuery();

        //printing part
        System.out.println("+---------------+---------------+---------------+---------------");
        System.out.println("| Date" + "\t\t\t| Time\t\t" + "\t| Status" + "\t\t| User Name");
        System.out.println("+---------------+---------------+---------------+---------------");

        while (rs.next()) {
            System.out.println(
                    "| " +
                            rs.getString("Date")+"\t| "+
                            rs.getString("Time")+"\t\t| "+
                            rs.getString("Status")+"\t| "+
                            rs.getString("usedName")+""
            );
            c = c + 1;
        }
        if (c==0){
            System.out.println("|\t\t No content found");
        }
        System.out.println("+---------------+---------------+---------------+---------------");
    }

    //video table >> text file
    public void an3(String q) throws SQLException, IOException {

        int c = 0;
        conn = connectDB.connect();
        pst = conn.prepareStatement(q);
        rs = pst.executeQuery();

        //file generate
        name = "temp.txt";
        File f = new File(name);
        f.createNewFile();
        FileWriter fw = new FileWriter(name);
        BufferedWriter fww = new BufferedWriter(fw);

        fww.write("+-----------------+-----------------+-----------------+-----------------+-----------------+-------------------------\n");
        fww.write("| Starting Date"  + "\t| Starting Time" + "\t| End Date\t" + "\t| End Time\t" + "\t| Duration\t" + "\t| Video Name\n");
        fww.write("+-----------------+-----------------+-----------------+-----------------+-----------------+-------------------------\n");

        while (rs.next()) {

            fww.write(
                    "| " + rs.getString("S_date") +
                            "\t| " + rs.getString("S_time") +
                            "\t\t| " + rs.getString("E_date") +
                            "\t| " + rs.getString("S_time") +
                            "\t\t\t|"+
                            "\t\t| " + rs.getString("VideoName") +
                            "\n"
            );
            c = c + 1;
        }

        if (c==0){
            fww.write("|\t\t No content found");
        }
        fww.write("+-----------------+-----------------+-----------------+-----------------+-----------------+-------------------------\n");
        fww.close();
    }

    //login data table
    public void an4(String q) throws SQLException {

        int c = 0;
        conn = connectDB.connect();
        pst = conn.prepareStatement(q);
        rs = pst.executeQuery();

        //printing part
        System.out.println("+---------------+---------------+---------------+---------------");
        System.out.println("| Date" + "\t\t\t| Time\t\t" + "\t| Status" + "\t\t| User Name");
        System.out.println("+---------------+---------------+---------------+---------------");

        while (rs.next()) {
            System.out.println(
                    "| " +
                            rs.getString("Date")+"\t| "+
                            rs.getString("Time")+"\t\t| "+
                            rs.getString("Status")+"\t| "+
                            rs.getString("usedName")+""
            );
            c = c + 1;
        }
        if (c==0){
            System.out.println("|\t\t No content found");
        }
        System.out.println("+---------------+---------------+---------------+---------------");
    }




}
