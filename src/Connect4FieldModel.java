/* 
 * Connect4Field.java 
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
 * This program implements Connect4FieldInterface Interface.
 * It requires one player to play the game and terminate the game if player
 * wins.
 * @author	Chaitali Kamble
 * @author	Vaibhavi Awghate
 */
public class Connect4FieldModel implements Connect4IntefaceModel{
	//array to store board for the game 
	public char Board[][] = new char[10][26];
	//column in which the game piece is inserted
	public int column_number=0;
	//stores game piece assigned to player
	public char gamePiece;
	//method to print my playing board
	public void printBoard(){
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
	}
	/*
	 * This method checks if the game piece can be dropped at the column number
	 * entered by player.
	 * @return	flag	either true or false based on the validations
	 */
	public boolean checkIfPiecedCanBeDroppedIn(int column_number){
		boolean flag = false;
		int row = 8;
		while(row>=0){
			if(column_number <0 || column_number>24){
				flag=false;
				break;
			}
			if(Board[row][column_number]=='\u0000' || 
					Board[row][column_number]!='O' ){
						row--;
			}	
			else if(Board[row][column_number]== 'O'){
					flag = true;
					break;
				}
		}
		return flag;
	}
	/*
	 * This method drops the game piece in the column number entered by user.
	 * It checks for the most bottom empty place in the column and drops there
	 * and prints the updated board.
	 */
	public void dropPieces(int column_number, char gamePiece){
		
		int row = 8;
		while(row>=0){
			if(Board[row][column_number]=='\u0000' ||
					Board[row][column_number]!='O' ){
						row--;
			}	
			else if(Board[row][column_number]== 'O'){
					Board[row][column_number]=gamePiece;
					break;
			}
		}
		for(int iteration_i = 0;iteration_i<9;iteration_i++){
			for(int iteration_j=0;iteration_j<25;iteration_j++){
				if (Board[iteration_i][iteration_j]=='\u0000')
					System.out.print(" ");
				else
					System.out.print(Board[iteration_i][iteration_j]);
			}
			System.out.println();
		}
	}
	/*
	 * This method allows two players to play simultaneously.
	 * It also checks for the draw and winning condition between two players 
	 * and prints the appropriate winner.  
	 */
	public void playTheGame() {
		boolean gameIsOver = false;
		do{
			checkIfPiecedCanBeDroppedIn(column_number);
			dropPieces(column_number,gamePiece);
			if ( didLastMoveWin() ) {
				gameIsOver = true;
			}
			System.out.println("A"+didLastMoveWin());
			System.out.println("b"+gameIsOver);
		}while(! gameIsOver);
	}
	/*
	 * This method checks for the winning condition horizontally, vertically
	 * and diagonally.
	 * @return	flag	either true or false depending upon the players' move
	 */
	public boolean didLastMoveWin(){
		boolean flag=false;
		if(horizontalWin()){
			System.out.println("You Won");
			flag=true;
		}
		if(verticalWin()){
			System.out.println("You Won");
			flag=true;
		}
		if(diagonalWin()){
			System.out.println("You Won");
			flag=true;
		}
		return flag;
	}
	/*
	 *This method checks for the winning condition horizontally. 
	 *@return	flag	either true or false depending upon the player's move
	 */
	boolean horizontalWin(){
		int match_counter=1;
		boolean flag=false;
		for(int row=0;row<9;row++){
			for(int column=0;column<25;column++){
				if(Board[row][column]!='\u0000'){
					if(Board[row][column]!='O'){
						if (Board[row][column]==Board[row][column+1]){
							match_counter++;
							}
						else
							flag = false;
					}
				}
				if(match_counter==4){
					flag=true;
					break;
				}
			}
			if (flag == true)
				break;
		}
		return flag;
	}
	/*
	 *This method checks for the winning condition vertically. 
	 *@return	flag	either true or false depending upon the player's move
	 */
	boolean verticalWin(){
		int match_counter=1;
		boolean flag=false;
		for(int column=0;column<25;column++){
			for(int row=8;row>=0;row--){
				if(Board[row][column]!='\u0000'){
					if(Board[row][column]!='O'){
						if(Board[row][column]==Board[row+1][column])
							match_counter++;
						else
							flag=false;
					}
				}
				if(match_counter==4){
					flag=true;
					break;
				}
			}
			if (flag == true)
				break;
		}
		return flag;
	}
	/*
	 *This method checks for the winning condition diagonally. 
	 *@return	flag	either true or false depending upon the player's move
	 */
	boolean diagonalWin(){
		int match_counter=0;
		boolean flag=false;
		for(int row=8; row>=3;row--){
			for(int column=0;column<25;column++){
				if(Board[row][column]!='\u0000'){
					if(Board[row][column]!='O'){
						if(Board[row][column]==Board[row-1][column+1]&& 
						   Board[row-1][column+1]== Board[row-2][column+2]&&
						   Board[row-2][column+2]==Board[row-3][column+3]){
							flag=true;
							break;
						}
						else
							flag=false;
					}
				}	
			}
			if (flag == true)
				break;
		}
		return flag;	
	}
	public String toString(){
		return this.getClass().getName();
	}
	/*
	 *This method checks for the draw condition. 
	 *@return	flag	either true or false depending upon the player's move
	 */
	public boolean isItaDraw(){
		boolean flag=false;
		for(int row=0;row<9;row++){
			for(int column=0;column<25;column++){
				if(Board[row][column] == 'O'){
					flag=false;
					break;
				}
				else
					flag=true;
			}
			if(flag==false)
				break;
		}
		if (flag == true)
			System.out.println("It's a draw");
		return flag;
	}
	//method to initialize name of the players 
	public void init( PlayerInterfaceModel playerA, 
			PlayerInterfaceModel playerB ){
		System.out.println("Connect Four Game");
	}
	public void init(PlayerModel aPlayer, PlayerModel bPlayer) {
		// TODO Auto-generated method stub
		System.out.println("The player is A");
	}
}

