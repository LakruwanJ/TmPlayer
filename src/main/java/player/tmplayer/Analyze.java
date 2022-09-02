package player.tmplayer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Analyze extends Analyze2{

    public static int sch1_;
    public static int sch2_;
    public static Scanner scan = new Scanner(System.in);

    //variable for search
    public static String cname;
    public static String sname;
    public static int again;
    public static int textfile;
    public static int limit;


    public static void main(String[] args) throws SQLException {

        //start
        System.out.println("+------------------------------------------------------------------------------+");
        System.out.println("|                            >>> TrustMe Player <<<                            |");
        System.out.println("|                      *--------------------------------*                      |");
        System.out.println("|                       ~ All You Gotta Do is TrustMe ~                        |");
        System.out.println("+ - - -------------------------------------------------------------------- - - +\n\n");

        // main options
        System.out.println("Options :");
        System.out.println("  Search ---> \t\t\t\tPress 1");
        System.out.println("  Get Analyze List ---> \tPress 2");
        System.out.println("  Watch History ---> \t\tPress 3");
        System.out.println("  Login History ---> \t\tPress 4");
        System.out.println("  Developing team --->  \tPress 5");
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
            limit = scan.nextInt();
            System.out.println("\n- - - -------------------------------------------------------------------- - - +\n");
            w_his();

        }else if (option_ == 4){

            // 4 - Login History
            System.out.println(" *----- Login History");
            System.out.print("\nEnter the number of rows : ");
            limit = scan.nextInt();
            System.out.println("\n- - - -------------------------------------------------------------------- - - +\n");
            l_his();

        }else if (option_ == 5){
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
            System.out.println("  Status --> \t\tPress 4");

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
                System.out.print("Enter the value (Successful/Unsuccessful) : ");
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

    public static void al() throws SQLException {
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

        String q = null;
        
        if (ana==1){

            q = "SELECT * FROM watchvideo ORDER BY S_time DESC";

        } else if (ana==2) {

            q = "SELECT * FROM watchvideo ORDER BY date(S_date) DESC";

        } else if (ana==3) {

            q = "SELECT * FROM watchvideo ORDER BY month(S_date) DESC";

        } else if (ana==4) {

            q = "SELECT * FROM watchvideo ORDER BY year(S_date) DESC";

        } else if (ana==5) {
            // 2 - 5 - Analyze
            System.out.println(" *----- Details of a hour");
            System.out.print("\nEnter the required hour: ");
            int a = scan.nextInt();
            q = "SELECT * FROM watchvideo where hour(S_date) = " + a;
            System.out.println("\n- - - -------------------------------------------------------------------------+\n");
        } else if (ana==6) {
            // 2 - 6 - Analyze
            System.out.println(" *----- Details of a day");
            System.out.print("\nEnter the required day: ");
            int a = scan.nextInt();
            System.out.println("(format :- 1,2,3...30,31)");
            q = "SELECT * FROM watchvideo where day(S_date) = " + a;
            System.out.println("\n- - - -------------------------------------------------------------------------+\n");
        } else if (ana==7) {
            // 2 - 7 - Analyze
            System.out.println(" *----- Details of a month");
            System.out.print("\nEnter the required month: ");
            System.out.println("(format :- 1,2,3...23,24)");
            int a = scan.nextInt();
            q = "SELECT * FROM watchvideo where month(S_date) = " + a;
            System.out.println("\n- - - -------------------------------------------------------------------------+\n");
        } else if (ana==8) {
            // 2 - 8 - Analyze
            System.out.println(" *----- Details of a year");
            System.out.print("\nEnter the required year: ");
            System.out.println("(format :- 2022,2023...)");
            int a = scan.nextInt();
            q = "SELECT * FROM watchvideo where year(S_date) = " + a;
            System.out.println("\n- - - -------------------------------------------------------------------------+\n");
        } else {
            System.out.println("Enter the valid input ");
            System.out.println("********************************************************************************\n");
            al();
        }

        Analyze2 a2 = new Analyze2();
        a2.an(q);

        //word file
        int i = 0;
        while(i==0){
            System.out.print("\nDo you need text file for this (Yes >> 1 / No  >> 2) : ");
            again = scan.nextInt();
            System.out.println(again);
            if (again == 1){
                try {
                    tsch_1();
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
            if (textfile == 1){
                al();
                j = j + 1;
            } else if (textfile == 2){
                j = j + 1;
            } else if (textfile == 3){
                main(null);
                j = j + 1;
            }
        }

    }

    public static void search1() throws SQLException {
        if (sch1_==1){
            cname = "VideoName";
            sch_1();
        } else if (sch1_==2){
            cname = "S_date";
            sch_1();
        }  else if (sch1_==3){
            cname = "S_time";
            sch_1();
        }else if (sch1_==4){
            cname = "E_date";
            sch_1();
        }else if (sch1_==5){
            cname = "E_time";
            sch_1();
        }else {
            System.out.println("Enter the valid input ");
            System.out.println("********************************************************************************\n");
            search1();
        }
    }

    public static void search2() throws SQLException {

        if (sch2_==1){
            cname = "usedName";
            sch_2();
        } else if (sch2_==2){
            cname = "Date";
            sch_2();
        } else if (sch2_==3){
            cname = "Time";
            sch_2();
        }else if (sch2_==4){
            cname = "Status";
            sch_2();
        }else {
            System.out.println("Enter the valid input ");
            System.out.println("********************************************************************************\n");
            search2();
        }
    }


    // ----------- Database controlling part start -------------

    public static void sch_1() throws SQLException {

        Analyze2 a1 = new Analyze2();
        String q = "SELECT * FROM watchvideo where " + cname + " like \'%" + sname + "%\'";
        a1.an(q);

        //word file
        int i = 0;
        while(i==0){
            System.out.print("\nDo you need text file for this (Yes >> 1 / No  >> 2) : ");
            again = scan.nextInt();
            System.out.println(again);
            if (again == 1){
                try {
                    tsch_1();
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
            if (textfile == 1){
                sch();
                j = j + 1;
            } else if (textfile == 2){
                j = j + 1;
            } else if (textfile == 3){
                main(null);
                j = j + 1;
            }
        }

    }

    public static void sch_2() throws SQLException {

        //sql part
        Analyze2 a2 = new Analyze2();
        String q = "SELECT * FROM userlogin where " +cname+ " like '%" +sname+ "%'";
        a2.an2(q);

        //word file
        int i = 0;
        while(i==0){
            System.out.print("\nDo you need text file for this (Yes >> 1 / No  >> 2) : ");
            textfile = scan.nextInt();
            System.out.println(again);
            if (textfile == 1){
                try {
                    tsch_2();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                i = i + 1;
            } else if (textfile == 2){
                i = i + 1;
            }
        }

        //run again
        int j = 0;
        while(j==0){
            System.out.print("\nDo you want run this again (Yes >> 1 / No  >> 2), Go to main >> 3 : ");
            again = scan.nextInt();
            if (again == 1){
                sch();
                j = j + 1;
            } else if (again == 2){
                j = j + 1;
            } else if (again == 3){
                main(null);
                j = j + 1;
            }
        }

    }

    public static void w_his() throws SQLException {

        String q = "SELECT * FROM watchvideo order by ID desc limit "+limit+"";
        Analyze2 a1 = new Analyze2();
        a1.an(q);

        //word file
        int i = 0;
        while(i==0){
            System.out.print("\nDo you need text file for this (Yes >> 1 / No  >> 2) : ");
            again = scan.nextInt();
            System.out.println(again);
            if (again == 1){
                try {
                    tw_his();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                i = i + 1;
            } else if (again == 2){
                i = i + 1;
            }
        }

        //go to main
        int j = 0;
        while(j==0){
            System.out.print("\nGo to main >> 1 : ");
            textfile = scan.nextInt();
            if (textfile == 1) {
                main(null);
                j = j + 1;
            }else{
                j = j + 1;
            }
        }

    }

    public static void l_his() throws SQLException {

        String q ="SELECT * FROM userlogin order by Date desc limit "+ limit +"";
        Analyze2 a2 = new Analyze2();
        a2.an2(q);

        //word file
        int i = 0;
        while(i==0){
            System.out.print("\nDo you need text file for this (Yes >> 1 / No  >> 2) : ");
            again = scan.nextInt();
            System.out.println(again);
            if (again == 1){
                try {
                    tl_his();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                i = i + 1;
            } else if (again == 2){
                i = i + 1;
            }
        }

        //go to main
        int j = 0;
        while(j==0){
            System.out.print("\nGo to main >> 1 : ");
            textfile = scan.nextInt();
            if (textfile == 1) {
                main(null);
                j = j + 1;
            }else{
                j = j + 1;
            }
        }

    }

    // ------------ Database controlling part end ---------------


    // ------------- Textfile creating part start ---------------

    public static void tsch_1() throws SQLException, IOException {

        Analyze2 a3 = new Analyze2();
        String q = "SELECT * FROM watchvideo where " +cname+ " like '%" +sname+ "%'";
        a3.an3(q);

    }

    public static void tsch_2() throws SQLException, IOException {

        Analyze2 a4 = new Analyze2();
        String q = "SELECT * FROM userlogin where " +cname+ " like '%" +sname+ "%'";
        a4.an4(q);

    }

    public static void tw_his() throws SQLException, IOException {

        Analyze2 a3 = new Analyze2();
        String q = "SELECT * FROM watchvideo where " +cname+ " like '%" +sname+ "%'";
        a3.an3(q);

    }

    public static void tl_his() throws SQLException, IOException {

        Analyze2 a4 = new Analyze2();
        String q = "SELECT * FROM userlogin where " +cname+ " like '%" +sname+ "%'";
        a4.an4(q);

    }

    // ------------- Textfile creating part end ---------------

}