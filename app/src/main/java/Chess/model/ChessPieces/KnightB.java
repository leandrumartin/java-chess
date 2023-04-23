package Chess.model.ChessPieces;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;
import java.util.ArrayList;

public class KnightB extends ChessPiece
{
    private ChessPieceColor color = ChessPieceColor.B;

    private ArrayList<int[]> squares = new ArrayList<int[]>()  { 
        { 
                add(new int[]{-1, -2});
                add(new int[]{-2, -1});
                add(new int[]{-2, 1});
                add(new int[]{-1, 2});
                add(new int[]{1, -2});
                add(new int[]{2, -1});
                add(new int[]{2, 1});
                add(new int[]{1, 2});
        } 
    };

    public KnightB(int row, int col, ChessBoard board)
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
        for (int[] square : this.squares)
        {
            if (super.row + square[0] > -1 & super.row + square[0] < 8 & super.col + square[1] > -1 & super.col + square[1] < 8)
            {
                result.add(new int[]{super.row + square[0], super.col + square[1]});
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

            if (piece != null)
            {
                if (piece.getColor() != this.color)
                {
                    movableSquares.add(location);
                }
            }
            else
            {
                movableSquares.add(location);

            }
        }
        return movableSquares;
    }

}

