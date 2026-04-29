/**
 * Main.java
 * Author: Sakshum Vij
 * Date: 4/28/26
 * Program: Conference Planner
 * Purpose: This class runs the conference planner program. The class creates the conference object
 * and then uses the methods from the conference class to run the program
*/ 

import java.util.Scanner;
public class Main{
    public static void main (String[] args){
		
        
        Scanner s = new Scanner(System.in);// scanner for user input
        System.out.println("Welcome to Conference Planner");
		System.out.println("------------------------------");
		
		
		//take in customizable variables in the conference
		System.out.println("How many people can one table seat?: ");
		int pplPerTable = s.nextInt();
		s.nextLine();
		System.out.println("How many tables are available?: ");
		int numTables = s.nextInt();
		s.nextLine();
		System.out.println("How many indivduals per company can attend?: ");
		int maxIndividualPerCompany = s.nextInt();
		
		
		//create conference object
        Conference c1 = new Conference(pplPerTable,numTables, maxIndividualPerCompany);//define new conference
        c1.readFile(); //read in text file
        c1.emptyFill(); //fill the tables array with empty attendees
        c1.menu();// run the menu
    }
}
