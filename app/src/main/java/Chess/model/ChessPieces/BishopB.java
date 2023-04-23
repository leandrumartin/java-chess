package Chess.model.ChessPieces;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;
import java.util.ArrayList;

public class BishopB extends ChessPiece
{
    private ChessPieceColor color = ChessPieceColor.B;

    // private ArrayList<int[]> squares = new ArrayList<int[]>()  { 
    //     { 
    //             add(new int[]{-1, -2});
    //             add(new int[]{-2, -1});
    //             add(new int[]{-2, 1});
    //             add(new int[]{-1, 2});
    //             add(new int[]{1, -2});
    //             add(new int[]{2, -1});
    //             add(new int[]{2, 1});
    //             add(new int[]{1, 2});
    //     } 
    // };

    public BishopB(int row, int col, ChessBoard board)
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
        int i = 1;
        while (super.row + i < 8 & super.col + i < 8)
        {
            result.add(new int[]{super.row + i, super.col + i});
        }
        while (super.row + i < 8 & super.col + i > -1)
        {
            result.add(new int[]{super.row + i, super.col - i});
        }
        while (super.row + i > -1 & super.col + i < 8)
        {
            result.add(new int[]{super.row - i, super.col + i});
        }
        while (super.row + i > -1 & super.col + i > -1)
        {
            result.add(new int[]{super.row - i, super.col - i});
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

