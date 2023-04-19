package Chess.controller;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.model.ChessPieces.ChessPiece;
import Chess.view.ChessView;
import Chess.controller.ChessController;

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

    public void makeMove(int toRow, int toCol)
    {
        if (this.legalPiece)
        {
            board.placeChessPiece(toRow, toCol, this.currentChessPiece);
        }
    }

    public void selectPiece(int fromRow, int fromCol)
    {
        ChessPiece selectedPiece = board.getChessPiece(fromRow, fromCol);
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
}
