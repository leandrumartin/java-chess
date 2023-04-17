package Chess.controller;

import Chess.model.ChessModel;
import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.view.ChessView;

public class ChessControllerTwoPlayer implements ChessController {
    private ChessModel model;
    private ChessView view;
    private ChessPiece selectedPiece;
    private ChessPieceColor currentPlayer;

    public ChessControllerTwoPlayer(ChessModel model) {
        this.model = model;
        this.view = new ChessView(this, model);
        this.currentPlayer = ChessPieceColor.W;
    }

    public void start() {
        // Set up the initial state of the game here
    }

    /**
     * Handles selection of the chesspiece to move
     * @param piece the piece that the player selected
     */
    public void pieceSelected(ChessPiece piece) {
        if (piece.getColor() == this.currentPlayer) {
            this.selectedPiece = piece;
        }
    }

    public void makeMove(int toRow, int toCol) {
        // Make a move on the chessboard and update the GUI here
        // TODO: check for `this.selectedPiece.isValidMove(this.selectedPiece, toRow, toCol)` (deliverable 2)
        if (true) {
            this.model.movePiece(this.selectedPiece, toRow, toCol);

            // Switch to other player
            if (this.currentPlayer == ChessPieceColor.W) {
                this.currentPlayer = ChessPieceColor.B;
            } else {
                this.currentPlayer = ChessPieceColor.W;
            }
        }
    }

    public void endGame() {
        // End the game and show the result here
    }
}
