package Chess.model;

/*  Create a ChessBoard class that represents the chessboard. 
    The ChessBoard class should contain a two-dimensional array of ChessPiece objects representing 
    the current state of the board. 
*/
import java.util.ArrayList;

import Chess.model.ChessPieces.ChessPiece;
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

            ChessPiece PawnB = new new PawnB(1, i);;
            this.board[1][i].add(PawnB);
        }
    }

    public ChessPiece getChessPiece(int row, int col)
    {
       return this.pieces[row][col];
    }

    // GameInterface
    public void register(GameObserver observer)
    {
       observers.add(observer);
    }
 
    public void notifyObservers()
    {
       for(GameObserver observer: observers)
       {
          observer.update();
       }
 
    }
 

}
