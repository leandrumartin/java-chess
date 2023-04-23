package Chess.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*; 

public class ChessClock extends JLabel implements ActionListener
{
    public static final int UPDATE_INTERVAL = 1000;     //1 second
    private Timer timer;
    public int playerTime;


    public ChessClock(int time)
    {
        this.playerTime = time * 60;        //player time in seconds

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
        formattedTime = formattedTime.replace("0", UnicodeMap.zero);
        formattedTime = formattedTime.replace("1", UnicodeMap.one);
        formattedTime = formattedTime.replace("2", UnicodeMap.two);
        formattedTime = formattedTime.replace("3", UnicodeMap.three);
        formattedTime = formattedTime.replace("4", UnicodeMap.four);
        formattedTime = formattedTime.replace("5", UnicodeMap.five);
        formattedTime = formattedTime.replace("6", UnicodeMap.six);
        formattedTime = formattedTime.replace("7", UnicodeMap.seven);
        formattedTime = formattedTime.replace("8", UnicodeMap.eight);
        formattedTime = formattedTime.replace("9", UnicodeMap.nine);
        return formattedTime;
    }

    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == timer)
        {
            int[] currentTime = setTime(playerTime);
            String displayTime = formatTime(currentTime);
            setText(displayTime);
            playerTime--;
            if (checkZero(playerTime) == true)
            {
                timer.stop();
                //Figure out winner here
            }
        }
    }
}
