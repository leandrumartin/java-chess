package Chess.model.ChessPieces;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;
import java.util.ArrayList;

public class PawnW extends ChessPiece
{
    private ChessPieceColor color = ChessPieceColor.W;

    public PawnW(int row, int col, ChessBoard board)
    {
        super(row, col, board);
    }
    
    public ChessPieceColor getColor()
    {
        return this.color;
    }

    // white pawn goes up the array from row index 6 to 5, 4...
    public ArrayList<int[]> legalSquares()
    {
        ArrayList<int[]> result = new ArrayList<int[]>();
        if (super.hasNotMoved)
        {
            result.add(new int[]{super.row - 2, super.col});
        }
        if (super.row - 1 > -1)
        {
            result.add(new int[]{super.row - 1, super.col});
            if (super.col - 1 > -1)
            {
                result.add(new int[]{super.row - 1, super.col - 1});
            }
            if (super.col + 1 < 8)
            {
                result.add(new int[]{super.row - 1, super.col + 1});
            }
        }
        return result;
    }

    public ArrayList<int[]> movableSquares()
    {
        ArrayList<int[]> legalSquares = this.legalSquares();

        ArrayList<int[]> movableSquares = new ArrayList<int[]>();
        for (int[] location : legalSquares)
        {
            int row = location[0];
            int col = location[1];
            ChessPiece piece = super.board.getChessPiece(row, col);

            if (isDiagonal(row, col))
            {
                if (piece != null)
                {
                    if (piece.getColor() != this.color)
                    {
                        movableSquares.add(location);
                    }
                }
            }
            else
            {
                if (piece == null)
                {
                    movableSquares.add(location);
                }
            }
        }
        return movableSquares;
    }


    private boolean isDiagonal(int newRow, int newCol)
    {
        boolean result = false;
        if (newRow == super.row - 1)
        {
            if (newCol == super.col - 1 | newCol == super.col + 1)
            {
                result = true;
            }
        }
        return result;
    }
}

