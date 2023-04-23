package Chess.model;

/*
    Create a ChessBoard class that represents the chessboard. 
    The ChessBoard class should contain a two-dimensional array of ChessPiece objects representing 
    the current state of the board. 
*/
import java.util.ArrayList;
import java.io.Serializable;

import Chess.model.ChessPieces.*;
import Chess.GameInterface;
import Chess.GameObserver;

public class ChessBoard implements GameInterface, Serializable
{
    private ChessPiece[][] board;
    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();

    public ChessBoard()
    {
        this.board = new ChessPiece[8][8];

        for (int i = 0; i < 8; i++) {
            ChessPiece PawnW = new PawnW(6, i, this);
            this.board[6][i] = PawnW;

            ChessPiece PawnB = new PawnB(1, i, this);
            this.board[1][i] = PawnB;

            if (i == 1 || i == 6) 
            {
                ChessPiece KnightW = new KnightW(7, i, this);
                this.board[7][i] = KnightW;
                ChessPiece KnightB = new KnightB(0, i, this);
                this.board[0][i] = KnightB;
            }
        }

    }

    public ChessPiece getChessPiece(int row, int col)
    {
        return this.board[row][col];
    }

    public void removePiece(int row, int col)
    {
        this.board[row][col] = null;
        
    }

    public ArrayList<int[]> movableSquares(ChessPiece piece)
    {
        return piece.movableSquares();
    }

    public void placeChessPiece(int toRow, int toCol, ChessPiece piece)
    {
        // pickup piece
        int fromRow = piece.getCurrentRow();
        int fromCol = piece.getCurrentCol();
        this.removePiece(fromRow, fromCol);

        // drop piece
        // if there is a piece at destination, remove piece
        if (this.board[toRow][toCol] != null)
        {
            this.removePiece(toRow, toCol);
        }
        piece.move(toRow, toCol);
        this.board[toRow][toCol] = piece;

        // location information to pass on to observers
        ArrayList<int[]> result = new ArrayList<int[]>();
        result.add(new int[]{fromRow, fromCol});
        result.add(new int[]{toRow, toCol});

        this.notifyObservers(result);
    }

    public ArrayList<int[]> findPieces(ChessPieceColor color)
    {
        ArrayList<int[]> result = new ArrayList<int[]>();
        for (int row = 0; row < 8; row++) //go through rows
        {
            for (int col = 0; col < 8; col++) //go through columns
            {
                if (this.board[row][col] != null) 
                {
                    if (color == this.board[row][col].getColor())
                    {
                        result.add(new int[]{row, col});
                    }
                }   
            }
        }
        return result;
    }

    // GameInterface
    public void register(GameObserver observer)
    {
       observers.add(observer);
    }

    public void unregister(GameObserver observer)
    {
       observers.remove(observer);
    }
 
    public void notifyObservers(ArrayList<int[]> pieceLocations)
    {
       for(GameObserver observer: observers)
       {
          observer.update(pieceLocations);
       }
 
    }
}
