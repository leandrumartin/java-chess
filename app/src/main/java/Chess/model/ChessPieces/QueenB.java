package Chess.model.ChessPieces;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.view.UnicodeMap;

import java.util.ArrayList;

public class QueenB extends ChessPiece
{
    private ChessPieceColor color = ChessPieceColor.B;
    // private BishopB bishop;
    // private RookB rook;

    public QueenB(int row, int col, ChessBoard board)
    {
        super(row, col, board);
        // this.bishop = new BishopB(row, col, board);
        // this.rook = new RookB(row, col, board);
    }
    
    public ChessPieceColor getColor()
    {
        return this.color;
    }

    public ArrayList<int[]> legalSquares()
    {
        return new ArrayList<int[]>();
    }

    // public ArrayList<int[]> movableSquares()
    // {
    //     ArrayList<int[]> movableSquares = new ArrayList<int[]>();
    //     movableSquares.addAll(this.bishop.legalSquares2());
    //     movableSquares.addAll(this.rook.legalSquares2());
    //     return movableSquares;
    // }

    public ArrayList<ArrayList<int[]>> legalSquares2()
    {
        ArrayList<ArrayList<int[]>> finalResult = new ArrayList<ArrayList<int[]>>();

        int i = 1;
        ArrayList<int[]> result1 = new ArrayList<int[]>();
        while (super.row + i < 8)
        {
            result1.add(new int[]{super.row + i, super.col});
            i++;
        }
        finalResult.add(result1);

        i = 1;
        ArrayList<int[]> result2 = new ArrayList<int[]>();
        while (super.col - i > -1)
        {
            result2.add(new int[]{super.row, super.col - i});
            i++;
        }
        finalResult.add(result2);

        i = 1;
        ArrayList<int[]> result3 = new ArrayList<int[]>();
        while (super.row - i > -1)
        {
            result3.add(new int[]{super.row - i, super.col});
            i++;
        }
        finalResult.add(result3);

        i = 1;
        ArrayList<int[]> result4 = new ArrayList<int[]>();
        while (super.col + i < 8)
        {
            result4.add(new int[]{super.row, super.col + i});
            i++;
        }
        finalResult.add(result4);

        // bishop
        i = 1;
        ArrayList<int[]> result5 = new ArrayList<int[]>();
        while (super.row + i < 8 & super.col + i < 8)
        {
            result5.add(new int[]{super.row + i, super.col + i});
            i++;
        }
        finalResult.add(result5);

        i = 1;
        ArrayList<int[]> result6 = new ArrayList<int[]>();
        while (super.row + i < 8 & super.col - i > -1)
        {
            result6.add(new int[]{super.row + i, super.col - i});
            i++;
        }
        finalResult.add(result6);

        i = 1;
        ArrayList<int[]> result7 = new ArrayList<int[]>();
        while (super.row - i > -1 & super.col + i < 8)
        {
            result7.add(new int[]{super.row - i, super.col + i});
            i++;
        }
        finalResult.add(result7);

        i = 1;
        ArrayList<int[]> result8 = new ArrayList<int[]>();
        while (super.row - i > -1 & super.col - i > -1)
        {
            result8.add(new int[]{super.row - i, super.col - i});
            i++;
        }
        finalResult.add(result8);
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
        return UnicodeMap.bQueen;
    }
}

