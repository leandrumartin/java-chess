package Chess.model.ChessPieces;

import Chess.model.ChessPieces.Pawn;
import Chess.model.ChessPieces.ChessPieceColor;

public class WPawn extends Pawn
{
    private ChessPieceColor color = ChessPieceColor.W;

    public WPawn(int row, int col)
    {
        super.row = row;
        super.row = col;
    }

    public ChessPieceColor getColor()
    {
        return this.color;
    }
}
