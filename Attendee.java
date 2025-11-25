
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
    private int company;
    private int id;
	public Attendee (String first_name, String last_name, int company_number, int ID){//contructor
        name = first_name+last_name;//assign name
        company = company_number;// and company number
        id = ID;
    }
    public String getName(){//method to access attendee name
        return name;
    }
    public int getCompany(){//method to access company number for attendee
        return company;
    }
    public int getID(){
		return id;//method to access id number for attendee
	}
}
