package Chess.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Chess.model.ChessModel;

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
                if ((row + col) % 2 == 0) {
                    boardSegment[row][col].setBackground(Color.LIGHT_GRAY);
                } else {
                    boardSegment[row][col].setBackground(Color.WHITE);
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
            ImageIcon whitePawn = new ImageIcon("assets/wPawn.png");
            JLabel wPawn = new JLabel(whitePawn);
            boardSegment[6][i].add(wPawn);

            ImageIcon blackPawn = new ImageIcon("assets/bPawn.png");
            JLabel bPawn = new JLabel(blackPawn);
            boardSegment[1][i].add(bPawn);

            int height = whitePawn.getIconHeight(); // Assume each image has same height and width
            int width = whitePawn.getIconWidth();

            height = (int) (height * 0.25);
            width = (int) (width * 0.25);
            Image wPawnImage = whitePawn.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
            Image bPawnImage = blackPawn.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
            whitePawn.setImage(wPawnImage);
            blackPawn.setImage(bPawnImage);

            if (i == 0 || i == 7) {
                ImageIcon whiteRook = new ImageIcon("assets/wRook.png");
                JLabel wRook = new JLabel(whiteRook);
                boardSegment[7][i].add(wRook);

                ImageIcon blackRook = new ImageIcon("assets/bRook.png");
                JLabel bRook = new JLabel(blackRook);
                boardSegment[0][i].add(bRook);

                Image wRookImage = whiteRook.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
                Image bRookImage = blackRook.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
                whiteRook.setImage(wRookImage);
                blackRook.setImage(bRookImage);
            } else if (i == 1 || i == 6) {
                ImageIcon whiteKnight = new ImageIcon("assets/wKnight.png");
                JLabel wKnight = new JLabel(whiteKnight);
                boardSegment[7][i].add(wKnight);

                ImageIcon blackKnight = new ImageIcon("assets/bKnight.png");
                JLabel bKnight = new JLabel(blackKnight);
                boardSegment[0][i].add(bKnight);

                Image wKnightImage = whiteKnight.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
                Image bKnightImage = blackKnight.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
                whiteKnight.setImage(wKnightImage);
                blackKnight.setImage(bKnightImage);
            } else if (i == 2 || i == 5) {
                ImageIcon whiteBishop = new ImageIcon("assets/wBishop.png");
                JLabel wBishop = new JLabel(whiteBishop);
                boardSegment[7][i].add(wBishop);

                ImageIcon blackBishop = new ImageIcon("assets/bBishop.png");
                JLabel bBishop = new JLabel(blackBishop);
                boardSegment[0][i].add(bBishop);

                Image wBishopImage = whiteBishop.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
                Image bBishopImage = blackBishop.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
                whiteBishop.setImage(wBishopImage);
                blackBishop.setImage(bBishopImage);
            } else if (i == 3) {
                ImageIcon whiteQueen = new ImageIcon("assets/wQueen.png");
                JLabel wQueen = new JLabel(whiteQueen);
                boardSegment[7][i].add(wQueen);

                ImageIcon blackQueen = new ImageIcon("assets/bQueen.png");
                JLabel bQueen = new JLabel(blackQueen);
                boardSegment[0][i].add(bQueen);

                Image wQueenImage = whiteQueen.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
                Image bQueenImage = blackQueen.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
                whiteQueen.setImage(wQueenImage);
                blackQueen.setImage(bQueenImage);
            } else if (i == 4) {
                ImageIcon whiteKing = new ImageIcon("assets/wKing.png");
                JLabel wKing = new JLabel(whiteKing);
                boardSegment[7][i].add(wKing);

                ImageIcon blackKing = new ImageIcon("assets/bKing.png");
                JLabel bKing = new JLabel(blackKing);
                boardSegment[0][i].add(bKing);

                Image wKingImage = whiteKing.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
                Image bKingImage = blackKing.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
                whiteKing.setImage(wKingImage);
                blackKing.setImage(bKingImage);
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
