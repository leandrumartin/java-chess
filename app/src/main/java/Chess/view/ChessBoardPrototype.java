package Chess.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Chess.model.ChessModel;
import Chess.view.UnicodeMap;

public class ChessBoardPrototype extends JFrame implements ActionListener {
    private JButton[][] boardSegment = new JButton[8][8];
    private ChessModel model;

    private int panelWidth = 625;
    private int panelHeight = 625;

    public ChessBoardPrototype(ChessModel model) {
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

        this.addPieces();

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Helper function to add pieces to board.
     */
    private void addPieces() {
        for (int i = 0; i < 8; i++) {
            JLabel wPawnLabel = new JLabel(UnicodeMap.wPawn);
            // Set font size to 32
            wPawnLabel.setFont(new Font("Dialog", Font.BOLD, 32));
            wPawnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            boardSegment[6][i].add(wPawnLabel);

            JLabel bPawnLabel = new JLabel(UnicodeMap.bPawn);
            bPawnLabel.setFont(new Font("Dialog", Font.BOLD, 32));
            bPawnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            boardSegment[1][i].add(bPawnLabel);

            if (i == 0 || i == 7) {
                JLabel wRookLabel = new JLabel(UnicodeMap.wRook);
                wRookLabel.setFont(new Font("Dialog", Font.BOLD, 32));
                wRookLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                boardSegment[7][i].add(wRookLabel);

                JLabel bRookLabel = new JLabel(UnicodeMap.bRook);
                bRookLabel.setFont(new Font("Dialog", Font.BOLD, 32));
                bRookLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                boardSegment[0][i].add(bRookLabel);
            } else if (i == 1 || i == 6) {
                JLabel wKnightLabel = new JLabel(UnicodeMap.wKnight);
                wKnightLabel.setFont(new Font("Dialog", Font.BOLD, 32));
                wKnightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                boardSegment[7][i].add(wKnightLabel);

                JLabel bKnightLabel = new JLabel(UnicodeMap.bKnight);
                bKnightLabel.setFont(new Font("Dialog", Font.BOLD, 32));
                bKnightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                boardSegment[0][i].add(bKnightLabel);
            } else if (i == 2 || i == 5) {
                JLabel wBishopLabel = new JLabel(UnicodeMap.wBishop);
                wBishopLabel.setFont(new Font("Dialog", Font.BOLD, 32));
                wBishopLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                boardSegment[7][i].add(wBishopLabel);

                JLabel bBishopLabel = new JLabel(UnicodeMap.bBishop);
                bBishopLabel.setFont(new Font("Dialog", Font.BOLD, 32));
                bBishopLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                boardSegment[0][i].add(bBishopLabel);
            } else if (i == 3) {
                JLabel wQueenLabel = new JLabel(UnicodeMap.wQueen);
                wQueenLabel.setFont(new Font("Dialog", Font.BOLD, 32));
                wQueenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                boardSegment[7][i].add(wQueenLabel);

                JLabel bQueenLabel = new JLabel(UnicodeMap.bQueen);
                bQueenLabel.setFont(new Font("Dialog", Font.BOLD, 32));
                bQueenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                boardSegment[0][i].add(bQueenLabel);
            } else if (i == 4) {
                JLabel wKingLabel = new JLabel(UnicodeMap.wKing);
                wKingLabel.setFont(new Font("Dialog", Font.BOLD, 32));
                wKingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                boardSegment[7][i].add(wKingLabel);

                JLabel bKingLabel = new JLabel(UnicodeMap.bKing);
                bKingLabel.setFont(new Font("Dialog", Font.BOLD, 32));
                bKingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                boardSegment[0][i].add(bKingLabel);
            }
        }
    }

    private void updateGUI() {
        // TODO: Update the chessboard GUI here
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Handle user input events here
    }
}
