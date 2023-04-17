package Chess.controller;

import Chess.board.ChessBoard;
import Chess.board.ChessPieces.ChessPieceColor;
import Chess.board.ChessPieces.ChessPiece;
import Chess.view.ChessView;
import Chess.ControllerInterface;

public class ChessController implements ControllerInterface {
    private ChessBoard board;
    private ChessView view;
    private ChessPieceColor nextPlayer;
    private ChessPiece currentChessPiece;
    private boolean legalPiece;

    public ChessController(ChessBoard board, ChessView view) {
        this.board = board;
        this.view = view;
        this.nextPlayer = ChessPieceColor.W;
        this.legalPiece = false;
    }

    public void start() {
        // Set up the initial state of the game here
    }

    private void selectPiece(int fromRow, int fromCol)
    {
        ChessPiece selectedPiece = view.getChessPiece(fromRow, fromCol);
        ChessPieceColor selectedColor = selectedPiece.getColor();
        if (selectedColor == this.nextPlayer)
        {
            this.legalPiece = true;
            this.currentChessPiece = selectedPiece;
        }
        else 
        {
            this.legalPiece = false;
        }
    }

    private void selectDestination(int toRow, int toCol)
    {
        if (this.legalPiece)
        {
            board.placeChessPiece(toRow, toCol, this.currentChessPiece);
        }
    }

    private void endGame() {
        // End the game and show the result here
    }
}
