import java.awt.*;
import javax.swing.*;

public class BubbleShooter extends JPanel {
    private int bubbleY = 300;

    public BubbleShooter() {
        JFrame frame = new JFrame("Bubble Shooter");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);

        Timer timer = new Timer(50, e -> move());
        timer.start();
    }

    private void move() {
        bubbleY -= 5;
        if (bubbleY < 0) bubbleY = 300;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(175, bubbleY, 50, 50);
    }
}
