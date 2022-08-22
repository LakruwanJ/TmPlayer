package player.tmplayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {

    private static String host = "localhost";
    private static String port = "3306";
    private static String username = "root";
    private static String password = "";
    private static String dbname = "playerDB";

    private static String url = "jdbc:mysql://"+host +":"+port+"/"+dbname;


    public static Connection connect(){

        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
