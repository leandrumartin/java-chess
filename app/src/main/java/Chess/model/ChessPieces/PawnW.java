package Chess.model.ChessPieces;

import Chess.model.ChessPieces.Pawn;
import Chess.model.ChessPieces.ChessPieceColor;

public class PawnW extends Pawn implements ChessPieceColorInterface
{
    private ChessPieceColor color = ChessPieceColor.W;

    public PawnW(int row, int col)
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
        if (newRow - super.row == -1 & newCol == super.col)
        {
            result = true;
            
        }
        return result;
    }
}
