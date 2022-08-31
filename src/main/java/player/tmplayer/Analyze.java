package player.tmplayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Analyze {

    public static int sch1_;
    public static int sch2_;
    public static Scanner scan = new Scanner(System.in);
    public static Connection con = null;
    static PreparedStatement pst = null;
    static ResultSet rs = null;

    //variable for search
    public static String cname;
    public static String sname;
    public static int again;
    public static int textfile;
    public static String name;
    public static String[] info = {"","","","",""};


    public Analyze(){
        con = connectDB.connect();
    }


    public static void main(String[] args) throws SQLException {


        /* System.out.println("+*----------*----------*----------*----------*----------*----------*----------*+");
        System.out.println("********************************************************************************");
        System.out.println("+*          *----------*----------*----------*----------*----------*----------*+");*/

        //start
        System.out.println("+------------------------------------------------------------------------------+");
        System.out.println("|                            >>> TrustMe Player <<<                            |");
        System.out.println("|                      *--------------------------------*                      |");
        System.out.println("|                         ~ All You Gotta Do TrustMe ~                         |");
        System.out.println("+ - - -------------------------------------------------------------------- - - +\n\n");

        // main options
        System.out.println("Options :");
        System.out.println("  Search ---> \t\t\t\tPress 1");
        System.out.println("  Get Analyze List ---> \tPress 2");
        System.out.println("  Watch History ---> \t\tPress 3");
        System.out.println("  Login History ---> \t\tPress 4");
        System.out.println("  Password Manager --->  \tPress 5");
        System.out.println("  Developing team --->  \tPress 6");
        System.out.print("\nEnter your choice : ");
        int option_ = scan.nextInt();
        System.out.println("\n- - - -------------------------------------------------------------------------+\n");

        if (option_ == 1){

            sch();

        }else if (option_ == 2){

            al();

        }else if (option_ == 3){

            // 3 - Watch History
            System.out.println(" *----- Watch History");
            System.out.print("\nEnter the number of rows : ");
            int sch_ = scan.nextInt();
            System.out.println("\n- - - -------------------------------------------------------------------- - - +\n");

        }else if (option_ == 4){

            // 4 - Login History
            System.out.println(" *----- Login History");
            System.out.print("\nEnter the number of rows : ");
            int sch_ = scan.nextInt();
            System.out.println("\n- - - -------------------------------------------------------------------- - - +\n");

        }else if (option_ == 5){

            pm();

        }else if (option_ == 6){
            System.out.println("+------------------------------------------------------------------------------+");
            System.out.println("|                            >>> TrustMe Player <<<                            |");
            System.out.println("|                         ~ Developing Team Members ~                          |");
            System.out.println("+------------------------------------------------------------------------------+");
            System.out.println("|                                                                              |");
            System.out.println("|\t* Fathima Saliha \t\t\t0742291277                                     |");
            System.out.println("|\t* Lakruwan Jayathissa \t\t0772529441                                     |");
            System.out.println("|\t* Hashini Sulakshana \t\t0701259920                                     |");
            System.out.println("|\t* Niwandi Githma \t\t\t0716515705                                     |");
            System.out.println("|\t* Thakshila Dulanjani \t\t0762915371                                     |");
            System.out.println("|                                                                              |");
            System.out.println("+------------------------------------------------------------------------------+");

        } else {
            System.out.println("Enter the valid input ");
            System.out.println("********************************************************************************\n");
            main(null);
        }

    }

    public static void sch() throws SQLException {

        System.out.println(" *----- Search");
        System.out.println("Select the proper table :");
        System.out.println("  Table with watch history --> \tPress 1");
        System.out.println("  Table with login data --> \tPress 2");
        System.out.print("\nEnter your choice : ");
        int sch_ = scan.nextInt();
        System.out.println("\n- - - -------------------------------------------------------------------------+\n");

        if (sch_ == 1){

            // 1 - 1 - Table with watch history
            System.out.println(" *----- Table with watch History");
            System.out.println("Select the required column name : ");
            System.out.println("  Video name --> \tPress 1");
            System.out.println("  Starting date --> Press 2");
            System.out.println("  Starting time --> Press 3");
            System.out.println("  End date --> \t\tPress 4");
            System.out.println("  End time --> \t\tPress 5");

            System.out.print("\nEnter your choice : ");
            sch1_ = scan.nextInt();

            if (sch1_==2 || sch1_==4){
                System.out.println("(Date format :- YYYY-MM-DD)");
                System.out.print("Enter the Date : ");
            } else if (sch1_==3 || sch1_==5){
                System.out.println("(Time format :- HH:MM:SS)");
                System.out.print("Enter the Time : ");
            } else if (sch1_==1){
                System.out.print("Enter the key word : ");
            }

            sname = scan.next();
            System.out.println("\n- - - -------------------------------------------------------------------------+\n");

            search1();

        } else if (sch_ == 2){

            // 1 - 2 - Table with login history
            System.out.println(" *----- Table with login history");
            System.out.println("Select the required column name : ");
            System.out.println("  User name --> \tPress 1");
            System.out.println("  Date --> \t\t\tPress 2");
            System.out.println("  Time --> \t\t\tPress 3");
            System.out.println("  Status --> \tPress 4");

            System.out.print("\nEnter your choice : ");
            sch2_ = scan.nextInt();

            if (sch2_==2){
                System.out.println("(Date format :- YYYY-MM-DD)");
                System.out.print("Enter the Date : ");
            } else if (sch2_==3){
                System.out.println("(Time format :- HH:MM:SS)");
                System.out.print("Enter the Time : ");
            } else if (sch2_==1){
                System.out.print("Enter the key word : ");
            }else if (sch2_==4){
                System.out.print("Enter the Successful/Unsuccessful : ");
            }

            sname = scan.next();
            System.out.println("\n- - - -------------------------------------------------------------------------+\n");

            search2();

        } else{
            System.out.println("Enter the valid input ");
            System.out.println("********************************************************************************\n");
            sch();
        }

    }

    public static void al(){
        // 2 - Analyze
        System.out.println(" *----- Get Analyze List");
        System.out.println("Which time period do you prefer ?");
        System.out.println("  Hour by hour --> \t\t\tPress 1");
        System.out.println("  Day by day --> \t\t\tPress 2");
        System.out.println("  Month by month --> \t\tPress 3");
        System.out.println("  Year by Year --> \t\t\tPress 4");
        System.out.println("  Details of a hour --> \tPress 5");
        System.out.println("  Details of a day --> \t\tPress 6");
        System.out.println("  Details of a month --> \tPress 7");
        System.out.println("  Details of a year --> \tPress 8");
        System.out.print("\nEnter your choice : ");
        int ana = scan.nextInt();
        System.out.println("\n- - - -------------------------------------------------------------------------+\n");

        if (ana==1){

        } else if (ana==2) {

        } else if (ana==3) {

        } else if (ana==4) {

        } else if (ana==5) {
            // 2 - 5 - Analyze
            System.out.println(" *----- Details of a hour");
            System.out.print("\nEnter the required hour: ");
            int a = scan.nextInt();
            System.out.println("\n- - - -------------------------------------------------------------------------+\n");
        } else if (ana==6) {
            // 2 - 6 - Analyze
            System.out.println(" *----- Details of a day");
            System.out.print("\nEnter the required day: ");
            int a = scan.nextInt();
            System.out.println("\n- - - -------------------------------------------------------------------------+\n");
        } else if (ana==7) {
            // 2 - 7 - Analyze
            System.out.println(" *----- Details of a month");
            System.out.print("\nEnter the required month: ");
            int a = scan.nextInt();
            System.out.println("\n- - - -------------------------------------------------------------------------+\n");
        } else if (ana==8) {
            // 2 - 8 - Analyze
            System.out.println(" *----- Details of a year");
            System.out.print("\nEnter the required year: ");
            int a = scan.nextInt();
            System.out.println("\n- - - -------------------------------------------------------------------------+\n");
        } else {
            System.out.println("Enter the valid input ");
            System.out.println("********************************************************************************\n");
            al();
        }

    }

    public static void pm(){

    }

    public static void search1() throws SQLException {
        if (sch1_==1){
            cname = "VideoName";
            sch_1(cname,sname);
        } else if (sch1_==2){
            cname = "S_date";
            sch_1(cname,sname);
        }  else if (sch1_==3){
            cname = "S_time";
            sch_1(cname,sname);
        }else if (sch1_==4){
            cname = "E_date";
            sch_1(cname,sname);
        }else if (sch1_==5){
            cname = "E_time";
            sch_1(cname,sname);
        }else {
            System.out.println("Enter the valid input ");
            System.out.println("********************************************************************************\n");
            search1();
        }
    }

    public static void search2(){

        if (sch2_==1){
            //
        } else if (sch2_==2){
            //
        } else if (sch2_==2){
            //
        } else if (sch2_==3){
            //
        }else if (sch2_==4){
            //
        }else {
            System.out.println("Enter the valid input ");
            System.out.println("********************************************************************************\n");
            search2();
        }
    }

    public void execute_(String q) throws SQLException {
        pst = con.prepareStatement(q);
        pst.execute();
    }

    public static void sch_1(String a,String b) throws SQLException {

        //sql part
        String q = "SELECT * FROM watchvideo where " +cname+ " like \'%" +sname+ "%\'";
        int c = 0;
        con = connectDB.connect();
        pst = con.prepareStatement(q);
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

        //word file
        int i = 0;
        while(i==0){
            System.out.print("\nDo you need text file for this (Yes >> 1 / No  >> 2) : ");
            again = scan.nextInt();
            System.out.println(again);
            if (again == 1){
                try {
                    tsch_1(cname,sname);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                i = i + 1;
            } else if (again == 2){
                i = i + 1;
            }
        }

        //run again
        int j = 0;
        while(j==0){
            System.out.print("\nDo you want run this again (Yes >> 1 / No  >> 2), Go to main >> 3 : ");
            textfile = scan.nextInt();
            System.out.println(textfile);
            if (textfile == 1){
                search1();
                j = j + 1;
            } else if (textfile == 2){
                j = j + 1;
            } else if (textfile == 3){
                main(null);
                j = j + 1;
            }
        }

    }

    // ------------- Database controlling part end -------------

    // ------------- Textfile creating part end -------------

    public static void tsch_1(String a,String b) throws SQLException, IOException {

        //sql part
        String q = "SELECT * FROM watchvideo where " +cname+ " like \'%" +sname+ "%\'";
        int c = 0;
        con = connectDB.connect();
        pst = con.prepareStatement(q);
        rs = pst.executeQuery();

        String[] temp = {"","","","","",""};

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


}