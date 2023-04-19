package Chess.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.ArrayList;

import Chess.controller.ChessController;
import Chess.controller.ControllerInterface;
import Chess.model.ChessBoard;
import Chess.view.ChessPieces;

public class ChessView extends JFrame implements ActionListener {
    private JButton[][] boardSegment = new JButton[8][8];
    private ChessBoard model;
    private ControllerInterface controller;

    private boolean mouseInView;
    private int clickCount = 0;
    private int panelWidth = 700;
    private int panelHeight = 700;


    public ChessView(ControllerInterface controller, ChessBoard model) {
        this.model = model;

        JFrame frame = new JFrame("Chess Board");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        mainPanel.setBackground(new Color(192,192,192));

        JPanel boardPanel = new JPanel(new GridLayout(10, 10));
        boardPanel.setBackground(new Color(192,192,192));
        generateColumns(boardPanel);
        for (int row = 0; row < 8; row++) // 8 rows
        {
            // Create Labels for each of the rows on the board
            JLabel rowLabel = new JLabel(String.valueOf(Math.abs(row - 8)), JLabel.CENTER);
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
                boardSegment[row][col].addActionListener(this);
                boardPanel.add(boardSegment[row][col]);
            }

            JLabel rowLabel2 = new JLabel(String.valueOf(Math.abs(row - 8)), JLabel.CENTER);
            rowLabel.setPreferredSize(new Dimension(20, 70));
            boardPanel.add(rowLabel2);  
        }
        generateColumns(boardPanel);

        mainPanel.add(boardPanel);

        ChessPieces chessPieces = new ChessPieces(boardSegment);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

    }

    public void generateColumns(JPanel panel)
    {
        panel.add(new JLabel(""));
        for (int i = 0; i < 8; i++) 
        {
            JLabel colLabel = new JLabel(String.valueOf((char) ('A' + i)), JLabel.CENTER);
            colLabel.setPreferredSize(new Dimension(20, 70));
            panel.add(colLabel);
        }
        panel.add(new JLabel(""));
    }

    private void disableBoard()
    {
        for (int row = 0; row < 8; row++)
        {
            for (int col = 0; col < 8; col++)
            {
                boardSegment[row][col].setEnabled(false);
            }
        }
    }
    
    private void drawMoves(ArrayList<ArrayList<Integer>> enabledSquares) 
    {
        disableBoard(); //Disable all buttons

        for (ArrayList<Integer> getRowCol : enabledSquares) //Get every legal square and enable it
        {
            int row = getRowCol.get(0);
            int col = getRowCol.get(1);
            boardSegment[row][col].setEnabled(true);
            boardSegment[row][col].setText(UnicodeMap.dot);
            boardSegment[row][col].setFont(new Font("Dialog", Font.BOLD, 45));
        }
    }

    private void updateGUI(int fromRow, int fromCol, int toRow, int toCol)
    {
        boardSegment[fromRow][fromCol].setText("");
        boardSegment[toRow][toCol].setText("");
    }
    @Override
    public void actionPerformed(ActionEvent event)
    {
        clickCount++;       //clickCount = 1 - User made their piece selection
        int row = 0;
        int col = 0;
        JButton button = (JButton)event.getSource();
        for (int i = 0; i < 8; i++) //go through rows
        {
            for (int j = 0; j < 8; j++) //go through columns
            {
                if (button == boardSegment[i][j])
                {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        System.out.println("row: " + row +"col: " + col);
        if (clickCount == 1)
        {
            this.controller.selectPiece(row, col);
        }
        else if (clickCount == 2)
        {
            this.controller.makeMove(row, col);
        }
    }
}
