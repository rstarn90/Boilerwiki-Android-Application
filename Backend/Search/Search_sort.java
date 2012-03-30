import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//select * 
public class Search_sort {
	public static void main(String args[])  {
		
		String searchKey;
		System.out.println("Please input the name of the thing that u r looking for\n");
		Scanner sc = new Scanner(System.in);
		searchKey = sc.nextLine();
		
		String connectionURL = "jdbc:mysql://boilerwiki.db.8313420.hostedresource.com:3306/boilerwiki";
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(connectionURL, "boilerwiki", "dbAdmin_Pass1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String a = "select * from restaurants_details where restaurantName like \'%" + searchKey + "%\'";
			rs = statement.executeQuery(a);
			while (rs.next()) {
				String s = rs.getString(2);
				System.out.print(s);
				System.out.print(" ");
				s = rs.getString("restaurantFood");
				System.out.print(s);
				System.out.print(" ");
				s = rs.getString("restaurantPrice");
				System.out.print(s);
				System.out.print(" ");
				s = rs.getString("restaurantLocation");
				System.out.print(s);
				System.out.print(" ");
				System.out.println();
			}
			a = "select * from classes_detail where className like \'%" + searchKey + "%\' or classDescription like \'%" + searchKey + "%\'";
			rs = statement.executeQuery(a);
			while (rs.next()) {
				String s = rs.getString("classCollege");
				System.out.print(s);
				s = rs.getString("classNumber");
				System.out.print(s);
				System.out.print(" ");
				s = rs.getString("className");
				System.out.print(s);
				System.out.print(" ");
				s = rs.getString("className");
				System.out.print(s);
				System.out.println();
			}
			a = "select * from dining_details where diningName like \'%" + searchKey + "%\'";
			rs = statement.executeQuery(a);
			while (rs.next()) {
				String s = rs.getString(2);
				System.out.println(s);
			}
			a = "select * from hookah_details where hookahName like \'%" + searchKey + "%\'";
			rs = statement.executeQuery(a);
			while (rs.next()) {
				String s = rs.getString(2);
				System.out.println(s);
			}
			a = "select * from studentorg_details where studentorgName like \'%" + searchKey + "%\'";
			rs = statement.executeQuery(a);
			while (rs.next()) {
				String s = rs.getString(2);
				System.out.println(s);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
