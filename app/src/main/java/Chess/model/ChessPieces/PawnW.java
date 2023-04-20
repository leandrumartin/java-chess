package Chess.model.ChessPieces;

import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;
import java.util.ArrayList;

public class PawnW extends ChessPiece
{
    private ChessPieceColor color = ChessPieceColor.W;

    public PawnW(int row, int col)
    {
        super(row, col);
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

    public ArrayList<int[]> movableSquare(ArrayList<ChessPiece> blockingPieces)
    {
        ArrayList<int[]> movableSquares = new ArrayList<int[]>();

        for (ChessPiece blockingPiece : blockingPieces)
        {
            ChessPieceColor blockingPieceColor = blockingPiece.getColor();
            int pieceRow = piece.getCurrentRow();
            int pieceCol = piece.getCurrentCol();
            if (blockingPieceColor != this.color)
            {
                if (isDiagonal(pieceRow, pieceCol))
                {
                    movableSquares.add(new int[]{piece.getCurrentRow, piece.getCurrentCol});
                }
            }
        }
    }

    // assuming that it is a legal move:
    private boolean isDiagonal(int newRow, int newCol)
    {
        boolean result = false;
        if (newRow == super.row - 1 & newCol != super.col)
        {
            result = true;
        }
        return result;
    }
}

