package Chess.controller;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPiece;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.view.ChessView;

public class ChessControllerTwoPlayer implements ControllerInterface {
    private ChessBoard board;
    private ChessView view;
    private ChessPiece selectedPiece;
    private ChessPieceColor currentPlayer;

    public ChessControllerTwoPlayer(ChessBoard board) {
        this.board = board;
        this.view = new ChessView(this, board);
        this.currentPlayer = ChessPieceColor.W;
    }

    public void start() {
        // Set up the initial state of the game here
    }

    /**
     * Handles selection of the chesspiece to move
     * @param piece the piece that the player selected
     */
    public void selectPiece(int fromRow, int fromCol) 
    {
        ChessPiece selectedPiece = board.getChessPiece(fromRow, fromCol);
        if (selectedPiece.getColor() == this.currentPlayer) 
        {
            this.selectedPiece = selectedPiece;
        }
    }

    public void makeMove(int toRow, int toCol) 
    {
        // Make a move on the chessboard and update the GUI here
        // TODO: check for `this.selectedPiece.isValidMove(this.selectedPiece, toRow, toCol)` (deliverable 2)
        if (true) {
            //this.board.movePiece(this.selectedPiece, toRow, toCol);

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
