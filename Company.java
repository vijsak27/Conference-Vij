/*Company.java
 * Author: Sakshum Vij
 * Date: 4/28/26
 * Program Name: Conference Planner
 * Purpose: This class creates the structure of the company object and defines basic functinoality
 * and properities of the objects
 */
public class Company{
	private String name;
	private int ID;
	
	public Company(String n, int i){
		name = n;
		ID = i;
	}
	
	public String getName(){//retrieve company name
		return name;
	}
	public int getID(){//retrieve company ID
		return ID;
	}
	
}
