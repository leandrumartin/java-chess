package Chess.model.ChessPieces;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.view.UnicodeMap;

import java.util.ArrayList;

public class King extends ChessPiece
{
    private ArrayList<int[]> squares;

    public King(int row, int col, ChessBoard board, ChessPieceColor color)
    {
        super(row, col, board, color);

        squares = new ArrayList<int[]>();
        squares.add(new int[]{1, -1});  squares.add(new int[]{1, 0});  squares.add(new int[]{1, 1}); 
        squares.add(new int[]{0, -1});                                 squares.add(new int[]{0, 1});
        squares.add(new int[]{-1, -1}); squares.add(new int[]{-1, 0}); squares.add(new int[]{-1, 1}); 
    }

    // check boundary conditions
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
        finalResult.add(result);
        return finalResult;
    }

    // 1. check if any other pieces are blocking or capturable
    // 2. check if you are a move is putting a king in check
    @Override
    public ArrayList<int[]> getMovableSquares()
    {
        ArrayList<ArrayList<int[]>> legalSquares = this.getLegalSquares();
        ArrayList<int[]> result = new ArrayList<int[]>();

        // 1. check if any other pieces are blocking or capturable
        for (int[] location : legalSquares.get(0))
        {
            int row = location[0];
            int col = location[1];
            ChessPiece piece = this.board.getChessPiece(row, col);
            if (piece != null)
            {
                if (piece.getColor() != super.color)
                {
                    result.add(location);
                }
            }
            else
            {
                result.add(location);
            }
        }

        // 2. check if you are a move is putting a king in check
        ChessPieceColor opponentColor;
        if (super.color == ChessPieceColor.B)
        {
            opponentColor = ChessPieceColor.W;
        }
        else {
            opponentColor = ChessPieceColor.B;
        }

        ArrayList<int[]> opponentSquares = super.board.getAllMovableSquares(opponentColor);
        for (int[] movableSquare : result)
        {
            if (opponentSquares.contains(movableSquare))
            {
                result.remove(movableSquare);
            }
        }
        return result;
    }

    public boolean isCheckMate()
    {
        boolean result = false;
        ArrayList<int[]> movableSquares = this.getMovableSquares();
        if (movableSquares.size() == 0)
        {
            result = true;
        }
        return result;
    }

    public ArrayList<int[]> canCastle()
    {
        ArrayList<int[]> castleSquares = new ArrayList<int[]>();
        if (super.hasNotMoved)
        {
            for (int end = 3; end <= 4; end++)
            {
                int rowShftDir = (int) Math.pow((double)-1, (double)end+1);
                for (int rowShftAmt = 1; rowShftAmt <= end; rowShftAmt++)
                {
                    if (rowShftAmt  < end)
                    {
                        if (this.board.getChessPiece(super.row - rowShftDir*rowShftAmt, super.col) != null)
                        {
                            break;
                        }
                    }
                    else
                    {
                        ChessPiece potentialRook = this.board.getChessPiece(super.row - rowShftDir*rowShftAmt, super.col);
                        if(potentialRook == null)
                        {
                            break;
                        }
                        else {
                            if (!potentialRook.hasNotMoved)
                            {
                                break;
                            }
                        }
                    }
                    castleSquares.add(new int[]{super.row + rowShftDir, super.col});
                }
            }
        }
        return castleSquares;
    }

    public String getLabel() {
        String result;
        if (super.color == ChessPieceColor.B)
        {
            result = UnicodeMap.bKing;
        }
        else
        {
            result = UnicodeMap.wKing;
        }
        return result;
    }

}

