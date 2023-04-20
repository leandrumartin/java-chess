package Chess.view;

import java.awt.*;
import javax.swing.*;

public class ChessPieces 
{
    private JButton[][] boardSegment;

    public ChessPieces(JButton[][] boardSegment)
    {
        this.boardSegment = boardSegment;
        this.addPieces();
    }

    private void initializePiece(String label)
    {
        Font font = new Font("Dialog", Font.BOLD, 45);
    }

    /**
     * Helper function to add pieces to board.
     */
    public void addPieces() {
        for (int i = 0; i < 8; i++) {
            String wPawnLabel = UnicodeMap.wPawn;
            initializePiece(wPawnLabel);
            boardSegment[6][i].setText(wPawnLabel);

            String bPawnLabel = UnicodeMap.bPawn;
            initializePiece(bPawnLabel);
            boardSegment[1][i].setText(bPawnLabel);
/*
            if (i == 0 || i == 7) {
                JLabel wRookLabel = new JLabel(UnicodeMap.wRook);
                initializePiece(wRookLabel);
                boardSegment[7][i].add(wRookLabel);

                JLabel bRookLabel = new JLabel(UnicodeMap.bRook);
                initializePiece(bRookLabel);
                boardSegment[0][i].add(bRookLabel);

            } else if (i == 1 || i == 6) {
                JLabel wKnightLabel = new JLabel(UnicodeMap.wKnight);
                initializePiece(wKnightLabel);
                boardSegment[7][i].add(wKnightLabel);

                JLabel bKnightLabel = new JLabel(UnicodeMap.bKnight);
                initializePiece(bKnightLabel);
                boardSegment[0][i].add(bKnightLabel);
                
            } else if (i == 2 || i == 5) {
                JLabel wBishopLabel = new JLabel(UnicodeMap.wBishop);
                initializePiece(wBishopLabel);
                boardSegment[7][i].add(wBishopLabel);

                JLabel bBishopLabel = new JLabel(UnicodeMap.bBishop);
                initializePiece(bBishopLabel);
                boardSegment[0][i].add(bBishopLabel);

            } else if (i == 3) {
                JLabel wQueenLabel = new JLabel(UnicodeMap.wQueen);
                initializePiece(wQueenLabel);
                boardSegment[7][i].add(wQueenLabel);

                JLabel bQueenLabel = new JLabel(UnicodeMap.bQueen);
                initializePiece(bQueenLabel);
                boardSegment[0][i].add(bQueenLabel);

            } else if (i == 4) {
                JLabel wKingLabel = new JLabel(UnicodeMap.wKing);
                initializePiece(wKingLabel);
                boardSegment[7][i].add(wKingLabel);

                JLabel bKingLabel = new JLabel(UnicodeMap.bKing);
                initializePiece(bKingLabel);
                boardSegment[0][i].add(bKingLabel);
            }
            */
        }
    }
}
