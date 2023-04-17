package Chess.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Chess.view.UnicodeMap;
import Chess.model.ChessModel;
import Chess.model.ChessBoard;

public class MainMenu extends JFrame implements ActionListener
{
    public static final int UPDATE_INTERVAL = 500;
    protected Timer timer;
    private ChessModel model;
    JFrame mainFrame;
    JPanel mainPanel;
    JPanel buttonPanel;
    JPanel difficultyPanel;
    JPanel loadPanel;
    JPanel animationPanel;
    JButton onePlayer;
    JButton twoPlayer;
    JButton loadGame;
    JButton difficultyUp;
    JButton difficultyDown;
    JLabel title;
    JLabel record;
    JLabel difficultyLabel;
    JLabel instructions;
    JLabel animationLabel;
    int difficulty = 1;
    int pieceCount = 0;

    public MainMenu(ChessModel model)
    {
        this.model = model;

        mainFrame = new JFrame("Main Menu");
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.setPreferredSize(new Dimension(425, 375));
        mainPanel.setBackground(new Color(119, 148, 86));

        JLabel wKing = new JLabel(UnicodeMap.wKing);
        wKing.setFont(new Font("Dialog", Font.BOLD, 75));
        wKing.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(wKing);

        title = new JLabel("CHESS");
        title.setFont(new Font("Times New Roman", Font.PLAIN, 75));
        mainPanel.add(title);

        JLabel bKing = new JLabel(UnicodeMap.bKing);
        bKing.setFont(new Font("Dialog", Font.BOLD, 80));
        bKing.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(bKing);

        difficultyPanel = new JPanel();
        difficultyPanel.setPreferredSize(new Dimension(625, 50));
        difficultyPanel.setBackground(new Color(119, 148, 86));

        instructions = new JLabel("Set a Computer Difficulty:");
        instructions.setFont(new Font("Times New Roman", Font.PLAIN, 24));

        difficultyLabel = new JLabel(Integer.toString(difficulty));
        difficultyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));

        difficultyUp = new JButton(UnicodeMap.upArrow);
        initializeDifficultyButton(difficultyUp);

        difficultyDown = new JButton(UnicodeMap.downArrow);
        initializeDifficultyButton(difficultyDown);

        difficultyPanel.add(instructions);
        difficultyPanel.add(difficultyUp);
        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(difficultyDown);

        loadPanel = new JPanel();
        loadPanel.setLayout(new GridLayout(1, 1));
        loadPanel.setPreferredSize(new Dimension(150, 50));
        loadPanel.setBackground(new Color(119, 148, 86));

        loadGame = new JButton("Load Saved Game");
        loadGame.addActionListener(this);
        loadPanel.add(loadGame);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setPreferredSize(new Dimension(300, 100));

        onePlayer = new JButton("Player vs Computer");
        initializeButton(onePlayer);
        buttonPanel.add(onePlayer);

        twoPlayer = new JButton("Player vs Player");
        initializeButton(twoPlayer);
        buttonPanel.add(twoPlayer);

        animationPanel = new JPanel();

        animationPanel.setPreferredSize(new Dimension(425, 100));
        animationPanel.setBackground(new Color(119, 148, 86));

        timer = new Timer(UPDATE_INTERVAL, this);
        timer.start();

        mainPanel.add(difficultyPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(loadPanel);
        mainPanel.add(animationPanel);

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == timer)
        {
            pieceCount++;
            String piece = UnicodeMap.getRandomPiece();
            animationLabel = new JLabel(piece);
            //Point point = animationLabel.getLocation();
            //int x = point.x;
            //int y = point.y;
            //animationLabel.setLocation(x + 50, y);
            animationLabel.setFont(new Font("Dialog", Font.BOLD, 30));
            animationPanel.add(animationLabel);

            if (pieceCount <= 11)
            {
                animationPanel.revalidate();
                animationPanel.repaint();
            }
            else
            {
                timer.stop();
            }
        }

        else if (event.getSource() == this.difficultyUp)
        {
            difficulty++;
            if (difficulty == 11)
            {
                difficulty = 1;
            }
            difficultyLabel.setText(Integer.toString(difficulty));
        }
        else if (event.getSource() == this.difficultyDown)
        {
            difficulty--;
            if (difficulty == 0)
            {
                difficulty = 10;
            }
            difficultyLabel.setText(Integer.toString(difficulty));
        }
        else if(event.getSource() == this.twoPlayer)
        {
            ChessBoardGUI game = new ChessBoardGUI(model);
            game.setVisible(true);
            //mainFrame.setVisible(false);
        }
    }

    public void initializeDifficultyButton(JButton button)
    {
        button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        button.setPreferredSize(new Dimension(50,50));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
    }

    public void initializeButton(JButton button)
    {
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
