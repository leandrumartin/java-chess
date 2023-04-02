package Chess;

import Chess.model.ChessModel;
import Chess.view.ChessBoardPrototype;

public class App {
    public static void main(String[] args) {
        ChessModel model = new ChessModel();
        ChessBoardPrototype prototype = new ChessBoardPrototype(model);
    }
}
