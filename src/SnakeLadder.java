import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

public class SnakeLadder extends JFrame {
    private JButton[] cells = new JButton[30];
    private JButton rollDiceButton;
    private JLabel statusLabel;
    private int[] moves = new int[30];
    private int playerPosition = 0;
    private JLabel background;
    
    public SnakeLadder() {
        setTitle("Snake and Ladder Game");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Background image
        ImageIcon bgImage = new ImageIcon("snakes_ladders_bg.jpg");
        background = new JLabel(bgImage);
        background.setLayout(new BorderLayout());
        add(background);
        
        // Initialize board panel
        JPanel boardPanel = new JPanel(new GridLayout(5, 6));
        boardPanel.setOpaque(false);
        for (int i = 0; i < 30; i++) {
            cells[i] = new JButton(String.valueOf(i));
            cells[i].setEnabled(false);
            boardPanel.add(cells[i]);
        }
        background.add(boardPanel, BorderLayout.CENTER);

        // Control panel with dice and status
        JPanel controlPanel = new JPanel();
        rollDiceButton = new JButton("Roll Dice");
        statusLabel = new JLabel("Roll the dice to start");
        rollDiceButton.addActionListener(new DiceRollListener());
        controlPanel.add(rollDiceButton);
        controlPanel.add(statusLabel);
        background.add(controlPanel, BorderLayout.SOUTH);

        // Set up snakes and ladders
        Arrays.fill(moves, -1);
        moves[2] = 21; // Ladder
        moves[4] = 7;  // Ladder
        moves[10] = 25; // Ladder
        moves[19] = 28; // Ladder
        moves[26] = 0; // Snake
        moves[20] = 8; // Snake
        moves[16] = 3; // Snake
        moves[18] = 6; // Snake
        
        highlightPlayer();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void highlightPlayer() {
        for (int i = 0; i < cells.length; i++) {
            cells[i].setBackground(null);
            if (moves[i] != -1 && i < moves[i]) {
                cells[i].setBackground(Color.GREEN); // Ladders
            } else if (moves[i] != -1 && i > moves[i]) {
                cells[i].setBackground(Color.RED); // Snakes
            }
        }
        cells[playerPosition].setBackground(Color.BLUE);
    }
    
    private class DiceRollListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int diceRoll = (int) (Math.random() * 6) + 1;
            statusLabel.setText("Dice: " + diceRoll);
            int newPos = playerPosition + diceRoll;

            if (newPos >= 30) {
                statusLabel.setText("You Win!");
                rollDiceButton.setEnabled(false);
                return;
            }
            
            if (moves[newPos] != -1) {
                if (newPos > moves[newPos]) {
                    statusLabel.setText("Oh no! A snake bit you!");
                } else {
                    statusLabel.setText("Great! You climbed a ladder!");
                }
                newPos = moves[newPos];
            }
            
            playerPosition = newPos;
            highlightPlayer();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SnakeLadder::new);
    }
}
