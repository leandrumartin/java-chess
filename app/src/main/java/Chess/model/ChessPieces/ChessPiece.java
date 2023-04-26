package Chess.model.ChessPieces;

import Chess.model.ChessPieces.ChessPieceColor;
import Chess.model.ChessBoard;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class ChessPiece implements Serializable
{
    protected int row;
    protected int col;
    protected boolean hasNotMoved;
    protected ChessBoard board;
    protected ChessPieceColor color;

    public ChessPiece(int row, int col, ChessBoard board, ChessPieceColor color)
    {
        this.row = row;
        this.col = col;
        this.hasNotMoved = true;
        this.board = board;
        this.color = color;
    }

    public int getCurrentRow()
    {
        return this.row;
    }
    public int getCurrentCol()
    {
        return this.col;
    }

    public ChessPieceColor getColor()
    {
        return this.color;
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

    public ArrayList<int[]> movableSquares()
    {
        ArrayList<ArrayList<int[]>> legalSquares = this.legalSquares();

        ArrayList<int[]> movableSquares = new ArrayList<int[]>();
        for (ArrayList<int[]> list : legalSquares)
        {
            for (int[] location : list)
            {int row = location[0];
            int col = location[1];
            ChessPiece piece = this.board.getChessPiece(row, col);

            if (piece == null)
            {
                movableSquares.add(location);
            }
            else
            {
                if (piece.getColor() != this.color)
                {
                    movableSquares.add(location);
                }
                break;
            }}
        }
        return movableSquares;
    }

    public abstract ArrayList<ArrayList<int[]>> legalSquares();
    public abstract String getLabel();
}
