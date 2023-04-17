package Chess.model.ChessPieces;

import Chess.model.ChessPieces.ChessPiece;

public class Pawn implements ChessPiece
{
    private int row;
    private int col;
    private boolean hasNotMoved = false;
    private ChessPieceColor color;

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

    public boolean move(int newRow, int newCol)
    {
        this.row = newRow;
        this.col = newCol;
    }

    public ChessPieceColor getColor() {
        return this.color;
    }
}
