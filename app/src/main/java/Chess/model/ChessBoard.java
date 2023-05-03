package Chess.model;

import java.util.ArrayList;
import java.io.Serializable;
import Chess.model.ChessPieces.*;
import Chess.view.UnicodeMap;
import Chess.GameInterface;
import Chess.GameObserver;

public class ChessBoard implements GameInterface, Serializable
{
    private ChessPiece[][] board;
    private transient ArrayList<GameObserver> observers;
    private ChessPieceColor currentPlayer;
    private int clickCount;
    private ArrayList<ChessPiece> captured;
    private ArrayList<ChessPiece> wPawns;
    private ArrayList<ChessPiece> bPawns;
    private King wKing;
    private King bKing;

    public ChessBoard()
    {
        this.board = new ChessPiece[8][8];
        this.captured = new ArrayList<ChessPiece>();
        this.wPawns = new ArrayList<ChessPiece>();
        this.bPawns = new ArrayList<ChessPiece>();
        
        for (int i = 0; i < 8; i++) {
            this.board[6][i] = new Pawn(6, i, this, ChessPieceColor.W);
            wPawns.add(this.board[6][i]);

            this.board[1][i] = new Pawn(1, i, this, ChessPieceColor.B);
            bPawns.add(this.board[1][i]);

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
            else 
            {
                this.wKing = new King(7, i, this, ChessPieceColor.W);
                this.board[7][i] = this.wKing;
                this.bKing = new King(0, i, this, ChessPieceColor.B);
                this.board[0][i] = this.bKing;
            }
        }

        this.currentPlayer = ChessPieceColor.W;
        this.clickCount = 0;
    }

    public ChessPiece getChessPiece(int row, int col)
    {
        return this.board[row][col];
    }

    public String removePiece(int row, int col, ChessPiece removedPiece)
    {
        this.board[row][col] = null;
        this.captured.add(removedPiece);
        return removedPiece.getLabel();
    }

    public ArrayList<int[]> getMovableSquares(ChessPiece piece)
    {
        ArrayList<int[]> result = new ArrayList<int[]>();

        // Make sure that the move is not putting King in check.
        King currentKing;
        ChessPieceColor opponentColor;
        if (piece.getColor() == ChessPieceColor.B)
        {
            opponentColor = ChessPieceColor.W;
            currentKing = bKing;
        }
        else {
            opponentColor = ChessPieceColor.B;
            currentKing = wKing;
        }
        ArrayList<int[]> squares = piece.getMovableSquares();

        // Store row and column information.
        int originalRow = piece.getCurrentRow();
        int originalCol = piece.getCurrentCol();

        // Take out the piece.
        this.board[originalRow][originalCol] = null;

        if (piece != wKing & piece != bKing)
        {
            // Move to all potential squares and see if it causes a check.
            for (int[] square : squares)
            {
                ChessPiece temp = this.board[square[0]][square[1]];
                this.board[square[0]][square[1]] = piece;
                ArrayList<int[]> opponentSquares = this.getAllMovableSquares(opponentColor);
                boolean checkFound = false;
                for (int[] opponentSquare : opponentSquares)
                {
                    if (opponentSquare[0] == currentKing.getCurrentRow() & opponentSquare[1] == currentKing.getCurrentCol())
                    {
                        checkFound = true;
                        break;
                    }
                }
                if (!checkFound)
                {
                    result.add(square);
                }
                this.board[square[0]][square[1]] = temp;
            }
        }
        // If it is a king
        else 
        {
            // Move to all potential squares and see if it causes a check.
            for (int[] kingSquare : squares)
            {
                ChessPiece temp = this.board[kingSquare[0]][kingSquare[1]];
                this.board[kingSquare[0]][kingSquare[1]] = piece;
                ArrayList<int[]> opponentSquares = this.getAllMovableSquares(opponentColor);
                boolean checkMateFound = false;
                for (int[] opponentSquare : opponentSquares)
                {
                    if (opponentSquare[0] == kingSquare[0] & opponentSquare[1] == kingSquare[1])
                    {
                        checkMateFound = true;
                        break;
                    }
                }
                if (!checkMateFound)
                {
                    result.add(kingSquare);
                }
                this.board[kingSquare[0]][kingSquare[1]] = temp;
            }
        }
        // Put the piece back.
        this.board[originalRow][originalCol] = piece;
        
        return result;
    }

    // Reset Pawns' ability to En Passant.
    public void resetEnPassant()
    {
        if (this.currentPlayer == ChessPieceColor.W)
        {
            for (ChessPiece wPawn : this.wPawns)
            {
                wPawn.resetEnPassant();
            }
        }
        else 
        {
            for (ChessPiece bPawn : this.bPawns)
            {
                bPawn.resetEnPassant();
            }
        }

    }

    // Function to make the move.
    // Returns the label of captured pieces.
    public String placeChessPiece(int toRow, int toCol, ChessPiece piece)
    {
        this.resetEnPassant();
        String result = new String();
        // pickup piece
        int fromRow = piece.getCurrentRow();
        int fromCol = piece.getCurrentCol();
        this.board[fromRow][fromCol] = null;

        // drop piece
        // if there is a piece at destination, remove piece
        ChessPiece removedPiece = this.board[toRow][toCol];
        if (removedPiece != null)
        {
            result = this.removePiece(toRow, toCol, removedPiece);
        }
        piece.move(toRow, toCol);
        this.board[toRow][toCol] = piece;
       
        this.notifyObservers();
        return result;
    }

    public boolean isGameOver()
    {
        boolean result = false;
        ArrayList<int[]> finalResult = new ArrayList<int[]>();

        ArrayList<ChessPiece> pieces = this.getPieces(this.currentPlayer);
        for (ChessPiece piece : pieces)
        {
            finalResult.addAll(this.getMovableSquares(piece));
        }
        if (finalResult.size() == 0)
        {
            result = true;
        }
        return result;
    }

    public boolean isPawnAtEnd(ChessPiece potentialPawn)
    {
        boolean result = false;
        if (potentialPawn.getLabel() == UnicodeMap.bPawn)
        {
            if (potentialPawn.getCurrentRow() == 7)
            {
                result = true;
            }
        }
        else if (potentialPawn.getLabel() == UnicodeMap.wPawn)
        {
            if (potentialPawn.getCurrentRow() == 0)
            {
                result = true;
            }
        }
        return result;
    }

    // Specifically for when pawn reaches the end of the board
    public void addNewPiece(int toRow, int toCol, String unicode, ChessPieceColor color)
    {
        ChessPiece newPiece;
        if (unicode == UnicodeMap.wQueen || unicode == UnicodeMap.bQueen)
        {
            newPiece = new Queen(toRow, toCol, this, color);
        }
        else if (unicode == UnicodeMap.wBishop || unicode == UnicodeMap.bBishop)
        {
            newPiece = new Bishop(toRow, toCol, this, color);
        }
        else if (unicode == UnicodeMap.wRook || unicode == UnicodeMap.bRook)
        {
            newPiece = new Rook(toRow, toCol, this, color);
        }
        else
        {
            newPiece = new Knight(toRow, toCol, this, color);
        }
        this.board[toRow][toCol] = newPiece;
        this.notifyObservers();
    }

    /**
     * Finds all pieces of all colors.
     * @return
     */
    public ArrayList<int[]> getPiecesLocation()
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

    // Get all the location of pieces on the board with a specific color.
    public ArrayList<int[]> getPiecesLocation(ChessPieceColor color)
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

    // Get all the pieces on the board with a specific color.
    public ArrayList<ChessPiece> getPieces(ChessPieceColor color)
    {
        ArrayList<ChessPiece> result = new ArrayList<ChessPiece>();
        for (int row = 0; row < 8; row++) //go through rows
        {
            for (int col = 0; col < 8; col++) //go through columns
            {
                ChessPiece piece = this.board[row][col];
                if (piece != null) 
                {
                    if (color == piece.getColor())
                    {
                        result.add(piece);
                    }
                }   
            }
        }
        return result;
    }

    // Get all the movable squares of pieces on the board with a specific color.
    public ArrayList<int[]> getAllMovableSquares(ChessPieceColor color)
    {
        ArrayList<ChessPiece> pieces = this.getPieces(color);
        ArrayList<int[]> result = new ArrayList<int[]>();
        for (ChessPiece piece : pieces) //go through rows
        {
            if (piece != wKing & piece != bKing)
            {
                ArrayList<int[]> movableSquares = piece.getMovableSquares();
                result.addAll(movableSquares);
            }
        }
        // ? do we need to do this? unsure
        //result = removeDuplicates(result);
        return result;
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

    // Function to remove duplicates from an ArrayList.
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {
        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();
    
        // Traverse through the first list
        for (T element : list) {
    
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
    
                newList.add(element);
            }
        }
    
        // return the new list
        return newList;
    }

    // Functions for GameInterface.
    public void register(GameObserver observer)
    {
        if (observers == null)
        {
            // Must be instantiated here or loading saved game fails
            observers = new ArrayList<GameObserver>(); 
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
