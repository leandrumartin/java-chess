package Chess.model.ChessPieces;

import Chess.model.ChessPieces.ChessPieceColor;
import Chess.model.ChessBoard;
import java.util.ArrayList;

public abstract class ChessPiece 
{
    protected int row;
    protected int col;
    protected boolean hasNotMoved;
    protected ChessBoard board;

    public ChessPiece(int row, int col, ChessBoard board)
    {
        this.row = row;
        this.col = col;
        this.hasNotMoved = true;
        this.board = board;
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

    public abstract ChessPieceColor getColor();
    public abstract ArrayList<int[]> legalSquares();
    public abstract ArrayList<int[]> movableSquares();
}
