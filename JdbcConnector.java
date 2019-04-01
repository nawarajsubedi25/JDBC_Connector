import java.sql.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class JdbcConnector 
{
   public String JdbcConnector() 
   {
      Connection con = null;
      Statement stmtFirst,stmtSecond,stmtThird;
      ResultSet resultFirst, resultSecond,resultThird;
      String query, dname,name, ssn,dnumber,changeDepartment=" ";
      StringBuilder requiredString =new StringBuilder();
      requiredString.append(emptySpaces("",25)+"Department Dependent Report\r\n");
      try
      {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         con = DriverManager.getConnection("jdbc:mysql://localhost/COMPANY",
            "root", "");
         String pattern = "M/d/yyyy";
         SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
         if(!con.isClosed())
         {  
            stmtFirst = con.createStatement();
            query = "SELECT `Fname`, `Minit`, `Lname`, `Ssn`, `Dno` FROM `employee` ORDER BY `Dno`,`Lname` ASC;";
            resultFirst = stmtFirst.executeQuery(query);
            while(resultFirst.next())
            {
               String relationship="";
               name = resultFirst.getString("Fname") +" "+ resultFirst.getString("Minit")+". " + 
                      resultFirst.getString("Lname");
               dnumber=resultFirst.getString("Dno");
               ssn  = resultFirst.getString("Ssn");
               query ="SELECT `Dname` FROM `department` WHERE `Dnumber`="+dnumber+";";
               stmtSecond = con.createStatement();
               resultSecond=stmtSecond.executeQuery(query);
               while(resultSecond.next()){
                  dname=resultSecond.getString("Dname");
                  if(!dname.equals(changeDepartment))
                  {
                     changeDepartment=dname;
                     requiredString.append(changeDepartment+"\r\n");
                  }
               }
               stmtThird = con.createStatement();
               query="SELECT `Relationship`,`Sex`,`Bdate`,`Dependent_name` FROM `dependent` WHERE `Essn`="+ssn+
                     " ORDER BY FIELD(Relationship, 'spouse') DESC";
               resultThird=stmtThird.executeQuery(query);
               while(resultThird.next())
               {
                  String relation,sex,dependent,bdate="";
                  relation=resultThird.getString("Relationship");
                  if(!relation.equals("Spouse"))
                  {
                     relation="Child";
                  }
                  sex=resultThird.getString("Sex");
                  dependent=resultThird.getString("Dependent_name");
                  Date date = resultThird.getDate("Bdate");  
                  bdate=dateFormat.format(date);   
                  relationship+=emptySpaces("",12)+relation+emptySpaces(relation,10)+sex +emptySpaces("",5)+
                               bdate+emptySpaces(bdate,15)+dependent+"\r\n";
               }
               if(relationship.equals(""))
               {
                  relationship=emptySpaces("",12)+"NO DEPENDENTS\r\n";
               }
               requiredString.append(emptySpaces("",6)+name+"\r\n"+relationship);   
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
      return requiredString.toString();
   }  
   /*
   Create Whitespaces as required number.
   */
   public static String emptySpaces(String str,int num)
   {
      String emptySpace="";
      int i=0;
      while(i<(num-str.length()))
      {
         emptySpace+=" ";
         i++;
      }
      return emptySpace;
   } 
}
