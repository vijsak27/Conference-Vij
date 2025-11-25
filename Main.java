//Main.java
import java.util.Scanner;
/*
Main class that runs the program
creates a conference object
accesses many methods organize the conference seating
*/ 
public class Main{
    public static void main (String[] args){
        Conference c1 = new Conference(10,10);//define new conference
        Scanner s = new Scanner(System.in);// scanner for user input
        c1.emptyFill();//fill tables empty attendees
        c1.readFile();// read in the file
        boolean addMore = true;//starts off as true so thaty user is prompted to add attendees
        System.out.println("Menu:\n1. Organize seats\n2.Add Attendee Manually");
        
			do{
				System.out.println("Would you like to add any attendees? (y/n)");
				if((s.nextLine().equals("y"))){
					boolean added = c1.addManually();//run add manually if the user wants to add an attendee. save whether it was added to a boolean

					if (!added){//if not added
						addMore=false;// stop to loop by making addMore = false because mas occupancy has been reached
					}
					addMore = true;// else addMore = true so keep going
					
				}
				else{
					addMore=false;// if users enteres anything other than "y" stop the loop
				}
			}while(addMore);
        c1.organize();//organize the tables
        System.out.println(c1);//print tables
    }
}
