import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class TicTacToe extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private JPanel overlay;
    private int x1, y1, x2, y2;
    private boolean animate = false;
    private Timer timer;
    private int animationStep = 0;
    private boolean messageDisplayed = false;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initializeBoard();
        setLocationRelativeTo(null);
        
        overlay = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (animate) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(5));
                    g2d.setColor(currentPlayer == 'X' ? Color.PINK : Color.RED);
                    g2d.drawLine(x1, y1, x1 + (x2 - x1) * animationStep / 10, y1 + (y2 - y1) * animationStep / 10);
                }
            }
        };
        overlay.setOpaque(false);
        setGlassPane(overlay);
        overlay.setVisible(true);

        setVisible(true);
    }
    
    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton(" ");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                add(buttons[row][col]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals(" ")) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                buttons[row][col].setForeground(currentPlayer == 'X' ? Color.BLUE: Color.RED);
                
                if (checkWinner()) {
                    playSound("win.wav");
                    startAnimation();
                } else if (checkDraw()) {
                    playSound("draw.wav");
                    JOptionPane.showMessageDialog(null, "It's a Draw!");
                    resetBoard();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (isWinningLine(i, 0, i, 1, i, 2)) return true;
            if (isWinningLine(0, i, 1, i, 2, i)) return true;
        }
        if (isWinningLine(0, 0, 1, 1, 2, 2)) return true;
        if (isWinningLine(0, 2, 1, 1, 2, 0)) return true;
        return false;
    }

    private boolean isWinningLine(int r1, int c1, int r2, int c2, int r3, int c3) {
        if (buttons[r1][c1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[r2][c2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[r3][c3].getText().equals(String.valueOf(currentPlayer))) {
            x1 = buttons[r1][c1].getLocation().x + buttons[r1][c1].getWidth() / 2;
            y1 = buttons[r1][c1].getLocation().y + buttons[r1][c1].getHeight() / 2;
            x2 = buttons[r3][c3].getLocation().x + buttons[r3][c3].getWidth() / 2;
            y2 = buttons[r3][c3].getLocation().y + buttons[r3][c3].getHeight() / 2;
            return true;
        }
        return false;
    }

    private boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void startAnimation() {
        animate = true;
        animationStep = 0;
        overlay.repaint();
        timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                animationStep++;
                overlay.repaint();
                if (animationStep >= 10) {
                    timer.stop();
                    if (!messageDisplayed) {
                        messageDisplayed = true;
                        JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                        resetBoard();
                    }
                }
            }
        });
        timer.start();
    }

    private void resetBoard() {
        animate = false;
        messageDisplayed = false;
        overlay.repaint();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(" ");
            }
        }
        currentPlayer = 'X';
    }

    private void playSound(String soundFile) {
        try {
            File file = new File(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | IllegalArgumentException | IllegalStateException ex) {
            System.out.println("Error playing sound: " + ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
