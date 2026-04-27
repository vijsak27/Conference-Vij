//Main.java
import java.util.Scanner;
/*
Main class that runs the program
creates a conference object
accesses many methods organize the conference seating
*/ 
public class Main{
    public static void main (String[] args){
		
        
        Scanner s = new Scanner(System.in);// scanner for user input
        System.out.println("Welcome to Conference Planner");
		System.out.println("------------------------------");
		System.out.println("How many people can one table seat?: ");
		int pplPerTable = s.nextInt();
		s.nextLine();
		System.out.println("How many tables are available?: ");
		int numTables = s.nextInt();
		s.nextLine();
		System.out.println("How many indivduals per company can attend: ");
		int maxIndividualPerCompany = s.nextInt();
		
        Conference c1 = new Conference(pplPerTable,numTables, maxIndividualPerCompany);//define new conference
        c1.readFile();
        c1.emptyFill();
        c1.menu();
    }
}
