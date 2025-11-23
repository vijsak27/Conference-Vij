//Main.java
import java.util.Scanner;
public class Main{
    public static void main (String[] args){
        Conference c1 = new Conference(10,10);
        Scanner s = new Scanner(System.in);
        boolean addMore = true;
        do{
            System.out.println("Would you like to add any attendees? (y/n)");
            if((s.nextLine().equals("y"))){
                boolean added = c1.addManually();
                if (!added){
                    addMore=false;
                }
                addMore = true;
                
            }
            else{
                addMore=false;
            }
        }while(addMore);
        c1.emptyFill();
        c1.readFile();
        c1.organize();
        System.out.println(c1);
    }
}