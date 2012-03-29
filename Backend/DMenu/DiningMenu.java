import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.util.ByteArrayBuffer;
import java.util.ArrayList;
import java.util.Scanner;

public class DiningMenu {
    
    static final int EARHART = 2;
    static final int FORD = 10;
    static final int HILLENBRAND = 9;
    static final int WILEY = 4;
    static final int WINDSOR = 11;
    static final String PATH = "C:\\Users\\Reid\\Documents\\Boilerwiki\\";
    static String currentDC = "Earhart";
    
    static double currentLA = 40.42575;
    static double currentLO = -86.92499;  
    static boolean vegeOnly = false;
    
    static public ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    static ArrayList<diningCourt> diningList = new ArrayList<diningCourt>();
    
    static public boolean DownloadFromUrl(String imageURL, String fileName) {  //this is the downloader method
        try {
            URL url = new URL(imageURL); //you can write here any link
            File file = new File(fileName);
            
            long startTime = System.currentTimeMillis();
            
            /* Open a connection to that URL. */
            URLConnection ucon = url.openConnection();
            
            /*
             * Define InputStreams to read from the URLConnection.
             */
            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            
            /*
             * Read bytes to the Buffer until there is nothing more to read(-1).
             */
            ByteArrayBuffer baf = new ByteArrayBuffer(50);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            
            /* Convert the Bytes read to a String. */
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baf.toByteArray());
            fos.close();
            return true;
            
        } catch (IOException e) {
            return false;
        }
        
    }//downloader
    
    static void updateList( int type ) {
        
        list.clear();
        int index = 0;
        switch (type){
            case EARHART: index = 0;currentLA = 40.42575; currentLO = -86.92499;currentDC = "Earhart";break;//40.42575 -86.92499
            case FORD: index = 1;currentLA = 40.43211;currentLO = -86.91965; currentDC = "Ford";break;//40.43211 -86.91965
            case HILLENBRAND: index = 2;currentLA = 40.42663; currentLO = -86.92670;currentDC = "Hillenbrand";break;//40.42663 -86.92670
            case WILEY: index = 3 ;currentLA = 40.42945;currentLO = -86.92082;currentDC = "Wiley";break;//40.42945 -86.92082
            case WINDSOR: index = 4;currentLA = 40.42681; currentLO = -86.92097;currentDC = "Windsor";break;//40.42681 -86.92097
        }
        
        try {
            System.out.println(diningList.get(index).place.size());
            
            for (int i =0; i< diningList.get(index).place.size(); i++){
                HashMap<String, String> tempHash = null;    
                
                String placeName = diningList.get(index).place.get(i).name ;
                String menu = "";
                
                for(int j=0;j < diningList.get(index).place.get(i).food.size();j++){
                    if(vegeOnly){
                        if(diningList.get(index).place.get(i).food.get(j).contains("*"))
                            menu += diningList.get(index).place.get(i).food.get(j)+"\n";
                    }
                    else {
                        menu += diningList.get(index).place.get(i).food.get(j)+"\n";
                    } 
                    
                }
                
                if(!menu.equals("")){
                    
                    System.out.println("Place: " + placeName);
                    System.out.println("Menu: " + menu);
                    
                    tempHash = new HashMap<String, String>();
                    tempHash.put("place", placeName);
                    tempHash.put("menu", menu);
                    
                    System.out.println("Place: " + placeName);
                    System.out.println("Menu: " + menu);
                    
                    
                    list.add(tempHash);
                    
                }
            }
            
        } catch (Exception e) { System.out.println("well crap");
            ;
        }
    }
    
    
    static void read(String fileName, int id) throws IOException {
        final String mealType = "<div data-role=\"page\" data-theme=\"d\" id=\"";
        final String mealTypeEnd = "\">";
        // final String newStand = "<div id=\"mealStation\" class=\"meal\">";
        final String newStand = "<li data-role=\"list-divider\">";
        final String endStand = "</li>";
        final String newDish = "<li>";
        final String newDishEnd = "</li>";
        final String newDishEndOther = "<span";
        
        String dcName = null;
        switch (id){
            case EARHART: dcName = "Earhart";break;
            case FORD:dcName = "Ford";break;
            case HILLENBRAND: dcName = "Hillenbrand";break;
            case WINDSOR: dcName = "Windsor";break;
            case WILEY: dcName = "Wiley";break;
        }
        
        System.out.println("-------------------");
        System.out.println("PLACE: " + dcName);
        System.out.println("-------------------");
        
        diningList.add(new diningCourt(dcName));
        int index = 0;
        if(diningList.size()!=0)
            index = diningList.size()-1;
        
        int secondIndex = 0; 
        try{
            
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            
            int olde= 0;
            int oldd = 0;
            
            boolean entryStart = false;
            
            while ((strLine = br.readLine()) != null)   {
                
                String buffer = new String(strLine);
                // System.out.println(strLine );
                if(buffer.contains("There are no items available for this meal")){
                    return;
                    
                }
                
                
                int  s=buffer.indexOf(newStand, olde);
                int ds = buffer.indexOf(newDish,olde);
                int ms = buffer.indexOf(mealType,olde);
                
                int e,de,me;
                //System.out.println("newstand index: " + s + " newdish index: " +  ds);
                if(s != -1 ) {
                    
                    e=buffer.indexOf(endStand, s);
                    String standName = strLine.substring(s+newStand.length(), e);
                    // System.out.println("-------------------");
                    if( standName.contains("&amp;") ){
                        standName = standName.replace(" &amp;","");
                    }
                    if( standName.contains("Monday") || standName.contains("Saturday") || standName.contains("Sunday")) {
                        break;
                    }
                    else {
                        System.out.println();
                        System.out.println("STAND:" + standName);
                        System.out.println("-------------------");
                    }
                    
                }
                else if( ds != -1 ) {
                    if( buffer.contains(newDishEndOther))
                        de=buffer.indexOf(newDishEndOther, ds);
                    else
                        de=buffer.indexOf(newDishEnd, ds);
                    String dishName = strLine.substring(ds+newDish.length(), de);
                    if( dishName.contains("&amp;") ){
                        dishName = dishName.replace(" &amp;","");
                    }
                    System.out.println("\t"+dishName);
                }
                else if( ms != -1 ) {
                    me= buffer.indexOf(mealTypeEnd, ds);
                    String mealName = strLine.substring(ms+mealType.length(), me);
                    if(mealName.equals("Breakfast") || mealName.equals("Lunch") || mealName.equals("Dinner")) {
                        
                        System.out.println(mealName);
                        System.out.println("-------------------");
                        System.out.println("-------------------");
                    }
                }
            }
            /*
             while(s!=-1 && ds!=-1 || entryStart){
             System.out.println("wtf");
             entryStart = true;
             //System.out.println("s: "+ s + " ds: " + ds);
             if(s<ds && s!=-1){
             e=buffer.indexOf(endStand, s);
             String standName = strLine.substring(s+newStand.length(), e);
             System.out.println("Stand name?");
             System.out.println(standName);
             diningList.get(index).addPlace(standName);
             
             if(diningList.get(index).place.size()!=0)
             secondIndex = diningList.get(index).place.size()-1;
             else
             secondIndex=  0;
             
             olde = e+1;
             s=buffer.indexOf(newStand, olde);
             
             }else if (s==-1 && ds != -1){
             entryStart = false;
             while(ds!=-1){
             de=buffer.indexOf(newDishEnd, ds);
             String dishName = strLine.substring(ds+newDish.length(), de);
             
             System.out.println("\t"+dishName);
             diningList.get(index).place.get(secondIndex).addFood(dishName);
             
             oldd = de+1;
             ds=buffer.indexOf(newDish, oldd);
             }
             
             }//else if
             else{
             de=buffer.indexOf(newDishEnd, ds);
             String dishName = strLine.substring(ds+newDish.length(), de);
             
             System.out.println("\t"+dishName);
             diningList.get(index).place.get(secondIndex).addFood(dishName);    
             
             oldd = de+1;
             ds=buffer.indexOf(newDish, oldd);
             
             }
             
             }
             olde = 0;
             oldd= 0;
             }//while
             */
            //Close the input stream
            in.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            
        }
        
        
    }//read
    
    public static void main(String args[]) {
        
        
        System.out.println("Pot?to, Pot&#228;to");
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH)+1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        
        String downloadAddr = "http://www.housing.purdue.edu/Menus/ERHT/" + mYear + "/" + mMonth + "/"+ mDay;
        DownloadFromUrl(downloadAddr, PATH+"menu"+EARHART);
        
        downloadAddr = "http://www.housing.purdue.edu/Menus/FORD/" + mYear + "/" + mMonth + "/"+ mDay;
        DownloadFromUrl(downloadAddr, PATH+"menu"+FORD);
        
        downloadAddr = "http://www.housing.purdue.edu/Menus/HILL/" + mYear + "/" + mMonth + "/"+ mDay;
        DownloadFromUrl(downloadAddr, PATH+"menu"+HILLENBRAND);
        
        downloadAddr = "http://www.housing.purdue.edu/Menus/WILY/" + mYear + "/" + mMonth + "/"+ mDay;
        DownloadFromUrl(downloadAddr, PATH+"menu"+WILEY);
        
        downloadAddr = "http://www.housing.purdue.edu/Menus/WIND/" + mYear + "/" + mMonth + "/"+ mDay;
        DownloadFromUrl(downloadAddr, PATH+"menu"+WINDSOR);
        
        System.out.println("Choose a Dinning Court");
        System.out.println("1) EARHEART \n 2) FORD \n 3) HILLENBRAND \n 4) WILEY \n 5) WINDSOR 0) EXIT");
        
        Scanner user_input = new Scanner( System.in );    
        int input;
        while( (input = user_input.nextInt()) != 0 ) {
            
            try{
                switch(input) {
                    case 1: read(PATH+"menu"+EARHART, EARHART); break;
                    case 2: read(PATH+"menu"+FORD, FORD); break;
                    case 3: read(PATH+"menu"+HILLENBRAND, HILLENBRAND);
                    case 4: read(PATH+"menu"+WILEY, WILEY); break;
                    case 5: read(PATH+"menu"+WINDSOR, WINDSOR);
                }
                // read(PATH+"menu"+EARHART, EARHART);
                //read(PATH+"menu"+FORD, FORD);
                //read(PATH+"menu"+HILLENBRAND, HILLENBRAND);
                //read(PATH+"menu"+WILEY, WILEY);
                //read(PATH+"menu"+WINDSOR, WINDSOR);
                
            }catch(Exception e){
                e.printStackTrace();
                
            }
        }
        // updateList(EARHART);
        
    }
}

