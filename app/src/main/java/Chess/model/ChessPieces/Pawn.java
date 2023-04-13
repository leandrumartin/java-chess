package Chess.model.ChessPieces;

import Chess.model.ChessPieces.ChessPiece;

public class Pawn implements ChessPiece
{
    private int row;
    private int col;

    public Pawn(int row, int col)
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
    public boolean legalMove(int newRow, int newCol)
    {
        boolean result = false;
        if (newRow - this.row == 1 & newCol == this.col)
        {
            result = true;
            this.row = newRow;
            this.col = newCol;
        }
        return result;
    }
}
