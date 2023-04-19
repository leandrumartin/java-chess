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

    public ChessController(ChessBoard board, ChessView view) {
        this.board = board;
        this.view = view;
        this.nextPlayer = ChessPieceColor.W;
    }

    private void selectPiece(int fromRow, int fromCol)
    {
        this.currentChessPiece = board.getChessPiece(fromRow, fromCol);
    }

    private void selectDestination(int toRow, int toCol)
    {
        board.placeChessPiece(toRow, toCol, this.currentChessPiece);
    }

}
