package player.tmplayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;



public class password {

    public Connection con = null;
    private static Vector<String> u_name = new Vector();
    private static Vector<String> p_word = new Vector();
    public static void main(String[] args) throws SQLException {

        System.out.println("Enter main ID to use this option :");
        Scanner s = new Scanner(System.in);
        String mid = s.nextLine();

        if (TmPlayer.mainID=="123abc@A"){
            db();
        }
        else {
            main(null);
        }
    }

    public static void db() throws SQLException {

        Connection con = null;
        con = connectDB.connect();
        PreparedStatement pst = null;
        ResultSet rs = null;

        String q = "SELECT * FROM login";
        pst = con.prepareStatement(q);
        rs = pst.executeQuery();

        while(rs.next()){
            u_name.add(rs.getString("username"));
            p_word.add(rs.getString("password"));
        }
    }

    public static Vector<String> getU_name() {
        return u_name;
    }

    public static Vector<String> getP_word() {
        return p_word;
    }

    public static void setU_name(Vector<String> u_name) {
        password.u_name = u_name;
    }

    public static void setP_word(Vector<String> p_word) {
        password.p_word = p_word;
    }
}
