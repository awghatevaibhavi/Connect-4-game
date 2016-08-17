/* 
 * Connect4View.java 
 * 
 * Version: 
 *     $1.0$ 
 * 
 * Revisions: 
 *     $initial$ 
 */
import java.io.*;
/*
 * This program implements Connect Four Game.
 * This is the view program which takes input from user and passes data to 
 * controller class. Also displays the winner received from controller class.
 * @author	Chaitali Kamble
 * @author	Vaibhavi Awghate
 */
public class Connect4View {
	static String response = "";
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Connect4Controller c1 = new Connect4Controller();
		System.out.println("********Connect 4********");
		System.out.println("Do you want to play alone? (Y/N)");
		BufferedReader br = new BufferedReader
				(new InputStreamReader(System.in));
		response= br.readLine();
		c1.main(args);
		System.out.println("The winner is:" + c1.ultimate_winner);
	}
}
