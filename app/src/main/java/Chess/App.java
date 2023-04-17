package Chess;

import Chess.model.ChessModel;
import Chess.controller.ChessControllerTwoPlayer;;

public class App {
    public static void main(String[] args) {
        ChessModel model = new ChessModel();
        // ChessBoardPrototype prototype = new ChessBoardPrototype(model);
        ChessControllerTwoPlayer controllerTwoPlayer = new ChessControllerTwoPlayer(model, null);
    }
}
