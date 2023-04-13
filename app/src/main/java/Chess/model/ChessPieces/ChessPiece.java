package Chess.model.ChessPieces;

public interface ChessPiece {
    public int getCurrentRow();
    public int getCurrentCol();
    public boolean move(int newRow, int newCol);
}
