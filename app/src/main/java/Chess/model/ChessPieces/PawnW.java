package Chess.model.ChessPieces;

import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;

public class PawnW extends ChessPiece
{
    private ChessPieceColor color = ChessPieceColor.W;

    public ChessPieceColor getColor()
    {
        return this.color;
    }

    public boolean legalMove(int newRow, int newCol)
    {
        boolean result = false;
        if (newRow - super.row == -1 & newCol == super.col)
        {
            result = true;
            
        }
        return result;
    }
}
