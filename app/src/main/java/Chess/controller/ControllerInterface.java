package Chess.controller;

public interface ControllerInterface 
{
    public void userPressed(int row, int col);
    public void selectPiece(int fromRow, int fromCol);
    public void selectDestination(int toRow, int toCol);
    
}
