package Chess.view;

import java.awt.*;
import javax.swing.*; 

import Chess.controller.ControllerInterface;
import Chess.controller.ChessControllerTwoPlayer;
import Chess.model.ChessPieces.ChessPieceColor;

public class GameDisplay extends JLabel
{
    private ChessPieceColor currentPlayer;
    private ChessControllerTwoPlayer controller;

    public GameDisplay(ControllerInterface controller)
    {
        this.controller = (ChessControllerTwoPlayer) controller;
        this.currentPlayer = this.controller.getCurrentPlayer();
    }

    public String displayGameStatus()
    {
        this.currentPlayer = this.controller.getCurrentPlayer();
        System.out.println(currentPlayer);
        if (currentPlayer == ChessPieceColor.W)
        {
            return "White";
        }
        else
        {
            return "Black";
        }
    }
    
    public void invalidMove()
    {
        setText("No Valid Moves. Select A New Piece");
    }
}
