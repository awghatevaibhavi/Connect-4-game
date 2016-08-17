
/* 
 *ComputerModel.java 
 * 
 * Version: 
 *     $1.0$ 
 * 
 * Revisions: 
 *     $initial$ 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/*
 * This program implements Connect Four Game.
 * This program implements PlayerInterface and Connect4FieldInterface 
 * Interfaces. The two options are: 
 * One player is human and the other is computer. The computer is 
 * intelligent enough to recognize it's next move.
 * Two players also play the game.
 * @author	Chaitali Kamble
 * @author	Vaibhavi Awghate
 */
public class ComputerModel extends Connect4Controller implements
	PlayerInterfaceModel, Connect4IntefaceModel {
	// array to store the names of players
	String thePlayersName[] = new String[2];
	// stores the name assigned to player
	String playerName;
	char playerGamePiece;
	// array to store the game pieces of players
	char playerGamePieces[] = new char[2];
	// column in which the game piece is inserted
	public int column_number = 0;
	String winner = "";
	//parameterized constructor to initialize name and game piece of the player
	ComputerModel(String name, char gamePiece) {
		playerName = name;
		playerGamePiece = gamePiece;
	}
	// default constructor of Player2 class
	public ComputerModel() {
	}
	/*
	 * This method returns the game piece assigned to player
	 * 
	 * @return playerGamePiece game piece assigned to player
	 */
	public char getGamePiece() {
		return playerGamePiece;
	}
	/*
	 * This method returns the name assigned to player
	 * 
	 * @return playerName name assigned to player
	 */
	public String getName() {
		return playerName;
	}
	/*
	 * This method returns the name assigned to player
	 * 
	 * @return column_number column number entered by player
	 */
	public int nextMove() throws IOException {
		System.out.println("Enter column number for your move");
		BufferedReader br1 = new BufferedReader
				(new InputStreamReader(System.in));
		column_number = Integer.parseInt(br1.readLine());
		return column_number;
	}
	/*
	 * This method allows the player and computer to play simultaneously. It
	 * also checks for the draw and winning condition between player and
	 * computer and prints the appropriate player.
	 */
	public void playTheGame() {
		int column = 0;
		char piece = '\0';
		ComputerModel aPlayer = new ComputerModel("Vaibhavi", '+');
		aPlayer.thePlayersName[0] = aPlayer.getName();
		aPlayer.playerGamePieces[0] = aPlayer.getGamePiece();
		boolean gameIsOver = false;
		do {
			for (int index = 0; index < 2; index++) {
				// checks for the draw condition
				if (isItaDraw()) {
					System.out.println("Draw");
					gameIsOver = true;
				} else {
					try {
						if (index == 0) {
							// lets the player play the game
							column = aPlayer.nextMove();
							piece = aPlayer.playerGamePieces[0];
						} else {
							// lets the computer play the game
							column = computerPlay();
							piece = '*';
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					/*
					 * method to drop game piece to column entered by user
					 */
					dropPieces(column, piece);
					// checks for the winning condition
					if (didLastMoveWin()) {
						gameIsOver = true;
						if (index == 0) {
							winner = aPlayer.getName();
						} else
							winner = "computer";
						break;
					}
				}
			}
		} while (!gameIsOver);
	}
	// method for two players to play the game
	public void playTheGame2() {
		int column = 0;
		char piece = '\0';
		ComputerModel aPlayer = new ComputerModel("A", '+');
		aPlayer.thePlayersName[0] = aPlayer.getName();
		aPlayer.playerGamePieces[0] = aPlayer.getGamePiece();
		ComputerModel bPlayer = new ComputerModel("B", '*');
		bPlayer.thePlayersName[1] = bPlayer.getName();
		bPlayer.playerGamePieces[1] = bPlayer.getGamePiece();
		boolean gameIsOver = false;
		do {
			for (int index = 0; index < 2; index++) {
				if (isItaDraw()) {
					System.out.println("Draw");
					gameIsOver = true;
				} else {
					try {
						if (index == 0) {
							System.out.println("Player A is playing");
							column = aPlayer.nextMove();
							piece = aPlayer.playerGamePieces[0];
						} else {
							System.out.println("Player B is playing");
							column = bPlayer.nextMove();
							piece = bPlayer.playerGamePieces[1];
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					dropPieces(column, piece);
					if (didLastMoveWin()) {
						gameIsOver = true;
						if (index == 0)
							winner = aPlayer.getName();
						else
							winner = bPlayer.getName();
						break;
					}
				}
			}
		} while (!gameIsOver);
	}
	/*
	 * This method checks for the winning condition horizontally, vertically 
	 * and diagonally.
	 * 
	 * @return flag either true or false depending upon the player's move
	 */
	public boolean didLastMoveWin() {
		boolean flag = false;
		// checks for the winning condition horizontally
		if (horizontalWin()) {
			flag = true;
		}
		// checks for the winning condition vertically
		if (verticalWin()) {
			flag = true;
		}
		// checks for the winning condition diagonally
		if (diagonalWin()) {
			flag = true;
		}
		return flag;
	}
	/*
	 * This method checks for the winning condition horizontally.
	 * 
	 * @return flag either true or false depending upon the player's move
	 */
	boolean horizontalWin() {
		boolean flag = false;
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 25; column++) {
				if (Board[row][column] != '\u0000') {
					if (Board[row][column] != 'O') {
						// checks if the player is winning the game
						if (Board[row][column] == Board[row][column + 1]
								&& Board[row][column + 1] 
										== Board[row][column + 2]
								&& Board[row][column + 2] == 
									Board[row][column + 3] 
											&& Board[row][column + 3] == '+') {
							flag = true;
							break;
						} else
							// checks if the computer is winning the game
							if (Board[row][column] == Board[row][column + 1]
									&& Board[row][column + 1] 
											== Board[row][column + 2]
									&& Board[row][column + 2] 
											== Board[row][column + 3]
									&& Board[row][column + 3] == '*') {
							flag = true;
							break;
						}
					}
				}
			}
			if (flag == true)
				break;
		}
		return flag;
	}

	/*
	 * This method checks for the winning condition vertically.
	 * 
	 * @return flag either true or false depending upon the player's move
	 */
	boolean verticalWin() {
		boolean flag = false;
		for (int column = 0; column < 25; column++) {
			for (int row = 8; row >= 1; row--) {
				if (Board[row][column] != '\u0000') {
					if (Board[row][column] != 'O') {
						// checks if the player is winning the game
						if (Board[row][column] == Board[row - 1][column]
								&& Board[row - 1][column] == Board[row - 2][column]
								&& Board[row - 2][column] == Board[row - 3][column] 
										&& Board[row - 3][column] == '+'){
							flag = true;
							break;
						} else
							// checks if the computer is winning the game
							if (Board[row][column] == Board[row - 1][column]
									&& Board[row - 1][column] 
											== Board[row - 2][column]
									&& Board[row - 2][column] 
											== Board[row - 3][column]
									&& Board[row - 3][column] == '*') {
							flag = true;
							break;
						}
					}
				}
			}
			if (flag == true)
				break;
		}
		return flag;
	}
	/*
	 * This method checks for the winning condition diagonally.
	 * 
	 * @return flag either true or false depending upon the player's move
	 */
	boolean diagonalWin() {
		int match_counter = 0;
		boolean flag = false;
		for (int row = 8; row >= 3; row--) {
			for (int column = 0; column < 25; column++) {
				if (Board[row][column] != '\u0000') {
					if (Board[row][column] != 'O') {
						if (Board[row][column] == Board[row - 1][column + 1]
								&& Board[row - 1][column + 1] == 
									Board[row - 2][column + 2]
								&& Board[row - 2][column + 2] == 
									Board[row - 3][column + 3]){
							flag = true;
							break;
						} else
							flag = false;
					}
				}
			}
			if (flag == true)
				break;
		}
		return flag;

	}
	/*
	 * This method checks for the draw condition.
	 * 
	 * @return flag either true or false depending upon the player's move
	 */
	public boolean isItaDraw() {
		boolean flag = false;
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 25; column++) {
				if (Board[row][column] == 'O') {
					flag = false;
					break;
				} else
					flag = true;
			}
			if (flag == false)
				break;
		}
		if (flag == true)
			System.out.println("It's a draw");
		return flag;
	}
	/*
	 * This method drops the game piece in the column number entered by user. It
	 * checks for the most bottom empty place in the column and drops there and
	 * prints the updated board.
	 */
	public void dropPieces(int column_number, char gamePiece) {
		int row = 8;
		while (row >= 0) {
			if (Board[row][column_number] == '\u0000' 
					|| Board[row][column_number] != 'O') {
				row--;
			} else if (Board[row][column_number] == 'O') {
				Board[row][column_number] = gamePiece;
				break;
			}
		}
		for (int iteration_i = 0; iteration_i < 9; iteration_i++) {
			for (int iteration_j = 0; iteration_j < 25; iteration_j++) {
				if (Board[iteration_i][iteration_j] == '\u0000')
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
	 * 
	 * @return flag either true or false based on the validations
	 */
	public boolean checkIfPiecedCanBeDroppedIn(int column_number) {
		boolean flag = false;
		int row = 8;
		while (row >= 0) {
			if (Board[row][column_number] == '\u0000' || 
					Board[row][column_number] != 'O') {
				row--;
			} else if (Board[row][column_number] == 'O') {
				flag = true;
				break;
			}
		}
		return flag;
	}
	/*
	 * This method decides horizontal, vertical and diagonal moves of the
	 * computer.
	 * 
	 * @return column column number where the game piece is to be dropped by
	 * computer
	 */
	public int computerPlay() {
		int column = 0;
		column = horizontalMove();
		if (column == 0)
			column = VerticalMove();
		if (column == 0)
			column = DiagonalMove();
		if (column == 0)
			column = blockMove();
		if (column == 0) {
			Random rand = new Random();
			column = rand.nextInt(25);
		}
		return column;
	}
	/*
	 * This method decides the horizontal moves of the computer. If the board
	 * has three *'s in one row, then it will place the fourth one to win the
	 * game.
	 * 
	 * @return column_number column number where the game piece is to be dropped
	 * by the computer
	 */
	public int horizontalMove() {
		boolean flag = false;
		int column_number = 0;
		for (int row = 8; row >= 0; row--) {
			for (int column = 0; column < 25; column++) {
				if (Board[row][column] != '\u0000') {
					if (Board[row][column] != '+') {
						if ((Board[row][column] == Board[row][column + 1])
								&& (Board[row][column + 1] 
										== Board[row][column + 2])
								&& Board[row][column + 2] == '*') {
							if (Board[row][column + 3] == 'O') {
								column_number = column + 3;
								flag = true;
								break;
							}
						} else
							column_number = twoInARow();
					} else {
						column_number = column + 1;
						break;
					}
				}
			}
			if (flag == true)
				break;
		}
		return column_number;
	}
	/*
	 * This method decides the vertical moves of the computer. If the board has
	 * three *'s in one column, then it will place the fourth one to win the
	 * game.
	 * 
	 * @return column_number column number where the game piece is to be dropped
	 * by the computer
	 */
	public int VerticalMove() {
		boolean flag = false;
		int column_number = 0;
		for (int row = 8; row >= 3; row--) {
			for (int column = 0; column < 25; column++) {
				if (Board[row][column] != '\u0000') {
					if (Board[row][column] != '+') {
						if ((Board[row][column] == Board[row - 1][column])
								&& (Board[row - 1][column] 
										== Board[row - 2][column])
								&& Board[row - 2][column] == '*') {
							if (Board[row - 3][column] == 'O') {
								column_number = column;
								flag = true;
								break;
							}
						} else
							column_number = twoInARow();
					}
				}
			}
			if (flag == true)
				break;
		}
		return column_number;
	}
	/*
	 * This method decides the diagonal moves of the computer. If the board has
	 * three *'s in one diagonal, then it will place the fourth one to win the
	 * game.
	 * 
	 * @return column_number column number where the game piece is to be dropped
	 * by the computer
	 */
	public int DiagonalMove() {
		boolean flag = false;
		int column_number = 0;
		for (int row = 8; row >= 3; row--) {
			for (int column = 0; column < 25; column++) {
				if (Board[row][column] != '\u0000')
					if (Board[row][column] != '+') {
						if (Board[row][column] == Board[row - 1][column + 1]
								&& Board[row - 1][column + 1] 
										== Board[row - 2][column + 2]
								&& Board[row - 2][column + 2] == '*') {
							if (Board[row - 3][column + 3] == 'O') {
								column_number = column + 3;
								flag = true;
								break;
							}
						} else
							column_number = twoInARow();
					}
			}
			if (flag == true)
				break;
		}
		return column_number;
	}

	/*
	 * This method decides the random column number for the computer to play the
	 * game.
	 * 
	 * @return column_number column number where the game piece is to be dropped
	 * by the computer
	 */
	public int randomMove() {
		System.out.println("Board:" + Board[0][0]);
		boolean flag = false;
		int column_number = 0;
		for (int row = 8; row >= 0; row--) {
			for (int column = 0; column < 25; column++) {
				if (Board[row][column] != '\0' && Board[row][column] != '+' 
						&& Board[row][column] != '*') {
					column_number = column;
					flag = true;
					break;
				}
			}
			if (flag == true)
				break;
		}
		return column_number;
	}
	/*
	 * This method blocks the moves of the human player in order to prevent it
	 * from winning. It checks for all the horizontal, vertical and diagonal
	 * conditions.
	 * 
	 * @return column_number column number where the game piece is to be dropped
	 * by the computer
	 */
	public int blockMove() {
		boolean flag = false;
		int column_number = 0;
		for (int row = 8; row >= 3; row--) {
			for (int column = 0; column < 25; column++) {
				if (Board[row][column] != '\u0000') {
					if (Board[row][column] != '*') {
						if ((Board[row][column] == Board[row][column + 1])
								&& (Board[row][column + 1] 
										== Board[row][column + 2])
								&& Board[row][column + 2] == '+') {
							if (Board[row][column + 3] == 'O') {
								column_number = column + 3;
								flag = true;
								break;
							}
							if (flag == true)
								break;
						}
						if ((Board[row][column] == Board[row - 1][column])
								&& (Board[row - 1][column] 
										== Board[row - 2][column])
								&& Board[row - 2][column] == '+') {
							if (Board[row - 3][column] == 'O') {
								column_number = column;
								flag = true;
								break;
							}
							if (flag == true)
								break;
						}
						if (Board[row][column] == Board[row - 1][column + 1]
								&& Board[row - 1][column + 1] 
										== Board[row - 2][column + 2]
								&& Board[row - 2][column + 2] == '+') {
							if (Board[row - 3][column + 3] == 'O') {
								column_number = column + 3;
								flag = true;
								break;
							}
							if (flag == true)
								break;
						}
					}
				}
			}
			if (flag == true)
				break;
		}
		return column_number;
	}
	/*
	 * This method checks if two *'s appear consecutively in horizontal,
	 * vertical and diagonal manner and puts the game piece at the third
	 * position.
	 * 
	 * @return column_number column number where the game piece is to be dropped
	 * by the computer
	 */
	public int twoInARow() {
		boolean flag = false;
		int column_number = 0;
		for (int row = 8; row >= 1; row--) {
			for (int column = 0; column < 25; column++) {
				if (Board[row][column] != '\u0000') {
					if (Board[row][column] != '+') {
						if ((Board[row][column] == Board[row][column + 1]) 
								&& (Board[row][column + 1] == '*')) {
							if (Board[row][column + 2] == 'O') {
								column_number = column + 2;
								flag = true;
								break;
							}
							if (flag == true)
								break;
						}
						if ((Board[row][column] == Board[row - 1][column]) 
								&& (Board[row][column] == '*')) {
							if (Board[row - 2][column] == 'O') {
								column_number = column;
								flag = true;
								break;
							}
							if (flag == true)
								break;
						}
						if (Board[row][column] == Board[row - 1][column + 1] 
								&& Board[row - 1][column + 1] == '*') {
							if (Board[row - 2][column + 2] == 'O') {
								column_number = column + 2;
								flag = true;
								break;
							}
							if (flag == true)
								break;
						}
					}

				}
			}
			if (flag == true)
				break;
		}
		return column_number;
	}
	public String toString() {
		return this.getClass().getName();
	}
	public void init(PlayerInterfaceModel playerA, PlayerInterfaceModel playerB){
		System.out.println("Connect Four Game");
	}
}