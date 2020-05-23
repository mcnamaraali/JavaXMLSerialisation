import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException;
import java.util.NoSuchElementException;
import javax.xml.bind.JAXB;

public class CreateSequentialFile {

	public static void main(String[] args) {
		try(BufferedWriter output = 
				Files.newBufferedWriter(Paths.get("out.xml"))) {
			Scanner input = new Scanner(System.in);
			
			Accounts accounts = new Accounts();
			
			System.out.printf("%s%n%s%n", "Enter account number, first name, last name and balance", 
					"Enter end-of-file indicator to end input"
					);
			
			while(input.hasNext()) {
				try {
					Account record = new Account(input.nextInt(), input.next(), input.next(), input.nextDouble());
					
					accounts.getAccounts().add(record);				
					}
				catch (NoSuchElementException e){
					System.err.println("Invalid input. Please try again");
					input.nextLine();
					
				}
				System.out.println("*");
			}
			//write AccountList's XML to output
			JAXB.marshal(accounts,output);
		}
		catch (IOException ioException) {
			System.err.println("Error opening file. Terminating.");
		}

	}

}
