import java.awt.*;
import javax.swing.*;

public class PacMan extends JPanel {
    private int x = 10;

    public PacMan() {
        JFrame frame = new JFrame("Pac-Man");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);

        Timer timer = new Timer(50, e -> move());
        timer.start();
    }

    private void move() {
        x += 5;
        if (x > getWidth()) x = 0;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillArc(x, 100, 50, 50, 45, 270);
    }
}
