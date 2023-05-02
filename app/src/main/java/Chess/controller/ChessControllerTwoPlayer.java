package Chess.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.model.ChessPieces.ChessPiece;
import Chess.view.ChessView;
import Chess.view.ConfirmationDialog;
import Chess.view.FileSelector;
import Chess.view.UnicodeMap;

public class ChessControllerTwoPlayer implements ControllerInterface {
    private ChessBoard board;
    private ChessView view;
    private ChessPiece currentChessPiece;

    public ChessControllerTwoPlayer(ChessBoard board, int time) {
        if (ConfirmationDialog.confirmLoadGame()) {
            this.loadGame();
        } else {
            this.board = board;
        }
        
        this.view = new ChessView(this, this.board, time); // Make sure to pass `this.board`, not `board` from the constructor arguments
        ArrayList<int[]> allCurrentPieces = this.board.getPiecesLocation(this.board.getCurrentPlayer());
        this.view.enableSquares(allCurrentPieces);
        this.view.setVisible(true);
    }
    
    public ChessPieceColor getCurrentPlayer()
    {
        return this.board.getCurrentPlayer();
    }

    public void passTime()
    {
        this.view.updateClock();
    }

    private void switchPlayers() {
        if (this.board.getCurrentPlayer() == ChessPieceColor.W) 
        {
            this.board.setCurrentPlayer(ChessPieceColor.B);
        } 
        else 
        {
            this.board.setCurrentPlayer(ChessPieceColor.W);
        }
    }

    // ControllerInterface methods

    @Override
    public void userPressed(int row, int col)
    {
        int clickCount = this.board.getClickCount();
        clickCount++;
        this.board.setClickCount(clickCount);

        if (clickCount == 1)
        {
            this.selectPiece(row, col);

        }
        else if (clickCount == 2)
        {
            this.selectDestination(row, col);
            this.board.setClickCount(0);
        }
    }

    @Override
    public void selectPiece(int fromRow, int fromCol)
    {
        this.currentChessPiece = this.board.getChessPiece(fromRow, fromCol);
        ArrayList<int[]> movableSquares = this.board.getMovableSquares(this.currentChessPiece);
        this.view.drawPossibleMoves(movableSquares);
        if (movableSquares.size() == 0)
        {
            this.board.setClickCount(0);
            this.view.invalidDisplay();
            ArrayList<int[]> allCurrentPieces = this.board.getPiecesLocation(this.board.getCurrentPlayer());
            this.view.enableSquares(allCurrentPieces);
        }
    }

    @Override
    public void selectDestination(int toRow, int toCol)
    {
        this.board.placeChessPiece(toRow, toCol, this.currentChessPiece);
        this.view.updateDisplay();

        System.out.println(toRow);

        // Check if a pawn has reached the opposite end of the board
        if (this.board.isPawnAtEnd(this.currentChessPiece)) 
        {
            String newPiece = this.view.promptNewPiece();
        }
        this.endOfTurn();
    }

    public void convertPawn(String unicode)
    {
        this.board.addNewPiece(this.currentChessPiece.getCurrentRow(), this.currentChessPiece.getCurrentCol(), unicode);
        this.endOfTurn();
    }

    public void endOfTurn()
    {
        this.switchPlayers();

        // Check for game over
        if (board.isGameOver()) 
        {
            System.out.println("Game over!"); // TODO: replace with visual once that's been made
        }
        else
        {
            ArrayList<int[]> allCurrentPieces = this.board.getPiecesLocation(this.board.getCurrentPlayer());
            this.view.enableSquares(allCurrentPieces);
        }
    }

    @Override
    public void userQuit() {
        if (ConfirmationDialog.confirmSaveGame()) {
            try {
                // Retrieve file to save to
                String file = FileSelector.selectFileToSave();
                FileOutputStream fileOutput = new FileOutputStream(file);

                // Save to file
                try {
                    ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

                    objectOutput.writeObject(this.board);
                    
                    objectOutput.close();
                } catch (IOException error) {
                    System.out.println("Saving failed: " + error);
                }

                fileOutput.close();
            } catch (FileNotFoundException error) {
                System.out.println("File selection failed: " + error);
            } catch (IOException error) {
                System.out.println("Saving failed: " + error);
            }
        }
        
    }

    public void loadGame() {
        try {
            // Retrieve file to load from
            String file = FileSelector.getFileToLoad();
            FileInputStream fileInput = new FileInputStream(file);

            // Load from file
            try {
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);

                this.board = (ChessBoard) objectInput.readObject();
                
                objectInput.close();
            } catch (ClassNotFoundException error) {
                System.out.println("Loading failed: " + error);
            } catch (IOException error) {
                System.out.println("Loading failed: " + error);
            }

            fileInput.close();
        } catch (FileNotFoundException error) {
            System.out.println("File selection failed: " + error);
        } catch (IOException error) {
            System.out.println("Loading failed: " + error);
        }
    }
}
