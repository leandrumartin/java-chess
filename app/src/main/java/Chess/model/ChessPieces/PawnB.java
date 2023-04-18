package Chess.model.ChessPieces;

import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;

import java.util.ArrayList;

public class PawnB extends ChessPiece
{
    private ChessPieceColor color = ChessPieceColor.B;

    public ChessPieceColor getColor()
    {
        return this.color;
    }

    public boolean legalMove(int newRow, int newCol)
    {
        boolean result = false;
        if (newRow - super.row == 1 & newCol == super.col)
        {
            result = true;
            
        }
        return result;
    }

    // black pawn goes down the array from row index 1 to 2, 3...
    @Override
    public ArrayList<int[]> legalSquares()
    {
        ArrayList<int[]> result = new ArrayList<int[]>();
        if (super.hasNotMoved)
        {
            result.add(new int[]{super.row + 2, super.col});
        }
        if (super.row + 1 < 8)
        {
            result.add(new int[]{super.row + 1, super.col});
            if (super.col - 1 > -1)
            {
                result.add(new int[]{super.row + 1, super.col - 1});
            }
            if (super.col + 1 < 8)
            {
                result.add(new int[]{super.row + 1, super.col + 1});
            }
        }
        return result;
    }
}
