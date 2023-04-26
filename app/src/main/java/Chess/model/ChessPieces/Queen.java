package Chess.model.ChessPieces;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.view.UnicodeMap;

import java.util.ArrayList;

public class Queen extends ChessPiece
{
    private ChessPieceColor color = ChessPieceColor.B;
    // private BishopB bishop;
    // private RookB rook;

    public Queen(int row, int col, ChessBoard board, ChessPieceColor color)
    {
        super(row, col, board, color);
        // this.bishop = new BishopB(row, col, board);
        // this.rook = new RookB(row, col, board);
    }
    
    // public ArrayList<int[]> movableSquares()
    // {
    //     ArrayList<int[]> movableSquares = new ArrayList<int[]>();
    //     movableSquares.addAll(this.bishop.legalSquares2());
    //     movableSquares.addAll(this.rook.legalSquares2());
    //     return movableSquares;
    // }

    public ArrayList<ArrayList<int[]>> legalSquares()
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

    public String getLabel() {
        String result;
        if (super.color == ChessPieceColor.B)
        {
            result = UnicodeMap.bQueen;
        }
        else
        {
            result = UnicodeMap.wQueen;
        }
        return result;
    }
}

