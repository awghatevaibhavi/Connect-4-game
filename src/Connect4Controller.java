/* 
 * Connect4Controller.java 
 * 
 * Version: 
 *     $1.0$ 
 * 
 * Revisions: 
 *     $initial$ 
 */
import java.io.IOException;
/*
 * This program implements Connect Four Game.
 * This is the controller program which receives input from view program and
 * passes data to model class. Also passes the result received from model class
 * and passes result to view program.
 * @author	Chaitali Kamble
 * @author	Vaibhavi Awghate
 */

public class Connect4Controller extends Connect4View {
	//board on which the game is played
	public static char Board[][] = new char[10][26];
	static String ultimate_winner="";
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connect4View cv1= new Connect4View();
		ComputerModel c2= new ComputerModel("A",'+');
		for(int row = 0; row <9; row++)
		{
			for(int column = row; column<25-row; column++)
			{
				Board[row][column]='O';
			}
		}
		for(int iteration_i = 0;iteration_i<9;iteration_i++){
			for(int iteration_j=0;iteration_j<25;iteration_j++)
			{	
				if (Board[iteration_i][iteration_j]=='\u0000')
					System.out.print(" ");
				else
					System.out.print(Board[iteration_i][iteration_j]);
			}
			System.out.println();
		}
		
		if( (cv1.response).equals("Y") || (cv1.response).equals("y"))
			c2.playTheGame();
		else
			c2.playTheGame2();
		ultimate_winner=c2.winner;
	}
	
}
