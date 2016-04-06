import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * This class only contains main() methods.
 * Main reads the input.txt file and inserts all keys to the Skip List.
 * After reaching the end of the file, it continuously reads inputs from the user.
 * Insert*<key>, search*<key>, save fileName, load fileName commands are waited from user.
 * If user enters quit, Main method will terminate itself.
 */

public class Main{

	private static String inputFileName = "input.txt";
	private static SkipList mySkipList;

	public static void main(String[] args){

		BufferedReader rd = null;

		try {
			rd = new BufferedReader(new FileReader(inputFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String inputs = null;
		try {
			inputs = rd.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int maxCapacity = 0;
		try {
			maxCapacity = Integer.parseInt(rd.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * Initialize the skip list with maxCapacity.
		 * Then insert all numbers in the first line.
		 */
		mySkipList = new SkipList(maxCapacity);
		StringTokenizer tok = new StringTokenizer(inputs, " ");
		while (tok.hasMoreTokens()){
			int num1 = Integer.parseInt(tok.nextToken());
			mySkipList.insert(num1);
		}

		Visualize.print(mySkipList);

		/*
		 * Scan the command.
		 * Determine whether it is I/O operation or Visualize operation.
		 */
		Scanner sc = new Scanner(System.in);
		String line = "";
		while(true){
			System.out.print("Type your command > ");
			line = sc.nextLine();

			if (line.equalsIgnoreCase("quit"))
				break;

			tok = new StringTokenizer(line, " *");

			String command = tok.nextToken();
			if (command.equalsIgnoreCase("insert")){
				int key = Integer.parseInt(tok.nextToken());
				Visualize.printSearchPath(mySkipList, key);
				mySkipList.insert(key);
				Visualize.print(mySkipList);
			}
			else if (command.equalsIgnoreCase("search")){
				int key = Integer.parseInt(tok.nextToken());
				Visualize.printSearchPath(mySkipList,key);
			}
			else if (command.equalsIgnoreCase("save")){
				String outputFile = tok.nextToken();
				Operations.save(mySkipList, outputFile);
				System.out.println("Skip List has been saved successfully");

			}
			else if (command.equalsIgnoreCase("load")){
				String source = tok.nextToken();
				mySkipList = new SkipList(maxCapacity);
				Operations.load(source, mySkipList);
				Visualize.print(mySkipList);
			}
			else if (command.equalsIgnoreCase("print")){
				Visualize.print(mySkipList);
			}

			else{
				System.out.println("Could not recognized the command.");
			}
		}

		try {
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sc.close();
		
		/*
		 * End of the program.
		 */
		System.out.println("The Main terminated successfully.");

	}
}
