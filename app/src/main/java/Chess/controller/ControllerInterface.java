package Chess.controller;

public interface ControllerInterface 
{
    public void selectPiece(int fromRow, int fromCol);
    public void makeMove(int toRow, int toCol);
public interface ControllerInterface {

    public void start();

    /**
     * Handles selection of the chesspiece to move
     * @param row row of selected piece
     * @param col column of selected piece
     */
    public void selectPiece(int row, int col);

    /**
     * 
     * @param row
     * @param col
     */
    public void selectDestination(int row, int col);

    public void endGame();
    
}
