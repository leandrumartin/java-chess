package Chess.model.ChessPieces;

import Chess.model.ChessPieces.ChessPieceColor;

public class ChessPiece 
{
    private int row;
    private int col;
    private boolean hasNotMoved = false;

    public ChessPiece(int row, int col)
    {
        this.row = row;
        this.row = col;
    }

    public int getCurrentRow()
    {
        return this.row;
    }
    public int getCurrentCol()
    {
        return this.col;
    }

    public boolean move(int newRow, int newCol)
    {
        this.row = newRow;
        this.col = newCol;
    }

    // Dummy Implementation
    public ChessPieceColor getColor()
    {
        return ChessPieceColor.B;
    }

    // Dummy Implementation
    public boolean legalMove(int newRow, int newCol)
    {
        return false;
    }
}
