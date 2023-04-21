package Chess.controller;

import java.util.ArrayList;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.model.ChessPieces.ChessPiece;
import Chess.view.ChessView;

public class ChessController implements ControllerInterface {
    private ChessBoard board;
    private ChessView view;
    private ChessPieceColor currentPlayer;
    private ChessPiece currentChessPiece;
    private int clickCount;

    public ChessController(ChessBoard board) {
        this.board = board;
        
        this.view = new ChessView(this, board);
        this.view.setVisible(true);

        this.currentPlayer = ChessPieceColor.W;
        this.clickCount = 0;
        System.out.println("Chess Controller Constructor is called");
    }

    public void userPressed(int row, int col)
    {
        System.out.println("In  Controller, userPressed() is called");
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

    public void selectPiece(int fromRow, int fromCol)
    {
        this.currentChessPiece = board.getChessPiece(fromRow, fromCol);
        ArrayList<int[]> movableSquares = this.board.movableSquares(this.currentChessPiece);
        this.view.drawPossibleMoves(movableSquares);

        // need to add logic for when movableSquares.size() == 0
    }

    public void selectDestination(int toRow, int toCol)
    {
        this.board.placeChessPiece(toRow, toCol, this.currentChessPiece);
        this.switchPlayers();
        ArrayList<int[]> allCurrentPieces = this.board.findPieces(this.currentPlayer);
        this.view.enableSquares(allCurrentPieces);
    }

    private void switchPlayers() {
        if (this.currentPlayer == ChessPieceColor.W) {
            this.currentPlayer = ChessPieceColor.B;
        } else {
            this.currentPlayer = ChessPieceColor.W;
        }
    }

}
