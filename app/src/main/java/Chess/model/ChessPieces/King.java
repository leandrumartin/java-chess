package Chess.model.ChessPieces;

import javax.swing.*;
import java.awt.*;

public class King implements ChessPiece 
{
    JFrame mainFrame;
    JPanel mainPanel;
    ImageIcon icon;
    JLabel imageLabel;

    public King()
    {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
        mainPanel = new JPanel();
        mainFrame.add(mainPanel);
  
        icon = new ImageIcon("blackKing.PNG");          //TODO: Figure out both color kings in 1 class
        imageLabel = new JLabel(icon);
        mainPanel.add(imageLabel);
  
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    public void getColor()
    {

    }

    public void getPieceType()
    {

    }
}
