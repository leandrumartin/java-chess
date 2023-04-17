package Chess.model;

/*  Create a ChessBoard class that represents the chessboard. 
    The ChessBoard class should contain a two-dimensional array of ChessPiece objects representing 
    the current state of the board. 
*/
import java.util.ArrayList;

import Chess.model.ChessPieces.*;
import Chess.GameInterface;
import Chess.GameObserver;

public class ChessBoard implements GameInterface
{
    private ChessPiece[][] pieces;
    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();

    public ChessBoard()
    {
        this.board = new ChessPiece[8][8];

        for (int i = 0; i < 8; i++) {
            ChessPiece PawnW = new PawnW(6, i);
            this.board[6][i].add(PawnW);

            ChessPiece PawnB = new PawnB(1, i);
            this.board[1][i].add(PawnB);
        }

    }

    public ChessPiece getChessPiece(int row, int col)
    {
        return this.pieces[row][col];
    }

    public boolean placeChessPiece(int newRow, int newCol, ChessPiece piece)
    {
        boolean result = false;
        boolean legal = piece.legalMove(newRow, newCol);
        boolean empty = (this.pieces[newRow][newCol] == null);
        if (legal & empty)
        {
            piece.move(newRow, newCol);
            result = true;
            this.notifyObservers();
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
 
    public void notifyObservers()
    {
       for(GameObserver observer: observers)
       {
          observer.update();
       }
 
    }
 
}
