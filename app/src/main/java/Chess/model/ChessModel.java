package Chess.model;

import Chess.model.ChessPieces.ChessPiece;

public class ChessModel {
    private ChessBoard board;

    public ChessModel() {
        this.board = new ChessBoard();
    }

    // Temporarily disabled to let prototype compile
    /*
     * public ChessPiece getPiece(int row, int col) {
     * return board.getPiece(row, col);
     * }
     * 
     * public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol) {
     * return board.movePiece(fromRow, fromCol, toRow, toCol);
     * }
     * 
     * public boolean isCheckmate() {
     * return board.isCheckmate();
     * }
     */
}
