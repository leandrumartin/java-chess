package Chess.model;

/*  Create a ChessBoard class that represents the chessboard. 
    The ChessBoard class should contain a two-dimensional array of ChessPiece objects representing 
    the current state of the board. 
*/
import Chess.model.ChessPieces.ChessPiece;

public class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard()
    {
        this.board = new ChessPiece[8][8];

        for (int i = 0; i < 8; i++) {
            ChessPiece wPawn = new WPawn(6, i);
            this.board[6][i].add(wPawn);

            ChessPiece bPawn = new BPawn(1, i);;
            this.board[1][i].add(bPawn);
        }

    }

}
