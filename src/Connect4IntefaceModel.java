/* 
 * Connect4IntefaceModel.java 
 * 
 * Version: 
 *     $1.0$ 
 * 
 * Revisions: 
 *     $initial$
 */      
import java.io.*;
public interface Connect4IntefaceModel {
	//methods to be used by the class which implements this interface
	public boolean checkIfPiecedCanBeDroppedIn(int column);
	public void dropPieces(int column, char gamePiece);
	boolean didLastMoveWin();
	public boolean isItaDraw();
	public void init( PlayerInterfaceModel playerA, 
			PlayerInterfaceModel playerB);
	public String toString();
	public void playTheGame() throws IOException;
}
