package Chess.model.ChessPieces;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.view.UnicodeMap;

import java.util.ArrayList;

public class Pawn extends ChessPiece
{

    private ArrayList<int[]> squares = new ArrayList<int[]>();
    private int hasNotMovedForward;
    private int rowForward;

    public Pawn(int row, int col, ChessBoard board, ChessPieceColor color)
    {
        super(row, col, board, color);
        if (super.color == ChessPieceColor.B)
        {
            rowForward = 1;
            hasNotMovedForward = 2;
        }
        else
        {
            rowForward = -1;
            hasNotMovedForward = -2;
        }            
        squares.add(new int[]{rowForward, -1});
        squares.add(new int[]{rowForward, 1});
        squares.add(new int[]{rowForward, 0});
    }

    public ArrayList<ArrayList<int[]>> getLegalSquares()
    {
        ArrayList<ArrayList<int[]>> finalResult = new ArrayList<ArrayList<int[]>>();
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
            result.add(new int[]{super.row + hasNotMovedForward, super.col});
        }
        finalResult.add(result);
        return finalResult;
    }

    @Override
    public ArrayList<int[]> getMovableSquares()
    {
        ArrayList<ArrayList<int[]>> legalSquares = this.getLegalSquares();
        
        ArrayList<int[]> movableSquares = new ArrayList<int[]>();
        
        for (int[] location : legalSquares.get(0))
        {
            int row = location[0];
            int col = location[1];
            ChessPiece piece = super.board.getChessPiece(row, col);

            if (isDiagonal(row, col))
            {
                if (piece != null)
                {
                    if (piece.getColor() != super.color)
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
        if (newRow == super.row + rowForward)
        {
            if (newCol == super.col - 1 | newCol == super.col + 1)
            {
                result = true;
            }
        }
        return result;
    }
    
    public String getLabel() {
        String result;
        if (super.color == ChessPieceColor.B)
        {
            result = UnicodeMap.bPawn;
        }
        else
        {
            result = UnicodeMap.wPawn;
        }
        return result;
    }
}
