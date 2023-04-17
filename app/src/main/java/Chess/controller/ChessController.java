package Chess.controller;

import Chess.model.ChessModel;
//import Chess.view.ChessView;

public class ChessController {
    private ChessModel model;
    //private ChessView view;

    public ChessController(ChessModel model) {
        this.model = model;
        //this.view = view;
    }

    public void start() {
        // Set up the initial state of the game here
    }

    public void userPressed(int fromRow, int fromCol)
    {
        System.out.println(Integer.toString(fromRow) + Integer.toString(fromCol));
    }

    public void userReleased(int toRow, int toCol)
    {
        System.out.println(Integer.toString(toRow) + Integer.toString(toCol));
    }

    private void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Make a move on the chessboard and update the GUI here
    }

    private void endGame() {
        // End the game and show the result here
    }
}
