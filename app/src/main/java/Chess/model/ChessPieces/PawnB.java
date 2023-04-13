package Chess.model.ChessPieces;

import Chess.model.ChessPieces.Pawn;
import Chess.model.ChessPieces.ChessPieceColor;

public class PawnB extends Pawn
{
    private ChessPieceColor color = ChessPieceColor.B;

    public PawnB(int row, int col)
    {
        super.row = row;
        super.row = col;
    }

    public ChessPieceColor getColor()
    {
        return this.color;
    }

    public boolean legalMove(int newRow, int newCol)
    {
        boolean result = false;
        if (newRow - super.row == 1 & newCol == super.col)
        {
            result = true;
            super.row = newRow;
            super.col = newCol;
        }
        return result;
    }
}
