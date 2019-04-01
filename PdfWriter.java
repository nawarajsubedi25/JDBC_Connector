import java.io.*;
import java.nio.file.*;

public class PdfWriter {
   public static void main(String [] args){
      try {
         PrintWriter pw = new PrintWriter("file1.txt");
         JdbcConnector connectDatabase = new JdbcConnector();
         String s = connectDatabase.JdbcConnector();
         pw.write(s);
         pw.flush();
      }
      catch (Exception e){
         System.out.println(e);
      }
   }
}
