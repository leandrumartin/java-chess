import java.awt.*;
import javax.swing.*;

public class ChessBoardPrototype extends JFrame
{
    private JButton[][] boardSegment = new JButton[8][8];
    private int panelWidth = 625;
    private int panelHeight = 625;
    public ChessBoardPrototype()
    {
        JFrame frame = new JFrame("Chess Board Prototype");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        JPanel boardPanel = new JPanel(new GridLayout(9,9));
        for (int row = 0; row < 8; row++)   // 8 rows
        {
            //Create Labels for each of the rows on the board
            JLabel rowLabel = new JLabel(String.valueOf(row + 1), JLabel.CENTER);   
            rowLabel.setPreferredSize(new Dimension(20, 70));
            boardPanel.add(rowLabel);
  
            //Creates the segments of the board, each segment is a button
            for (int col = 0; col < 8; col++)  //8 columns
            {           
                boardSegment[row][col] = new JButton();
                if ((row + col) % 2 == 0)
                {
                    boardSegment[row][col].setBackground(Color.WHITE);                  
                }
                else
                {
                    boardSegment[row][col].setBackground(Color.BLACK);
                }
                boardSegment[row][col].setPreferredSize(new Dimension(70,70));
                boardPanel.add(boardSegment[row][col]);
            }
        }  

        boardPanel.add(new JLabel("")); //This is necessary in order to ensure A lines up with the first column and not the column on numbers.
        //Create labels for each of the columns on the board
        for (int i = 0; i < 8; i++)
        {
            JLabel colLabel = new JLabel(String.valueOf((char)('A' + i)), JLabel.CENTER);
            colLabel.setPreferredSize(new Dimension(20, 70));
            boardPanel.add(colLabel);
        }

        mainPanel.add(boardPanel);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }   
    public static void main(String[] args)
    {
        new ChessBoardPrototype();
    }
}
