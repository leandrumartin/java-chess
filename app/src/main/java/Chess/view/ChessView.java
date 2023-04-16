package Chess.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Chess.controller.ChessController;
import Chess.model.ChessModel;
import Chess.view.ChessPieces;

public class ChessView extends JFrame implements MouseListener {
    private ChessController controller;

    private JButton[][] boardSegment = new JButton[8][8];
    private ChessModel model;

    private int panelWidth = 625;
    private int panelHeight = 625;

    public ChessView(ChessController controller, ChessModel model) {
        this.controller = controller;
        this.model = model;

        JFrame frame = new JFrame("Chess Board Prototype");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        for (int row = 0; row < 8; row++) // 8 rows
        {
            // Create Labels for each of the rows on the board
            JLabel rowLabel = new JLabel(String.valueOf(row + 1), JLabel.CENTER);
            rowLabel.setPreferredSize(new Dimension(20, 70));
            boardPanel.add(rowLabel);

            // Creates the segments of the board, each segment is a button
            for (int col = 0; col < 8; col++) // 8 columns
            {
                boardSegment[row][col] = new JButton();
                boardSegment[row][col].setOpaque(true);
                boardSegment[row][col].setBorder(null);
                if ((row + col) % 2 == 0) {
                    boardSegment[row][col].setBackground(new Color(235, 235, 208));
                } else {
                    boardSegment[row][col].setBackground(new Color(119, 148, 86));
                }
                boardSegment[row][col].setPreferredSize(new Dimension(70, 70));
                boardPanel.add(boardSegment[row][col]);
            }
        }

        boardPanel.add(new JLabel("")); // This is necessary in order to ensure A lines up with the first column and not
                                        // the column on numbers.

        // Create labels for each of the columns on the board
        for (int i = 0; i < 8; i++) {
            JLabel colLabel = new JLabel(String.valueOf((char) ('A' + i)), JLabel.CENTER);
            colLabel.setPreferredSize(new Dimension(20, 70));
            boardPanel.add(colLabel);
        }

        mainPanel.add(boardPanel);

        ChessPieces chessPieces = new ChessPieces(boardSegment);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

    }

    private void updateGUI() {
        // TODO: Update the chessboard GUI here
    }

    @Override
    public void mouseEntered(MouseEvent event)
    {
       System.out.println("Mouse entered");
    }
    @Override
    public void mouseExited(MouseEvent event)
    {
       System.out.println("Mouse exited");
    }
 
    @Override 
    public void mousePressed(MouseEvent event)
    {
        JButton button = (JButton)event.getSource();
    }
    @Override 
    public void mouseReleased(MouseEvent event)
    {
        JButton button = (JButton)event.getSource();
    }
    @Override 
    public void mouseClicked(MouseEvent event){}
}
