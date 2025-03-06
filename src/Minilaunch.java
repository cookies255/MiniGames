import java.awt.*;
import javax.swing.*;

public class Minilaunch extends JFrame {
    public Minilaunch() {
        setTitle("Mini Game Hub");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        JButton ticTacToeButton = new JButton("Tic Tac Toe");
        JButton chessButton = new JButton("Chess");
        JButton snakeLadderButton = new JButton("Snake & Ladder");
        JButton pacManButton = new JButton("Pac-Man");
        JButton spaceShooterButton = new JButton("Space Shooter");
        JButton bubbleShooterButton = new JButton("Bubble Shooter");

        ticTacToeButton.addActionListener(e -> new TicTacToe());
        chessButton.addActionListener(e -> new Chess());
        snakeLadderButton.addActionListener(e -> new SnakeLadder());
        pacManButton.addActionListener(e -> new PacMan());
        pacManButton.addActionListener(e -> {
            System.out.println("Pac-Man button clicked!"); // Debugging ke liye
            new PacMan();
        });
        spaceShooterButton.addActionListener(e -> new SpaceShooter());
        bubbleShooterButton.addActionListener(e -> new BubbleShooter());

        add(ticTacToeButton);
        add(chessButton);
        add(snakeLadderButton);
        add(pacManButton);
        add(spaceShooterButton);
        add(bubbleShooterButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Minilaunch::new);
    }
}
