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
            else if (i == 2 || i == 5)
            {
                ChessPiece BishopW = new BishopW(7, i, this);
                this.board[7][i] = BishopW;
                ChessPiece BishopB = new BishopB(0, i, this);
                this.board[0][i] = BishopB;
            }
            else if (i == 0 || i == 7)
            {
                ChessPiece RookW = new RookW(7, i, this);
                this.board[7][i] = RookW;
                ChessPiece RookB = new RookB(0, i, this);
                this.board[0][i] = RookB;
            }
            else if (i == 3)
            {
                ChessPiece QueenW = new QueenW(7, i, this);
                this.board[7][i] = QueenW;
                ChessPiece QueenB = new QueenB(0, i, this);
                this.board[0][i] = QueenB;
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
