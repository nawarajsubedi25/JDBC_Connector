import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class FileOut2 {
	public static void main(String [] args){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Simple2.txt"));         
			String s ="1234567890|1234567890|1234567890\r\n";
			s += String.format("%10s|%10s|%10s\r\n", "John", "Mary", "Sue");
			s += String.format("%-10s|%-10s|%-10s\r\n", "John", "Mary", "Sue");
			s += String.format("%-10d|%10d|%-10d\r\n", 123, 456, 789);
			s += String.format("%-10.1f|%10.1f|%-10.1f\r\n", 1.23, 4.56, 7.89);
        
			writer.write(s);
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
}
