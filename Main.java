//Main.java
public class Main{
    public static void main (String[] args){
        Conference c1 = new Conference(10,10);
        c1.emptyFill();
        c1.readFile();
        c1.organize();
        System.out.println(c1);
    }
}