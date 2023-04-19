// package Chess.controller;

// import java.util.ArrayList;

// import Chess.model.ChessBoard;
// import Chess.model.ChessPieces.ChessPiece;
// import Chess.model.ChessPieces.ChessPieceColor;
// import Chess.view.ChessBoardGUI;
// import Chess.controller.ChessController;

// public class ChessControllerTwoPlayer implements ControllerInterface {
//     private ChessBoard board;
//     private ChessBoardGUI view;
//     private ChessPiece selectedPiece;
//     private ChessPieceColor currentPlayer;

//     public ChessControllerTwoPlayer(ChessBoard board) {
//         this.board = board;
//         this.view = new ChessBoardGUI(this, board);
//         this.currentPlayer = ChessPieceColor.W;
//     }

//     /**
//      * Helper function to switch to the next player.
//      */
//     private void switchPlayers() {
//         if (this.currentPlayer == ChessPieceColor.W) {
//             this.currentPlayer = ChessPieceColor.B;
//         } else {
//             this.currentPlayer = ChessPieceColor.W;
//         }

//         // Disable all buttons except those of the next player's pieces

//         // Disable all buttons
//         ArrayList<int[]> disabled = new ArrayList<int[]>();
//         for (int i = 0; i < 8; i++) {
//             for (int j = 0; j < 8; j++) {
//                 int[] square = {i, j};
//                 disabled.add(square);
//             }
//         }
//         this.view.disableSquares(disabled);

//         // Reenable only the next player's pieces
//         ArrayList<int[]> enabled = new ArrayList<int[]>();
//         for (int i = 0; i < 8; i++) {
//             for (int j = 0; j < 8; j++) {
//                 if (this.board.getChessPiece(i, j).getColor() == this.currentPlayer) {
//                     int[] square = {i, j};
//                     enabled.add(square);
//                 }
//             }
//         }
//         this.view.enableSquares(enabled);
        
//     }

//     // ControllerInterface methods

//     @Override
//     public void start() {
//         // Set up the initial state of the game here
//     }

//     @Override
//     public void selectPiece(int row, int col) {
//         this.selectedPiece = this.board.getChessPiece(row, col);
//     }

//     @Override
//     public void selectDestination(int row, int col) {
//         this.board.placeChessPiece(row, col, selectedPiece);
//         this.switchPlayers();
//     }

//     @Override
//     public void endGame() {
//         // End the game and show the result here
//     }
// }
