//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.ResultSet;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;


public class JdbcExample2
{
   public static void main(String args[]) 
   {
      Connection con = null;
      Statement stmt;
      ResultSet result;
      String query;
      String name;
   
      try
      {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         con = DriverManager.getConnection("jdbc:mysql://localhost/COMPANY", "root", "");
         String pattern = "MM/DD/YYYY";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
         
         if(!con.isClosed())
         {  
            System.out.println("Successfully connected to " +
               "MySQL server using TCP/IP...");
            stmt = con.createStatement();
            query = "SELECT * FROM EMPLOYEE";
            result = stmt.executeQuery(query);
         // result.first()
            while(result.next())
            {
               name = result.getString("Fname") + " " + 
                      result.getString("Lname");
               Date date = result.getDate("Bdate");  
			      String dateStr = dateFormat.format(date);     
               System.out.println(name + "   " + dateStr);
            }    
         }
      } 
      catch(Exception e) 
      {
         System.err.println("Exception: " + e.getMessage());
      } 
      finally
      {
         try
         {
            if(con != null)
               con.close();
         } 
         catch(SQLException e) {}
      }
   }   
}
