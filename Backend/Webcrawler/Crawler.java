import java.io.*;
import java.net.*;
import java.util.regex.*;
import java.sql.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler
{
	Connection connection;
	int urlID;
	String parentURL;
	
	public Properties props;

	Crawler() {
		urlID = 0;
		
	}
	
	public static String toText(String url) throws IOException{
		     try{
		     Document doc = Jsoup.connect(url).get();
		     String contentGet = doc.toString();
		  
		  
		     return Jsoup.parse(contentGet).text();
		     }catch(Exception e){
		      return null;
		     }
		  
		    }
		    

	public void readProperties() throws IOException {
      		props = new Properties();
      		FileInputStream in = new FileInputStream("database.properties");
      		props.load(in);
      		in.close();
	}

	public void openConnection() throws SQLException, IOException
	{
		String drivers = props.getProperty("jdbc.drivers");
      		if (drivers != null) System.setProperty("jdbc.drivers", drivers);

      		String url = props.getProperty("jdbc.url");
      		String username = props.getProperty("jdbc.username");
      		String password = props.getProperty("jdbc.password");

		connection = DriverManager.getConnection( url, username, password);
   	}

	public void createDB() throws SQLException, IOException {
		openConnection();

         	Statement stat = connection.createStatement();
		
		// Delete the table first if any
		try {
			stat.executeUpdate("DROP TABLE URLS");
			stat.executeUpdate("DROP TABLE WORD");
			stat.executeUpdate("DROP TABLE URLSTMP");
		}
		catch (Exception e) {
		}
			
		// Create the table
        	stat.executeUpdate("CREATE TABLE URLSTMP (urlid INT, url VARCHAR(512), description VARCHAR(1))");
        	stat.executeUpdate("CREATE TABLE WORD (word VARCHAR(200), urllist VARCHAR(2000))");
        	stat.executeUpdate("CREATE TABLE URLS (urlid INT, url VARCHAR(512), description VARCHAR(200))");
	}
	
	public boolean wordinDB(String wordFound, int currentIndex) throws SQLException{
		int flag=0;
		int start=0;
		int end=0;
		Statement stat = connection.createStatement();
		//String temp="SELECT * FROM word WHERE word LIKE '"+wordFound+"'";
		//System.out.println(temp);
		ResultSet result = stat.executeQuery( "SELECT * FROM word WHERE word LIKE '"+wordFound+"'");
		if (result.next()) {
        	String urllist=result.getString("urllist");
        	System.out.println(urllist);
        	for(int i=0; i<urllist.length(); i++){
        		if(urllist.charAt(i)==';'){
        			end=i;
        			int indexfound=Integer.parseInt(urllist.substring(start, end));
        			if(indexfound==currentIndex){
        				flag=1;
        			}
        		}
        		start=end+1;
        		
        	}
        	if(flag==0){
        	
			urllist+=Integer.toString(currentIndex);
			urllist+=";";
			System.out.println(urllist);
			stat.executeUpdate("UPDATE word SET urllist='"+urllist+"' WHERE word = '"+wordFound+"'");
        	}
		return true;
	}
		return false;
		
	}

	public boolean urlInDB(String urlFound) throws SQLException, IOException {
        Statement stat = connection.createStatement();
		ResultSet result = stat.executeQuery( "SELECT * FROM urls WHERE url LIKE '"+urlFound+"'");

		if (result.next()) {
	        	System.out.println("URL "+urlFound+" already in DB");
			return true;
		}
	       // System.out.println("URL "+urlFound+" not yet in DB");
		return false;
	}
	
		

	public void insertURLInDB( String url) throws SQLException, IOException {
         	Statement stat = connection.createStatement();
		String query = "INSERT INTO urlstmp VALUES ('"+urlID+"','"+url+"','')";
		//System.out.println("Executing "+query);
		stat.executeUpdate( query );
		urlID++;
	}
	
	public String readURLInDB(int index) throws SQLException, IOException{
		Statement stat = connection.createStatement();
		ResultSet result = stat.executeQuery( "SELECT * FROM urlstmp WHERE urlid='"+index+"'");
		String urlReaded=null;
		while(result.next()){
		urlReaded=result.getString(2);
		}
		System.out.print("urlReaded is "+urlReaded+"\n");
		return urlReaded;
			}


	public String makeAbsoluteURL(String url, String parentURL) {
		if (url.indexOf(":")>0) {
			return url;
		}

		if (url.length() > 0 && url.charAt(0) == '/') {
	
			int posHost = parentURL.indexOf("://");
			if (posHost <0) {
				return url;
			}

			return parentURL + url;
		} 


		
		int posHost = parentURL.indexOf("://");
		if (posHost <0) {
			return url;
		}
		
		return parentURL+url;
		

	}


   	public void fetchURL(String urlScanned) {
		try {
			URL url = new URL(urlScanned);
			System.out.println("urlscanned="+urlScanned+" url.path="+url.getPath());
 
    			// open reader for URL
    			InputStreamReader in = new InputStreamReader(url.openStream());

    			// read contents into string builder
    			StringBuilder input = new StringBuilder();
    			int ch;
			while ((ch = in.read()) != -1) {
         			input.append((char) ch);
			}

     			// search for all occurrences of pattern
    			String patternString =  "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
    			Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
    			Matcher matcher = pattern.matcher(input);
		
			while (matcher.find()) {
    				//int start = matcher.start();
    				//int end = matcher.end();
    				//String match = input.substring(start, end);
				String urlFound = matcher.group(1);
				
				makeAbsoluteURL(urlFound, parentURL);
				
				System.out.println(urlFound);

				// Check if it is already in the database
				if (!urlInDB(urlFound)) {
					insertURLInDB(urlFound);
				}				
	
    				//System.out.println(match);
 			}

		}
      		catch (Exception e)
      		{
       			e.printStackTrace();
      		}
	}
   	
   	
   	////////////////////////////////////////////////////////////////////////////////////////crawl
   	public void startCrawl(String root) throws SQLException, IOException {
   		String[] rootURLs=new String[10];
		int beginIndex=0, endIndex=0;
		int numofURLs=0;
		for(int i=0; i<root.length(); i++){
			if(root.charAt(i)==';'){
				endIndex=i;
				rootURLs[numofURLs]=root.substring(beginIndex, endIndex);
				
				System.out.print(rootURLs[numofURLs]+"\n");
				numofURLs++;
				beginIndex=i+1;
			}
		}
		parentURL=rootURLs[0];
		//System.out.print(numofURLs);
		for(int i=0;i<numofURLs;i++){
			insertURLInDB(rootURLs[i]);
		}
		  		
   	}
   	/////////////////////////////////////////////
   	///////////////////////////////////////////
   	/////////////////////////////////////////
   	///////////////////////////////////////********crawl********
   	public void crawl(int maxurls) throws SQLException, IOException
   	{
   		
   		URL url;
   		int nextURLIDScanned=0;   		
		int wordcount=0;
   		while (nextURLIDScanned < urlID) {
   			int urlIndex=nextURLIDScanned;
   			nextURLIDScanned++;
   			String nexturl;
			nexturl = readURLInDB(urlIndex);
			parentURL=nexturl;
			try {
   		    url = new URL(nexturl);
			} catch(IOException e){
				continue;
			}
			
			
   			InputStreamReader in;
			try {
				in = new InputStreamReader(url.openStream());
			} catch (IOException e) {
				//**********************************************************************
				String des="no description";
	   			Statement stat = connection.createStatement();
	   			String query = "INSERT INTO urls VALUES ('"+urlIndex+"','"+nexturl+"','"+des+"')";
	   			stat.executeUpdate( query );
				
				
				//e.printStackTrace();
				continue;
			}

			StringBuilder input = new StringBuilder();
			int ch;
			while ((ch = in.read()) != -1) {
     			input.append((char) ch);
		}

			String patternString =  "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
			Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(input);
	
		while (matcher.find() && urlID<maxurls) {
			String urlFound = matcher.group(1);
			urlFound=urlFound.substring(1, urlFound.length()-1);
			urlFound=makeAbsoluteURL(urlFound, parentURL);
			int checkurl = urlFound.indexOf("http");
			int checkurl0=urlFound.indexOf("pdf");
			if (checkurl <0 || checkurl0 >=0) {
				continue;
			}
			

			// Check if it is already in the database
			if (!urlInDB(urlFound)) {
				insertURLInDB(urlFound);
				//parentURL=urlFound;
			}				

				//System.out.println(match);
			}
   			
		String contentGet=toText(nexturl);// the String contain the content of html without tag
   			//get description there
		try{
			String description;
			if(contentGet==null){
				description="no description";
			}else if(contentGet.length()<=100){
			description=contentGet;
		}else{
		description=contentGet.substring(0, 99);
		}

		
		
		
		//System.out.print("description:  "+description+"\n");
		Statement stat = connection.createStatement();
		System.out.print("index:"+urlIndex+"  url:"+nexturl+"  description: "+description+" \n");
		String query = "INSERT INTO urls VALUES ('"+urlIndex+"','"+nexturl+"','"+description+"')";
		stat.executeUpdate( query );
		
		
		
		
		} catch (SQLException e){ 
   			String des="no description";
   			Statement stat = connection.createStatement();
   			String query = "INSERT INTO urls VALUES ('"+urlIndex+"','"+nexturl+"','"+des+"')";
   			stat.executeUpdate( query );
   			continue;
   		}
   			// fill the word table here******************************************************
		int fromindex=0;
		int toindex=0;
		
		
		
		for(int i=0; i<contentGet.length(); i++){
		
			 if(contentGet.charAt(i)==' '||contentGet.charAt(i)==','||contentGet.charAt(i)=='.'||contentGet.charAt(i)=='<'||contentGet.charAt(i)=='>'||contentGet.charAt(i)==' '||contentGet.charAt(i)=='\n'||contentGet.charAt(i)=='\r'||contentGet.charAt(i)=='\t'||contentGet.charAt(i)==' '||contentGet.charAt(i)=='.'||contentGet.charAt(i)==','||contentGet.charAt(i)==';'||contentGet.charAt(i)=='('||contentGet.charAt(i)==')'||contentGet.charAt(i)==':'||contentGet.charAt(i)=='&'||contentGet.charAt(i)=='#'||contentGet.charAt(i)=='+'||contentGet.charAt(i)=='-'||contentGet.charAt(i)=='*'||contentGet.charAt(i)=='/'||contentGet.charAt(i)=='!'||contentGet.charAt(i)=='?'||contentGet.charAt(i)=='"'||contentGet.charAt(i)=='['||contentGet.charAt(i)==']'||contentGet.charAt(i)=='{'||contentGet.charAt(i)=='}'||contentGet.charAt(i)==';'||contentGet.charAt(i)=='|'||contentGet.charAt(i)=='='||contentGet.charAt(i)=='\''||contentGet.charAt(i)=='\\'){
				toindex=i;
				String wordfound=contentGet.substring(fromindex, toindex);
				 if(!wordinDB(wordfound,urlIndex)){
					//word is not in DB 
					String urllist=Integer.toString(urlIndex);
					Statement stat = connection.createStatement();
					String query = "INSERT INTO word VALUES ('"+wordfound+"','"+urllist+"')";
					stat.executeUpdate( query );
					 wordcount++;
				 }
				 
				 fromindex=toindex+1;
				 
			 }
		}
		
   			
   		}//end while		
   		
   	}
   	
   	
   	

   	public static void main(String[] args)
   	{
		Crawler crawler = new Crawler();

		try {
			crawler.readProperties();
			String root = crawler.props.getProperty("crawler.root");
			int maxurls = Integer.parseInt(crawler.props.getProperty("crawler.maxurls"));
			crawler.createDB();
			//crawler.fetchURL(root);
			//initial urls
			
			
			crawler.startCrawl(root);
			crawler.crawl(maxurls);
			
			
			
			
		}
		catch( Exception e) {
         		e.printStackTrace();
		}
    	}
}

