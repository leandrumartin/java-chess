package Chess;

import Chess.model.ChessModel;
import Chess.view.MainMenu;

public class App {
    public static void main(String[] args) {
        ChessModel model = new ChessModel();
        MainMenu menu = new MainMenu(model);
    }
}
