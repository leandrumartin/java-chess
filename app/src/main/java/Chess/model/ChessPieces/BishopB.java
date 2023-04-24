package Chess.model.ChessPieces;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.view.UnicodeMap;

import java.util.ArrayList;

public class BishopB extends ChessPiece
{
    private ChessPieceColor color = ChessPieceColor.B;

    public BishopB(int row, int col, ChessBoard board)
    {
        super(row, col, board);
    }
    
    public ChessPieceColor getColor()
    {
        return this.color;
    }

    public ArrayList<int[]> legalSquares()
    {
        return new ArrayList<int[]>();
    }

    // white pawn goes up the array from row index 6 to 5, 4...
    public ArrayList<ArrayList<int[]>> legalSquares2()
    {
        ArrayList<ArrayList<int[]>> finalResult = new ArrayList<ArrayList<int[]>>();

        int i = 1;
        ArrayList<int[]> result1 = new ArrayList<int[]>();
        while (super.row + i < 8 & super.col + i < 8)
        {
            result1.add(new int[]{super.row + i, super.col + i});
            i++;
        }
        finalResult.add(result1);

        i = 1;
        ArrayList<int[]> result2 = new ArrayList<int[]>();
        while (super.row + i < 8 & super.col - i > -1)
        {
            result2.add(new int[]{super.row + i, super.col - i});
            i++;
        }
        finalResult.add(result2);

        i = 1;
        ArrayList<int[]> result3 = new ArrayList<int[]>();
        while (super.row - i > -1 & super.col + i < 8)
        {
            result3.add(new int[]{super.row - i, super.col + i});
            i++;
        }
        finalResult.add(result3);

        i = 1;
        ArrayList<int[]> result4 = new ArrayList<int[]>();
        while (super.row - i > -1 & super.col - i > -1)
        {
            result4.add(new int[]{super.row - i, super.col - i});
            i++;
        }
        finalResult.add(result4);
        return finalResult;
    }

    public ArrayList<int[]> movableSquares()
    {
        ArrayList<ArrayList<int[]>> legalSquares = this.legalSquares2();

        ArrayList<int[]> movableSquares = new ArrayList<int[]>();
        for (ArrayList<int[]> list : legalSquares)
        {
            for (int[] location : list)
            {int row = location[0];
            int col = location[1];
            ChessPiece piece = super.board.getChessPiece(row, col);

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

    public String getLabel() {
        return UnicodeMap.bBishop;
    }

}

