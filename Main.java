import java.util.Scanner;

class Main {
  
 public static void main(String[] args)
  {
    Magpie4 maggie = new Magpie4();
		
		System.out.println (maggie.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
		String statementLower = statement.toLowerCase();
    
		while (!statementLower.equals("bye"))
		{
			System.out.println (maggie.getResponse(statement));
			statement = in.nextLine();
      statementLower = statement.toLowerCase();
		}
  }
}