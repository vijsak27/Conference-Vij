
//Attendee.java
/*
the attendee class will layout the basic structure for an attendee of the conference
an attendee will include attributes like first name, last name, and company number
these attributes will be used to organize the array
create get methods to be able to access those variables and values
*/
import java.util.Scanner;
public class Attendee{
	private String name;
    private int companyID;
    private int id;
    private int table;
    private Company c;
    private int seat;
	public Attendee (String first_name, String last_name, int company_number, int ID){//contructor
        name = first_name+" "+last_name;//assign name
        companyID = company_number;// and company number
        id = ID;
        table = -1; //start unassigned
    }
    public void makeUnassignedsSinceExtraForCompany(){
		name = name + " - Not Seated (Over max attendee per company limit)";
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
    public String getCompanyName(){
		return c.getName();
	}
    public int getID(){
		return id;//method to access id number for attendee
	}
	public void setTable(int t){
		table = t;
	}
	public int getTable(){
		return table;
	}
	public void setCompany(Company comp){
		c = comp;
	}
	public void setCompanyID(int comp){
		companyID = comp;
	}
	public void setSeat(int s){
		seat = s;
	}
	public int getSeat(){
		return seat;
	}
}
