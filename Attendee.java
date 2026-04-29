
/*Attendee.java
 * Author: Sakshum Vij
 * Date: 4/28/26
 * Program: Conference Planner
 * Purpose: The attendee class lays out the basic structure for an attendee of the conference
an attendee will include attributes like name, company number, id, table, seat, Company object.
These attributes will be used to organize the array.
*/

import java.util.Scanner;
public class Attendee{
	//instance variables
	private String name;
    private int companyID;
    private int id;
    private int table;
    private Company c;
    private int seat;
    
    //constructor
	public Attendee (String first_name, String last_name, int company_number, int ID){
        name = first_name+" "+last_name;//assign name
        companyID = company_number;// and company number
        id = ID;
        table = -1; //start unassigned (-1 means unassigned) - this was a challenge area (tracking the assignment of the attendees)
        //default assignment to zero allowed the organize method to assign table to the objects as well while preserving
        // any registrants that were unassigned
    }
    
    /*
		The makeUnassignedsSinceExtraForCompany() method is accessed in the cleanUpTableRosters() method
		of the conference class. This method sets the attendee objects table to -1, signalling unassignment
		. A tag is also added on to the attendee's name to indicate that they are unassigned
     */
    public void makeUnassignedsSinceExtraForCompany(){
		name = name + " - Not Seated (Over Company Limit)";
		table = -1;
	}
    public String getName(){//method to access attendee name
        return name;
    }
    public int getCompanyID(){//method to access company number for attendee
        return companyID;
    }
    public Company getCompany(){//method to access company number for attendee
        return c;
    }
    public String getCompanyName(){//retrieve company name of attendee
		return c.getName();
	}
    public int getID(){
		return id;//method to access id number for attendee
	}
	public void setTable(int t){//set table for attendee
		table = t;
	}
	public int getTable(){//retrieve table of attendee
		return table;
	}
	public void setCompany(Company comp){//assign the attendee to a company object
		c = comp;
	}
	public void setSeat(int s){//set the seat of the attendee
		seat = s;
	}
	public int getSeat(){//retrieve the attendee's seat at the table
		return seat;
	}
}
