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

public class ChessControllerTwoPlayer implements ControllerInterface {
    private ChessBoard board;
    private ChessView view;
    private ChessPieceColor currentPlayer;
    private ChessPiece currentChessPiece;
    private int clickCount;

    public ChessControllerTwoPlayer(ChessBoard board, int time) {
        this.board = board;
        
        this.view = new ChessView(this, board, time);
        this.view.setVisible(true);

        this.currentPlayer = ChessPieceColor.W;
        this.clickCount = 0;
    }
    
    public ChessPieceColor getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    private void switchPlayers() {
        if (this.currentPlayer == ChessPieceColor.W) 
        {
            this.currentPlayer = ChessPieceColor.B;
        } 
        else 
        {
            this.currentPlayer = ChessPieceColor.W;
        }
    }

    // ControllerInterface methods

    @Override
    public void userPressed(int row, int col)
    {
        clickCount++;
        if (clickCount == 1)
        {
            this.selectPiece(row, col);

        }
        else if (clickCount == 2)
        {
            this.selectDestination(row, col);
            clickCount = 0;
        }
    }

    @Override
    public void selectPiece(int fromRow, int fromCol)
    {
        this.currentChessPiece = board.getChessPiece(fromRow, fromCol);
        ArrayList<int[]> movableSquares = this.board.movableSquares(this.currentChessPiece);
        this.view.drawPossibleMoves(movableSquares);
        if (movableSquares.size() == 0)
        {
            clickCount = 0;
            ArrayList<int[]> allCurrentPieces = this.board.findPieces(this.currentPlayer);
            view.enableSquares(allCurrentPieces);
        }
    }

    @Override
    public void selectDestination(int toRow, int toCol)
    {
        this.board.placeChessPiece(toRow, toCol, this.currentChessPiece);
        this.switchPlayers();
        ArrayList<int[]> allCurrentPieces = this.board.findPieces(this.currentPlayer);
        this.view.enableSquares(allCurrentPieces);
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
        if (ConfirmationDialog.confirmLoadGame()) {
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
}
