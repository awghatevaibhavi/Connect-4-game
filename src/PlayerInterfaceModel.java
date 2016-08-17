/* 
 * PlayerInterfaceModel.java 
 * 
 * Version: 
 *     $1.0$ 
 * 
 * Revisions: 
 *     $initial$ 
 */
import java.io.IOException;

public interface PlayerInterfaceModel {
	//methods to be used by the class which implements this interface
 	public char getGamePiece();
	public String getName();
	public int  nextMove() throws IOException;
}
