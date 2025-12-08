//Conference.java
import java.util.Scanner;
import java.io.*;
public class Conference{
    private int nTables;
    private int ppl_per_table;
    private Attendee attendeeArray[];
    private Attendee[][] tables;
    private Boolean organized = false;
    private String[] companyNames= new String [16];
    private int[] companyNumbers = new int[16];
    
    /* take in the number of people per table and the number of tables into the Conference object
    Also define the attendeeArray with the now given numTable and pplPer Table (include 1.5x multiplier to add all resgistered guests
    an ensure they all fit)
    */
    public Conference(int pplPerTable, int numTables){//constructor
        ppl_per_table=pplPerTable;
        nTables = numTables;
        attendeeArray = new Attendee[(int)((ppl_per_table*nTables)*1.5)];
        tables = new Attendee[nTables][ppl_per_table];
    }
    

    /*
    read in the java file using a try catch set up
    go throuhg the file and split each line and create a new attenddee object from each line
    add each attendee object to the attendeeArray
    */
   
    public void readFile(){
        File f1 = new File("confGuests.txt");
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
    }
    
    public void readCompanies(){
        File f1 = new File("companies.txt");
        try(Scanner reader = new Scanner(f1)){//try catch set up
        
        int i =0;
        while (reader.hasNextLine()){
            String line = reader.nextLine();
			if ((line.split(",")).length<2){
				continue;
			}
			
            String[] split = line.split(",");//split string into an array at commas to get individual datapoints
            String companyName = split[1];//access the company name by looking at index 1 (based on companies.txt order)
            int companyNumber = Integer.parseInt(split[0]);//access the company number by looking at index 0 (based on companies.txt order)
            companyNames[i]=companyName;//add to company name list
            companyNumbers[i]=companyNumber;// add to company numbers list
            
            i++;
            
        }

        } catch (FileNotFoundException e){//if error, show error
            System.out.println("Error");
            e.printStackTrace();
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
        int length = attendeeArray.length;
        int attendeeCount=0;
        for(int i = 0; i<length; i++){
            if (attendeeArray[i]!=null){//get numAttendees
                attendeeCount++;
                
            }
        }
        int maxOccupancy =nTables*ppl_per_table;//calculated maxOccupancy based on number of total seats
        if (attendeeCount>maxOccupancy){// dont add more than maxOccupancy
            System.out.println("Max Occupancy ("+maxOccupancy+") Reached\n");
            return false;//return that the attendee was not added - used in Main.java for loop logic
        }
        else{
            System.out.println("Number of Attendees: "+attendeeCount);//show how many current attendees
            Scanner scan = new Scanner(System.in);// for input from user
            System.out.println("First name of attendee: ");
            String fName = scan.nextLine();//get first name
            System.out.println("Last name of attendee: ");
            String lName = scan.nextLine();//get last name
            System.out.println("Company number of attendee: ");
            int compNum = Integer.parseInt(scan.nextLine());//parse the users input for a company number int
			int id = attendeeCount+1;
			System.out.println("\nAttendee added!\nName: "+ fName+" "+lName+ ", Id: "+id +"\n");
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


    
    public void printTables(){
		for (int i = 0 ; i <nTables; i++){
			System.out.println("Table " + (i+1)+":\n");
			for(int n = 0 ; n<ppl_per_table; n++){
				if ((tables[i][n].getName()).equals("emptyempty")){
					System.out.println("----\n");
				}
				else{
					System.out.println(tables[i][n].getName());
				}
				
			}
		
		}
		}
		
    /*
    organize the tables array with the attendees. First check if a table already has the company of the attendee
    if alrHasCompany is false, seat the attendee at the next available seat
    after it has organized the tables it will return the tables array
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
                    if((tables[n][a].getCompany())==((attendeeArray[i]).getCompany())){//check if the company is alreayd there
                        alrHasCompany = true;//if the company is already there set alrHasCompany to true                
                    }
                }                
                for (int c = 0; c<ppl_per_table; c++){// loop through the table
                    if(!alrHasCompany){//if the table doesn't already have the company
                        if(tables[n][c].getCompany()==-1){// and if the seat is empty
                            tables[n][c]=attendeeArray[i];//place attendee in that seat
                            seated = true;//make seated true 
                            break; // break out of the loop so it doesnt keep on placing the same attendee - reference: https://www.w3schools.com/java/java_break.asp
                        }                               
                    }
                }
                if (seated){//if seated stop trying to loop through the tables and organzie that same attendee
                    break;
                }                
            }
		}	
        return tables;
    }

    /*
    prints out the tables array
    currently the result string is accessing the company numbers of every attendee
    may be changed to name/id number/etc. later on
    */
    public String toString(){
        String result  = "";
        for(int i = 0; i<nTables; i++){//loop through the tables array
            for (int n = 0; n<nTables;n++){
                result += (tables[i][n]).getID() + " ";//use getCompany() on each item in the tables array
            }
            result += "\n";//spacing
        }
        return result;//return array of company numbers
    }
    
    public void companyRosters(){
		Scanner s1 = new Scanner(System.in);
		System.out.println("Please accurately type the company you are looking for\n and capitalize the first letter of each word: ");
		String target = s1.nextLine();
		int length = companyNames.length;
		int targetIndex = 0;
		boolean found = false;
		for (int i =0; i<length; i++){
				if (companyNames[i].equals(target)){
					found = true;
					targetIndex = i;
					break;
				}
			}
		if (found){
			for (int r =0 ; r<nTables; r++){
				for (int c = 0; c<ppl_per_table; c++){
					if(tables[r][c].getCompany()==companyNumbers[targetIndex]){
							System.out.println("\nName: "+ tables[r][c].getName() + "\nTable: "+ r + "\nSeat: "+ c);
					}
				}
			}
		}
		else{
			System.out.println("Company does not exists.\n");
		}
		}
    
    public void menu(){
		Scanner s1 = new Scanner(System.in);
		
		int input = 1;
		do {
			System.out.println("Menu:\n1. Add Attendee Manually\n2. Organize seats\n3. Seating by Table\n4. Print Specific Company Roster");
			input = Integer.parseInt(s1.nextLine());
			if (input ==1){
				
				addManually();//run add manually if the user wants to add an attendee.
			}
			else if (organized ==false &&input ==2){
				organize();
				organized = true;
				System.out.println(this.toString());
			}
			else if(organized ==true &&input ==2){
				System.out.println(this.toString());
			}
			else if (input ==3){
				printTables();
			}
			else if (input ==4){
			companyRosters();
			}
		} while ((input ==1)||(input ==2)||(input ==3)||(input==4));			
		
	}
}
