import java.sql.Date;
import java.text.SimpleDateFormat;


public class DepartmentDependent
{
Connection con = null;
      Statement stmt;
      ResultSet result;
      String query;
      String name;


   public static String DepartMentFamily()
   {
   
      JdbcConnector connectDatabase = new JdbcConnector();
      connectDatabase.JdbcConnector();
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
      return "";
   }


}