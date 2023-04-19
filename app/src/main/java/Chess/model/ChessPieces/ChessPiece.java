package Chess.model.ChessPieces;

import Chess.model.ChessPieces.ChessPieceColor;

public class ChessPiece 
{
    protected int row;
    protected int col;
    private boolean hasNotMoved;

    public ChessPiece(int row, int col)
    {
        this.row = row;
        this.row = col;
        this.hasNotMoved = true;
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
        if (this.hasNotMoved)
        {
            this.hasNotMoved = false;
        }
        return this.hasNotMoved;
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
