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
    private transient ArrayList<GameObserver> observers; // Cannot be instantiated here or loading game fails
    private ChessPieceColor currentPlayer;
    private int clickCount;

    public ChessBoard()
    {
        this.board = new ChessPiece[8][8];

        for (int i = 0; i < 8; i++) {
            this.board[6][i] = new Pawn(6, i, this, ChessPieceColor.W);
            this.board[1][i] = new Pawn(1, i, this, ChessPieceColor.B);

            if (i == 1 || i == 6) 
            {
                this.board[7][i] = new Knight(7, i, this, ChessPieceColor.W);
                this.board[0][i] = new Knight(0, i, this, ChessPieceColor.B);
            }
            else if (i == 2 || i == 5)
            {
                this.board[7][i] = new Bishop(7, i, this, ChessPieceColor.W);
                this.board[0][i] = new Bishop(0, i, this, ChessPieceColor.B);
            }
            else if (i == 0 || i == 7)
            {
                this.board[7][i] = new Rook(7, i, this, ChessPieceColor.W);
                this.board[0][i] = new Rook(0, i, this, ChessPieceColor.B);
            }
            else if (i == 3)
            {
                this.board[7][i] = new Queen(7, i, this, ChessPieceColor.W);
                this.board[0][i] = new Queen(0, i, this, ChessPieceColor.B);
            }
        }

        this.currentPlayer = ChessPieceColor.W;
        this.clickCount = 0;
    }

    public ChessPiece getChessPiece(int row, int col)
    {
        return this.board[row][col];
    }

    public int getClickCount()
    {
        return this.clickCount;
    }
    
    public void setClickCount(int count)
    {
        this.clickCount = count;
    }
    
    public ChessPieceColor getCurrentPlayer()
    {
        return this.currentPlayer;
    }
    
    public void setCurrentPlayer(ChessPieceColor color)
    {
        this.currentPlayer = color;
    }

    public void removePiece(int row, int col)
    {
        this.board[row][col] = null;
        // return location information to view so that it can display captured pieces
        
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

        this.notifyObservers();
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

    /**
     * Finds all pieces of all colors.
     * @return
     */
    public ArrayList<int[]> findPieces()
    {
        ArrayList<int[]> result = new ArrayList<int[]>();
        for (int row = 0; row < 8; row++) //go through rows
        {
            for (int col = 0; col < 8; col++) //go through columns
            {
                if (this.board[row][col] != null) 
                {
                    result.add(new int[]{row, col});
                }
            }
        }
        return result;
    }

    // GameInterface
    public void register(GameObserver observer)
    {
        if (observers == null)
        {
            observers = new ArrayList<GameObserver>(); // Must be instantiated here or loading saved game fails
        }
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
