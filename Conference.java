//Conference.java
import java.util.Scanner;

public class Conference(){
    private int ntables;
    private int ppl_per_table;
    private Attendee attendeeArray[];
    private Attendee[][] tables;
    public Conference(int pplPerTable; int numTables){
        ppl_per_table=pplPerTable;
        ntables = numTables;
        attendeeArray[] = new Attendee[(ppl_per_table*ntables)*1.5];
        tables = new Attendee[nTables][ppl_per_table];
    }
    
    public readFile() throws IOExpection{
        File f1 = new File("confGuests.txt");
        Scanner reader = new Scanner(f1);
        int i =0;
        while (reader.hasNextLine()){
            String line = reader.nextLine();
            String[] split = line.split(",");
            String firName = split[2];
            String lasName = split[1];
            int company = Integer.parseInt(split[3]);
            Attendee a = attendeeArray[i];
            i++;
        }

    }

    public Attendee[][] organize(){
        int len = attendeeArray.length;
        for (int i =0; i<len;i++){
            boolean alrHasCompany = false;
            for(int n = 0; i<nTables; n++){
                for (int a = 0; a<ppl_per_table; a++){
                    if((tables[n][a])==(attendeeArray[i].getCompany())){
                        alrHasCompany = True;
                    }
                    else if(tables=[n][a])){//need to check if the spot is empy before assinging the spot
                        tables[n][a]=attendeeArray[i].get;
                    }
                }
            }
        }
        return tables[][]
    }
}