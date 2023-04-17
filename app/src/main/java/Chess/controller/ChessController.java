package Chess.controller;

import Chess.model.ChessModel;
import Chess.model.ChessPieces.ChessPiece;
import Chess.view.ChessView;

public interface ChessController {

    public void start();

    public void pieceSelected(ChessPiece piece);

    public void makeMove(int toRow, int toCol);

    public void endGame();

}
