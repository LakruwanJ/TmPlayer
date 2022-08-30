package player.tmplayer;

import java.time.LocalDate;
import java.util.Scanner;

public class Analyze {

    public static int sch1_;
    public static int sch2_;
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

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
                System.out.println("\n- - - -------------------------------------------------------------------------+\n");

            } else if (sch_ == 2){

                // 1 - 2 - Table with login history
                System.out.println(" *----- Table with login history");
                System.out.println("Select the required column name : ");
                System.out.println("  User name --> \tPress 1");
                System.out.println("  Date --> \tPress 2");
                System.out.println("  Time --> \tPress 3");
                System.out.println("  Status --> \tPress 4");

                System.out.print("\nEnter your choice : ");
                sch2_ = scan.nextInt();
                System.out.println("\n- - - -------------------------------------------------------------------------+\n");

            } else{
                main(null);
            }


        }else if (option_ == 2){
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

            }

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

        }else if (option_ == 6){
            System.out.println("  Developing team --->  \tPress 6");
        } else {
            System.out.println("Enter the valid input ");
        }

    }

    public void sch(){

    }

    public void search1(){
        if (sch1_==1){
            //
        } else if (sch1_==2){
            //
        } else if (sch1_==2){
            //
        } else if (sch1_==3){
            //
        }else if (sch1_==4){
            //
        }else if (sch1_==5){
            //
        }else {
            //
        }
    }

    public void search2(){
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
            //
        }
    }

}