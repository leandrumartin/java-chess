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
}
