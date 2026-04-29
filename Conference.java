//Conference.java
import java.util.*;
import java.io.*;
public class Conference{
    private int nTables;
    private int ppl_per_table;
    private Attendee attendeeArray[];
    private ArrayList<Company> companies = new ArrayList<Company>();
    private int numCompanies;
    private Attendee[][] tables;
    private int maxPplPerCompany;
    
    /* take in the number of people per table and the number of tables into the Conference object
    Also define the attendeeArray with the now given numTable and pplPer Table (include 1.5x multiplier to add all resgistered guests
    an ensure they all fit)
    */
    public Conference(int pplPerTable, int numTables, int maxPerCompany){//constructor
        ppl_per_table=pplPerTable;
        nTables = numTables;
        attendeeArray = new Attendee[(int)((ppl_per_table*nTables)*1.5)];//multiplying by 1.5 as suggested by Mr. Twyford to account for extra attendees
        tables = new Attendee[nTables][ppl_per_table];
        numCompanies = companies.size();
        maxPplPerCompany =maxPerCompany;
    }
    

    /*
    read in the java file using a try catch set up
    go throuhg the file and split each line and create a new attenddee object from each line
    add each attendee object to the attendeeArray
    */
    public void readFile(){
        File f1 = new File("confGuests.txt");
        File f2 = new File("companies.txt");
        try(Scanner reader = new Scanner(f1)){//try catch set up
        
        int i =0;
        while (reader.hasNextLine()){
            String line = reader.nextLine();
            String[] split = line.split(",");//split string into an array at commas to get individual datapoints
            String firName = split[2];//access the first name by looking at index 2 (based on confGuests.txt order)
            String lasName = split[1];//access the last name by looking at index 1 (based on confGuests.txt order)
            int company = Integer.parseInt(split[3]);//access company number at index 3 (based on confGuests.txt order) + need to use parseInt() to parse the string and find the company number
            int id = Integer.parseInt(split[0]);
            Attendee a = new Attendee(firName,lasName,company, id);//make attendee object
            attendeeArray[i]=a;//add to list
            i++;
        }

        } catch (FileNotFoundException e){//if error, show error
            System.out.println("Error");
            e.printStackTrace();
        }
        
        
        try(Scanner reader1 = new Scanner(f2)){//try catch set up
        
        int i =0;
        while (reader1.hasNextLine()){
            String line = reader1.nextLine();
            String[] split = line.split(",");//split string into an array at commas to get individual datapoints
            String name = split[1];
            int id = Integer.parseInt(split[0]);
          
            Company c = new Company(name,id);//make company object
            companies.add(c);//add to list
            i++;
        }

        } catch (FileNotFoundException e){//if error, show error
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    


	public void printCompanies(){
		
		System.out.println("Companies Attending Conference with IDs:");
		int length = companies.size();
		for(int i = 0; i<length; i++){
			System.out.println(companies.get(i).getName()+", "+companies.get(i).getID());
		}
		
		System.out.println("\n\n");
		
	}
	
	public void cleanUpTableRosters(){
		int length = companies.size();
		ArrayList<Attendee> extrasForCompany = new ArrayList<Attendee>();
		int count;
		for(int i = 0; i<length; i++){
			count = 0;
			for(int table = 0; table<nTables; table++){
				for(int seat = 0; seat<ppl_per_table; seat++){
					if(companies.get(i).getID()==tables[table][seat].getCompanyID()){
						count++;
						if(count>maxPplPerCompany){
							extrasForCompany.add(tables[table][seat]);
							tables[table][seat].setTable(-1);//set to negative 1 to ensure that it shows up as no assign attendee at that seat
							
							tables[table][seat] = new Attendee("empty","empty", -1,-1);//fill with default empty values
						}
					}
				}
			}
		}
		int numExtras = extrasForCompany.size();
		if(numExtras>0){
			System.out.println("The following individuals were removed since they were over the max\nnumber of attendees per company of ("+maxPplPerCompany+")");
			for(int i = 0; i<numExtras; i++){
				System.out.println((i+1)+". "+extrasForCompany.get(i).getName()+", "+extrasForCompany.get(i).getCompany().getName());
				extrasForCompany.get(i).makeUnassignedsSinceExtraForCompany();
			}
		}
		
	}

    /*
    this function allows the user to manually add any addtional guests to the registration list
    returns a boolean that shows whether the attendee could be added based on the max occupancy
    this retunred value will be used in main in the do while loop that will only run while attendees can be added
    if attendees can be added, take in user inputs using a scanner and create an attendee object and put it in the
    next empty spot in attendeeArray[]
    */
    public boolean addManually(){
		numCompanies = companies.size();
        int length = attendeeArray.length;
        int attendeeCount=0;
        for(int i = 0; i<length; i++){
            if (attendeeArray[i]!=null){//get numAttendees
                attendeeCount++;
                
            }
        }
        int maxOccupancy =nTables*ppl_per_table;//calculated maxOccupancy based on number of total seats
        if (attendeeCount>=maxOccupancy){// dont add more than maxOccupancy
            System.out.println("Max Occupancy ("+maxOccupancy+") Reached. Press enter to continue\n");
            return false;//return that the attendee was not added - used in Main.java for loop logic
        }
        else{
			printCompanies(); //show the user what companies correspond with which ID
            System.out.println("Number of Attendees: "+attendeeCount);//show how many current attendees
            Scanner scan = new Scanner(System.in);// for input from user
            System.out.println("First name of attendee: ");
            String fName = scan.nextLine();//get first name
            System.out.println("Last name of attendee: ");
            String lName = scan.nextLine();//get last name
            int compNum = -1;
            boolean compNumValid = false;
            while(!compNumValid){
				System.out.println("Company number of attendee (must be 1-16; see above): ");
				compNum = Integer.parseInt(scan.nextLine());//parse the users input for a company number int
				for(int i = 0; i < numCompanies; i++){
					if(companies.get(i).getID()==compNum){
						compNumValid = true;
						break;
					}
				}
			}
			System.out.println("Press enter to continue");
			int id = attendeeCount+1;
            Attendee a1 = new Attendee(fName, lName, compNum, id);//make attendee
            for(int i = 0 ; i<attendeeArray.length; i++){//loop through attendeeArray
                if(attendeeArray[i]==null){//find empty spot
                    attendeeArray[i] = a1;//fill it with attendee
                    break;
                }
            }
            
            
        }
        return true;
    }
    /*
    this method simply fills the tables array with empty Attendee objects which will be used when comparing whether another
    person can be seated there
    */
    public void emptyFill(){
        for(int i = 0; i<nTables; i++){//loop through tables array
            for (int n = 0; n<ppl_per_table; n++){
                tables[i][n]= new Attendee("empty","empty", -1,-1);//fill with default empty values
            }
        }
    }

    /*
    organize the tables array with the attendees. First check if a table already has the company of the attendee
    if alrHasCompany is false, seat the attendee at the next available seat
    after it has organized the tables it will return the ta){bles array
    */
    public Attendee[][] organize(){
        int len = attendeeArray.length;
        for (int i =0; i<len;i++){
            if (attendeeArray[i]==null){//make sure it is not an empty spot in the attendeeArray
                continue; // if no attendee in that spot in attendeeArray skip this iteration - reference: https://www.w3schools.com/java/java_break.asp
            }
            boolean seated = false;//every attendee starts off as not seated

            for(int n = 0; n<nTables; n++){
                boolean alrHasCompany = false;//assume each table does not have the company of the current attendee already there
                for (int a = 0; a<ppl_per_table; a++){//loop through the current table
                    if((tables[n][a].getCompanyID())==((attendeeArray[i]).getCompanyID())){//check if the company is alreayd there
                        alrHasCompany = true;//if the company is already there set alrHasCompany to true                
                    }
                }                
                for (int c = 0; c<ppl_per_table; c++){// loop through the table
                    if(!alrHasCompany){//if the table doesn't already have the company
                        if(tables[n][c].getCompanyID()==-1){// and if the seat is empty
                            tables[n][c]=attendeeArray[i];//place attendee in that seat
                            tables[n][c].setSeat(c);
                            seated = true;//make seated true 
                            attendeeArray[i].setTable(n);
                            break; // break out of the loop so it doesnt keep on placing the same attendee - reference: https://www.w3schools.com/java/java_break.asp
                        }                               
                    }
                }
                if (seated){//if seated stop trying to loop through the tables and organzie that same attendee
                    break;
                }                
            }
		}	
		cleanUpTableRosters();
        return tables;
    }


	public ArrayList<Attendee> howManyUnseated(){
		int numAttendees = attendeeArray.length;
		ArrayList<Attendee> unseatedAttendees = new ArrayList<Attendee>();
		
		for(int attendee = 0; attendee<numAttendees; attendee++){
			boolean seated = false;
			for(int table = 0; table<nTables; table++){
				for(int seat = 0; seat<ppl_per_table; seat++){
					if(tables[table][seat].getID()==(attendeeArray[attendee].getID())){
						seated = true;
					}
				}
			}
			if(!seated){
				unseatedAttendees.add(attendeeArray[attendee]);
			}
		}
		
		return unseatedAttendees;
	}

	public void assignAttendeeCompanyObjects(){
		int length = attendeeArray.length;
		for(int i = 0; i<length; i++){
			if(attendeeArray[i]==null){
					continue; //skip extra spaces in attendee array
			}
			for(int n = 0; n<companies.size(); n++){
				if(attendeeArray[i].getCompanyID()==companies.get(n).getID()){
					attendeeArray[i].setCompany(companies.get(n));
					break;
				}
			}
		}
	}

    /*
    prints out the tables array
    currently the result string is accessing the company numbers of every attendee
    may be changed to name/id number/etc. later on
    */
    public String toString(){
        String result  = "";
        for(int i = 0; i<nTables; i++){//loop through the tables array
            for (int n = 0; n<ppl_per_table;n++){
                result += (tables[i][n]).getID() + " ";//use getID() on each item in the tables array
            }
            result += "\n";//spacing
        }
        return result;//return array of company numbers
    }
    
    public void menu(){
		assignAttendeeCompanyObjects();
		boolean organized = false;
		System.out.println("Launching Conference Planner...");
		System.out.println("------------------------------");
		System.out.println("Enter 'q' to quit or press enter to continue");
		Scanner s2 = new Scanner(System.in);
		while(!s2.nextLine().equals("q")){
			System.out.println("\nOptions:");
			if(!organized){
				System.out.println("1. Organize Attendees");
				System.out.println("2. Add Attendee Manually");
				System.out.println("Enter 1, 2, or q (quit)");
				String userInput = s2.nextLine();
				if(userInput.equals("1")){
					organize();
					organized = true;
					System.out.println("Press enter to continue");
				}
				else if(userInput.equals("2")){
					addManually();
					assignAttendeeCompanyObjects();
				}
				else if(userInput.equals("q")){
					break;
				}
				else{
					System.out.println("Invalid Input. Press enter to continue\n");
				}
			}
			else {
				System.out.println("1. Print Tables");
				System.out.println("2. Print Specific Table");
				System.out.println("3. Print Company Rosters");
				System.out.println("4. Locate Specfic Individual");
				System.out.println("Enter 1, 2, 3, 4, or q (quit)");
				String userInput = s2.nextLine();
				
				if(userInput.equals("1")){
					String result  = "Table Assignments (-1 indicates an empty spot)\n";
					
					for(int i = 0; i<nTables; i++){//loop through the tables array
						result += "Table " + (i+1)+": ";
						for (int n = 0; n<ppl_per_table;n++){
							
							result += (tables[i][n]).getID() + " ";//use getCompanyID() on each item in the tables array
						}
						result += "\n";//spacing
					}
					
					
					result+="\nPress enter to continue";
					System.out.println(result);
				}
				else if(userInput.equals("2")){
					Scanner s5 = new Scanner(System.in);
					int targetTable = -1;
					ArrayList<Attendee> tableRoster = new ArrayList<Attendee>();
					
					while(targetTable <1 || targetTable>nTables){
						System.out.println("Which table's rosters would you like to print? Must be 1-"+nTables);
						targetTable=s5.nextInt();
					}
					
					for(int seat = 0; seat<ppl_per_table; seat++){
						if(tables[targetTable-1][seat].getID()!=-1){
							System.out.println((seat+1)+". "+tables[targetTable-1][seat].getName()+", "+tables[targetTable-1][seat].getCompanyName());
						}
					}
					
					
					System.out.println("Press enter to return to menu");
					
				}
				else if(userInput.equals("3")){
					Scanner s6 = new Scanner(System.in);
					System.out.println("Company IDs:");
					printCompanies();
					int compNum=-1;
					boolean compNumValid=false;
					while(!compNumValid){
						System.out.println("Which company's roster would you like to print (must be 1-16; see above): ");
						compNum = Integer.parseInt(s6.nextLine());//parse the users input for a company number int
						int length = companies.size();
						for(int i = 0; i <length ; i++){
							if(companies.get(i).getID()==compNum){
								compNumValid = true;
								break;
							}
						}
					}
					
					
					
					System.out.println("Company Roster with Table Numbers");
					ArrayList<Attendee> companyRoster = new ArrayList<Attendee>();
					
					int numAttendees = attendeeArray.length;
					
					for(int table = 0; table<nTables; table++){
						for(int seat = 0; seat<ppl_per_table; seat++){
							if(tables[table][seat].getCompanyID() == compNum){
								companyRoster.add(tables[table][seat]);
							}
						}
					
					}
					int rosterSize = companyRoster.size();
					for(int i = 0; i<rosterSize; i++){
						System.out.println((i+1)+". "+companyRoster.get(i).getName()+ ", "+ companyRoster.get(i).getTable());
					}
					
					System.out.println("Press enter to return to menu");
					
				}
				else if(userInput.equals("4")){
					Scanner s7 = new Scanner(System.in);
					System.out.println("Enter Attendee ID (if you do not know Attendee ID enter 0)");
					int response = s7.nextInt();
					if(response==0){
						System.out.println("Full List of Attendees with IDs");
						int numAttendees = attendeeArray.length;
						for(int attendee = 0; attendee<numAttendees; attendee++){
							if(attendeeArray[attendee]==null){
								continue;
							}
							else{
								System.out.println("Attendee: "+attendeeArray[attendee].getName()+", ID: "+attendeeArray[attendee].getID());
							}
						}
						System.out.println("Identify the ID of your desired attendee and press enter to return to menu");
					}
					else if (response>0 && response<attendeeArray.length && attendeeArray[response-1]!=null){
						if(attendeeArray[response-1].getTable()==-1){
							System.out.println("This attendee is not seated/was removed since the limit of attendees for their company was hit or seating was not possible");
						}
						else{
							System.out.println("Attendee: "+attendeeArray[response-1].getName()+", Company: "+attendeeArray[response-1].getCompanyName()+", Table: "+(attendeeArray[response-1].getTable()+1)+", Seat: "+(attendeeArray[response-1].getSeat()+1));
							System.out.println("Press enter to return to menu");
						}
					}
					else{
						System.out.println("Invalid ID. No attendee has this ID. Press enter to return to menu");
					}
				}
				else if(userInput.equals("q")){
					break;
				}
				else{
					System.out.println("Invalid Input. Press enter to continue\n");
					continue;
				}
			}
		}
	}
}


