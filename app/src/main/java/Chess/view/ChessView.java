package Chess.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.ArrayList;

import Chess.controller.ChessControllerTwoPlayer;
import Chess.controller.ControllerInterface;
import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.view.ChessPieces;
import Chess.GameObserver;

public class ChessView extends JFrame implements ActionListener, GameObserver {
    private JButton[][] boardSegment = new JButton[8][8];
    private ChessBoard board;
    private ControllerInterface controller;

    private int panelWidth = 930;
    private int panelHeight = 700;
    private int clockTime;
    private JPanel boardPanel;
    private JPanel clockPanel;

    public ChessView(ControllerInterface controller, ChessBoard board, int time) {
        this.board = board;
        this.controller = controller;
        this.clockTime = time;

        // register this object as the observer of the game
        this.board.register(this);

        JFrame frame = new JFrame("Chess Board");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
               controller.userQuit();
            }
         });

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        mainPanel.setBackground(new Color(192,192,192));

        this.boardPanel = new JPanel(new GridLayout(10, 10));
        this.boardPanel.setBackground(new Color(192,192,192));
        generateColumns(this.boardPanel);
        for (int row = 0; row < 8; row++) // 8 rows
        {
            // Create Labels for each of the rows on the board
            JLabel rowLabel = new JLabel(String.valueOf(Math.abs(row - 8)), JLabel.CENTER);
            rowLabel.setPreferredSize(new Dimension(20, 70));
            this.boardPanel.add(rowLabel);

            // Creates the segments of the board, each segment is a button
            for (int col = 0; col < 8; col++) // 8 columns
            {
                boardSegment[row][col] = new JButton();
                boardSegment[row][col].setOpaque(true);
                boardSegment[row][col].setBorder(null);
                if ((row + col) % 2 == 0) 
                {
                    boardSegment[row][col].setBackground(new Color(235, 235, 208));
                } 
                else 
                {
                    boardSegment[row][col].setBackground(new Color(119, 148, 86));
                }
                boardSegment[row][col].setPreferredSize(new Dimension(70, 70));
                boardSegment[row][col].addActionListener(this);
                if (row <= 3)
                {
                    boardSegment[row][col].setEnabled(false);
                }
                this.boardPanel.add(boardSegment[row][col]);
            }

            JLabel rowLabel2 = new JLabel(String.valueOf(Math.abs(row - 8)), JLabel.CENTER);
            rowLabel.setPreferredSize(new Dimension(20, 70));
            this.boardPanel.add(rowLabel2);  
        }
        generateColumns(this.boardPanel);

        clockPanel = new JPanel(new GridLayout(2, 2));
        clockPanel.setPreferredSize(new Dimension(100, 50));
        clockPanel.setBackground(new Color(192,192,192));

        JLabel whiteClockLabel = new JLabel("White: ");
        JLabel whiteClock = generateClock(clockPanel, clockTime);
        clockPanel.add(whiteClockLabel);
        clockPanel.add(whiteClock);

        JLabel blackClockLabel = new JLabel("Black: ");
        JLabel blackClock = generateClock(clockPanel, clockTime);
        clockPanel.add(blackClockLabel);
        clockPanel.add(blackClock);

        mainPanel.add(this.boardPanel);
        mainPanel.add(clockPanel);

        ChessPieces chessPieces = new ChessPieces(boardSegment);

        this.update(); // Draw the current pieces--necessary for loading a saved game

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public ChessClock generateClock(JPanel panel, int clockTime)
    {
        ChessClock clock = new ChessClock(clockTime);
        return clock;
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
                boardSegment[row][col].setOpaque(true); 
                boardSegment[row][col].setBorder(null);
            }
        }
    }
    
    public String getCurrentLabel(int row, int col)
    {
        return boardSegment[row][col].getText();
    }
    
    public void drawPossibleMoves(ArrayList<int[]> enabledSquares) 
    {
        this.disableBoard(); // Disable all buttons

        for (int[] getRowCol : enabledSquares) //Get every legal square and enable it
        {
            int row = getRowCol[0];
            int col = getRowCol[1];
            boardSegment[row][col].setEnabled(true);
            boardSegment[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4, true));
            boardSegment[row][col].setOpaque(true); 
        }
    }

    public void enableSquares(ArrayList<int[]> allCurrentPieces)
    {
        this.disableBoard();
        for (int[] pieceLoc : allCurrentPieces)
        {
            int row = pieceLoc[0];
            int col = pieceLoc[1];
            boardSegment[row][col].setEnabled(true);
            boardSegment[row][col].setOpaque(true); 
            boardSegment[row][col].setBorder(null);
        }
    }
    
    public void removeDots()
    {
        for (int row = 0; row < 8; row++)
        {
            for (int col = 0; col < 8; col++)
            {
                if ((row + col) % 2 == 0) 
                {
                    boardSegment[row][col].setBackground(new Color(235, 235, 208));
                    boardSegment[row][col].setOpaque(true); 
                    boardSegment[row][col].setBorder(null);
                } 
                else 
                {
                    boardSegment[row][col].setBackground(new Color(119, 148, 86));
                    boardSegment[row][col].setOpaque(true); 
                    boardSegment[row][col].setBorder(null);
                }
            }
        }
    }

    // public void update(ArrayList<int[]> move)
    public void update()
    {
        // Erase board
        removeDots();
        for (JButton[] buttons : this.boardSegment) {
            for (JButton button : buttons) {
                button.setText(null);
            }
        }

        // Draw pieces on board
        ArrayList<int[]> pieces = this.board.findPieces();
        for (int[] piece : pieces) {
            int row = piece[0];
            int col = piece[1];
            String label = this.board.getChessPiece(row, col).getLabel();
            this.boardSegment[row][col].setText(label);
            this.boardSegment[row][col].setFont(new Font("Dialog", Font.PLAIN, 45));
        }
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
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
        this.controller.userPressed(row, col);
    }
}
