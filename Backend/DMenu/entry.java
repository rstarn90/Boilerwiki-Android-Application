import java.util.ArrayList;
public class entry {
    
    String name;
    public ArrayList <String> food = new ArrayList<String>();
    
    public void addFood(String f){
        food.add(f);
    }
    
    
    public entry(String n){
        name = n;
    }
    
}