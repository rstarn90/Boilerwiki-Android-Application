
import java.io.IOException;
import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.*;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PurdueNews {
 
 
 static Document dom;
 static ArrayList<article> articles = new ArrayList<article>();
 
 
  static void parseNews() {

  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
  
  try {
   
   DocumentBuilder db = dbf.newDocumentBuilder();
 
   //parse using builder to get DOM representation of the XML file
   dom = db.parse("http://www.purdue.edu/newsroom/rss/FeaturedNews.xml");
 
 
  }catch(ParserConfigurationException pce) {
   pce.printStackTrace();
  }catch(SAXException se) {
   se.printStackTrace();
  }catch(IOException ioe) {
   ioe.printStackTrace();
  }
  
  Element root = dom.getDocumentElement();
  NodeList nl = root.getElementsByTagName("item");
  
  if(nl != null && nl.getLength() > 0) {
   for(int i = 0; i < nl.getLength(); i++) {
    
    //get the news info
    Element news_element = (Element)nl.item(i);
    

    //make a new artical element
    article new_article = getArticleInfo(news_element);
    
    //add to list
    articles.add(new_article);
   }
  }
 }
 
 public static article getArticleInfo(Element e) {
  String date = getTextValue(e, "pubDate");
  String title = getTextValue(e, "title");
  String desc =  getTextValue(e, "description");
  String link = getTextValue(e, "link");
  
  article a = new article(date, title, desc, link);
  return a;
  
 }
 
 private static String getTextValue(Element ele, String tagName) {
  String textVal = null;
  NodeList nl = ele.getElementsByTagName(tagName);
  if(nl != null && nl.getLength() > 0) {
   Element el = (Element)nl.item(0);
   if( el.getFirstChild() != null ) {
       textVal = el.getFirstChild().getNodeValue();
   }
  }

  return textVal;
 }
 
 public static void main(String args[]) {
  parseNews();
  String[] artArr = new String[articles.size()];
  for(int i = 0; i < articles.size(); i++) { 
   article temp = articles.get(i);
   artArr[i] = temp.title + '\n' + temp.date + '\n' + temp.story + '\n' + temp.link;
   
   System.out.println("-----------------------------------");
   System.out.println(artArr[i]);
  // System.out.println("Title: " + temp.title );
  // System.out.println("Date: " + temp.date );
  // System.out.println("Story: " + temp.story );
  // System.out.println("link: " + temp.link );
   System.out.println("-----------------------------------");
   System.out.println();
   
   
  }
  
 }
}
