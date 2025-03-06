import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class PacMan {
    private JFrame frame;
    private JButton[] buttons;
    private String[] images = {
        "images/watermelon.png", "images/apple.png", "images/pineapple.png",
        "images/graphs.png", "images/banana.png", "images/cherry.png",
        "images/watermelon.png", "images/apple.png", "images/pineapple.png",
        "images/graphs.png", "images/banana.png", "images/cherry.png"
    };
    
    private int firstChoice = -1;
    private int secondChoice = -1;
    private boolean[] matched;
    private ArrayList<String> shuffledImages;
    private int matchedPairs = 0; // Counter for matched pairs

    public PacMan() {
        frame = new JFrame("Memory Matching Game");
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(3, 4));

        buttons = new JButton[12];
        matched = new boolean[12];

        // Shuffle images
        shuffledImages = new ArrayList<>();
        Collections.addAll(shuffledImages, images);
        Collections.shuffle(shuffledImages);

        // Initialize buttons
        for (int i = 0; i < 12; i++) {
            buttons[i] = new JButton();
            buttons[i].setPreferredSize(new Dimension(100, 100));
            buttons[i].setIcon(new ImageIcon("images/cover.png")); // Default card cover
            int index = i;
            buttons[i].addActionListener(e -> handleButtonClick(index));
            frame.add(buttons[i]);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void handleButtonClick(int index) {
        if (matched[index] || index == firstChoice) return;

        buttons[index].setIcon(new ImageIcon(shuffledImages.get(index))); // Show image

        if (firstChoice == -1) {
            firstChoice = index;
        } else {
            secondChoice = index;
            Timer timer = new Timer(500, e -> checkMatch());
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void checkMatch() {
        if (shuffledImages.get(firstChoice).equals(shuffledImages.get(secondChoice))) {
            matched[firstChoice] = true;
            matched[secondChoice] = true;
            matchedPairs++; // Increase matched count

            if (matchedPairs == 6) { // 6 pairs = Game won
                JOptionPane.showMessageDialog(frame, "🎉 Congratulations! You Win! 🎉");
                System.exit(0); // Close game after winning
            }
        } else {
            buttons[firstChoice].setIcon(new ImageIcon("images/cover.png")); // Hide image
            buttons[secondChoice].setIcon(new ImageIcon("images/cover.png"));
        }
        firstChoice = -1;
        secondChoice = -1;
    }

    public static void main(String[] args) {
        new PacMan();
    }
}
