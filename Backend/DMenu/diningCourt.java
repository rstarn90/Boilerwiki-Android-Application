import java.util.ArrayList;

public class diningCourt {

 public ArrayList <entry> place = new ArrayList<entry>();
 String name;
 public diningCourt(String n){
  name = n ;
 }

 public void addPlace (String n){
  place.add(new entry(n));
 }

}