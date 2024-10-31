import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
public class WhackAMole {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Vishnu : new Game");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[] board = new JButton[9];
    ImageIcon GODZILLAIcon;
    ImageIcon KONGIcon;
    JButton currGODZILLATile;
    JButton currKONGTile;
    Random vishnu = new Random();
    Timer setGODZILLATimer;
    Timer setKONGTimer;
    int score = 0;

    WhackAMole() {
      //  frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout ());
        textLabel.setFont(new Font("Lucida ",Font.ROMAN_BASELINE,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Score: 0");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.SOUTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.blue);
        frame.add(boardPanel);

       Image GODZILLAImg = new ImageIcon(getClass().getResource("./GODZILLA.jpg")).getImage();
       GODZILLAIcon = new ImageIcon(GODZILLAImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
        
       Image KONGImg = new ImageIcon(getClass().getResource("./KONG.jpg")).getImage();
       KONGIcon = new ImageIcon(KONGImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
       score = 0;
        for(int i = 0; i < 9; i++){
            JButton tile = new JButton();
            board[i] = tile;
            boardPanel.add(tile);
            //tile.setIcon(KONGIcon);
            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton tile = (JButton) e.getSource();
                    if (tile == currGODZILLATile){
                        score += 10;
                        textLabel.setText("Score: " + Integer.toString(score));

                    } else if (tile == currKONGTile) {
                        textLabel.setText("Game Over: " + Integer.toString(score));
                        setGODZILLATimer.stop();
                        setKONGTimer.stop();
                        for (int i = 0; i < 9; i++){
                            board[i].setEnabled(false);
                        }
                    }
                }
            });
        }
        setGODZILLATimer = new Timer(1000,new ActionListener() {
            public  void actionPerformed(ActionEvent e) {
                // remove mole from current tile
                if (currGODZILLATile != null) {
                    currGODZILLATile.setIcon(null);
                    currGODZILLATile = null;
                }
                // random select another tile
                int num = vishnu.nextInt(9);
                JButton tile = board[num];
                //set tile to mole
                currGODZILLATile = tile;
                currGODZILLATile.setIcon(GODZILLAIcon);
            }
                
            
        });

        setKONGTimer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // remove mole from currrent tile
                if (currKONGTile != null) {
                    currKONGTile.setIcon(null);
                    currKONGTile = null;
                }
                //randomly select another tile
                int num = vishnu.nextInt(9);
                JButton tile = board[num];

                //if tile is occupied by plant, skip tile for this turn
                if(currKONGTile == tile) return;
                // set tile to mole.
                currKONGTile = tile;
                currKONGTile.setIcon(KONGIcon);
            }
        });
        setGODZILLATimer.start();
        setKONGTimer.start();
        frame.setVisible(true);




    }

    
}
