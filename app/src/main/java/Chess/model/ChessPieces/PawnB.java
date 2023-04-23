package Chess.model.ChessPieces;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;
import java.util.ArrayList;

public class PawnB extends ChessPiece
{
    private ChessPieceColor color = ChessPieceColor.B;

    private ArrayList<int[]> squares = new ArrayList<int[]>()  { 
        { 
            add(new int[]{1, -1}); // diagonal 
            add(new int[]{1, 1}); // diagonal 
            add(new int[]{1, 0}); // forward 
            //add(new int[]{-2, 0}); // forward two if hasNotMoved 
        } 
    };

    public PawnB(int row, int col, ChessBoard board)
    {
        super(row, col, board);
    }
    
    public ChessPieceColor getColor()
    {
        return this.color;
    }

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
        if (super.hasNotMoved)
        {
            result.add(new int[]{super.row + 2, super.col});
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
                else
                {
                    break;
                }
            }
        }
        return movableSquares;
    }


    private boolean isDiagonal(int newRow, int newCol)
    {
        boolean result = false;
        if (newRow == super.row + 1)
        {
            if (newCol == super.col - 1 | newCol == super.col + 1)
            {
                result = true;
            }
        }
        return result;
    }
}
