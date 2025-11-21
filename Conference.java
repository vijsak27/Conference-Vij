//Conference.java
import java.util.Scanner;
import java.io.*;
public class Conference{
    private int nTables;
    private int ppl_per_table;
    private Attendee attendeeArray[];
    private Attendee[][] tables;
    public Conference(int pplPerTable, int numTables){
        ppl_per_table=pplPerTable;
        nTables = numTables;
        attendeeArray = new Attendee[(int)((ppl_per_table*nTables)*1.5)];
        tables = new Attendee[nTables][ppl_per_table];
    }
    
    public void readFile() throws IOException{
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
    public void emptyFill(){
        for(int i = 0; i<nTables; i++){
            for (int n = 0; n<ppl_per_table; n++){
                tables[i][n]= new Attendee("empty","empty", -1);
            }
        }
    }
    public Attendee[][] organize(){
        int len = attendeeArray.length;
        for (int i =0; i<len;i++){
            boolean alrHasCompany = false;
            for(int n = 0; i<nTables; n++){
                for (int a = 0; a<ppl_per_table; a++){
                    if((tables[n][a].getCompany())==(attendeeArray[i].getCompany())){
                        alrHasCompany = true;
                    }

                }
                for (int r = 0; r<nTables; r++){
                    for (int c = 0; c<ppl_per_table; c++){
                        if(!alrHasCompany){
                            if(tables[r][c].getCompany()==-1){
                                tables[r][c]=attendeeArray[i];
                            }
                               
                    }
                    }
                }
                
            }
            
        }
        return tables[][];
    }


    public String toString(){
        for(int i = 0; i<nTables; i++){
            for (int n = 0; n<nTables;n++){
                System.out.println(tables[i][n]);
            }
            System.out.println("\n");
        }
    }
}