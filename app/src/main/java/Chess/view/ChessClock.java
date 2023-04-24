package Chess.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*; 

import Chess.controller.ChessControllerTwoPlayer;
import Chess.controller.ControllerInterface;
import Chess.model.ChessPieces.ChessPieceColor;

public class ChessClock extends JLabel implements ActionListener
{
    public static final int UPDATE_INTERVAL = 1000;     //1 second
    private Timer timer;
    public int whiteTime;
    public int blackTime;
    private ChessControllerTwoPlayer controller;
    private ChessPieceColor currentPlayer;

    public ChessClock(int time, ControllerInterface controller)
    {
        this.whiteTime = time * 60;        //player time in seconds
        this.blackTime = time * 60;
        this.controller = (ChessControllerTwoPlayer) controller;

        timer = new Timer(UPDATE_INTERVAL, this);
        timer.start();
    }

    public boolean checkZero(int time)
    {
        if (time == 0)
        {
            return true;
        }
        return false;
    }

    public int[] setTime(int time)
    {
        int[] result = new int[2];
        int minutes = time / 60;
        int seconds = time % 60;
        result[0] = minutes;
        result[1] = seconds;
        return result;
    }

    public String formatTime(int[] time)
    {
        String formattedTime = String.format("%02d:%02d", time[0], time[1]); //2 digits for minutes and 2 digits for seconds
        return formattedTime;
    }

    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == timer)
        {
            currentPlayer = this.controller.getCurrentPlayer();
            System.out.println(currentPlayer);
            if (currentPlayer == ChessPieceColor.W)
            {
                int[] currentWhiteTime = setTime(whiteTime);
                String displayWhiteTime = formatTime(currentWhiteTime);
                setText(displayWhiteTime);
                whiteTime--;
                if (checkZero(whiteTime) == true)
                {
                    timer.stop();
                    //Figure out winner here
                } 
            }
            else
            {
                int[] currentBlackTime = setTime(blackTime);
                String displayBlackTime = formatTime(currentBlackTime);
                setText(displayBlackTime);
                blackTime--;
                if (checkZero(blackTime) == true)
                {
                    timer.stop();
                    //Figure out winner here
                }
            }
        }
    }
}
