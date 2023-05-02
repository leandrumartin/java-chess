package Chess.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Chess.model.ChessPieces.ChessPieceColor;

public class ConvertPawn extends JDialog implements ActionListener
{
    private JButton knightButton;
    private JButton bishopButton;
    private JButton rookButton;
    private JButton queenButton;
    private JFrame mainFrame;
    private JPanel convertPanel;
    private ChessPieceColor color;
    private int index;

    public ConvertPawn(ChessPieceColor color, JFrame parent)
    {
        super(parent, "Select A New Piece", true);
        setLayout(new GridLayout(1,4));
        setSize(400, 50);
        setLocationRelativeTo(parent);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        this.color = color;
        if (this.color == ChessPieceColor.W)
        {
            knightButton = new JButton(UnicodeMap.wKnight);
            bishopButton = new JButton(UnicodeMap.wBishop);
            rookButton = new JButton(UnicodeMap.wRook);
            queenButton = new JButton(UnicodeMap.wQueen);
        }
        else
        {
            knightButton = new JButton(UnicodeMap.bKnight);
            bishopButton = new JButton(UnicodeMap.bBishop);
            rookButton = new JButton(UnicodeMap.bRook);
            queenButton = new JButton(UnicodeMap.bQueen);
        }
        initSwapButtons(convertPanel, knightButton);
        initSwapButtons(convertPanel, bishopButton);
        initSwapButtons(convertPanel, rookButton);
        initSwapButtons(convertPanel, queenButton);
    }

    public void initSwapButtons(JPanel panel, JButton button)
    {
        button.setFont(new Font("Arial Unicode MS", Font.PLAIN, 40));
        button.setPreferredSize(new Dimension(50,50));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener(this);
        panel.add(button);
    }

    public int getIndex()
    {
        return this.index;
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == this.knightButton)
        {
            if (this.color == ChessPieceColor.W)
            {
                this.index = 0;
            }
            else
            {
                this.index = 1;;
            }
        }
        else if (event.getSource() == this.bishopButton)
        {
            if (this.color == ChessPieceColor.W)
            {
                this.index = 2;;
            }
            else
            {
                this.index = 3;
            }
        }
        else if (event.getSource() == this.rookButton)
        {
            if (this.color == ChessPieceColor.W)
            {
                this.index = 4;
            }
            else
            {
                this.index = 5;
            }
        }
        else                               
        {
            if (this.color == ChessPieceColor.W)
            {
                this.index = 6;
            }
            else
            {
                this.index = 7;
            }
        }
        this.dispose();
    }
}